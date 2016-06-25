package com.example.alice.twitterclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.crashlytics.android.Crashlytics;
import com.example.alice.twitterclient.main.MainActivity;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "aliceresponde@gmail.com";
    private static final String TWITTER_SECRET = "200415651ua";
    @Bind(R.id.twitterLoginBtn)
    TwitterLoginButton twitterLoginBtn;
    @Bind(R.id.container)
    RelativeLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig), new Crashlytics());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //verify sesion
        if (Twitter.getSessionManager().getActiveSession() != null){
            navigateToMainScreen();
        }

        twitterLoginBtn.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                navigateToMainScreen();
            }

            @Override
            public void failure(TwitterException exception) {
                String msgError = String.format(getString(R.string.login_error_message) ,
                                                exception.getLocalizedMessage());
                Snackbar.make(container, msgError , Snackbar.LENGTH_SHORT ).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginBtn.onActivityResult(requestCode, resultCode , data);
    }

    private void navigateToMainScreen() {
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }


}
