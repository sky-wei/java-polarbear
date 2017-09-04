package com.sky.account.manager.data.disk.converter

import com.sky.account.manager.data.disk.entity.AccountEntity
import com.sky.account.manager.model.AccountModel

/**
 * Created by sky on 17-9-4.
 */
object AccountConverter {

    fun converterEntity(entity: List<AccountEntity>): List<AccountModel> {
        return entity.map { converterEntity(it) }
    }

    fun converterEntity(entity: AccountEntity): AccountModel {
        return AccountModel(entity.id, entity.adminId, entity.name, entity.password, entity.url, entity.desc, entity.createTime)
    }

    fun converterModel(model: List<AccountModel>): List<AccountEntity> {
        return model.map { converterModel(it) }
    }

    fun converterModel(model: AccountModel): AccountEntity {
        return AccountEntity(model.id, model.adminId, model.name, model.password, model.url, model.desc, model.createTime)
    }
}