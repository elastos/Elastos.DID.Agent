/**
 * Copyright (c) 2017-2019 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * clark
 * <p>
 * 4/14/20
 */
public interface IReqCurd extends CrudRepository<ReqCrud,Integer> {

    List<ReqCrud> findByStatus(Integer status);

}
