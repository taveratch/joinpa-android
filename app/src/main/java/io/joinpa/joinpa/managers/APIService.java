package io.joinpa.joinpa.managers;

import io.joinpa.joinpa.models.Element;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.models.EventElement;
import io.joinpa.joinpa.models.Message;
import io.joinpa.joinpa.models.Token;
import io.joinpa.joinpa.models.User;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @POST("signin")
    Call<Token> signin(@Body RequestBody requestBody);

    @POST("signup")
    Call<Token> signup(@Body RequestBody requestBody);

    @POST("verify/{token}")
    Call<User> verify(@Path("token") String token , @Body RequestBody requestBody);

    @POST("friend/search")
    Call<Element> searchFriend(@Header("Authorization") String value , @Body RequestBody requestBody);

    @POST("friend/request")
    Call<Message> sendFriendRequest(@Header("Authorization") String value , @Body RequestBody requestBody);

    @POST("friend/accept-request")
    Call<Message> acceptFriendRequest(@Header("Authorization") String value , @Body RequestBody requestBody);

    @GET("friend/friends")
    Call<Element> getFriendList(@Header("Authorization") String value);

    @POST("friend/unfriend")
    Call<Message> unfriend(@Header("Authorization") String value , @Body RequestBody requestBody);

    @GET("event/publicEvent")
    Call<EventElement> getPublicEvents(@Header("Authorization") String value);

    @GET("event/joinedEvent")
    Call<EventElement> getJoinedEvents(@Header("Authorization") String value);

    @GET("event/invitedEvent")
    Call<EventElement> getInvitedEvents(@Header("Authorization") String value);

    @GET("event/myEvent")
    Call<EventElement> getMyEvents(@Header("Authorization") String value);

    @POST("event/join")
    Call<Message> joinEvent(@Header("Authorization") String value, @Body RequestBody requestBody);

    @POST("event/create")
    Call<Message> createEvent(@Header("Authorization") String value, @Body Event event);

    @POST("event/decline")
    Call<Message> cancelEvent(@Header("Authorization") String value, @Body RequestBody requestBody);

    @POST("event/remove")
    Call<Message> removeEvent(@Header("Authorization") String value, @Body RequestBody requestBody);

    @POST("event/edit")
    Call<Message> editEvent(@Header("Authorization") String value, @Body RequestBody requestBody);
    
    @POST("event/invite")
    Call<Message> inviteFriend(@Header("Authorization") String value, @Body RequestBody requestBody);
}
