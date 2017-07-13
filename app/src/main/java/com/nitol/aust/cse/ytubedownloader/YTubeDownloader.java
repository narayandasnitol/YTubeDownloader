package com.nitol.aust.cse.ytubedownloader;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class YTubeDownloader extends AppCompatActivity {

    private TabLayout t1;
    private ViewPager vp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        t1 = (TabLayout) findViewById(R.id.tab1);
        vp1 = (ViewPager) findViewById(R.id.ViewPager1);


        setUpMyViewPager(vp1);
        t1.setupWithViewPager(vp1);



    }

    void setUpMyViewPager(ViewPager vp){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addMyFragment(new Youtube(),"Youtube");
        viewPagerAdapter.addMyFragment(new Download(),"Download");

        vp.setAdapter(viewPagerAdapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> my_list = new ArrayList<Fragment>();
        private final List<String> my_title = new ArrayList<String>();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return my_list.get(position);
        }

        @Override
        public int getCount() {
            return my_list.size();
        }


        void addMyFragment(Fragment f, String title){
            my_list.add(f);
            my_title.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return my_title.get(position);
        }

    }


}
