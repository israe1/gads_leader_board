package com.israel.gadstoplearners.ui.submission;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;

import com.israel.gadstoplearners.R;

public class SubmissionStateDialog extends AppCompatDialogFragment {
    private int image;
    private String text;

    public SubmissionStateDialog(int image, String text) {
        this.image = image;
        this.text = text;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_submission_state, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(v);

        initUi(v);
        return builder.create();
    }

    private void initUi(View v) {
        ImageView imageView = v.findViewById(R.id.ivState);
        TextView textView = v.findViewById(R.id.tvStateText);
        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), image));
        textView.setText(text);
    }
}
