package com.example.babycare.api;

import com.example.babycare.model.AnswerQuestion;
import com.example.babycare.model.DataDiagnosa;
import com.example.babycare.model.ResponseDiagnosa;
import com.example.babycare.model.ResponseGetBaby;
import com.example.babycare.model.ResponseLogin;
import com.example.babycare.model.ResponseUpdateProfile;
import com.example.babycare.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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

    @GET("process-method")
    Call<ResponseDiagnosa> toProcessDiagnosa(

    );
}
