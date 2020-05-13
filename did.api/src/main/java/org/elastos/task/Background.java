/**
 * Copyright (c) 2017-2019 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.task;


import com.alibaba.fastjson.JSON;
import org.elastos.conf.BasicConfiguration;
import org.elastos.elaweb.ElaController;
import org.elastos.entity.IKeyCurd;
import org.elastos.entity.IReqCurd;
import org.elastos.entity.KeyCurd;
import org.elastos.entity.ReqCrud;
import org.elastos.util.ElaKit;
import org.elastos.util.HttpKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * clark
 * <p>
 * 4/15/20
 */
@Component
public class Background {

    private Logger logger = LoggerFactory.getLogger(Background.class);

    @Autowired
    private IReqCurd reqCurd;
    @Autowired
    private IKeyCurd keyCurd;
    @Autowired
    private BasicConfiguration basicConfiguration;

    String h1 = "{\n" +
            "    \"method\":\"gendidtxbyprivatekey\",\n" +
            "    \"params\":{\n" +
            "        \"transaction\":{\n" +
            "            \"privatekeys\":[\n" +
            "                {\n" +
            "                    \"privatekey\":\"";
    String h2 = "\"}\n" +
            "            ],\n" +
            "            \"outputs\":[\n" +
            "                {\n" +
            "                    \"address\":\"";
    String h3 = "\",\n" +
            "                    \"amount\":\"0\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"payload\": \"";
    String h4 = "\",\n" +
            "            \"changeaddress\":\"";
    String h5 = "\"}\n" +
            "    }\n" +
            "}";

    int startPoint = 0;

//    @Scheduled(cron = "*/1 * * * * ?")
//    @Scheduled(cron = "*/30 * * * * ?")
//    public  void handle(){
//        logger.info("Starting task");
//        List<ReqCrud> data = reqCurd.findByStatus(0);
//        List<KeyCurd> keys = keyCurd.findByStatus(0);
//
//        for (int j=0;j<data.size();j++){
//            ReqCrud req = data.get(j);
//            String payload = req.getPayload();
//            if (startPoint == keys.size()){
//                startPoint = 0;
//            }
//            for(int i= startPoint; i<keys.size(); i++) {
//                KeyCurd key = keys.get(i);
//                String privateKey = key.getPrivateKey();
//                String address = key.getAddress();
//                String did = key.getDid();
//                String message = h1 + privateKey + h2 + did + h3 + payload + h4 + address + h5;
//                try {
//                    String response = ElaController.processMethod(message);
//                    Map<String, Object> dataMap = (Map<String, Object>) JSON.parse(response);
//                    Map<String, Object> result = (Map<String, Object>) dataMap.get("result");
//                    String rawTx = (String) result.get("rawtx");
//                    String endpoint = basicConfiguration.getENDPOINT();
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("method", "sendrawtransaction");
//                    Map<String, Object> tx = new HashMap<>();
//                    tx.put("data", rawTx);
//                    map.put("params", tx);
//                    Map<String, String> header = new HashMap<>();
//                    header.put("Content-Type", "application/json");
//                    String resultData = HttpKit.post(endpoint, JSON.toJSONString(map), header);
//                    Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(resultData);
//                    Object rst = resultMap.get("result");
//                    if (rst != null){
//                        req.setStatus(1);
//                        req.setUpdateTime(new Date());
//                        req.setTxid((String)rst);
//                        reqCurd.save(req);
//                        startPoint = i+1;
//                        logger.info(resultData);
//                        break;
//                    }else {
//                        logger.warn(resultData);
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }

    public Map<String, Object> handle(ReqCrud req,String sendAddress) throws Exception{
        List<KeyCurd> keys = keyCurd.findByStatus(0);
        Map<String, Object> resultMap = new HashMap<>();
        String payload = req.getPayload();
        if (startPoint == keys.size()){
            startPoint = 0;
        }
        KeyCurd key = keys.get(startPoint);
        String privateKey = key.getPrivateKey();
        String address = key.getAddress();
//        String did = key.getDid();
        if (sendAddress == null){
            sendAddress = address;
        }
        String message = h1 + privateKey + h2 + sendAddress + h3 + payload + h4 + address + h5;
        String response = ElaKit.genRawTx(message);
//            String response = ElaController.processMethod(message);
        Map<String, Object> dataMap = (Map<String, Object>) JSON.parse(response);
        Map<String, Object> result = (Map<String, Object>) dataMap.get("result");
        String rawTx = (String) result.get("rawtx");
        String endpoint = basicConfiguration.getENDPOINT();
        Map<String, Object> map = new HashMap<>();
        map.put("method", "sendrawtransaction");
        Map<String, Object> tx = new HashMap<>();
        tx.put("data", rawTx);
        map.put("params", tx);
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        String resultData = HttpKit.post(endpoint, JSON.toJSONString(map), header);
        logger.info(resultData);
        resultMap = (Map<String, Object>) JSON.parse(resultData);
        Object rst = resultMap.get("result");
        if (rst != null){
            req.setStatus(1);
            req.setUpdateTime(new Date());
            req.setTxid((String)rst);
            reqCurd.save(req);
            startPoint = startPoint+1;
        }
        return resultMap;
    }
}
