package com.sky.account.manager.controller

import com.sky.account.manager.base.BaseController
import com.sky.account.manager.model.AccountModel
import javafx.fxml.Initializable
import javafx.stage.Stage
import java.net.URL
import java.util.*

/**
 * Created by sky on 17-9-6.
 */
class NewAccountController : BaseController<AccountModel, AccountModel>(), Initializable {

    lateinit var mStage: Stage
    lateinit var mParam: AccountModel
    lateinit var mCallback: Callback<AccountModel>

    override fun initialize(location: URL?, resources: ResourceBundle?) {
    }

    fun onNewAccountAction() {

        mCallback.onResult(AccountModel())
        mStage.close()
    }

    override fun initParam(stage: Stage, param: AccountModel) {
        super.initParam(stage, param)
        mStage = stage
        mParam = param
    }

    override fun setResultCallback(callback: Callback<AccountModel>) {
        super.setResultCallback(callback)
        mCallback = callback
    }
}
