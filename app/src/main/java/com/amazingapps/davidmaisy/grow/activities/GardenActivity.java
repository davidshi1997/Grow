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
import android.widget.ImageButton;

import android.widget.TextView;

import com.amazingapps.davidmaisy.grow.R;
import com.amazingapps.davidmaisy.grow.plant.Garden;
import java.util.ArrayList;
import android.view.View.OnClickListener;


// TODO: Implement pagetracker and replace placeholder fragments

public class GardenActivity extends FragmentActivity {

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
    static private ArrayList<Garden> gardens;
    static private ArrayList<ImageButton> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);
        gardens = new ArrayList<Garden>();
        Garden garden1 = new Garden(1, 2017);
        gardens.add(garden1);
        Garden garden2 = new Garden(2, 2017);
        gardens.add(garden2);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.

        mViewPager = (ViewPager) findViewById(R.id.container);
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

        class PlantListener implements OnClickListener{
            public void onClick (View v){
               System.out.println("HELLO");
            }
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_garden, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.tv_date);
            String str = getString(R.string.gardenDate, getArguments().getInt(ARG_SECTION_NUMBER));
            Garden currGarden = gardens.get(Integer.parseInt(str)-1);
            textView.setText(currGarden.getTitle());

            
            buttons= new ArrayList<ImageButton>();
            PlantListener l = new PlantListener();
            
            for(int i=1; i<37; i++) {
                String buttonID = "space" + i;
                int resID = getResources().getIdentifier(buttonID, "id", getActivity().getPackageName());
                buttons.add((ImageButton) getActivity().findViewById(resID));
                buttons.get(i-1).setOnClickListener(l);
//                buttons.get(i-1).setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                         TextView plantDate = (TextView)getView().findViewById(R.id.tv_plantDate);
////
////                         plantDate.setText("Plant");
//                           System.out.println("hello");
//
//                    }
//                });

            }


            
//
//            ImageButton space1 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space2 = (ImageButton)rootView.findViewById(R.id.space2);
//            ImageButton space3 = (ImageButton)rootView.findViewById(R.id.space3);
//            ImageButton space4 = (ImageButton)rootView.findViewById(R.id.space4);
//            ImageButton space5 = (ImageButton)rootView.findViewById(R.id.space5);
//            ImageButton space6 = (ImageButton)rootView.findViewById(R.id.space6);
//            ImageButton space7 = (ImageButton)rootView.findViewById(R.id.space7);
//            ImageButton space8 = (ImageButton)rootView.findViewById(R.id.space8);
//            ImageButton space9 = (ImageButton)rootView.findViewById(R.id.space9);
//            ImageButton space10 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space11 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space12 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space13 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space14 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space15 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space16 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space17 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space18 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space19 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space20 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space21 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space22 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space23 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space24 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space25 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space26 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space27 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space28 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space29 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space30 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space31 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space32 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space33 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space34 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space35 = (ImageButton)rootView.findViewById(R.id.space1);
//            ImageButton space36 = (ImageButton)rootView.findViewById(R.id.space1);
//
//
//            plantDate.setOnClickListener(new OnClickListener(){
//                @Override
//                plantDate.setText("hello");
//
//
//            });
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
            return gardens.size();
        }
    }
    public void showPlantDetails(View view) {

   
    }

}
