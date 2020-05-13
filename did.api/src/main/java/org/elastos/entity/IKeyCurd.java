/**
 * Copyright (c) 2017-2018 The Elastos Developers
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
 * 4/15/20
 */
public interface IKeyCurd extends CrudRepository<KeyCurd,Integer> {

    List<KeyCurd> findByStatus(Integer status);

}
