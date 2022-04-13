package com.example.expressefood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.expressefood.R;
import com.example.expressefood.adapter.IntroViewAdapter;
import com.example.expressefood.model.ScreenItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager vpScreen;
    IntroViewAdapter introViewAdapter;
    List<ScreenItem> screenItemList;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        //fill list screen
        screenItemList=new ArrayList<>();
        screenItemList.add(new ScreenItem("Order whatever you want to eat ","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum a elementum sit eu quam vulputate ultricies a.",R.drawable.intro_1));
        screenItemList.add(new ScreenItem("Choose a better delivery location","Lorem ipsum dolor sit amet, consectetur adipis a elementum sit eu quam vulputate ultricies a.",R.drawable.intro_2));
        screenItemList.add(new ScreenItem("Get your order as fast as you think","Lorem ipsum dolor sit amet, consectetur adipis a elementum sit eu quam vulputate ultricies a.",R.drawable.intro_3));
        screenItemList.add(new ScreenItem("Let’s Order your favourite food","Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sed mi velit semper tortor orci. Quam.",R.drawable.intro_4));
        //setup viewpaper
        vpScreen=findViewById(R.id.vp_ex);
        introViewAdapter=new IntroViewAdapter(this,screenItemList);
        vpScreen.setAdapter(introViewAdapter);

        tabLayout=findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicator(0);
        tabLayout.setupWithViewPager(vpScreen);
    }
}