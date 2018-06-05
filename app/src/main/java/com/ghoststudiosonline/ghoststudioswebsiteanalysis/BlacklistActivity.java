package com.ghoststudiosonline.ghoststudioswebsiteanalysis;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toolbar;

import java.util.ArrayList;

public class BlacklistActivity extends AppCompatActivity {

    private ArrayList<String> mBl_item = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blacklist);

        initBlacklist();

    }

    private void initBlacklist() {

        mBl_item.add("https://www.Yelp.com");
        mBl_item.add("https://www.Facebook.com");
        mBl_item.add("https://www.Healthgrades.com");
        mBl_item.add("1");
        mBl_item.add("2");
        mBl_item.add("3");
        mBl_item.add("4");
        mBl_item.add("5");
        mBl_item.add("6");
        mBl_item.add("7");
        mBl_item.add("8");
        mBl_item.add("9");
        mBl_item.add("10");

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_blacklist);
        RV_Adapter adapter = new RV_Adapter(this, mBl_item);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    public void bl_remove(View view) {

    }

    public void bl_add(View view) {


    }


}


//    //First We Declare Titles And Icons For Our Navigation Drawer List View
//    //This Icons And Titles Are holded in an Array as you can see
//
//    String TITLES[] = {"Home","Events","Mail","Shop","Travel"};
//    int ICONS[] = {R.drawable.ic_home_black_24dp,R.drawable.ic_event_black_24dp,R.drawable.ic_markunread_mailbox_black_24dp,R.drawable.ic_shop_black_24dp,R.drawable.ic_card_travel_black_24dp};
//
//    //Similarly we Create a String Resource for the name and email in the header view
//    //And we also create a int resource for profile picture in the header view
//
//    String NAME = "Akash Bangad";
//    String EMAIL = "akash.bangad@android4devs.com";
//    int PROFILE = R.drawable.logo;
//
//    private Toolbar toolbar;                              // Declaring the Toolbar Object
//
//    RecyclerView mRecyclerView;                           // Declaring RecyclerView
//    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
//    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
//    DrawerLayout Drawer;                                  // Declaring DrawerLayout
//
//    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_blacklist);
//
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.rv_blacklist); // Assigning the RecyclerView Object to the xml View
//
//        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
//
//        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
//        // And passing the titles,icons,header view name, header view email,
//        // and header view profile picture
//
//        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
//
//        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
//
//        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager
//
//    }
//
//}
