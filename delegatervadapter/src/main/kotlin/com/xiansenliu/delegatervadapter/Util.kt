package com.xiansenliu.delegatervadapter

import java.lang.reflect.ParameterizedType

/**
 * Author       xinliu
 * Date         8/28/17
 * Time         8:37 PM
 */
class Util {
    companion object {
        fun getParameterizedClass(d: ModelDelegate<*>): Class<*> {
            val dataType = (d.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<*>
            return dataType
        }
    }
}