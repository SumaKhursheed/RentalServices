/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bossfight.rentalservices.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.bossfight.rentalservices.customer.CustomerDashboard;
import com.bossfight.rentalservices.utility.AppConfig;
import com.bossfight.rentalservices.utility.ImageUrlUtils;
import com.facebook.drawee.view.SimpleDraweeView;

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


public class ImageListFragment extends Fragment {

    public static final String STRING_IMAGE_URI = "ImageUri";
    public static final String STRING_PROD_NAME = "ProductName";
    public static final String STRING_PROD_DESC = "ProductDescription";
    public static final String STRING_PROD_PRICE = "ProductPrice";
    public static final String STRING_IMAGE_POSITION = "ImagePosition";
    private static CustomerDashboard mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (CustomerDashboard) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.layout_recylerview_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        String[] items=null;
        if (ImageListFragment.this.getArguments().getInt("type") == 1){
            items =ImageUrlUtils.getSolarUrls();
        }else if (ImageListFragment.this.getArguments().getInt("type") == 2){
            items =ImageUrlUtils.getHvacUrls();
        }else if (ImageListFragment.this.getArguments().getInt("type") == 3){
            items =ImageUrlUtils.getSmartUrls();
        }else if (ImageListFragment.this.getArguments().getInt("type") == 4){
            items =ImageUrlUtils.getWindowUrls();
        }else {
            items = ImageUrlUtils.getImageUrls();
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(recyclerView, items));

    }

    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private String[] mValues;
        private RecyclerView mRecyclerView;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final SimpleDraweeView mImageView;
            public final LinearLayout mLayoutItem;
            public final ImageView mImageViewWishlist;
            public final TextView name, description, price;
            public final String BASE_URL = "https://gentle-cliffs-60386.herokuapp.com";

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (SimpleDraweeView) view.findViewById(R.id.image1);
                mLayoutItem = (LinearLayout) view.findViewById(R.id.layout_item);
                mImageViewWishlist = (ImageView) view.findViewById(R.id.ic_wishlist);
                name = (TextView) view.findViewById(R.id.name);
                description = (TextView) view.findViewById(R.id.description);
                price = (TextView) view.findViewById(R.id.price);


            }
        }

        public SimpleStringRecyclerViewAdapter(RecyclerView recyclerView, String[] items) {
            mValues = items;
            mRecyclerView = recyclerView;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onViewRecycled(ViewHolder holder) {
            if (holder.mImageView.getController() != null) {
                holder.mImageView.getController().onDetach();
            }
            if (holder.mImageView.getTopLevelDrawable() != null) {
                holder.mImageView.getTopLevelDrawable().setCallback(null);
//                ((BitmapDrawable) holder.mImageView.getTopLevelDrawable()).getBitmap().recycle();
            }
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            final Uri uri = Uri.parse(mValues[position]);
            holder.mImageView.setImageURI(uri);

            //getResults();

            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint("https://gentle-cliffs-60386.herokuapp.com") //Setting the Root URL
                    .build();

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
//                                     holder.n[i] = "";
//                                     holder.d[i] = "";
//                                     holder.p[i] = "";
                                     for (i=0;i<obj.length();i++){
                                         JSONObject jObject = obj.getJSONObject(i);
                                         if(jObject!=null) {
                                             n[i] = jObject.getString("name");
                                             holder.name.setText(n[i]);
                                             d[i] = jObject.getString("description");
                                             holder.description.setText(d[i]);
                                             p[i] = jObject.getString("price");
                                             holder.price.setText(p[i]);
                                             // i=2;
                                         }



                                     }
//                                     holder.name.setText(holder.n[i]);
//                                     holder.description.setText(holder.d[i]);
//                                     holder.price.setText(holder.p[i]);


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



            holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, ItemDetailsActivity.class);
                    intent.putExtra(STRING_IMAGE_URI, mValues[position]);
                    intent.putExtra(STRING_IMAGE_POSITION, position);
                    intent.putExtra(STRING_PROD_NAME, holder.name.getText().toString());
                    intent.putExtra(STRING_PROD_DESC, holder.description.getText().toString());
                    intent.putExtra(STRING_PROD_PRICE, holder.price.getText().toString());
                    mActivity.startActivity(intent);

                }
            });

            //Set click action for wishlist
            holder.mImageViewWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
                    imageUrlUtils.addWishlistImageUri(mValues[position]);
                    imageUrlUtils.addWishListName(holder.name.getText().toString());
                    imageUrlUtils.addWishListDesc(holder.description.getText().toString());
                    imageUrlUtils.addWishListPrice(holder.price.getText().toString());
                   // holder.mImageViewWishlist.setImageResource(R.drawable.ic_favorite_black_18dp);
                    notifyDataSetChanged();
                    Toast.makeText(mActivity,"Item added to wishlist.",Toast.LENGTH_SHORT).show();

                }
            });

        }

        @Override
        public int getItemCount() {
            return mValues.length;
        }

//        public void getResults(){
//            RestAdapter adapter = new RestAdapter.Builder()
//                    .setEndpoint("https://gentle-cliffs-60386.herokuapp.com") //Setting the Root URL
//                    .build();
//
//            AppConfig.readproducts api = adapter.create(AppConfig.readproducts.class);
//            api.readData(new Callback<Response>() {
//                             @Override
//                             public void success(Response result, Response response) {
//
//                                 try {
//
//                                     BufferedReader reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
//                                     String resp;
//                                     resp = reader.readLine();
//                                     Log.d("success", "" + resp);
//
//                                     JSONObject jObj = new JSONObject(resp);
//                                     int success = jObj.getInt("success");
//
//                                     JSONArray obj = jObj.getJSONArray("product");
//
//                                     int i;
//                                     n = "";
//                                     d = "";
//                                     p = "";
//                                     for (i=0;i<obj.length();i++){
//                                         JSONObject jObject = obj.getJSONObject(i);
//                                         if(jObject!=null) {
//                                             n += jObject.getString("name") + "\n\n";
//                                             name.setText(n);
//                                             d += jObject.getString("description") + "\n\n";
//                                             description.setText(d);
//                                             p += jObject.getString("price") + "\n\n";
//                                             price.setText(p);
//
//
//
//
//                                         }
//                                     }
//
//
//                                 } catch (IOException e) {
//                                     Log.d("Exception", e.toString());
//                                 } catch (JSONException e) {
//                                     Log.d("JsonException", e.toString());
//                                 }
//                             }
//
//                             @Override
//                             public void failure(RetrofitError error) {
//                                 Toast.makeText(ImageListFragment.this, error.toString(), Toast.LENGTH_LONG).show();
//                             }
//                         }
//            );
//        }

    }


}
