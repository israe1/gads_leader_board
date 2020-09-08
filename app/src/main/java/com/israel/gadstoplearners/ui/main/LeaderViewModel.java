package com.israel.gadstoplearners.ui.main;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.israel.gadstoplearners.models.Leader;
import com.israel.gadstoplearners.utils.APIUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderViewModel extends ViewModel {
    private MutableLiveData<String> mError;
    private MutableLiveData<Boolean> mLoad;
    private MutableLiveData<List<Leader>> mLearners;

    public LeaderViewModel() {
        mError = new MutableLiveData<>();
        mLoad = new MutableLiveData<>();
        mLearners = new MutableLiveData<>();
    }

    public MutableLiveData<String> getError() {
        return mError;
    }

    public MutableLiveData<Boolean> getLoad() {
        return mLoad;
    }

    public MutableLiveData<List<Leader>> getLearners() {
        return mLearners;
    }

    public void getTopLearners(String category){
        mLoad.setValue(true);
        Call<ResponseBody> call = APIUtils.getLeaderRequests().getLeaders(category);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mLoad.setValue(false);
                if (response.isSuccessful() && response.body() != null){
                    try {
                        String output = response.body().string();
                        List<Leader> learners = new Gson().fromJson(new JsonParser().parse(output),
                                new TypeToken<List<Leader>>(){}.getType());
                        mLearners.setValue(learners);
                    } catch (IOException e) {
                        e.printStackTrace();
                        mError.setValue("Sorry! An unexpected error occurred");
                    }
                }else {
                    mError.setValue("Request error. Please try later");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mLoad.setValue(false);
                mError.setValue("Please check internet connection");
            }
        });
    }
}
