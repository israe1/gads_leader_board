package com.israel.gadstoplearners.utils;

import com.israel.gadstoplearners.requests.LeaderRequest;
import com.israel.gadstoplearners.requests.SubmissionRequest;

public class APIUtils {

    public static LeaderRequest getLeaderRequests(){
        return RetrofitUtils.getLeadersClient().create(LeaderRequest.class);
    }

    public static SubmissionRequest postProject(){
        return RetrofitUtils.getSubmissionClient().create(SubmissionRequest.class);
    }
}
