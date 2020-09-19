package com.example.hikai.hikaiplayer;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.hikai.R;
import com.google.android.material.tabs.TabLayout;

public class Player extends AppCompatActivity {

    VideoViewMainFragment videoViewMainFragment;


    ViewPagerAdapter viewPagerAdapter;

    Uri videoUrl;
    long seek=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_player);


        //Linking
        final ViewPager viewPager = findViewById(R.id.view_pager_main);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        //Adapter for tab items
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),2);

        videoViewMainFragment = new VideoViewMainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main,videoViewMainFragment).commit();

        //Connect ViewPagerAdapter to Tablayout
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            //vfragment = new VideoFragment();
            seek = bundle.getLong("seek");
            videoUrl = Uri.parse(bundle.getString("path"));
            //Log.i("SAURABH", "videoUrl");
            Toast.makeText(this, "BACK", Toast.LENGTH_SHORT).show();
            //vfragment.playVideo("http://techslides.com/demos/sample-videos/small.mp4");
            //sendData("http://techslides.com/demos/sample-videos/small.mp4");
            //a.SM.sendData("http://techslides.com/demos/sample-videos/small.mp4");

            FragmentManager fragmentManager = getSupportFragmentManager();
            final FragmentTransaction t = fragmentManager.beginTransaction();
            final VideoViewMainFragment f = new VideoViewMainFragment();

            Bundle b2 = new Bundle();
            b2.putLong("seek",seek);
            b2.putString("path",videoUrl.toString());
            f.setArguments(b2);
            t.add(R.id.frame_layout_main,f);
            t.commit();
        }

    }

  @Override
    public void onPause() {
        super.onPause();
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onBackPressed();

   }


}