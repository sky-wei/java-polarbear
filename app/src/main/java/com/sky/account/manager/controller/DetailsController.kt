package com.sky.account.manager.controller

import com.sky.account.manager.base.BaseController
import com.sky.account.manager.model.AccountModel
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.stage.Stage
import java.net.URL
import java.util.*

/**
 * Created by sky on 17-9-7.
 */
class DetailsController : BaseController<AccountModel>(), Initializable {

    @FXML lateinit var jtaDetails: TextArea
    @FXML lateinit var jBtmOk: Button

    private lateinit var mStage: Stage

    override fun initialize(location: URL?, resources: ResourceBundle?) {
    }

    override fun initParam(stage: Stage, param: AccountModel) {
        super.initParam(stage, param)

        mStage = stage

        val info = StringBuilder().apply {
            append("ID : ${param.id}\n")
            append("AdminID : ${param.adminId}\n")
            append("用户名 : ${param.name}\n")
            append("密码 :  ${param.password}\n")
            append("网站地址 : ${param.url}\n")
            append("描述内容 : ${param.desc}\n")
            append("创建时间 : ${HomeController.DATA_FORMAT.format(param.createTime)}")
        }

        // 设置详细信息
        jtaDetails.text = info.toString()
    }

    fun onOkAction() {
        // 关闭
        mStage.close()
    }
}