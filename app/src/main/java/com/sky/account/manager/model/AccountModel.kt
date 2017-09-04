package com.sky.account.manager.model

/**
 * Created by sky on 17-9-3.
 */
data class AccountModel(var id: Int, var adminId: Int, var name: String, var password: String, var url: String, var desc: String, var createTime: Long) {

    constructor(): this(0, 0, "", "", "", "", 0L)
}
