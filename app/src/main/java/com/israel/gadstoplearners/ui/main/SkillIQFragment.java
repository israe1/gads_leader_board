package com.israel.gadstoplearners.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.israel.gadstoplearners.R;
import com.israel.gadstoplearners.models.Leader;

import java.util.Collections;
import java.util.List;

public class SkillIQFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_learner, container, false);
        initUi(v);
        return v;
    }

    private void initUi(View v) {
        final RecyclerView recyclerView = v.findViewById(R.id.rvLeader);
        final ProgressBar progressBar = v.findViewById(R.id.progressBar);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        LeaderViewModel viewModel = new ViewModelProvider(this, factory).get(LeaderViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel.getTopLearners("skilliq");
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s == null)return;
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getLoad().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean == null)return;
                progressBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                recyclerView.setVisibility(aBoolean ? View.GONE : View.VISIBLE);
            }
        });

        viewModel.getLearners().observe(this, new Observer<List<Leader>>() {
            @Override
            public void onChanged(List<Leader> leaders) {
                if(leaders == null)return;
                Collections.sort(leaders);
                LeaderAdapter adapter = new LeaderAdapter(leaders);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
