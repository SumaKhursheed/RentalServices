package com.bossfight.rentalservices.customer;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bossfight.rentalservices.R;
import com.bossfight.rentalservices.utility.AppConfig;
import com.bossfight.rentalservices.utility.ImageUrlUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PrecustomerDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precustomer_dashboard);

        new getResult().execute("");
    }

    private class getResult extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            RestAdapter adapter = new RestAdapter.Builder().setEndpoint("https://gentle-cliffs-60386.herokuapp.com").build();

            AppConfig.readproducts api = adapter.create(AppConfig.readproducts.class);
            api.readData(new Callback<Response>() {
                             @Override
                             public void success(Response result, Response response) {

                                 try {

                                     BufferedReader reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                                     String resp;
                                     resp = reader.readLine();
                                     Log.d("success", "" + resp);

                                     JSONObject jObj = new JSONObject(resp);
                                     int success = jObj.getInt("success");

                                     JSONArray obj = jObj.getJSONArray("product");

                                     int i;
                                     String[] n = new String[obj.length()];
                                     String[] d = new String[obj.length()];
                                     String[] p = new String[obj.length()];
                                     String[] category = new String[obj.length()];

                                     for (i=0;i<obj.length();i++){
                                         JSONObject jObject = obj.getJSONObject(i);
                                         if(jObject!=null) {
                                             ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
                                             category[i]= jObject.getString("category");
                                             if(category[i].equals("SOLAR")){
                                                 n[i] = jObject.getString("productname");
                                                 imageUrlUtils.addsProductListName(n[i]);
                                                 //holder.name.setText(n[i]);
                                                 d[i] = jObject.getString("productdescription");
                                                 imageUrlUtils.addsProductListDesc(d[i]);
                                                 p[i] = jObject.getString("price");
                                                 imageUrlUtils.addsProductListPrice(p[i]);
                                             }
                                             else if(category[i].equals("HVAC")){
                                                 n[i] = jObject.getString("productname");
                                                 imageUrlUtils.addhProductListName(n[i]);
                                                 //holder.name.setText(n[i]);
                                                 d[i] = jObject.getString("productdescription");
                                                 imageUrlUtils.addhProductListDesc(d[i]);
                                                 p[i] = jObject.getString("price");
                                                 imageUrlUtils.addhProductListPrice(p[i]);
                                             }
                                             else if(category[i].equals("SMART LIGHTING")){
                                                 n[i] = jObject.getString("productname");
                                                 imageUrlUtils.addtProductListName(n[i]);
                                                 //holder.name.setText(n[i]);
                                                 d[i] = jObject.getString("productdescription");
                                                 imageUrlUtils.addtProductListDesc(d[i]);
                                                 p[i] = jObject.getString("price");
                                                 imageUrlUtils.addtProductListPrice(p[i]);
                                             }
                                             else if(category[i].equals("WINDOW FILM")){
                                                 n[i] = jObject.getString("productname");
                                                 imageUrlUtils.addwProductListName(n[i]);
                                                 //holder.name.setText(n[i]);
                                                 d[i] = jObject.getString("productdescription");
                                                 imageUrlUtils.addwProductListDesc(d[i]);
                                                 p[i] = jObject.getString("price");
                                                 imageUrlUtils.addwProductListPrice(p[i]);
                                             }
                                             // i=2;
                                             Log.d("SECOND SUCCESS", n[i]);
                                         }
                                     }
                                 } catch (IOException e) {
                                     Log.d("Exception", e.toString());
                                 } catch (JSONException e) {
                                     Log.d("JsonException", e.toString());
                                 }
                             }

                             @Override
                             public void failure(RetrofitError error) {
                                 Log.d("tag", "failure");
                             }
                         }
            );
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("success", "Executed");
            Intent intent = new Intent();
            intent.setClass(PrecustomerDashboard.this, CustomerDashboard.class);
            startActivity(intent);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
