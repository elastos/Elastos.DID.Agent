/**
 * Copyright (c) 2017-2019 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.elastos.api.Basic;
import org.elastos.api.DidTransaction;
import org.elastos.common.Config;
import org.elastos.ela.Asset;
import org.elastos.ela.TxOutput;
import org.elastos.ela.UsableUtxo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import static org.elastos.api.Basic.getRawTxAndAssetIdMap;
import static org.elastos.common.InterfaceParams.*;
import static org.elastos.common.InterfaceParams.CHANGE_ADDRESS;

/**
 * clark
 * <p>
 * 4/22/20
 */
public class ElaKit {

    private static Logger logger = LoggerFactory.getLogger(ElaKit.class);

    public static String genRawTx(String params) throws Exception{
        try {
            Config.getConfig();
        } catch (Exception ex) {
            throw new Exception("Can not init config");
        }
        JSONObject jsonObject = JSONObject.fromObject(params);
        JSONObject param = jsonObject.getJSONObject("params");
        return genDidTxByPrivateKey(param);
    }

    private static String genDidTxByPrivateKey(JSONObject inputsAddOutpus){
        try {
            final JSONObject json_transaction = inputsAddOutpus.getJSONObject(TRANSACTION);
            final JSONArray PrivateKeys = json_transaction.getJSONArray(PRIVATEKEYS);

            List<String> privateList = Basic.parsePrivates(PrivateKeys);
            // add outputs
            final JSONArray outputs = json_transaction.getJSONArray(OUTPUTS);
            LinkedList<TxOutput> outputList = Basic.parseOutputs(outputs);

            //Payload
            String payload = json_transaction.getString(PAYLOAD);

            String changeAddress = json_transaction.getString(CHANGE_ADDRESS);

            //创建rawTransaction
            String rawTx = UsableUtxo.makeAndSignTxByDid(privateList, outputList, changeAddress ,payload);
            LinkedHashMap<String, Object> resultMap = getRawTxAndAssetIdMap(rawTx, UsableUtxo.txHash, Asset.AssetId);

            logger.info(Basic.getSuccess(resultMap));
            return Basic.getSuccess(resultMap);
        } catch (Exception e) {
            logger.error(e.toString());
            return e.toString();
        }
    }

}
