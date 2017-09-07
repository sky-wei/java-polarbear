/*
 * Copyright (c) 2017. The sky Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.account.manager.data.disk.impl

import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.sky.account.manager.Constant
import com.sky.account.manager.data.disk.DBManager
import com.sky.account.manager.data.disk.entity.AccountEntity
import com.sky.account.manager.data.disk.entity.AdminEntity
import com.sky.account.manager.util.FileUtil


/**
 * Created by sky on 17-9-4.
 */
class DBManagerImpl : DBManager {

    private val DIR = "database/"
    private val URL = "jdbc:sqlite:$DIR${Constant.Database.NAME}"

    private val connectionSource: ConnectionSource
    private val adminDao: Dao<AdminEntity, Int>
    private val accountDao: Dao<AccountEntity, Int>

    init {

        // 创建目录
        FileUtil.mkdirs("$DIR")

        // 创建连接
        connectionSource = JdbcConnectionSource(URL)

        adminDao = DaoManager.createDao(connectionSource, AdminEntity::class.java)
        accountDao = DaoManager.createDao(connectionSource, AccountEntity::class.java)

        // if you need to create the table
        TableUtils.createTableIfNotExists(connectionSource, AdminEntity::class.java)
        TableUtils.createTableIfNotExists(connectionSource, AccountEntity::class.java)
    }

    override fun getAdminDao(): Dao<AdminEntity, Int> {
        return adminDao
    }

    override fun getAccountDao(): Dao<AccountEntity, Int> {
        return accountDao
    }

    override fun release() {
        connectionSource.closeQuietly()
    }
}