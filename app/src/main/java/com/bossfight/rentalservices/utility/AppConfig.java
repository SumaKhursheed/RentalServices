package com.bossfight.rentalservices.utility;

/**
 * Created by Suma on 10/24/2017.
 */

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public class AppConfig {
    public interface signin {
        @FormUrlEncoded
        @POST("/signin")
        void login(
                @Field("email") String email,
                @Field("password") String password,
                Callback<Response> callback);
    }

    public interface signup {
        @FormUrlEncoded
        @POST("/signup")
        void buyproduct(
                @Field("firstname") String firstname,
                @Field("lastname") String lastname,
                @Field("address") String address,
                @Field("email") String email,
                @Field("password") String password,
                @Field("contact") String contact,
                @Field("usertype") String usertype,
                Callback<Response> callback);
    }

    public interface addprod {
        @FormUrlEncoded
        @POST("/products")
        void addproduct(
                @Field("productname") String productname,
                @Field("productdescription") String productdescription,
                @Field("price") String price,
                @Field("quantity") String quantity,
                @Field("category") String category,
                Callback<Response> callback);
    }

    public interface feedback {
        @FormUrlEncoded
        @POST("/feedback")
        void usercomments(
                //@Field("name") String name,
                @Field("description") String description,
                @Field("email") String emailaddress,
               // @Field("rating") String rating,
                Callback<Response> callback);
    }
    public interface comment {
        @FormUrlEncoded
        @POST("/comments")
        void usercomments(
                @Field("name") String name,
                @Field("comment") String comment,
                @Field("rating") String rating,
                Callback<Response> callback);
    }

    public interface payment {
        @FormUrlEncoded
        @POST("/payments")
        void pay(
                @Field("card") String card,
//                @Field("cvc") String cvc,
//                @Field("amount") String amount,
//                @Field("email") String email,
                Callback<Response> callback);
    }

    public interface pay {
        @FormUrlEncoded
        @POST("/orders")
        void pay(
                @Field("cardname") String cardname,
                @Field("cardnumber") String cardnumber,
                @Field("productname") String productname,
                @Field("price") String price,
                Callback<Response> callback);
    }

    public interface read {
        @GET("/comments")
        void readData(Callback<Response> callback);
    }

    public interface readorders {
        @GET("/orders")
        void readData(Callback<Response> callback);
    }

    public interface readproducts {
        @GET("/products")
        void readData(Callback<Response> callback);
    }

    public interface readsignin {
        @GET("/user")
        void logindata(Callback<Response> callback);
    }
}
