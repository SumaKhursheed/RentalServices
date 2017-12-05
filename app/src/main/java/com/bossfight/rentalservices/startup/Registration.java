package com.bossfight.rentalservices.startup;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bossfight.rentalservices.R;
import com.bossfight.rentalservices.utility.AppConfig;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Registration extends AppCompatActivity{

    public String ScanRegistrationBarCode;
    public JSONObject jsonObj;

    String BASE_URL = "https://gentle-cliffs-60386.herokuapp.com";
    public EditText firstname, lastname, address, email, password, contact;
    private ZXingScannerView mScannerView;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                ScanRegistrationBarCode = result.getContents();
                try {
                    jsonObj = new JSONObject(ScanRegistrationBarCode);
                    firstname.setText(jsonObj.getString("firstname"));
                    lastname.setText(jsonObj.getString("lastname"));
                    email.setText(jsonObj.getString("email"));
                    address.setText(jsonObj.getString("address"));
                    contact.setText(jsonObj.getString("contact"));
                    Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void onClick_scan(View v) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    public void login(View v) {
        Intent intent = new Intent(Registration.this, LoginActivity.class);
        startActivity(intent);
    }
}
