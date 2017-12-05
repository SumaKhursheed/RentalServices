package com.bossfight.rentalservices.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
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

public class CommentActivity extends AppCompatActivity {

    EditText ProdDescription, ProdRate, ProdName;
    private RatingBar ratingBar;
    private TextView txtRatingValue;
    String BASE_URL = "https://gentle-cliffs-60386.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ProdDescription = (EditText) findViewById(R.id.desc);
        ProdRate = (EditText) findViewById(R.id.txtRatingValue);
        ProdName = (EditText) findViewById(R.id.name);
        addListenerOnRatingBar();
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));

            }
        });
    }

    public void addComment (View v){
        String name = ProdName.getText().toString();
        String comment = ProdDescription.getText().toString();
        String rating = ProdRate.getText().toString();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL) //Setting the Root URL
                .build();

        AppConfig.comment api = adapter.create(AppConfig.comment.class);
        api.usercomments(
                name,
                comment,
                rating,
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        try {

                            BufferedReader reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            String resp;
                            resp = reader.readLine();
                            Log.d("success", "" + resp);

                            JSONObject jObj = new JSONObject(resp);
                            String t = jObj.getString("_id");

                            if(t != null){
                                Toast.makeText(getApplicationContext(), "Thankyou for your feedback", Toast.LENGTH_SHORT).show();;
                            } else{
                                Toast.makeText(getApplicationContext(), "Feedback Failed", Toast.LENGTH_SHORT).show();
                            }


                        } catch (IOException e) {
                            Log.d("Exception", e.toString());
                        } catch (JSONException e) {
                            Log.d("JsonException", e.toString());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(CommentActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    public void review(View view)
    {
        Intent intent = new Intent(CommentActivity.this, ViewComments.class);
        startActivity(intent);
    }
}
