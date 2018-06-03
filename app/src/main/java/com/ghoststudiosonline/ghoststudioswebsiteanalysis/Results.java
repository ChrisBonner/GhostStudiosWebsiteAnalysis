package com.ghoststudiosonline.ghoststudioswebsiteanalysis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Results extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        new Thread(new Runnable() {
            @Override
            public void run() {

                Intent intent = getIntent();
                Website siteToCheck = intent.getParcelableExtra("siteToCheck");

                if(siteToCheck.isCompared()) {

                    Website topOne = intent.getParcelableExtra("topOne");
                    Website topTwo = intent.getParcelableExtra("topTwo");
                    Website topThree = intent.getParcelableExtra("topThree");
                    Website site = checkSites(siteToCheck, topOne, topTwo, topThree);

                    checkSite(site);
                } else {
                    checkSite(siteToCheck);
                }

            }
        }).start();
    }



    // check what iteration we are on
    public Website checkSites(Website siteToCheck, Website topOne, Website topTwo, Website topThree) {

        // check which site we are evaluating
        Website site = new Website();

        if((!siteToCheck.getMobileFriendlyFinish() && !siteToCheck.getWebsiteEvalFinish())      // entered site not processed
                && (!topOne.getMobileFriendlyFinish() && !topOne.getWebsiteEvalFinish())        // first site not processed
                && (!topTwo.getMobileFriendlyFinish() && !topTwo.getWebsiteEvalFinish())        // second site not processed
                && (!topThree.getMobileFriendlyFinish() && !topThree.getWebsiteEvalFinish())) { // third site not processed

            site = siteToCheck;
        } else if((siteToCheck.getMobileFriendlyFinish() && siteToCheck.getWebsiteEvalFinish()) // entered site HAS been processed
                && (!topOne.getMobileFriendlyFinish() && !topOne.getWebsiteEvalFinish())        // first site not processed
                && (!topTwo.getMobileFriendlyFinish() && !topTwo.getWebsiteEvalFinish())        // second site not processed
                && (!topThree.getMobileFriendlyFinish() && !topThree.getWebsiteEvalFinish())) { // third site not processed

            site = topOne;
        } else if((siteToCheck.getMobileFriendlyFinish() && siteToCheck.getWebsiteEvalFinish()) // entered site HAS been processed
                && (topOne.getMobileFriendlyFinish() && topOne.getWebsiteEvalFinish())        // first site HAS been processed
                && (!topTwo.getMobileFriendlyFinish() && !topTwo.getWebsiteEvalFinish())        // second site not processed
                && (!topThree.getMobileFriendlyFinish() && !topThree.getWebsiteEvalFinish())) { // third site not processed

            site = topTwo;
        } else if((siteToCheck.getMobileFriendlyFinish() && siteToCheck.getWebsiteEvalFinish()) // entered site HAS been processed
                && (topOne.getMobileFriendlyFinish() && topOne.getWebsiteEvalFinish())          // first site HAS been processed
                && (topTwo.getMobileFriendlyFinish() && topTwo.getWebsiteEvalFinish())          // second site HAS been processed
                && (!topThree.getMobileFriendlyFinish() && !topThree.getWebsiteEvalFinish())) { // third site not processed

            site = topThree;
        }
        return site;
    }

    public void checkSite (Website site) {

        site.processSite();

        if(site.getMobileFriendlyFinish() && site.getWebsiteEvalFinish() ) {
            updateIcons(site); // else update the icons
        } else if (site.getWebsiteEvalError() != null && site.getWebsiteEvalError().trim().equals("")) {
            setProcessingAlert(site);
        } else {
            setGenericAlert();
        }
    }

    public void updateIcons(final Website site) {

        new Thread(new Runnable() {
            @Override
            public void run() {


                // update the mobile friendly icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // Capture the layout's TextView and set the string as its text
                        TextView msg = findViewById(R.id.mobileFriendly);
                        ImageView passIcon = findViewById(R.id.mobileFriendlyPassIcon);
                        ImageView failIcon = findViewById(R.id.mobileFriendlyFailIcon);
                        ProgressBar mobileFriendlyLoadingIcon = findViewById(R.id.mobileFriendlyLoadingPanel);

                        if (site.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.PASS) { // friendly
                            msg.setText(site.getMobileFriendlyMessage());
                            passIcon.setVisibility(View.VISIBLE);
                        } else if (site.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.FAIL) { // not friendly
                            msg.setText(site.getMobileFriendlyMessage());
                            failIcon.setVisibility(View.VISIBLE);
                        } else if (site.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.ERROR) { // failed
                            msg.setText(site.getMobileFriendlyMessage());
                            failIcon.setVisibility(View.VISIBLE);
                        } else if (site.getMobileFriendlyCheck() == Website.EMobileFriendlyCheck.INIT){ // initialization still set
                            msg.setText(site.getMobileFriendlyMessage());
                            failIcon.setVisibility(View.VISIBLE);
                        }

                        mobileFriendlyLoadingIcon.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the title icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        TextView title = findViewById(R.id.title);
                        ImageView titlePassIcon = findViewById(R.id.titlePassIcon);
                        ImageView titleFailIcon = findViewById(R.id.titleFailIcon);
                        ProgressBar titleLoadingPanel = findViewById(R.id.titleLoadingPanel);

                        if (site.getTitleCheck()) { // pass
                            title.setText(site.getTitleMessage());
                            titlePassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            title.setText(site.getTitleMessage());
                            titleFailIcon.setVisibility(View.VISIBLE);
                        }
                        titleLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the description icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        TextView desc = findViewById(R.id.desc);
                        ImageView descPassIcon = findViewById(R.id.descPassIcon);
                        ImageView descFailIcon = findViewById(R.id.descFailIcon);
                        ProgressBar descLoadingPanel = findViewById(R.id.descLoadingPanel);

                        if (site.getDescriptionCheck()) { // pass
                            desc.setText(site.getDescriptionMessage());
                            descPassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            desc.setText(site.getDescriptionMessage());
                            descFailIcon.setVisibility(View.VISIBLE);
                        }
                        descLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the h1 icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        TextView h1 = findViewById(R.id.h1);
                        ImageView h1PassIcon = findViewById(R.id.h1PassIcon);
                        ImageView h1FailIcon = findViewById(R.id.h1FailIcon);
                        ProgressBar h1LoadingPanel = findViewById(R.id.h1LoadingPanel);

                        if (site.getH1Check()) { // pass
                            h1.setText(site.getH1Message());
                            h1PassIcon.setVisibility(View.VISIBLE);
                        } else { // fail
                            h1.setText(site.getH1Message());
                            h1FailIcon.setVisibility(View.VISIBLE);
                        }
                        h1LoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                // update the alt image icon on screen
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        TextView alt = findViewById(R.id.alt);
                        ImageView altPassIcon = findViewById(R.id.altPassIcon);
                        ImageView altFailIcon = findViewById(R.id.altFailIcon);
                        ProgressBar altLoadingPanel = findViewById(R.id.altLoadingPanel);

                        if (site.getImgCheck()) {
                            alt.setText(site.getImgMessage());
                            altPassIcon.setVisibility(View.VISIBLE);
                        } else {
                            alt.setText(site.getImgMessage());
                            altFailIcon.setVisibility(View.VISIBLE);
                        }
                        altLoadingPanel.setVisibility(View.GONE); // remove loading icon in any situation
                    }
                });

                if(site.isCompared()) {
                    // update btn to process next site
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btn = findViewById(R.id.btn_next);

                            if(site.getSiteOrder() < 3) {
                                btn.setText(getResources().getString(R.string.next_site));
                            } else {
                                btn.setText(getResources().getString(R.string.compared_results));
                            }

                            btn.setVisibility(View.VISIBLE);
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    nextActivity(site);
                                }
                            });
                        }
                    });
                }

            }
        }).start();
    }

    public void setProcessingAlert(final Website site) {

        //error in processing message
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                AlertDialog alertDialog = new AlertDialog.Builder(Results.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Error processing website. " + site.getWebsiteEvalError() + " Please Try Again.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }

    public void setGenericAlert() {

        //error in processing message
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                AlertDialog alertDialog = new AlertDialog.Builder(Results.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Process Timed Out. Please Try Again.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }

    public void nextActivity(Website site) {


        // get sites from intent
        Intent intent = getIntent();
        Website siteToCheck = intent.getParcelableExtra("siteToCheck");
        Website topOne = intent.getParcelableExtra("topOne");
        Website topTwo = intent.getParcelableExtra("topTwo");
        Website topThree = intent.getParcelableExtra("topThree");

        // check that no site has been processed yet
        if((siteToCheck.getMobileFriendlyFinish() && siteToCheck.getWebsiteEvalFinish())      // entered site not processed
                && (!topOne.getMobileFriendlyFinish() && !topOne.getWebsiteEvalFinish())        // first site not processed
                && (!topTwo.getMobileFriendlyFinish() && !topTwo.getWebsiteEvalFinish())        // second site not processed
                && (!topThree.getMobileFriendlyFinish() && !topThree.getWebsiteEvalFinish())) { // third site not processed

            Intent newIntent = new Intent(this, Results.class);

            newIntent.putExtra("siteToCheck", siteToCheck);
            newIntent.putExtra("topOne", topOne);
            newIntent.putExtra("topTwo", topTwo);
            newIntent.putExtra("topThree", topThree);
            startActivity(newIntent);

        } else if((siteToCheck.getMobileFriendlyFinish() && siteToCheck.getWebsiteEvalFinish()) // entered site HAS been processed
                && (topOne.getMobileFriendlyFinish() && topOne.getWebsiteEvalFinish())        // first site not processed
                && (!topTwo.getMobileFriendlyFinish() && !topTwo.getWebsiteEvalFinish())        // second site not processed
                && (!topThree.getMobileFriendlyFinish() && !topThree.getWebsiteEvalFinish())) { // third site not processed

            Intent newIntent = new Intent(this, Results.class);

            newIntent.putExtra("siteToCheck", siteToCheck);
            newIntent.putExtra("topOne", topOne);
            newIntent.putExtra("topTwo", topTwo);
            newIntent.putExtra("topThree", topThree);
            startActivity(newIntent);

        } else if((siteToCheck.getMobileFriendlyFinish() && siteToCheck.getWebsiteEvalFinish()) // entered site HAS been processed
                && (topOne.getMobileFriendlyFinish() && topOne.getWebsiteEvalFinish())          // first site HAS been processed
                && (topTwo.getMobileFriendlyFinish() && topTwo.getWebsiteEvalFinish())        // second site not processed
                && (!topThree.getMobileFriendlyFinish() && !topThree.getWebsiteEvalFinish())) { // third site not processed

            Intent newIntent = new Intent(this, Results.class);

            newIntent.putExtra("siteToCheck", siteToCheck);
            newIntent.putExtra("topOne", topOne);
            newIntent.putExtra("topTwo", topTwo);
            newIntent.putExtra("topThree", topThree);
            startActivity(newIntent);

        } else if((siteToCheck.getMobileFriendlyFinish() && siteToCheck.getWebsiteEvalFinish()) // entered site HAS been processed
                && (topOne.getMobileFriendlyFinish() && topOne.getWebsiteEvalFinish())          // first site HAS been processed
                && (topTwo.getMobileFriendlyFinish() && topTwo.getWebsiteEvalFinish())          // second site HAS been processed
                && (topThree.getMobileFriendlyFinish() && topThree.getWebsiteEvalFinish())) { // third site not processed - we just finished processing this site! go to compared results

            Intent newIntent = new Intent(this, ComparedResults.class);

            newIntent.putExtra("siteToCheck", siteToCheck);
            newIntent.putExtra("topOne", topOne);
            newIntent.putExtra("topTwo", topTwo);
            newIntent.putExtra("topThree", topThree);
            startActivity(newIntent);
        }

    }

}