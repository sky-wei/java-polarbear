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

import com.sky.account.manager.Constant
import com.sky.account.manager.base.BaseController
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Hyperlink
import javafx.scene.control.TextArea
import javafx.scene.text.Text
import java.net.URL
import java.util.*

/**
 * Created by sky on 17-9-7.
 */
class AboutController : BaseController<Any>(), Initializable {

    @FXML lateinit var jtVersion: Text
    @FXML lateinit var jtMail: Text
    @FXML lateinit var jhlSource: Hyperlink
    @FXML lateinit var jtaLicense: TextArea

    override fun initialize(location: URL?, resources: ResourceBundle?) {

        jtVersion.text = "版本：${Constant.App.VERSION}"
        jtMail.text = "邮箱：${Constant.App.MAIL}"
        jhlSource.text = Constant.App.GIT_HUB

        jtaLicense.text = "License\n" +
                "\n" +
                "Copyright 2017 The sky Authors\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "    http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License."
    }
}
