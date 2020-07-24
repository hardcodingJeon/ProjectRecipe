package com.sonlcr1.projectrecipe;

import android.app.Application;
import android.content.Context;

import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

public class KakaoLoginInit extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //카카오 SDK의 앱의 Application연결
        //KakaoAdapter : 카카오와 앱을 연결 하는 객체

        //카카오 초기화
        KakaoSDK.init(new KakaoAdapter() {
            @Override
            public IApplicationConfig getApplicationConfig() {
                //Application이 가지고 있는 정보를 얻기위한 인터페이스
                return new IApplicationConfig() {
                    @Override
                    public Context getApplicationContext() {
                        return KakaoLoginInit.this;  //앱과 카카오가 연결됨
                    }
                };
            }
        });
    }
}
