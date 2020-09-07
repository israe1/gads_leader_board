package com.israel.gadstoplearners.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.israel.gadstoplearners.APIUtils.APIUtils;
import com.israel.gadstoplearners.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearnerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_learner, container, false);
        initUi(v);
        return v;
    }

    private void initUi(View v) {
        getLearners();
        RecyclerView recyclerView = v.findViewById(R.id.rvLeader);
    }

    private void getLearners() {
        Call<ResponseBody> call = APIUtils.getLeaderRequests().getLeaders("");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null){

                }else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
