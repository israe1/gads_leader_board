package com.israel.gadstoplearners.APIUtils;

import com.israel.gadstoplearners.requests.LeaderRequest;
import com.israel.gadstoplearners.utils.RetrofitUtils;

public class APIUtils {
    public static LeaderRequest getLeaderRequests(){
        return RetrofitUtils.getRetrofitClient().create(LeaderRequest.class);
    }
}
