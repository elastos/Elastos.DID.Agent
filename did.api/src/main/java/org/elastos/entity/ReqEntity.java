/**
 * Copyright (c) 2017-2019 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.entity;

/**
 * clark
 * <p>
 * 4/13/20
 */
public class ReqEntity<T> {
    private String method;
    private T params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}

class Transaction {
    private PrivateKeys[] privatekeys;
    private Outputs[] outputs;
    private String payload;
    private String changeaddress;

    public PrivateKeys[] getPrivatekeys() {
        return privatekeys;
    }

    public void setPrivatekeys(PrivateKeys[] privatekeys) {
        this.privatekeys = privatekeys;
    }

    public Outputs[] getOutputs() {
        return outputs;
    }

    public void setOutputs(Outputs[] outputs) {
        this.outputs = outputs;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getChangeaddress() {
        return changeaddress;
    }

    public void setChangeaddress(String changeaddress) {
        this.changeaddress = changeaddress;
    }
}

class PrivateKeys {
    private String  privatekey;

    public String getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
    }
}

class Outputs {
    private String address;
    private String amount;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}


