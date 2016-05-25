package io.joinpa.joinpa.managers;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static HttpManager httpManager = null;

    private HttpManager() {}

    public static HttpManager getInstance() {
        if (httpManager == null) httpManager = new HttpManager();
        return httpManager;
    }

    public APIService getAPIService(Class<APIService> apiServiceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(apiServiceClass);
    }

    public RequestBody createRequestBody (Map<String, String> map) {
        return RequestBody.create(MediaType.parse("application/json"), new JSONObject(map).toString());
    }
}
