package com.example.cs496week2

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "52b7f02066b5d91072cfaa82aa8fb46f")
    }
}