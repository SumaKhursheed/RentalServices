package com.bossfight.rentalservices.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bossfight.rentalservices.product.ItemDetailsActivity;
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

public class PaymentActivity extends AppCompatActivity {

    String BASE_URL = "https://gentle-cliffs-60386.herokuapp.com";
    public EditText name, card, cvc, exp, prodname, price, pricewo;
    String pname, pprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        name= (EditText)findViewById(R.id.name);
        card= (EditText)findViewById(R.id.cardnumber);
        cvc= (EditText)findViewById(R.id.cvc);
        exp= (EditText)findViewById(R.id.exp);
        price= (EditText)findViewById(R.id.price);
        pricewo= (EditText)findViewById(R.id.price);
        prodname= (EditText)findViewById(R.id.productname);

        if (getIntent() != null) {
            pname = getIntent().getStringExtra(ItemDetailsActivity.STRING_PROD_NAME);
            pprice = getIntent().getStringExtra(ItemDetailsActivity.STRING_PROD_PRICE);
        }

        price.setText("$" + pprice);
        prodname.setText(pname);
    }
    public void onPay(View v){
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL) //Setting the Root URL
                .build();

        AppConfig.pay api = adapter.create(AppConfig.pay.class);
        api.pay(
                prodname.getText().toString(),
                name.getText().toString(),
                cvc.getText().toString(),
                pprice,
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        try {

                            BufferedReader reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            String resp;
                            resp = reader.readLine();
                            Log.d("success", "" + resp);

                            JSONObject jObj = new JSONObject(resp);
                            int success = jObj.getInt("success");


                            if(success == 1){
                                Toast.makeText(getApplicationContext(), "Payment Successful", Toast.LENGTH_SHORT).show();;
                                Intent intent = new Intent();
                                intent.setClass(com.bossfight.rentalservices.customer.PaymentActivity.this, CustomerDashboard.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(getApplicationContext(), "Payment failed", Toast.LENGTH_SHORT).show();
                            }


                        } catch (IOException e) {
                            Log.d("Exception", e.toString());
                        } catch (JSONException e) {
                            Log.d("JsonException", e.toString());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(com.bossfight.rentalservices.customer.PaymentActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void cancel(View v) {
        Intent intent = new Intent(com.bossfight.rentalservices.customer.PaymentActivity.this, CustomerDashboard.class);
        startActivity(intent);
    }
}
