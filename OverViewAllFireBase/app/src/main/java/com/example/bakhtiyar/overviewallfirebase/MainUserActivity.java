package com.example.bakhtiyar.overviewallfirebase;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);


        try {

            Fragmentpager adp = new Fragmentpager(getSupportFragmentManager());
            ViewPager pager = (ViewPager) findViewById(R.id.pager);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            // pager.setOffscreenPageLimit(0);

            tabLayout.setupWithViewPager(pager);
            pager.setAdapter(adp);


        }catch (Exception e){
            Toast.makeText(MainUserActivity.this, ""+e, Toast.LENGTH_SHORT).show();

        }

    }
}
