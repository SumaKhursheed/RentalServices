package com.bossfight.rentalservices.options;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bossfight.rentalservices.R;
import com.bossfight.rentalservices.customer.ViewComments;
import com.bossfight.rentalservices.product.ItemDetailsActivity;
import com.bossfight.rentalservices.utility.AppConfig;
import com.bossfight.rentalservices.utility.ImageUrlUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.bossfight.rentalservices.fragments.ImageListFragment.STRING_IMAGE_POSITION;
import static com.bossfight.rentalservices.fragments.ImageListFragment.STRING_IMAGE_URI;

public class OrderlistActivity extends AppCompatActivity {
    TextView name, desc, price;
    String n, d, p;
    String BASE_URL = "https://gentle-cliffs-60386.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        name = (TextView) (findViewById(R.id.item_name));
        //desc = (TextView) (findViewById(R.id.item_desc));
        price = (TextView) (findViewById(R.id.item_price));
        getResults();
    }

    public void getResults() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL) //Setting the Root URL
                .build();

        AppConfig.readorders api = adapter.create(AppConfig.readorders.class);
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
                                 n="";
                                 p="";
                                 for (i = 0; i < obj.length(); i++) {
                                     final JSONObject jObject = obj.getJSONObject(i);

                                         if (jObject != null) {
                                             n += jObject.getString("name") + "\n\n";
                                            // d += jObject.getString("comment") + "\n\n";
                                             p += jObject.getString("price") + "\n\n";

                                             name.setText(n);
                                             price.setText(p);
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
                             Toast.makeText(OrderlistActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                         }
                     }
        );
    }
}



























    //-------------------------------------------STARTS HERE----------------------------------------------------

    //private static Context mContext;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_recylerview_list);
//        mContext = OrderlistActivity.this;
//
//        ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
//        ArrayList<String> orderlistImageUri =imageUrlUtils.getOrderListImageUri();
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
//        RecyclerView.LayoutManager recylerViewLayoutManager = new LinearLayoutManager(mContext);
//
//        recyclerView.setLayoutManager(recylerViewLayoutManager);
//        recyclerView.setAdapter(new OrderlistActivity.SimpleStringRecyclerViewAdapter(recyclerView, orderlistImageUri));
//    }

//    public static class SimpleStringRecyclerViewAdapter
//            extends RecyclerView.Adapter<OrderlistActivity.SimpleStringRecyclerViewAdapter.ViewHolder> {
//
//        private ArrayList<String> mOrderlistImageUri;
//        private RecyclerView mRecyclerView;
//
//        public static class ViewHolder extends RecyclerView.ViewHolder {
//            public final View mView;
//           // public final SimpleDraweeView mImageView;
//            public final LinearLayout mLayoutItem;
//            public final ImageView mImageViewOrderlist;
//            public ViewHolder(View view) {
//                super(view);
//                mView = view;
//                //mImageView = (SimpleDraweeView) view.findViewById(R.id.image_orderlist);
//                mLayoutItem = (LinearLayout) view.findViewById(R.id.layout_item_desc);
//                mImageViewOrderlist = (ImageView) view.findViewById(R.id.ic_orderlist);
//            }
//        }
//
//        public SimpleStringRecyclerViewAdapter(RecyclerView recyclerView, ArrayList<String> orderlistImageUri) {
//            mOrderlistImageUri = orderlistImageUri;
//            mRecyclerView = recyclerView;
//        }
//
//        @Override
//        public OrderlistActivity.SimpleStringRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_orderlist, parent, false);
//            return new OrderlistActivity.SimpleStringRecyclerViewAdapter.ViewHolder(view);
//        }

//        @Override
//        public void onViewRecycled(OrderlistActivity.SimpleStringRecyclerViewAdapter.ViewHolder holder) {
//            if (holder.mImageView.getController() != null) {
//                holder.mImageView.getController().onDetach();
//            }
//            if (holder.mImageView.getTopLevelDrawable() != null) {
//                holder.mImageView.getTopLevelDrawable().setCallback(null);
////                ((BitmapDrawable) holder.mImageView.getTopLevelDrawable()).getBitmap().recycle();
//            }
//        }

//        @Override
//        public void onBindViewHolder(final OrderlistActivity.SimpleStringRecyclerViewAdapter.ViewHolder holder, final int position) {
//            final Uri uri = Uri.parse(mOrderlistImageUri.get(position));
//            holder.mImageView.setImageURI(uri);
//            holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, ItemDetailsActivity.class);
//                    intent.putExtra(STRING_IMAGE_URI,mOrderlistImageUri.get(position));
//                    intent.putExtra(STRING_IMAGE_POSITION, position);
//                    mContext.startActivity(intent);
//                }
//            });
//
//            //Set click action for wishlist
//            holder.mImageViewOrderlist.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
//                    imageUrlUtils.removeOrderListImageUri(position);
//                    notifyDataSetChanged();
//                }
//            });
//        }

//        @Override
//        public int getItemCount() {
//            return mOrderlistImageUri.size();
//        }
//    }
//}
