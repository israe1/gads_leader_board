package com.israel.gadstoplearners.ui.submission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.israel.gadstoplearners.R;

public class SubmissionActivity extends AppCompatActivity {
    private TextInputEditText mEditTextFirstName;
    private TextInputEditText mEditTextLastName;
    private TextInputEditText mEditTextEmail;
    private TextInputEditText mEditTextProjectLink;

    private boolean canGo = true;
    private ProgressBar mProgressBar;
    private SubmissionViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        initToolbar();
        initUi();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (canGo){
                    finish();
                }else {
                    Toast.makeText(SubmissionActivity.this,
                            "Please wait until loading ends", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (canGo){
            super.onBackPressed();
        }else {
            Toast.makeText(this, "Please wait until loading ends",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void initUi() {
        mEditTextFirstName = findViewById(R.id.etFirstName);
        mEditTextLastName = findViewById(R.id.etLastName);
        mEditTextEmail = findViewById(R.id.etEmail);
        mEditTextProjectLink = findViewById(R.id.etProjectLink);
        mProgressBar = findViewById(R.id.progressBar);

        final Button buttonSubmit = findViewById(R.id.btnSubmit);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        mViewModel = new ViewModelProvider(this, factory).get(SubmissionViewModel.class);

        mViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s == null)return;
                SubmissionStateDialog dialog = new SubmissionStateDialog(
                        R.drawable.ic_warning_24, s);
                dialog.show(getSupportFragmentManager(), "StateDialog");
            }
        });

        mViewModel.getLoad().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean == null)return;
                canGo = aBoolean;
                mProgressBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                mEditTextFirstName.setEnabled(!aBoolean);
                mEditTextLastName.setEnabled(!aBoolean);
                mEditTextEmail.setEnabled(!aBoolean);
                mEditTextProjectLink.setEnabled(!aBoolean);
                buttonSubmit.setEnabled(!aBoolean);
            }
        });

        mViewModel.getSubmitted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean == null || !aBoolean)return;
                SubmissionStateDialog dialog = new SubmissionStateDialog(
                        R.drawable.ic_check_circle_24, "Submission Successful");
                dialog.show(getSupportFragmentManager(), "StateDialog");
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    submit();
                }
            }
        });
    }

    private void submit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Are you sure you want to submit ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        mViewModel.submit(
                                mEditTextFirstName.getText().toString(),
                                mEditTextLastName.getText().toString(),
                                mEditTextEmail.getText().toString(),
                                mEditTextProjectLink.getText().toString()
                        );
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        if (!isFinishing())builder.create().show();
    }

    private boolean validate(){
        boolean validator = true;
        if (TextUtils.isEmpty(mEditTextFirstName.getText())){
            mEditTextFirstName.setError("Please enter first name here");
            validator = false;
        }else {
            mEditTextFirstName.setError(null);
        }

        if (TextUtils.isEmpty(mEditTextLastName.getText())){
            mEditTextLastName.setError("Please enter last name here");
            validator = false;
        }else {
            mEditTextLastName.setError(null);
        }

        if (TextUtils.isEmpty(mEditTextEmail.getText())){
            mEditTextEmail.setError("Please enter email here");
            validator = false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(mEditTextEmail.getText()
                .toString()).matches()){
            mEditTextEmail.setError("Please enter a valid email");
            validator = false;
        }else {
            mEditTextEmail.setError(null);
        }

        if (TextUtils.isEmpty(mEditTextProjectLink.getText())){
            mEditTextProjectLink.setError("Please enter project link here");
            validator = false;
        }else if(!Patterns.WEB_URL.matcher(mEditTextProjectLink.getText()
                .toString()).matches()){
            mEditTextProjectLink.setError("Please enter a valid url");
            validator = false;
        }else {
            mEditTextProjectLink.setError(null);
        }

        return validator;
    }
}