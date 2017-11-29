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
                Callback<Response> callback);
    }
    public interface comment {
        @FormUrlEncoded
        @POST("/comments")
        void usercomments(
                //@Field("name") String name,
                @Field("comment") String comment,
               // @Field("rating") String rating,
                Callback<Response> callback);
    }

    public interface read {
        @GET("/comments")
        void readData(Callback<Response> callback);
    }
}
