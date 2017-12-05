package com.bossfight.rentalservices.customer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bossfight.rentalservices.R;
import com.bossfight.rentalservices.utility.AppConfig;

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

public class ViewComments extends AppCompatActivity {
    TextView nam,comm,rate=null;
    String name,comment,rating;
    String BASE_URL = "https://gentle-cliffs-60386.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        nam = (TextView) (findViewById(R.id.name));
        comm = (TextView) (findViewById(R.id.comment));
        rate = (TextView) (findViewById(R.id.rating));
        getResults();
    }

    public void getResults(){
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL) //Setting the Root URL
                .build();

        AppConfig.read api = adapter.create(AppConfig.read.class);
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

                                       JSONArray obj = jObj.getJSONArray("comment");

                                        int i;
                                        name = "";
                                        comment = "";
                                        rating = "";
                                   for (i=0;i<obj.length();i++){
                                       JSONObject jObject = obj.getJSONObject(i);
                                       if(jObject!=null) {
                                           name += jObject.getString("name") + "\n\n";
                                           nam.setText(name);
                                           comment += jObject.getString("comment") + "\n\n";
                                           comm.setText(comment);
                                           rating += jObject.getString("rating") + "\n\n";
                                           rate.setText(rating);




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
                               Toast.makeText(ViewComments.this, error.toString(), Toast.LENGTH_LONG).show();
                           }
                       }
        );
    }
    }