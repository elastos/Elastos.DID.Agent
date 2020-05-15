/**
 * Copyright (c) 2017-2019 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.entity;

import org.elastos.common.Util;

import java.io.DataOutputStream;

/**
 * clark
 * <p>
 * 4/22/20
 */
public class Operation {

    DIDHeaderInfo Header;
    String Payload;
    DIDProofInfo Proof;

    public void Serialize(DataOutputStream o) throws Exception{
        this.Header.Serialize(o);
        Util.WriteVarBytes(o,this.Payload.getBytes());
        this.Proof.Serialize(o);
    }

    public DIDHeaderInfo getHeader() {
        return Header;
    }

    public void setHeader(DIDHeaderInfo header) {
        Header = header;
    }

    public String getPayload() {
        return Payload;
    }

    public void setPayload(String payload) {
        Payload = payload;
    }

    public DIDProofInfo getProof() {
        return Proof;
    }

    public void setProof(DIDProofInfo proof) {
        Proof = proof;
    }

    public static class DIDHeaderInfo {
        private String Specification;
        private String Operation;
        private String PreviousTxid;

        public void Serialize(DataOutputStream o) throws Exception{
            Util.WriteVarBytes(o,this.Specification.getBytes());
            Util.WriteVarBytes(o,this.Operation.getBytes());
            if(this.Operation.equals("update")){
                Util.WriteVarBytes(o,this.PreviousTxid.getBytes());
            }
        }

        public String getSpecification() {
            return Specification;
        }

        public void setSpecification(String specification) {
            Specification = specification;
        }

        public String getOperation() {
            return Operation;
        }

        public void setOperation(String operation) {
            Operation = operation;
        }

        public String getPreviousTxid() {
            return PreviousTxid;
        }

        public void setPreviousTxid(String previousTxid) {
            PreviousTxid = previousTxid;
        }
    }

    public static class DIDProofInfo {
        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getVerificationMethod() {
            return VerificationMethod;
        }

        public void setVerificationMethod(String verificationMethod) {
            VerificationMethod = verificationMethod;
        }

        public String getSignature() {
            return Signature;
        }

        public void setSignature(String signature) {
            Signature = signature;
        }

        public void Serialize(DataOutputStream o) throws Exception{
            if (this.Type == null){
                this.Type = "";
            }
            Util.WriteVarBytes(o,this.Type.getBytes());
            Util.WriteVarBytes(o,this.VerificationMethod.getBytes());
            Util.WriteVarBytes(o,this.Signature.getBytes());
        }

        String Type;
        String VerificationMethod;
        String Signature;

    }
}



