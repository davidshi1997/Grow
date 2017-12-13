package com.amazingapps.davidmaisy.grow.activities;

import android.support.v4.app.FragmentActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;
import android.widget.TextView;

import com.amazingapps.davidmaisy.grow.R;

import org.w3c.dom.Text;

// TODO: Implement pagetracker and replace placeholder fragments

public class IntroActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public static String PACKAGE_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        PACKAGE_NAME = getApplicationContext().getPackageName();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mViewPager, true);


        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_intro, container, false);

            String str = getString(R.string.gardenDate, getArguments().getInt(ARG_SECTION_NUMBER));
            int currSection = Integer.parseInt(str);

            for (int i = 1; i < 5; i++){
                int resID = 0;
                    if(currSection == i){
                        String tutorialHeaderID = "tv_tutorialHeader" + i;
                        resID = getResources().getIdentifier(tutorialHeaderID, "id", PACKAGE_NAME);
                        TextView textView = (TextView) rootView.findViewById(resID);
                        textView.setVisibility(View.VISIBLE);
                    }


                if(i==1 && currSection == 1) {
                    String tutorialSubHeaderID = "tv_tutorialSubHeader1";
                    resID = getResources().getIdentifier(tutorialSubHeaderID, "id", PACKAGE_NAME);
                    TextView textView1 = (TextView) rootView.findViewById(resID);
                    textView1.setVisibility(View.VISIBLE);
                }

                if(currSection == i) {
                    String tutorialImgID = "tutorialImg" + i;
                    resID = getResources().getIdentifier(tutorialImgID, "id", PACKAGE_NAME);
                    View view = (View) rootView.findViewById(resID);
                    view.setVisibility(View.VISIBLE);
                }

                if(currSection == i) {
                    String tutorialOverlayImgID = "tutorialImgOverlay" + i;
                    if (i != 4) {
                        resID = getResources().getIdentifier(tutorialOverlayImgID, "id", PACKAGE_NAME);
                        View view1 = (View) rootView.findViewById(resID);
                        view1.setVisibility(View.VISIBLE);

                    }
                }

                if(currSection == i) {
                    String tutorialDescriptionID = "tv_tutorialDescription" + i;
                    resID = getResources().getIdentifier(tutorialDescriptionID, "id", PACKAGE_NAME);
                    TextView textView = (TextView) rootView.findViewById(resID);
                    textView.setVisibility(View.VISIBLE);
                }
            }
            /*                 String plantID = "space" + p.getPosition();
                 int resID = getResources().getIdentifier(plantID, "id", PACKAGE_NAME);*/
            /*
            * 1: tv_tutorialHeader1
            * tv_tutorialHeader1.1
            * tutorialImg1
            * tutorialImgOverlay1
            * tv_tutorialDescription1
            *
            * 2: tv_tutorialHeader2
            * tutorialImg2
            * tutorialImgOverlay2
            * tv_tutorialDescription2
            *
            * 3: tv_tutorialHeader3
            * tutorialImg3
            * tutorialImgOverlay3
            * tv_tutorialDescription3
            *
            * 4:tv_tutorialHeader4
            * tutorialImg4
            * tv_tutorialDescription4
            *
            * */
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }
    }
}
