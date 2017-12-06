package com.bossfight.rentalservices.options;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bossfight.rentalservices.R;
import com.bossfight.rentalservices.customer.PaymentActivity;
import com.bossfight.rentalservices.product.ItemDetailsActivity;
import com.bossfight.rentalservices.customer.CustomerDashboard;
import com.bossfight.rentalservices.utility.ImageUrlUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import static com.bossfight.rentalservices.fragments.ImageListFragment.STRING_IMAGE_POSITION;
import static com.bossfight.rentalservices.fragments.ImageListFragment.STRING_IMAGE_URI;
import static com.bossfight.rentalservices.fragments.ImageListFragment.STRING_PROD_NAME;
import static com.bossfight.rentalservices.fragments.ImageListFragment.STRING_PROD_DESC;
import static com.bossfight.rentalservices.fragments.ImageListFragment.STRING_PROD_PRICE;

public class CartListActivity extends AppCompatActivity {
    private static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        TextView textViewCancel = (TextView)findViewById(R.id.text_action_bottom1);
        TextView textViewBuyNow = (TextView)findViewById(R.id.text_action_bottom2);
        mContext = CartListActivity.this;

        ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
        ArrayList<String> cartlistImageUri =imageUrlUtils.getCartListImageUri();
        ArrayList<String> cartlistName =imageUrlUtils.getCartListName();
        ArrayList<String> cartlistDesc =imageUrlUtils.getCartListDesc();
        ArrayList<String> cartlistPrice =imageUrlUtils.getCartListPrice();
        //Show cart layout based on items
        setCartLayout();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager recylerViewLayoutManager = new LinearLayoutManager(mContext);

        recyclerView.setLayoutManager(recylerViewLayoutManager);
        recyclerView.setAdapter(new CartListActivity.SimpleStringRecyclerViewAdapter(recyclerView, cartlistImageUri, cartlistName, cartlistDesc, cartlistPrice));

        textViewBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartListActivity.this, "Please select an item for payment", Toast.LENGTH_SHORT).show();

            }
        });

        textViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, CustomerDashboard.class));

            }
        });
    }

    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<CartListActivity.SimpleStringRecyclerViewAdapter.ViewHolder> {

        private ArrayList<String> mCartlistImageUri, mCartlistName, mCartlistDesc, mCartlistPrice;
        private RecyclerView mRecyclerView;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final SimpleDraweeView mImageView;
            public final TextView name, description, price;
            public final LinearLayout mLayoutItem, mLayoutRemove , mLayoutEdit;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (SimpleDraweeView) view.findViewById(R.id.image_cartlist);
                name = (TextView) view.findViewById(R.id.name);
                description = (TextView) view.findViewById(R.id.description);
                price = (TextView) view.findViewById(R.id.price);
                mLayoutItem = (LinearLayout) view.findViewById(R.id.layout_item_desc);
                mLayoutRemove = (LinearLayout) view.findViewById(R.id.layout_action1);
                mLayoutEdit = (LinearLayout) view.findViewById(R.id.layout_action2);
            }
        }

        public SimpleStringRecyclerViewAdapter(RecyclerView recyclerView, ArrayList<String> wishlistImageUri, ArrayList<String> namelist, ArrayList<String> desclist, ArrayList<String> pricelist) {
            mCartlistImageUri = wishlistImageUri;
            mCartlistName = namelist;
            mCartlistDesc = desclist;
            mCartlistPrice = pricelist;
            mRecyclerView = recyclerView;
        }

        @Override
        public CartListActivity.SimpleStringRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cartlist_item, parent, false);
            return new CartListActivity.SimpleStringRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onViewRecycled(CartListActivity.SimpleStringRecyclerViewAdapter.ViewHolder holder) {
            if (holder.mImageView.getController() != null) {
                holder.mImageView.getController().onDetach();
            }
            if (holder.mImageView.getTopLevelDrawable() != null) {
                holder.mImageView.getTopLevelDrawable().setCallback(null);
//                ((BitmapDrawable) holder.mImageView.getTopLevelDrawable()).getBitmap().recycle();
            }
        }

        @Override
        public void onBindViewHolder(final CartListActivity.SimpleStringRecyclerViewAdapter.ViewHolder holder, final int position) {
            final Uri uri = Uri.parse(mCartlistImageUri.get(position));
            holder.mImageView.setImageURI(uri);

            //final Uri uri1 = Uri.parse(mCartlistName.get(position));
            holder.name.setText(mCartlistName.get(position));
            holder.description.setText(mCartlistDesc.get(position));
            holder.price.setText(mCartlistPrice.get(position));


            holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ItemDetailsActivity.class);
                    intent.putExtra(STRING_IMAGE_URI,mCartlistImageUri.get(position));
                    intent.putExtra(STRING_IMAGE_POSITION, position);
                    intent.putExtra(STRING_PROD_NAME, holder.name.getText().toString());
                    intent.putExtra(STRING_PROD_DESC, holder.description.getText().toString());
                    intent.putExtra(STRING_PROD_PRICE, holder.price.getText().toString());
                    mContext.startActivity(intent);
                }
            });

           //Set click action
            holder.mLayoutRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
                    imageUrlUtils.removeCartListImageUri(position);
                    notifyDataSetChanged();
                    //Decrease notification count
                    CustomerDashboard.notificationCountCart--;

                }
            });

            //Set click action
            holder.mLayoutEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }

        @Override
        public int getItemCount() {
            return mCartlistImageUri.size();
        }
    }

    protected void setCartLayout(){
        LinearLayout layoutCartItems = (LinearLayout) findViewById(R.id.layout_items);
        LinearLayout layoutCartPayments = (LinearLayout) findViewById(R.id.layout_payment);
        LinearLayout layoutCartNoItems = (LinearLayout) findViewById(R.id.layout_cart_empty);

        if(CustomerDashboard.notificationCountCart >0){
            layoutCartNoItems.setVisibility(View.GONE);
            layoutCartItems.setVisibility(View.VISIBLE);
            layoutCartPayments.setVisibility(View.VISIBLE);
        }else {
            layoutCartNoItems.setVisibility(View.VISIBLE);
            layoutCartItems.setVisibility(View.GONE);
            layoutCartPayments.setVisibility(View.GONE);

            Button bStartShopping = (Button) findViewById(R.id.bAddNew);
            bStartShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }
}
