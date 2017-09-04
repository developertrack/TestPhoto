package developertrack.testphoto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SharedPreferences settings;
    SharedPreferences.Editor editor;
    ProgressDialog progressBar;
    TelephonyManager telephonyManager;
    String profile_picture, name, coins, min_chance_can_set,
            number_of_photos_display_in_winnerlist, chances_per_coins,
            max_chance_can_set, likes_to_perform_before_upload, total_chance;

    String message, user_fmp_id, user_id;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        InstagramManager manager = InstagramManager.getInstance();
        String authRequest = manager.urlStringForAuthenticationRequest();

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view,
                                                    String urlString) {

                System.out.println(urlString);

                if (urlString.contains("#access_token=")) {

                    String[] arrURL = urlString.split("#");

                    settings = getSharedPreferences(Constants.PREF_NAME, 0);
                    editor = settings.edit();

                    // to set the token value
                    String[] tokens = arrURL[1].split("=");



                    InstagramManager manager = InstagramManager.getInstance();
                    manager.setAccessToken(tokens[1]);

                    // manager.deleteInstagramCookies();



					 Log.e("all_data", tokens[1].toString());


                    return true;
                }

                return false;
            }
        });
        myWebView.loadUrl(authRequest);
    }






}
