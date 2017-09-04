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
