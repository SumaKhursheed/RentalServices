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
import java.util.ArrayList;

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
    public static ImageListFragment iActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (CustomerDashboard) getActivity();
        iActivity = (ImageListFragment.this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.layout_recylerview_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        String[] items=null;
        ArrayList<String> productlistName= null;
        ArrayList<String> productlistDesc=null;
        ArrayList<String> productlistPrice=null;
//Solar list
        ArrayList<String> sproductlistName= null;
        ArrayList<String> sproductlistDesc=null;
        ArrayList<String> sproductlistPrice=null;
//HVAC list
        ArrayList<String> hproductlistName= null;
        ArrayList<String> hproductlistDesc=null;
        ArrayList<String> hproductlistPrice=null;
//Smart list
        ArrayList<String> tproductlistName= null;
        ArrayList<String> tproductlistDesc=null;
        ArrayList<String> tproductlistPrice=null;
//Window
        ArrayList<String> wproductlistName= null;
        ArrayList<String> wproductlistDesc=null;
        ArrayList<String> wproductlistPrice=null;

        ImageUrlUtils imageUrlUtils = new ImageUrlUtils();

        if (ImageListFragment.this.getArguments().getInt("type") == 1){
            items =ImageUrlUtils.getSolarUrls();
            sproductlistName =imageUrlUtils.getsProductListName();
            sproductlistDesc =imageUrlUtils.getsProductListDesc();
            sproductlistPrice =imageUrlUtils.getsProductListPrice();
        }else if (ImageListFragment.this.getArguments().getInt("type") == 2){
            items =ImageUrlUtils.getHvacUrls();
            hproductlistName =imageUrlUtils.gethProductListName();
            hproductlistDesc =imageUrlUtils.gethProductListDesc();
            hproductlistPrice =imageUrlUtils.gethProductListPrice();
        }else if (ImageListFragment.this.getArguments().getInt("type") == 3){
            items =ImageUrlUtils.getSmartUrls();
            tproductlistName =imageUrlUtils.gettProductListName();
            tproductlistDesc =imageUrlUtils.gettProductListDesc();
            tproductlistPrice =imageUrlUtils.gettProductListPrice();
        }else if (ImageListFragment.this.getArguments().getInt("type") == 4){
            items =ImageUrlUtils.getWindowUrls();
            wproductlistName =imageUrlUtils.getwProductListName();
            wproductlistDesc =imageUrlUtils.getwProductListDesc();
            wproductlistPrice =imageUrlUtils.getwProductListPrice();
        }else {
            items = ImageUrlUtils.getImageUrls();
//            sproductlistName =imageUrlUtils.getsProductListName();
//            sproductlistDesc =imageUrlUtils.getsProductListDesc();
//            sproductlistPrice =imageUrlUtils.getsProductListPrice();
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(recyclerView, items, sproductlistName, sproductlistDesc, sproductlistPrice, hproductlistName, hproductlistDesc, hproductlistPrice, tproductlistName, tproductlistDesc, tproductlistPrice, wproductlistName, wproductlistDesc, wproductlistPrice));

    }

    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private String[] mValues;
        private ArrayList<String> msProductlistName, msProductlistDesc, msProductlistPrice, mhProductlistName, mhProductlistDesc, mhProductlistPrice, mtProductlistName, mtProductlistDesc, mtProductlistPrice, mwProductlistName, mwProductlistDesc, mwProductlistPrice;
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

        public SimpleStringRecyclerViewAdapter(RecyclerView recyclerView, String[] items, ArrayList<String> sproductlistName, ArrayList<String> sproductlistDesc, ArrayList<String> sproductlistPrice, ArrayList<String> hproductlistName, ArrayList<String> hproductlistDesc, ArrayList<String> hproductlistPrice, ArrayList<String> tproductlistName, ArrayList<String> tproductlistDesc, ArrayList<String> tproductlistPrice, ArrayList<String> wproductlistName, ArrayList<String> wproductlistDesc, ArrayList<String> wproductlistPrice) {
            mValues = items;
            //Solar list
            msProductlistName = sproductlistName;
            msProductlistDesc = sproductlistDesc;
            msProductlistPrice = sproductlistPrice;
            //HVAC list
            mhProductlistName = hproductlistName;
            mhProductlistDesc = hproductlistDesc;
            mhProductlistPrice = hproductlistPrice;
            //Smart list
            mtProductlistName = tproductlistName;
            mtProductlistDesc = tproductlistDesc;
            mtProductlistPrice = tproductlistPrice;
            //Window list
            mwProductlistName = wproductlistName;
            mwProductlistDesc = wproductlistDesc;
            mwProductlistPrice = wproductlistPrice;
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

//            if(msProductlistName.size()!= 0) {
//            holder.name.setText(msProductlistName.get(position));
//            holder.description.setText(msProductlistDesc.get(position));
//            holder.price.setText(msProductlistPrice.get(position));
//                holder.mImageView.setImageURI(uri);
//            }
//            else{
//                Log.d("Continue","Continuing");
//                holder.name.setText("Item Name");
//                holder.description.setText("Item Description");
//                holder.price.setText("Item Price");
//            }



           // if (iActivity.getArguments().getInt("type") == 2){

                if(msProductlistName!=null && msProductlistName.size()!= 0) {
                    holder.name.setText(msProductlistName.get(position));
                    holder.description.setText(msProductlistDesc.get(position));
                    holder.price.setText(msProductlistPrice.get(position));
                    holder.mImageView.setImageURI(uri);
                }
//                else{
//                    Log.d("Continue","Continuing");
////                    holder.name.setText("Item Name");
////                    holder.description.setText("Item Description");
////                    holder.price.setText("Item Price");
//                }


           // }else if (iActivity.getArguments().getInt("type") == 3){

                if(mhProductlistName!=null && mhProductlistName.size()!= 0) {
                    holder.name.setText(mhProductlistName.get(position));
                    holder.description.setText(mhProductlistDesc.get(position));
                    holder.price.setText(mhProductlistPrice.get(position));
                    holder.mImageView.setImageURI(uri);
                }
//                else{
//                    Log.d("Continue","Continuing");
////                    holder.name.setText("Item Name");
////                    holder.description.setText("Item Description");
////                    holder.price.setText("Item Price");
//                }
           // }else if (iActivity.getArguments().getInt("type") == 4){

                if(mtProductlistName!=null && mtProductlistName.size()!= 0) {
                    holder.name.setText(mtProductlistName.get(position));
                    holder.description.setText(mtProductlistDesc.get(position));
                    holder.price.setText(mtProductlistPrice.get(position));
                    holder.mImageView.setImageURI(uri);
                }
//                else{
//                    Log.d("Continue","Continuing");
////                    holder.name.setText("Item Name");
////                    holder.description.setText("Item Description");
////                    holder.price.setText("Item Price");
//                }
          //  }else if (iActivity.getArguments().getInt("type") == 5){

                if(mwProductlistName!=null && mwProductlistName.size()!= 0) {
                    holder.name.setText(mwProductlistName.get(position));
                    holder.description.setText(mwProductlistDesc.get(position));
                    holder.price.setText(mwProductlistPrice.get(position));
                    holder.mImageView.setImageURI(uri);
                }
//                else{
//                    Log.d("Continue","Continuing");
////                    holder.name.setText("Item Name");
////                    holder.description.setText("Item Description");
////                    holder.price.setText("Item Price");
//                }
          //  }

            //getResults();

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
//                                     String[] n = new String[obj.length()];
//                                     String[] d = new String[obj.length()];
//                                     String[] p = new String[obj.length()];
////                                     holder.n[i] = "";
////                                     holder.d[i] = "";
////                                     holder.p[i] = "";
//                                     for (i=0;i<obj.length();i++){
//                                         JSONObject jObject = obj.getJSONObject(i);
//                                         if(jObject!=null) {
//                                             ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
//                                             n[i] = jObject.getString("productname");
//                                             imageUrlUtils.addProductListName(n[i]);
//                                             //holder.name.setText(n[i]);
//                                             d[i] = jObject.getString("productdescription");
//                                             holder.description.setText(d[i]);
//                                             p[i] = jObject.getString("price");
//                                             holder.price.setText(p[i]);
//                                             // i=2;
//                                         }
//
//
//
//                                     }
////                                     holder.name.setText(holder.n[i]);
////                                     holder.description.setText(holder.d[i]);
////                                     holder.price.setText(holder.p[i]);
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
//                                 Log.d("tag", "failure");
//                             }
//                         }
//            );



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
