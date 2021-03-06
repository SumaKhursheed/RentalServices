package com.bossfight.rentalservices.product;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bossfight.rentalservices.R;
import com.bossfight.rentalservices.customer.PaymentActivity;
import com.bossfight.rentalservices.fragments.ImageListFragment;
import com.bossfight.rentalservices.fragments.ViewPagerActivity;
import com.bossfight.rentalservices.notification.NotificationCountSetClass;
import com.bossfight.rentalservices.customer.CustomerDashboard;
import com.bossfight.rentalservices.utility.ImageUrlUtils;
import com.facebook.drawee.view.SimpleDraweeView;

public class ItemDetailsActivity extends AppCompatActivity {
    int imagePosition;
    public static final String STRING_PROD_NAME = "ProductName";
    public static final String STRING_PROD_PRICE = "ProductPrice";
    String stringImageUri, pname, pdesc, pprice;
    TextView name, description, price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        SimpleDraweeView mImageView = (SimpleDraweeView)findViewById(R.id.image1);
        TextView textViewAddToCart = (TextView)findViewById(R.id.text_action_bottom1);
        TextView textViewBuyNow = (TextView)findViewById(R.id.text_action_bottom2);
        name = (TextView)findViewById(R.id.name);
        description = (TextView)findViewById(R.id.description);
        price = (TextView)findViewById(R.id.price);

        //Getting image uri from previous screen
        if (getIntent() != null) {
            stringImageUri = getIntent().getStringExtra(ImageListFragment.STRING_IMAGE_URI);
            pname = getIntent().getStringExtra(ImageListFragment.STRING_PROD_NAME);
            pdesc = getIntent().getStringExtra(ImageListFragment.STRING_PROD_DESC);
            pprice = getIntent().getStringExtra(ImageListFragment.STRING_PROD_PRICE);
            imagePosition = getIntent().getIntExtra(ImageListFragment.STRING_IMAGE_URI,0);
        }
        name.setText(pname);
        description.setText("\u2022 " + pdesc);
        price.setText(pprice);
        Uri uri = Uri.parse(stringImageUri);
        mImageView.setImageURI(uri);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(ItemDetailsActivity.this, ViewPagerActivity.class);
                    intent.putExtra("position", imagePosition);
                    startActivity(intent);

            }
        });

        textViewAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
                imageUrlUtils.addCartListImageUri(stringImageUri);
                imageUrlUtils.addCartListName(pname);
                imageUrlUtils.addCartListDesc(pdesc);
                imageUrlUtils.addCartListPrice(pprice);
                Toast.makeText(ItemDetailsActivity.this,"Item added to cart.",Toast.LENGTH_SHORT).show();
                CustomerDashboard.notificationCountCart++;
                NotificationCountSetClass.setNotifyCount(CustomerDashboard.notificationCountCart);
            }
        });

        textViewBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(ItemDetailsActivity.this, PaymentActivity.class));
                Intent intent = new Intent(ItemDetailsActivity.this, PaymentActivity.class);
                intent.putExtra(STRING_PROD_NAME, name.getText().toString());
                intent.putExtra(STRING_PROD_PRICE, pprice);
                ItemDetailsActivity.this.startActivity(intent);

            }
        });

    }
}
