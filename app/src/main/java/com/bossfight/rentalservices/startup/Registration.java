package com.bossfight.rentalservices.startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bossfight.rentalservices.utility.AppConfig;
import com.bossfight.rentalservices.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Registration extends AppCompatActivity {

   // String BASE_URL = "https://smart-street.herokuapp.com";
   String BASE_URL = "https://gentle-cliffs-60386.herokuapp.com";
    public EditText firstname, lastname, address, email, password, contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstname= (EditText)findViewById(R.id.firstname);
        lastname= (EditText)findViewById(R.id.lastname);
        address= (EditText)findViewById(R.id.address);
        email= (EditText)findViewById(R.id.email);
        password= (EditText)findViewById(R.id.password);
        contact= (EditText)findViewById(R.id.contact);
    }
    public void onBuyProduct(View v){
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL) //Setting the Root URL
                .build();

        AppConfig.signup api = adapter.create(AppConfig.signup.class);
        api.buyproduct(
                firstname.getText().toString(),
                lastname.getText().toString(),
                address.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                contact.getText().toString(),
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        try {

                            BufferedReader reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            String resp;
                            resp = reader.readLine();
                            Log.d("success", "" + resp);

                            JSONObject jObj = new JSONObject(resp);
                            //int success = jObj.getInt("success");
                            String t = jObj.getString("token");

                            if(t != null){
                                Toast.makeText(getApplicationContext(), "Registration Successful, Please Log in", Toast.LENGTH_SHORT).show();;
                                Intent intent = new Intent();
                                intent.setClass(Registration.this, LoginActivity.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(getApplicationContext(), "Registration Fail", Toast.LENGTH_SHORT).show();
                            }


                        } catch (IOException e) {
                            Log.d("Exception", e.toString());
                        } catch (JSONException e) {
                            Log.d("JsonException", e.toString());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(Registration.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void login(View v) {
        Intent intent = new Intent(Registration.this, LoginActivity.class);
        startActivity(intent);
    }
}
