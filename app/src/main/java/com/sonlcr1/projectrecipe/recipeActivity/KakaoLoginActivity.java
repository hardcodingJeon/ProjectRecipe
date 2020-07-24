package com.sonlcr1.projectrecipe.recipeActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.exception.KakaoException;
import com.sonlcr1.projectrecipe.R;

import java.security.MessageDigest;

public class KakaoLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login);

        Session.getCurrentSession().addCallback(sessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    //카카오 로그인 서버와 연결을 시도하는 세션작업의 결과를 듣는 리스너
    ISessionCallback sessionCallback = new ISessionCallback() {
        @Override
        public void onSessionOpened() {
            Toast.makeText(KakaoLoginActivity.this, "로그인 세션연결 성공", Toast.LENGTH_SHORT).show();

            //로그인 된 사용자의 정보들 얻어오기
            requestUserInfo();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Toast.makeText(KakaoLoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
        }
    };

    //로그인 사용자정보 받기
    void requestUserInfo(){
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {

            }

            @Override
            public void onSuccess(MeV2Response result) {
                //사용자 계정 정보 객체
                UserAccount userAccount = result.getKakaoAccount();
                if (userAccount==null) return;

                Intent intent = new Intent(KakaoLoginActivity.this,BoardEdit.class);
                intent.putExtra("email",userAccount.getEmail());
                intent.putExtra("nickName",userAccount.getProfile().getNickname());
                intent.putExtra("img",userAccount.getProfile().getProfileImageUrl());
                startActivity(intent);
            }
        });
    }

    //앱이 종료될때 스트림 닫기
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Session.getCurrentSession().removeCallback(sessionCallback);
    }

}
