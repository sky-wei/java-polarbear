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

package com.sky.account.manager.data.disk

import com.sky.account.manager.data.DataException
import com.sky.account.manager.model.AccountModel
import com.sky.account.manager.model.AdminModel
import java.io.File

/**
 * Created by sky on 17-9-3.
 *
 * 这个类需要进行修改，部分功能不应该放在这里
 */
interface AccountManager {

    fun createAdmin(model: AdminModel): Boolean

    fun loginAdmin(model: AdminModel): Boolean

    fun getAdmin(): AdminModel

    fun updateAdmin(model: AdminModel): Boolean

    fun search(type: Int, key: String): List<AccountModel>

    fun createAccount(model: AccountModel): Boolean

    fun deleteAccount(model: AccountModel): Boolean

    fun updateAccount(model: AccountModel): Boolean

    @Throws(DataException::class)
    fun encryptAccount(model: AccountModel): AccountModel

    @Throws(DataException::class)
    fun decryptionAccount(model: AccountModel): AccountModel

    fun importAccount(models: List<AccountModel>): Boolean

    fun exportAccount(models: List<AccountModel>, path: File): Boolean
}