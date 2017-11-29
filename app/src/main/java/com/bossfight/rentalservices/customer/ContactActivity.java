package com.bossfight.rentalservices.customer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bossfight.rentalservices.R;

public class ContactActivity extends AppCompatActivity {
    Button ring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ring = (Button)findViewById(R.id.button_ring);
        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "408-909-786-2";

                String uri = "tel:" + number.trim() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });
    }
}
