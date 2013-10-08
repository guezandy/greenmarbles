/**
 * Copyright 2010-present Facebook.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zipper.zipcloset;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.LoggingBehavior;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.User;
import com.zipper.zipcloset.R;

public class FacebookLoginActivity extends Activity {
    private static final String URL_PREFIX_FRIENDS = "https://graph.facebook.com/me/friends?access_token=";

	protected static final String TAG = "Faceeeboooook";

    private TextView textInstructionsOrLink;
    private Button buttonLoginLogout;
    protected Client kinveyClient;
    //private Session.StatusCallback statusCallback = new SessionStatusCallback();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        buttonLoginLogout = (Button)findViewById(R.id.fblog);
        textInstructionsOrLink = (TextView)findViewById(R.id.fbinst);
    }

    public void submitFacebook(View view){
        // The FB SDK has a bit of a delay in response
        final ProgressDialog progressDialog = ProgressDialog.show(
                FacebookLoginActivity.this, "Connecting to Facebook",
                "Logging in with Facebook - just a moment");
        Session.openActiveSession(this, true, new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                if (exception == null) {
                    if (state.equals(RESULT_CANCELED)) {
                        Toast.makeText(FacebookLoginActivity.this, "FB login cancelled",
                               Toast.LENGTH_LONG).show();
                     } else if (state.isOpened()) {
                        if (progressDialog != null && progressDialog.isShowing()) {
                               progressDialog.dismiss();
                         }
                           Toast.makeText(FacebookLoginActivity.this, 
                                   "Logged in with Facebook.", Toast.LENGTH_LONG).show();
                           loginFacebookKinveyUser(progressDialog, 
                                   session.getAccessToken());
                      }
                } else {
                     error(progressDialog, exception.getMessage());
                   }
            }

			private void error(ProgressDialog progressDialog, String message) {
				// TODO Auto-generated method stub
				
			}
           });
    }
    private void loginFacebookKinveyUser(final ProgressDialog progressDialog, 
            String accessToken) {
        kinveyClient.user().loginFacebook(accessToken, new KinveyUserCallback() {
            @Override
            public void onFailure(Throwable e) {
                error(progressDialog, "Kinvey: " + e.getMessage());
                Log.e(TAG, "failed Kinvey facebook login", e);
            }
            private void error(ProgressDialog progressDialog, String string) {
				Toast.makeText(getApplicationContext(), "IN KinveyloginFailure", Toast.LENGTH_SHORT).show();
				
			}
			@Override
            public void onSuccess(User u) {
                Log.d(TAG, "successfully logged in with facebook");
				Toast.makeText(getApplicationContext(), "IN KinveyloginSuccess", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
