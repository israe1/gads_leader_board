package com.israel.gadstoplearners.requests;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LeaderRequest {
    @GET("api/{category}")
    @Headers({"Content-Type: Application/json", "Accept: application/json"})
    Call<ResponseBody> getLeaders(
            @Path("category")String category
    );
}
