/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elastos.entity.Operation;
import org.elastos.entity.PayloadEntity;
import org.elastos.entity.ReturnMsgEntity;
import org.elastos.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * clark
 * <p>
 * 9/20/18
 */
@RestController
public class AgentController extends BaseController {

    @Autowired
    private AgentService service;

    @RequestMapping(value = "/ping",method = RequestMethod.GET)
    @ResponseBody
    public String ping(){
        return call(null,null,"ping",service);
    }

    @RequestMapping(value = "/did/agent/payload",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String takeDidInfo(@RequestAttribute String reqBody){
        JSONObject obj = JSON.parseObject(reqBody);
        String data = obj.getString("data");
        String address = obj.getString("address");
        Map<String,Object> payload = new HashMap<>();
        payload.put("payload",data);
        payload.put("address",address);
        return call(JSON.toJSONString(payload), PayloadEntity.class,"takePayload",service);
    }

    @RequestMapping(value = "/did/agent/payload/pretty",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String takeDidInfo_pretty(@RequestAttribute String reqBody){
        Map<String,Object> payload = new HashMap<>();
        try{
            JSONObject obj = JSON.parseObject(reqBody);
            Operation data = obj.getObject("data", Operation.class);
            String address = obj.getString("address");
            if (data == null){
                JSON.toJSONString(new ReturnMsgEntity<>().setResult("data can not be blank").setStatus(400l));
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            data.Serialize(dos);
            payload.put("payload", DatatypeConverter.printHexBinary(baos.toByteArray()));
            payload.put("address",address);
        }catch(Exception ex){
            JSON.toJSONString(new ReturnMsgEntity<>().setResult("Invalid request param").setStatus(400l));
        }
        return call(JSON.toJSONString(payload), PayloadEntity.class,"takePayload",service);
    }

    @RequestMapping(value = "/init/key/{num}",method = RequestMethod.GET)
    @ResponseBody
    public String initKey(@PathVariable("num") Integer num){
        return call(num+"", String.class,"initKey",service);
    }

}
