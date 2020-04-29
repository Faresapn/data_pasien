package com.faresa.daftarpasienrs.connection;



import com.faresa.daftarpasienrs.pojo.GetData;
import com.faresa.daftarpasienrs.pojo.create.CreateResponse;
import com.faresa.daftarpasienrs.pojo.delete.Delete;
import com.faresa.daftarpasienrs.pojo.login.LoginResponse;
import com.faresa.daftarpasienrs.pojo.register.RegisterResponse;
import com.faresa.daftarpasienrs.pojo.update.UpdateResponse;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface Service {


   @GET("alldata")
   Call<GetData> getData();

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> loginRequest(@Field("email") String email,
                                     @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> registerRequest(@Field("name") String nama,
                                           @Field("email") String email,
                                           @Field("password") String password,
                                           @Field("c_password") String c_password);
   @Multipart
   @POST("create")
    Call<CreateResponse>createData(@Part("nama") RequestBody name,
                                   @Part("alamat") RequestBody alamat,
                                   @Part("keluhan") RequestBody keluhan


    );
    @Multipart
    @POST("update/{id}")
    Call<UpdateResponse>update(@Part("nama") RequestBody name,
                               @Part("alamat") RequestBody alamat,
                               @Part("keluhan") RequestBody keluhan,
                               @Path("id") String id

    );
    @DELETE("delete/{id}")
    Call<Delete> delete(@Path("id") int id);




}
