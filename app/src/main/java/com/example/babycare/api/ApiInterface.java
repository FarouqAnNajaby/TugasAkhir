package com.example.babycare.api;

import com.example.babycare.model.AnswerQuestion;
import com.example.babycare.model.Baby;
import com.example.babycare.model.ResponseDiagnosa;
import com.example.babycare.model.ResponseGetBaby;
import com.example.babycare.model.ResponseLogin;
import com.example.babycare.model.ResponseUpdateProfile;
import com.example.babycare.model.UserResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register.php")
    Call<UserResponse> toRegist(
            @Field("email_user") String email_user,
            @Field("name_user") String name_user,
            @Field("password_user") String password_user
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> toLogin(
            @Field("email_user") String email_user,
            @Field("password_user") String password_user
    );

    @FormUrlEncoded
    @POST("reset_password.php")
    Call<UserResponse> toResetPass(
            @Field("email_user") String email_user,
            @Field("password_user") String password_user
    );

    @FormUrlEncoded
    @POST("update_user.php")
    Call<ResponseUpdateProfile> toUpdateUser(
            @Field("name_user") String name_user,
            @Field("email_user") String email_user,
            @Field("password_user") String password_user
    );

    @FormUrlEncoded
    @POST("add_baby.php")
    Call<UserResponse> toAddBaby(
            @Field("name_baby") String name_baby,
            @Field("date_birth_baby") String date_birth_baby,
            @Field("sex_baby") String sex_baby,
            @Field("id_user") String id_user
    );

    @Headers({"Content-Type: application/json", "X-Requested-With: XMLHttpRequest"})
    @POST("store-baby")
    Call<UserResponse> toAddBaby(
            @Body Baby baby
    );

    @FormUrlEncoded
    @POST("retrieve_baby.php")
    Call<ResponseGetBaby> toGetBaby(
            @Field("id_user") String id_user
    );

    @Headers({"Content-Type: application/json", "X-Requested-With: XMLHttpRequest"})
    @POST("medical-temp")
    Call<UserResponse> toDiagnosa(
            @Body AnswerQuestion answerQuestion
    );

    @GET("list-history")
    Call<ResponseDiagnosa> toGeListHistory(
            @QueryMap Map<String, String> options
    );

    @GET("history")
    Call<ResponseDiagnosa> toDetilHistory(
            @QueryMap Map<String, String> options
    );

    @GET("process-method")
    Call<ResponseDiagnosa> toProcessDiagnosa(

    );

    @GET("destroy-baby")
    Call<ResponseDiagnosa> toDeleteBaby(
            @QueryMap Map<String, String> options
    );

    @Headers({"Content-Type: application/json", "X-Requested-With: XMLHttpRequest"})
    @PUT("update-baby")
    Call<UserResponse> toUpdateBaby(
            @QueryMap Map<String, String> options,
            @Body Baby baby
    );
}
