package com.ss.fieldbuzz

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyActivity : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("myrealm.realm").build()
        Realm.setDefaultConfiguration(config)
    }
}