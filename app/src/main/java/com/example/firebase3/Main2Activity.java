package com.example.firebase3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * <h1>Main2Activity</h1>
 * This activity will call the fragments so that we would have the
 * 3 activities in one. It handles the positions with the switch
 * statement and which from fragment to the other.
 *
 * @author  Rene Hermosillo
 * @since   04-06-2019
 */

public class Main2Activity extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ViewPager viewPager = findViewById(R.id.ViewPager);
        adapterViewPager = new MyPagerAdaptor(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(0);
    }

    public static class MyPagerAdaptor extends FragmentPagerAdapter{

        public MyPagerAdaptor(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                //case 0:
                //    return CameraFragment.newInstance();
                case 0:
                    //return LEDs
                    return StatusFragment.newInstance();
                case 1:
                    //return camara
                    return LEDFragment.newInstance();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
