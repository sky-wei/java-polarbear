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

package com.sky.account.manager.util

import javafx.fxml.FXMLLoader
import javafx.scene.image.Image
import java.net.URL

/**
 * Created by sky on 17-9-1.
 */
object ResUtil {

    fun getResource(name: String): URL {
        return javaClass.classLoader.getResource(name)
    }

    fun getResourceUrl(name: String): String {
        return getResource(name).toExternalForm()
    }

    fun getImage(name: String): Image {
        return Image(getResourceUrl(name))
    }

    fun <T> getLayout(name: String): T {
        return FXMLLoader.load(getResource(name))
    }

    fun getFXMLLoader(name: String): FXMLLoader {
        return FXMLLoader(getResource(name))
    }
}
