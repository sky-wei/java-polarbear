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

package com.sky.account.manager.util

import javafx.scene.control.Alert
import javafx.scene.control.TextField

/**
 * Created by sky on 17-9-6.
 */
object VerifyUtil {

    fun isEmpty(field: TextField, msg: String): Boolean {
        return isEmpty(field.text, msg)
    }

    fun isEmpty(value: String?, msg: String): Boolean {

        if (value == null || value.isEmpty()) {
            DialogUtil.showMessage(Alert.AlertType.ERROR, msg)
            return true
        }
        return false
    }

    fun isEquals(field: TextField, field2: TextField, msg: String): Boolean {
        return isEquals(field.text, field2.text, msg)
    }

    fun isEquals(value: String?, value2: String?, msg: String): Boolean {

        if (value == value2) return true

        if (!value.equals(value2)) {
            DialogUtil.showMessage(Alert.AlertType.ERROR, msg)
            return false
        }

        return true
    }

    fun isMinLength(field: TextField, minLength: Int, msg: String): Boolean {
        return isMinLength(field.text, minLength, msg)
    }

    fun isMinLength(value: String, minLength: Int, msg: String): Boolean {

        if (value.length < minLength) {
            DialogUtil.showMessage(Alert.AlertType.ERROR, msg)
            return true
        }
        return false
    }

    fun isMaxLength(field: TextField, maxLength: Int, msg: String): Boolean {
        return isMaxLength(field.text, maxLength, msg)
    }

    fun isMaxLength(value: String, maxLength: Int, msg: String): Boolean {

        if (value.length > maxLength) {
            DialogUtil.showMessage(Alert.AlertType.ERROR, msg)
            return true
        }
        return false
    }
}