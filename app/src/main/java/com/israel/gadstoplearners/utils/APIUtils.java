package com.israel.gadstoplearners.utils;

import com.israel.gadstoplearners.requests.LeaderRequest;
import com.israel.gadstoplearners.requests.SubmissionRequest;

public class APIUtils {
    private static final String BASE_URL = "https://gadsapi.herokuapp.com/";
    private static final String SUBMISSION_BASE_URL = "https://docs.google.com/forms/d/e/";

    public static LeaderRequest getLeaderRequests(){
        return RetrofitUtils.getRetrofitClient(BASE_URL).create(LeaderRequest.class);
    }

    public static SubmissionRequest postProject(){
        return RetrofitUtils.getRetrofitClient(SUBMISSION_BASE_URL).create(SubmissionRequest.class);
    }
}
