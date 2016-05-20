package io.joinpa.joinpa.managers;

import io.joinpa.joinpa.models.Token;
import io.joinpa.joinpa.models.User;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @POST("signin")
    Call<Token> signin(@Body RequestBody requestBody);

    @POST("signup")
    Call<Token> signup(@Body RequestBody requestBody);

    @POST("verify/{token}")
    Call<User> verify(@Path("token") String token , @Body RequestBody requestBody);
}
