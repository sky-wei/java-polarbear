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