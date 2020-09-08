package com.israel.gadstoplearners.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public static final int CONNECTION_TIMEOUT = 20;
    public static final int READ_TIMEOUT = 30;
    public static final int WRITE_TIMEOUT = 30;
    private static final String BASE_URL = "https://gadsapi.herokuapp.com/";
    private static final String SUBMISSION_BASE_URL = "https://docs.google.com/forms/d/e/";
    private static Retrofit mRetrofitLeader;
    private static Retrofit mRetrofitSubmission;

    public static Retrofit getLeadersClient(){
        if (mRetrofitLeader == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .addNetworkInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request.Builder builder = chain.request()
                                    .newBuilder()
                                    .addHeader("Connection", "close");
                            return chain.proceed(builder.build());
                        }
                    }).build();
            mRetrofitLeader = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return mRetrofitLeader;
    }

    public static Retrofit getSubmissionClient(){
        if (mRetrofitSubmission == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .addNetworkInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request.Builder builder = chain.request()
                                    .newBuilder()
                                    .addHeader("Connection", "close");
                            return chain.proceed(builder.build());
                        }
                    }).build();
            mRetrofitSubmission = new Retrofit.Builder()
                    .baseUrl(SUBMISSION_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return mRetrofitSubmission;
    }
}
