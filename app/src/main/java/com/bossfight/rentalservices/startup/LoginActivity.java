package com.bossfight.rentalservices.startup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bossfight.rentalservices.R;
import com.bossfight.rentalservices.utility.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends Activity
{

    public EditText email;
    public EditText password;
    //String BASE_URL = "https://smart-street.herokuapp.com";
    String BASE_URL = "https://gentle-cliffs-60386.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }

    public void onLogin(View v){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL) //Setting the Root URL
                .build();

        AppConfig.signin api = adapter.create(AppConfig.signin.class);
        email= (EditText)findViewById(R.id.uname_login);
        password= (EditText)findViewById(R.id.password_login);
        Log.d("email", email.getText().toString());
        Log.d("password", password.getText().toString());
        api.login(
                email.getText().toString(),
                password.getText().toString(),
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        try {

                            BufferedReader reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            String resp;
                            resp = reader.readLine();
                            Log.d("success", "" + resp);

                            JSONObject jObj = new JSONObject(resp);
                           // int success = jObj.getInt("success");
                            String t = jObj.getString("token");

                            if(t != null){
                                Toast.makeText(getApplicationContext(), "Log in Successful", Toast.LENGTH_SHORT).show();
                                String token = jObj.getString("token");
                                Intent intent = new Intent();
                                intent.setClass(LoginActivity.this, IntroActivity.class);
                                intent.putExtra("token", token);
                                startActivity(intent);
                            } else{
                                Toast.makeText(getApplicationContext(), "Log in Failed", Toast.LENGTH_SHORT).show();
                            }


                        } catch (IOException e) {
                            Log.d("Exception", e.toString());
                        } catch (JSONException e) {
                            Log.d("JsonException", e.toString());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
    public void Register(View view)
    {
        Intent intent = new Intent(LoginActivity.this, Registration.class);
        startActivity(intent);
    }

}

