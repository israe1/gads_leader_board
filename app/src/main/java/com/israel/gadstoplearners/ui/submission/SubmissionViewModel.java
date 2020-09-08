package com.israel.gadstoplearners.ui.submission;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.israel.gadstoplearners.utils.APIUtils;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionViewModel extends ViewModel {
    private MutableLiveData<String> mError;
    private MutableLiveData<Boolean> mLoad;
    private MutableLiveData<Boolean> mSubmitted;

    public SubmissionViewModel() {
        mError = new MutableLiveData<>();
        mLoad = new MutableLiveData<>();
        mSubmitted = new MutableLiveData<>();
    }

    public MutableLiveData<String> getError() {
        return mError;
    }

    public MutableLiveData<Boolean> getLoad() {
        return mLoad;
    }

    public MutableLiveData<Boolean> getSubmitted() {
        return mSubmitted;
    }

    public void submit(String firstName, String lastName, String email, String link){
        mLoad.setValue(true);
        Call<ResponseBody> call = APIUtils.postProject().submitProject(
                firstName,
                lastName,
                email,
                link
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mLoad.setValue(false);
                if (response.isSuccessful() && response.body() != null){
                    mSubmitted.setValue(true);
                }else {
                    mSubmitted.setValue(false);
                    mError.setValue("Request error. Please try later");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mLoad.setValue(false);
                mSubmitted.setValue(false);
                mError.setValue("Please check internet connection");
            }
        });
    }
}
