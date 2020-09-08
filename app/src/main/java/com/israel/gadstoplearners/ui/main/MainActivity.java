package com.israel.gadstoplearners.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.israel.gadstoplearners.R;
import com.israel.gadstoplearners.ui.submission.SubmissionActivity;

public class MainActivity extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        Button buttonSubmit = findViewById(R.id.btnSubmit);
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tab);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);

        pagerAdapter.addFragment(new LearnerFragment(), "Learning Leaders");
        pagerAdapter.addFragment(new SkillIQFragment(), "Skill IQ Leaders");
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SubmissionActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        } else {
            backToast = Toast.makeText(getApplicationContext(), "Click twice to exit", Toast.LENGTH_LONG);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}