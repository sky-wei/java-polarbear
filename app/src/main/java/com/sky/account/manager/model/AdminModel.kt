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

package com.sky.account.manager.model

import java.io.Serializable

/**
 * Created by sky on 17-9-3.
 */
data class AdminModel(var id: Int, var name: String, var password: String, var desc: String, var createTime: Long) : Serializable {

    constructor() : this(0, "", "", "", 0L)

    constructor(name: String, password: String, desc: String, createTime: Long) : this(0, name, password, desc, createTime)

    constructor(name: String, password: String) : this(0, name, password, "", 0L)
}