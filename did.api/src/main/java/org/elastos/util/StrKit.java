/**
 * Copyright (c) 2017-2019 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.util;

/**
 * clark
 * <p>
 * 4/13/20
 */
public class StrKit {

    public static boolean isBlank(String str) {
        if (str == null || str.trim() == "") {
            return true;
        }
        return false;
    }

}
