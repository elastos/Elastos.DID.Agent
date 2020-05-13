/**
 * Copyright (c) 2017-2019 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * clark
 * <p>
 * 4/14/20
 */
@Entity
@Table(name = "payload_record")
public class ReqCrud {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition="MEDIUMTEXT")
    private String payload;

    private Date localSystemTime = new Date();

    private Date updateTime = new Date();

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private Integer status;

    private String txid;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLocalSystemTime() {
        return localSystemTime;
    }

    public void setLocalSystemTime(Date localSystemTime) {
        this.localSystemTime = localSystemTime;
    }

    public Integer getId() {
        return id;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
