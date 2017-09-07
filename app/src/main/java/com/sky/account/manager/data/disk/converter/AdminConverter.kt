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

package com.sky.account.manager.data.disk.converter

import com.sky.account.manager.data.disk.entity.AdminEntity
import com.sky.account.manager.model.AdminModel

/**
 * Created by sky on 17-9-4.
 */
object AdminConverter {

    fun converterEntity(entity: List<AdminEntity>): List<AdminModel> {
        return entity.map { converterEntity(it) }
    }

    fun converterEntity(entity: AdminEntity): AdminModel {
        return AdminModel(entity.id, entity.name, entity.password, entity.desc, entity.createTime)
    }

    fun converterModel(model: List<AdminModel>): List<AdminEntity> {
        return model.map { converterModel(it) }
    }

    fun converterModel(model: AdminModel): AdminEntity {
        return AdminEntity(model.id, model.name, model.password, model.desc, model.createTime)
    }
}
