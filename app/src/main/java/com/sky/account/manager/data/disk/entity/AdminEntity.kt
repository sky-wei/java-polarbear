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

package com.sky.account.manager.data.disk.entity

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by sky on 17-9-4.
 */
@DatabaseTable(tableName = "admin")
data class AdminEntity(
        @DatabaseField(generatedId = true) var id: Int,
        @DatabaseField(canBeNull = true, unique = true) var name: String,
        @DatabaseField(canBeNull = true) var password: String,
        @DatabaseField var desc: String,
        @DatabaseField var createTime: Long) {

    constructor() : this(0, "", "", "", 0L)
}