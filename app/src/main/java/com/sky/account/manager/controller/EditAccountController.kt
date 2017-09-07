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

package com.sky.account.manager.controller

import com.sky.account.manager.base.BaseController
import com.sky.account.manager.model.AccountModel
import com.sky.account.manager.util.VerifyUtil
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.stage.Stage
import java.net.URL
import java.util.*

/**
 * Created by sky on 17-9-6.
 */
class EditAccountController : BaseController<AccountModel>(), Initializable {

    @FXML lateinit var jtfName: TextField
    @FXML lateinit var jtfPassword: PasswordField
    @FXML lateinit var jtfUrl: TextField
    @FXML lateinit var jtaDesc: TextArea
    @FXML lateinit var jBtmOk: Button

    private lateinit var mStage: Stage
    private lateinit var mParam: AccountModel
    private lateinit var mCallback: (AccountModel) -> Unit

    override fun initialize(location: URL?, resources: ResourceBundle?) {
    }

    fun onNewAccountAction() {

        if (VerifyUtil.isEmpty(jtfName, "用户名不能为空！")
                || VerifyUtil.isEmpty(jtfPassword, "密码不能为空！")) {
            return
        }

        // 复制对象,重新设置值
        val result = mParam.copy()

        result.name = jtfName.text
        result.password = jtfPassword.text
        result.url = jtfUrl.text
        result.desc = jtaDesc.text

        mCallback(result)
        mStage.close()
    }

    override fun initParam(stage: Stage, param: AccountModel) {
        super.initParam(stage, param)
        mStage = stage
        mParam = param

        // 初始化信息
        jtfName.text = param.name
        jtfPassword.text = param.password
        jtfUrl.text = param.url
        jtaDesc.text = param.desc
    }

    override fun setResultCallback(callback: (AccountModel) -> Unit) {
        super.setResultCallback(callback)
        mCallback = callback
    }
}
