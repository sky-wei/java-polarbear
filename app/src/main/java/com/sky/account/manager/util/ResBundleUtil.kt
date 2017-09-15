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

import java.util.*

/**
 * Created by sky on 17-9-15.
 */
object ResBundleUtil {

    private val mResBundle: ResourceBundle = ResourceBundle.getBundle("bundles.polarbear")

    fun getResBundle(): ResourceBundle {
        return mResBundle
    }

    fun getString(key: String): String {
        return mResBundle.getString(key)
    }

    fun getTitle(key: String): String {
        return "${mResBundle.getString("app.name")} - ${mResBundle.getString(key)}"
    }
}