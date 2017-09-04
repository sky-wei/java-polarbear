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