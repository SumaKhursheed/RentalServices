package com.bossfight.rentalservices.vendor;

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

public class ProductActivity extends AppCompatActivity {

    String BASE_URL = "https://gentle-cliffs-60386.herokuapp.com";
    public EditText productname, productdescription, price, quantity, category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        productname= (EditText)findViewById(R.id.productname);
        productdescription= (EditText)findViewById(R.id.description);
        price= (EditText)findViewById(R.id.price);
        quantity= (EditText)findViewById(R.id.quantity);
        category= (EditText)findViewById(R.id.category);
    }
    public void onAddProduct(View v){
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL) //Setting the Root URL
                .build();

        AppConfig.addprod api = adapter.create(AppConfig.addprod.class);
        api.addproduct(
                productname.getText().toString(),
                productdescription.getText().toString(),
                price.getText().toString(),
                quantity.getText().toString(),
                category.getText().toString(),
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
                            String t = jObj.getString("_id");

                            if(t != null){
                                Toast.makeText(getApplicationContext(), "Product has been successfully added", Toast.LENGTH_SHORT).show();;
                                Intent intent = new Intent();
                                intent.setClass(ProductActivity.this, VendorDashboard.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(getApplicationContext(), "Product addition failed. Please try again", Toast.LENGTH_SHORT).show();
                            }


                        } catch (IOException e) {
                            Log.d("Exception", e.toString());
                        } catch (JSONException e) {
                            Log.d("JsonException", e.toString());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(ProductActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void cancel(View v) {
        Intent intent = new Intent(ProductActivity.this, VendorDashboard.class);
        startActivity(intent);
    }
}

