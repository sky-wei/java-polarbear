package com.sky.account.manager.controller

import com.sky.account.manager.PolarBear
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

/**
 * Created by sky on 17-8-17.
 */
class LoginController : Initializable, AppController {

    lateinit var mPolarBear: PolarBear

    override fun initialize(location: URL?, resources: ResourceBundle?) {

    }

    override fun setPolarBear(bear: PolarBear) {
        mPolarBear = bear
    }
}
