package com.amazingapps.davidmaisy.grow.activities;

import android.graphics.drawable.Drawable;
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
import com.amazingapps.davidmaisy.grow.plant.Plant;

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
    public static String PACKAGE_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);

        /*DUMMY DATA WHILE WE DONT HAVE ANY DATA COMING FROM ANYWHERE ELSE!*/
        gardens = new ArrayList<Garden>();
        Garden garden1 = new Garden(1, 2017);
        gardens.add(garden1);
        Garden garden2 = new Garden(2, 2017);
        gardens.add(garden2);
        Plant plant1 = new Plant("flower", 5, "alive");
        plant1.putPosition(1);
        Plant plant2 = new Plant("flower", 4, "dead");
        plant2.putPosition(2);
        garden1.addPlant(plant1);
        garden1.addPlant(plant2);

        /**********************************/
        PACKAGE_NAME = getApplicationContext().getPackageName();

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
                TextView plantDate = (TextView)getView().findViewById(R.id.tv_plantDate);
                plantDate.setText("Plant");
            }
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_garden, container, false);

            //SETTING THE GARDEN'S MONTH/YEAR AND DISPLAYING
            TextView textView = (TextView) rootView.findViewById(R.id.tv_date);
            String str = getString(R.string.gardenDate, getArguments().getInt(ARG_SECTION_NUMBER));
            final Garden currGarden = gardens.get(Integer.parseInt(str)-1);
            textView.setText(currGarden.getTitle());

            //DISPLAYING THE GARDEN'S PLANTS AND POPULATING IT!
            for(Plant p : currGarden.getPlants()){
                 String plantID = "space" + p.getPosition();
                 int resID = getResources().getIdentifier(plantID, "id", PACKAGE_NAME);
                 ImageButton currPlant = (ImageButton) rootView.findViewById(resID);
                 String plantImg = "ic_" + p.getType() + "_" + p.getPhase() + "_" + p.getState();
                 int plantImgID = getResources().getIdentifier(plantImg, "drawable", PACKAGE_NAME);
                 Drawable drawable = getResources().getDrawable(plantImgID);
                 currPlant.setBackground(drawable);
            }

            //SETTING LISTENERS ON EACH PLANT IMAGEBUTTON IN ORDER TO ENABLE
            //SELECTING PLANT ON THE GARDEN TO DISPLAY ITS IMAGE!
            buttons= new ArrayList<ImageButton>();
            PlantListener l = new PlantListener();

            for(int i=1; i<37; i++) {
                String buttonID = "space" + i;
                int resID = getResources().getIdentifier(buttonID, "id", PACKAGE_NAME);
                buttons.add((ImageButton) rootView.findViewById(resID));
                buttons.get(i-1).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImageButton currButton = (ImageButton) view;
                        String resourceName = getResources().getResourceEntryName(view.getId());
                        int plantPosition = Integer.parseInt(resourceName.substring(resourceName.length()-1));
                        System.out.println(plantPosition);
                        for (Plant p: currGarden.getPlants())
                            if(p.getPosition()==plantPosition){
                                TextView plantDate = (TextView) getView().findViewById(R.id.tv_plantDate);
                                plantDate.setText(p.getDate());
                            }




                    }
                });


            }

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
