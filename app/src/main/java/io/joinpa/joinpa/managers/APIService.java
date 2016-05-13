package io.joinpa.joinpa.managers;

import io.joinpa.joinpa.models.Token;
import io.joinpa.joinpa.models.User;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("signin")
    Call<Token> signin(@Body User user);

    @POST("signup")
    Call<Token> signup(@Body User user);

    @POST("verify")
    Call<User> verify(@Body RequestBody requestBody);
}
