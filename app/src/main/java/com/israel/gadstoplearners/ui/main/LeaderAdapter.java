package com.israel.gadstoplearners.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.israel.gadstoplearners.R;
import com.israel.gadstoplearners.models.Leader;

import java.util.ArrayList;
import java.util.List;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.LearnerViewHolder> {
    private List<Leader> mLearners = new ArrayList<>();

    public LeaderAdapter(List<Leader> learners) {
        mLearners = learners;
    }

    @NonNull
    @Override
    public LearnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learner, parent, false);
        return new LearnerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerViewHolder holder, int position) {
        Leader learner = mLearners.get(position);
        holder.bind(learner);
    }

    @Override
    public int getItemCount() {
        return mLearners.size();
    }

    public static class LearnerViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageViewBadge;
        TextView mTextViewName;
        TextView mTextViewScore;
        public LearnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewBadge = itemView.findViewById(R.id.ivLearnerBadge);
            mTextViewName = itemView.findViewById(R.id.tvLearnerName);
            mTextViewScore = itemView.findViewById(R.id.tvLearnerScore);
        }

        public void bind(Leader learner) {
            mTextViewName.setText(learner.getName());
            if (learner.getHours() > 0){
                mTextViewScore.setText(
                        String.valueOf(learner.getHours())
                                .concat(" learning hours, ")
                                .concat(learner.getCountry())
                );
            }else if(learner.getScore() > 0){
                mTextViewScore.setText(
                        String.valueOf(learner.getScore())
                                .concat(" skill IQ Score, ")
                                .concat(learner.getCountry())
                );
            }

            Glide.with(itemView.getContext())
                    .load(learner.getBadgeUrl())
                    .into(mImageViewBadge);
        }
    }
}
