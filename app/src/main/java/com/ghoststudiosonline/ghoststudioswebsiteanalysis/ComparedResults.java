package com.ghoststudiosonline.ghoststudioswebsiteanalysis;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;


public class ComparedResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compared_results);

        //Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Website siteToCheck = intent.getParcelableExtra("siteToCheck");
        Website topOne = intent.getParcelableExtra("topOne");
        Website topTwo = intent.getParcelableExtra("topTwo");
        Website topThree = intent.getParcelableExtra("topThree");

        updateIcons(siteToCheck, topOne, topTwo, topThree);

    }

    public void updateIcons(final Website siteToCheck, final Website topOne, final Website topTwo, final Website topThree) {

        // Test Site Icon Update
        Thread testSiteIcon = new Thread(new Runnable() {
            @Override
            public void run() {

                // update the mobile friendly icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView msMobileFriendlyPassIcon = findViewById(R.id.msMobileFriendlyPassIcon);
                        ImageView msMobileFriendlyFailIcon = findViewById(R.id.msMobileFriendlyFailIcon);
                        ProgressBar msMobileFriendlyLoadingPanel = findViewById(R.id.msMobileFriendlyLoadingPanel);

                        if (siteToCheck.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.PASS) { // friendly
                            msMobileFriendlyPassIcon.setVisibility(View.VISIBLE);
                        } else if (siteToCheck.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.FAIL) { // not friendly
                            msMobileFriendlyFailIcon.setVisibility(View.VISIBLE);
                        } else if (siteToCheck.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.ERROR) { // failed
                            msMobileFriendlyFailIcon.setVisibility(View.VISIBLE);
                        } else if (siteToCheck.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.INIT){ // initialization still set
                            msMobileFriendlyFailIcon.setVisibility(View.VISIBLE);
                        }
                        msMobileFriendlyLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the title icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView msTitlePassIcon = findViewById(R.id.msTitlePassIcon);
                        ImageView msTitleFailIcon = findViewById(R.id.msTitleFailIcon);
                        ProgressBar msTitleLoadingPanel = findViewById(R.id.msTitleLoadingPanel);

                        if (siteToCheck.getTitleCheck()) { // pass
                            msTitlePassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            msTitleFailIcon.setVisibility(View.VISIBLE);
                        }
                        msTitleLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the description icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView msDescPassIcon = findViewById(R.id.msDescPassIcon);
                        ImageView msDescFailIcon = findViewById(R.id.msDescFailIcon);
                        ProgressBar msDescLoadingPanel = findViewById(R.id.msDescLoadingPanel);

                        if (siteToCheck.getDescriptionCheck()) { // pass
                            msDescPassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            msDescFailIcon.setVisibility(View.VISIBLE);
                        }
                        msDescLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the h1 icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView msH1PassIcon = findViewById(R.id.msH1PassIcon);
                        ImageView msH1FailIcon = findViewById(R.id.msH1FailIcon);
                        ProgressBar msH1LoadingPanel = findViewById(R.id.msH1LoadingPanel);

                        if (siteToCheck.getH1Check()) { // pass
                            msH1PassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            msH1FailIcon.setVisibility(View.VISIBLE);
                        }
                        msH1LoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the alt image icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView msAltPassIcon = findViewById(R.id.msAltPassIcon);
                        ImageView msAltFailIcon = findViewById(R.id.msAltFailIcon);
                        ProgressBar msAltLoadingPanel = findViewById(R.id.msAltLoadingPanel);

                        if (siteToCheck.getImgCheck()) {
                            msAltPassIcon.setVisibility(View.VISIBLE);
                        } else {
                            msAltFailIcon.setVisibility(View.VISIBLE);
                        }
                        msAltLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });


            }
        });


        // top one site update icons
        Thread topOneSiteIcon = new Thread(new Runnable() {
            @Override
            public void run() {

                // update the mobile friendly icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // Capture the layout's TextView and set the string as its text
                        ImageView soMobileFriendlyPassIcon = findViewById(R.id.soMobileFriendlyPassIcon);
                        ImageView soMobileFriendlyFailIcon = findViewById(R.id.soMobileFriendlyFailIcon);
                        ProgressBar soMobileFriendlyLoadingPanel = findViewById(R.id.soMobileFriendlyLoadingPanel);

                        if (topOne.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.PASS) { // friendly
                            soMobileFriendlyPassIcon.setVisibility(View.VISIBLE);
                        } else if (topOne.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.FAIL) { // not friendly
                            soMobileFriendlyFailIcon.setVisibility(View.VISIBLE);
                        } else if (topOne.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.ERROR) { // failed
                            soMobileFriendlyFailIcon.setVisibility(View.VISIBLE);
                        } else if (topOne.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.INIT){ // initialization still set
                            soMobileFriendlyFailIcon.setVisibility(View.VISIBLE);
                        }
                        soMobileFriendlyLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the title icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView soTitlePassIcon = findViewById(R.id.soTitlePassIcon);
                        ImageView soTitleFailIcon = findViewById(R.id.soTitleFailIcon);
                        ProgressBar soTitleLoadingPanel = findViewById(R.id.soTitleLoadingPanel);

                        if (topOne.getTitleCheck()) { // pass
                            soTitlePassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            soTitleFailIcon.setVisibility(View.VISIBLE);
                        }
                        soTitleLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the description icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView soDescPassIcon = findViewById(R.id.soDescPassIcon);
                        ImageView soDescFailIcon = findViewById(R.id.soDescFailIcon);
                        ProgressBar soDescLoadingPanel = findViewById(R.id.soDescLoadingPanel);

                        if (topOne.getDescriptionCheck()) { // pass
                            soDescPassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            soDescFailIcon.setVisibility(View.VISIBLE);
                        }
                        soDescLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the h1 icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView soH1PassIcon = findViewById(R.id.soH1PassIcon);
                        ImageView soH1FailIcon = findViewById(R.id.soH1FailIcon);
                        ProgressBar soH1LoadingPanel = findViewById(R.id.soH1LoadingPanel);

                        if (topOne.getH1Check()) { // pass
                            soH1PassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            soH1FailIcon.setVisibility(View.VISIBLE);
                        }
                        soH1LoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the alt image icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView soAltPassIcon = findViewById(R.id.soAltPassIcon);
                        ImageView soAltFailIcon = findViewById(R.id.soAltFailIcon);
                        ProgressBar soAltLoadingPanel = findViewById(R.id.soAltLoadingPanel);

                        if (topOne.getImgCheck()) {
                            soAltPassIcon.setVisibility(View.VISIBLE);
                        } else {
                            soAltFailIcon.setVisibility(View.VISIBLE);
                        }
                        soAltLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });
            }
        });

        // top two site update icons
        Thread topTwoSiteIcon = new Thread(new Runnable() {
            @Override
            public void run() {

                // update the mobile friendly icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView stMobileFriendlyPassIcon = findViewById(R.id.stMobileFriendlyPassIcon);
                        ImageView stMobileFriendlyFailIcon = findViewById(R.id.stMobileFriendlyFailIcon);
                        ProgressBar stMobileFriendlyLoadingPanel = findViewById(R.id.stMobileFriendlyLoadingPanel);

                        if (topTwo.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.PASS) { // friendly
                            stMobileFriendlyPassIcon.setVisibility(View.VISIBLE);
                        } else if (topTwo.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.FAIL) { // not friendly
                            stMobileFriendlyFailIcon.setVisibility(View.VISIBLE);
                        } else if (topTwo.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.ERROR) { // failed
                            stMobileFriendlyFailIcon.setVisibility(View.VISIBLE);
                        } else if (topTwo.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.INIT){ // initialization still set
                            stMobileFriendlyFailIcon.setVisibility(View.VISIBLE);
                        }
                        stMobileFriendlyLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the title icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView stTitlePassIcon = findViewById(R.id.stTitlePassIcon);
                        ImageView stTitleFailIcon = findViewById(R.id.stTitleFailIcon);
                        ProgressBar stTitleLoadingPanel = findViewById(R.id.stTitleLoadingPanel);

                        if (topTwo.getTitleCheck()) { // pass
                            stTitlePassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            stTitleFailIcon.setVisibility(View.VISIBLE);
                        }
                        stTitleLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the description icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView stDescPassIcon = findViewById(R.id.stDescPassIcon);
                        ImageView stDescFailIcon = findViewById(R.id.stDescFailIcon);
                        ProgressBar stDescLoadingPanel = findViewById(R.id.stDescLoadingPanel);

                        if (topTwo.getDescriptionCheck()) { // pass
                            stDescPassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            stDescFailIcon.setVisibility(View.VISIBLE);
                        }
                        stDescLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the h1 icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView stH1PassIcon = findViewById(R.id.stH1PassIcon);
                        ImageView stH1FailIcon = findViewById(R.id.stH1FailIcon);
                        ProgressBar stH1LoadingPanel = findViewById(R.id.stH1LoadingPanel);

                        if (topTwo.getH1Check()) { // pass
                            stH1PassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            stH1FailIcon.setVisibility(View.VISIBLE);
                        }
                        stH1LoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the alt image icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView stAltPassIcon = findViewById(R.id.stAltPassIcon);
                        ImageView stAltFailIcon = findViewById(R.id.stAltFailIcon);
                        ProgressBar stAltLoadingPanel = findViewById(R.id.stAltLoadingPanel);

                        if (topTwo.getImgCheck()) {
                            stAltPassIcon.setVisibility(View.VISIBLE);
                        } else {
                            stAltFailIcon.setVisibility(View.VISIBLE);
                        }
                        stAltLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });
            }
        });

        // top three site update icons
        Thread topThreeSiteIcon = new Thread(new Runnable() {
            @Override
            public void run() {

                // update the mobile friendly icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView steMobileFriendlyPassIcon = findViewById(R.id.steMobileFriendlyPassIcon);
                        ImageView steMobileFriendlyFailIcon = findViewById(R.id.steMobileFriendlyFailIcon);
                        ProgressBar steMobileFriendlyLoadingPanel = findViewById(R.id.steMobileFriendlyLoadingPanel);

                        if (topThree.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.PASS) { // friendly
                            steMobileFriendlyPassIcon.setVisibility(View.VISIBLE);
                        } else if (topThree.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.FAIL) { // not friendly
                            steMobileFriendlyFailIcon.setVisibility(View.VISIBLE);
                        } else if (topThree.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.ERROR) { // failed
                            steMobileFriendlyFailIcon.setVisibility(View.VISIBLE);
                        } else if (topThree.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.INIT){ // initialization still set
                            steMobileFriendlyFailIcon.setVisibility(View.VISIBLE);
                        }
                        steMobileFriendlyLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the title icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView steTitlePassIcon = findViewById(R.id.steTitlePassIcon);
                        ImageView steTitleFailIcon = findViewById(R.id.steTitleFailIcon);
                        ProgressBar steTitleLoadingPanel = findViewById(R.id.steTitleLoadingPanel);

                        if (topThree.getTitleCheck()) { // pass
                            steTitlePassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            steTitleFailIcon.setVisibility(View.VISIBLE);
                        }
                        steTitleLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the description icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView steDescPassIcon = findViewById(R.id.steDescPassIcon);
                        ImageView steDescFailIcon = findViewById(R.id.steDescFailIcon);
                        ProgressBar steDescLoadingPanel = findViewById(R.id.steDescLoadingPanel);

                        if (topThree.getDescriptionCheck()) { // pass
                            steDescPassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            steDescFailIcon.setVisibility(View.VISIBLE);
                        }
                        steDescLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the h1 icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView steH1PassIcon = findViewById(R.id.steH1PassIcon);
                        ImageView steH1FailIcon = findViewById(R.id.steH1FailIcon);
                        ProgressBar steH1LoadingPanel = findViewById(R.id.steH1LoadingPanel);

                        if (topThree.getH1Check()) { // pass
                            steH1PassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            steH1FailIcon.setVisibility(View.VISIBLE);
                        }
                        steH1LoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the alt image icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView steAltPassIcon = findViewById(R.id.steAltPassIcon);
                        ImageView steAltFailIcon = findViewById(R.id.steAltFailIcon);
                        ProgressBar steAltLoadingPanel = findViewById(R.id.steAltLoadingPanel);

                        if (topThree.getImgCheck()) {
                            steAltPassIcon.setVisibility(View.VISIBLE);
                        } else {
                            steAltFailIcon.setVisibility(View.VISIBLE);
                        }
                        steAltLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });
            }
        });

        testSiteIcon.start();
        topOneSiteIcon.start();
        topTwoSiteIcon.start();
        topThreeSiteIcon.start();
    }

}
