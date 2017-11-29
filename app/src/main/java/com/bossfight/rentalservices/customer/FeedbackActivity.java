package com.bossfight.rentalservices.customer;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.util.Log;
        import android.view.View;
        import android.support.design.widget.NavigationView;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.content.Context;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RatingBar;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.RatingBar.OnRatingBarChangeListener;

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
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        //here is the main place where we need to work on.
//        int id=item.getItemId();
//        switch (id){
//
//            case R.id.nav_home:
//                Intent h= new Intent(CommentActivity.this,AboutActivity.class);
//                startActivity(h);
//                break;
//            case R.id.nav_interact:
//                Intent i= new Intent(CommentActivity.this,InteractActivity.class);
//                startActivity(i);
//                break;
//            case R.id.nav_photo:
//                Intent g= new Intent(CommentActivity.this,PhotoActivity.class);
//                startActivity(g);
//                break;
//            case R.id.nav_audio:
//                Intent k= new Intent(CommentActivity.this,AudioActivity.class);
//                startActivity(k);
//                break;
//            case R.id.nav_share:
//                Intent s= new Intent(CommentActivity.this,ShareActivity.class);
//                startActivity(s);
//                break;
//            case R.id.nav_nearby:
//                Intent t= new Intent(CommentActivity.this,NearbyActivity.class);
//                startActivity(t);
//                break;
//            case R.id.nav_comments:
//                Intent x= new Intent(CommentActivity.this,CommentActivity.class);
//                startActivity(x);
//                break;
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}
