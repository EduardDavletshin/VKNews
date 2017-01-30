package com.example.eddy.vknews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;


public class LoginActivity extends Activity {
    private final static String[] scope = new String[]{VKScope.WALL, VKScope.FRIENDS};
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        VKSdk.login(this, scope);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                token = res.accessToken;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(VKError error) {

            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
