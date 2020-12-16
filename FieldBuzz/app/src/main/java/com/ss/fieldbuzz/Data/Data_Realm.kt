package com.ss.fieldbuzz.Data

import io.realm.RealmObject

open class Data_Realm(var email:String? = null, var createTime:Int? = null, var updateTime:Int? = null):RealmObject() {
}