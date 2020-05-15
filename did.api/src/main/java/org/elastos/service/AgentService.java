/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.service;

import com.alibaba.fastjson.JSON;
import org.elastos.conf.BasicConfiguration;
import org.elastos.conf.RetCodeConfiguration;
import org.elastos.ela.Ela;
import org.elastos.elaweb.ElaController;
import org.elastos.entity.*;
import org.elastos.task.Background;
import org.elastos.util.HttpKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author clark
 * <p>
 * Apr 21, 2018 12:45:54 PM
 */
@Service
public class AgentService {

    @Autowired
    private BasicConfiguration basicConfiguration;
    @Autowired
    private RetCodeConfiguration retCodeConfiguration;

    @Autowired
    private IReqCurd reqCurd;

    @Autowired
    private IKeyCurd ikeyCrud;

    @Autowired
    private Background back;

    private static Logger logger = LoggerFactory.getLogger(AgentService.class);

    public String ping() {
        return "pong";
    }

    public String takePayload(PayloadEntity reqEntity) throws Exception{
        ReqCrud req = new ReqCrud();
        req.setLocalSystemTime(new Date());
        req.setPayload(reqEntity.getPayload());
        req.setStatus(0);
        reqCurd.save(req);
        Map<String,Object> data = back.handle(req,reqEntity.getAddress());
        Object rst = data.get("result");
        if (rst != null){
            return JSON.toJSONString(new ReturnMsgEntity<String>(retCodeConfiguration.SUCC(),(String)rst));
        }
        Object error = data.get("error");
        if (error != null) {
            String message = (String)((Map<String,Object>)error).get("message");
            return JSON.toJSONString(new ReturnMsgEntity<String>(retCodeConfiguration.PROCESS_ERROR(),message));
        }
        return JSON.toJSONString(new ReturnMsgEntity<String>(retCodeConfiguration.PROCESS_ERROR(),"Wallet not ready , try again later"));
    }

    public String initKey(String num){
        for(int i=0;i<Integer.valueOf(num);i++){
            String privateKey = Ela.getPrivateKey();
            String publicKey = Ela.getPublicFromPrivate(privateKey);
            String address = Ela.getAddressFromPrivate(privateKey);
            String did = Ela.getIdentityIDFromPrivate(privateKey);
            System.out.println(privateKey +" " + publicKey +" " + address);
            KeyCurd curd = new KeyCurd();
            curd.setAddress(address);
            curd.setPrivateKey(privateKey);
            curd.setPublicKey(publicKey);
            curd.setStatus(0);
            curd.setDid(did);
            ikeyCrud.save(curd);
        }
        return "{\"status\":200}";
    }

}
