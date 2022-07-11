package com.example.cs496week2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
//import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause.*
import com.kakao.sdk.user.UserApiClient
class LoginActivity : AppCompatActivity() {
    lateinit var userID: String
    lateinit var name: String
    lateinit var email: String
    lateinit var photoSrc: String

//    var userID = "aa"
//    var name = "bb"
//    var email = "cc"
//    var photoSrc = "dd"

//    companion object {
//        var userID = "aa"
//        var name = "bb"
//        var email = "cc"
//        var photoSrc = "dd"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // If already logged in once then clicking is skipped
        // comment out for coding purpose,

        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
            }
            else if (tokenInfo != null) {
                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
                Log.d("token", "FIRST")
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e("callUserInfo2", "사용자 정보 요청 실패2", error)
                    }
                    else if (user != null) {
                        userID = user.id.toString();
                        name = user.kakaoAccount?.profile?.nickname.toString();
                        email = user.kakaoAccount?.email.toString();
                        photoSrc = user.kakaoAccount?.profile?.thumbnailImageUrl.toString();
                        Log.i("callUserInfo", "사용자 정보 요청 성공" +
                                "\n회원번호: $userID" +
                                "\n닉네임: $name" +
                                "\n이메일: $email" +
                                "\n프로필사진: $photoSrc")
                        val intent = Intent(this, InitProfileActivity::class.java)
                        intent.putExtra("userIDKey", userID)
                        intent.putExtra("nameKey", name)
                        intent.putExtra("emailKey", email)
                        intent.putExtra("photoSrcKey", photoSrc)
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finish()
//
//                        Log.i("callUserInfo2", "사용자 정보 요청 성공2" +
//                                "\n회원번호: ${user.id}" +
//                                "\n이메일: ${user.kakaoAccount?.email}" +
//                                "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
//                                "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                    }
                }
//                val intent = Intent(this, InitProfileActivity::class.java)
//                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                finish()
            }
        }

        //Callback for error cases
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
//                Log.i("loginSuccessfully", "로그인 성공(토큰) : " + token.accessToken);
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e("callUserInfo", "사용자 정보 요청 실패", error)
                    }
                    else if (user != null) {
                        userID = user.id.toString();
                        name = user.kakaoAccount?.profile?.nickname.toString();
                        email = user.kakaoAccount?.email.toString();
                        photoSrc = user.kakaoAccount?.profile?.thumbnailImageUrl.toString();
                        Log.i("callUserInfo", "사용자 정보 요청 성공" +
                                "\n회원번호: $userID" +
                                "\n닉네임: $name" +
                                "\n이메일: $email" +
                                "\n프로필사진: $photoSrc")
                        val intent = Intent(this, InitProfileActivity::class.java)
                        intent.putExtra("userIDKey", userID)
                        intent.putExtra("nameKey", name)
                        intent.putExtra("emailKey", email)
                        intent.putExtra("photoSrcKey", photoSrc)
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finish()
                    }
                }
            }
        }

        // Initial login
        val kakao_login_button = findViewById<ImageButton>(R.id.kakao_login_button) // 로그인 버튼
        kakao_login_button.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
            Log.d("after callback", "aft")

        }
    }
}