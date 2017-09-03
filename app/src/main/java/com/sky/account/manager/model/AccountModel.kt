package com.sky.account.manager.model

import java.io.Serializable

/**
 * Created by sky on 17-9-3.
 */
data class AccountModel(val id: Int, val name: String, val password: String, val desc: String, val createTime: String) : Serializable {

}
