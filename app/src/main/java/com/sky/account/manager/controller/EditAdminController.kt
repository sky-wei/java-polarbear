/*
 * Copyright (c) 2017 The sky Authors.
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
import com.sky.account.manager.model.AdminModel
import com.sky.account.manager.util.VerifyUtil
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.stage.Stage
import java.net.URL
import java.util.*

/**
 * Created by sky on 17-8-17.
 */
class EditAdminController : BaseController<AdminModel>(), Initializable {

    @FXML lateinit var jtfName: TextField
    @FXML lateinit var jtfOldPassword: PasswordField
    @FXML lateinit var jtfNewPassword: PasswordField
    @FXML lateinit var jBtmOk: Button

    private lateinit var mStage: Stage
    private lateinit var mParam: AdminModel
    private lateinit var mCallback: (AdminModel) -> Unit

    override fun initialize(location: URL?, resources: ResourceBundle?) {
    }

    override fun initParam(stage: Stage, param: AdminModel) {
        super.initParam(stage, param)
        mStage = stage
        mParam = param

        // 设置用户名
        jtfName.text = param.name
    }

    override fun setResultCallback(callback: (AdminModel) -> Unit) {
        super.setResultCallback(callback)
        mCallback = callback
    }

    fun onKeyPressedAction(event: KeyEvent) {

        if (KeyCode.ENTER == event.code) {
            // 登录
            Platform.runLater { onChangeAdminAction() }
        }
    }

    fun onChangeAdminAction() {

        val admin = getAccountManager().getAdmin()

        if (VerifyUtil.isEmpty(jtfName, "用户名不能为空！")
                || VerifyUtil.isEmpty(jtfOldPassword, "旧密码不能为空！")
                || VerifyUtil.isEmpty(jtfNewPassword, "新密码不能为空！")
                || VerifyUtil.isMinLength(jtfNewPassword, 9, "新密码不能小于九个字符！")
                || !VerifyUtil.isEquals(admin.password, jtfOldPassword.text, "输入的旧密码与登录密码不相同！")) {
            return
        }

        val result = mParam.copy()

        // 设置新的密码
        result.password = jtfNewPassword.text

        // 返回结果
        mCallback(result)
        mStage.close()
    }
}