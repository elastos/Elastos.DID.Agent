/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * clark
 *
 * 9/3/18
 *
 */
@Component
@ConfigurationProperties("basic")
public class BasicConfiguration {
    private boolean CROSS_DOMAIN;
    private String ENDPOINT;
    private String APIKEY;

    public String getAPIKEY() {
        return APIKEY;
    }

    public void setAPIKEY(String APIKEY) {
        this.APIKEY = APIKEY;
    }

    public String getENDPOINT() {
        return ENDPOINT;
    }

    public void setENDPOINT(String ENDPOINT) {
        this.ENDPOINT = ENDPOINT;
    }

    public boolean isCROSS_DOMAIN() {
        return CROSS_DOMAIN;
    }

    public void setCROSS_DOMAIN(boolean CROSS_DOMAIN) {
        this.CROSS_DOMAIN = CROSS_DOMAIN;
    }

}
