package com.bossfight.rentalservices.customer;


        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.support.v7.app.AppCompatActivity;
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

    public class FeedbackActivity extends AppCompatActivity
       {

    EditText ProdDescription;
    String BASE_URL = "https://smart-street.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ProdDescription = (EditText) findViewById(R.id.feedbackbox);

    }

    public void addComment (View v){
        String description = ProdDescription.getText().toString();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL) //Setting the Root URL
                .build();

        AppConfig.comment api = adapter.create(AppConfig.comment.class);
        api.usercomments(
                description,
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
                                Toast.makeText(getApplicationContext(), "You will receive an email shortly. Thank you.", Toast.LENGTH_SHORT).show();;
                            } else{
                                Toast.makeText(getApplicationContext(), "Submission Failed", Toast.LENGTH_SHORT).show();
                            }


                        } catch (IOException e) {
                            Log.d("Exception", e.toString());
                        } catch (JSONException e) {
                            Log.d("JsonException", e.toString());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(FeedbackActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

    }


    @Override
    public void onBackPressed() {
        finish();
    }

}
