package com.ghoststudiosonline.ghoststudioswebsiteanalysis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Rankings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);

        //Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        String url = intent.getStringExtra("url");

        TextView siteEntered = findViewById(R.id.entered_site_url);
        siteEntered.setText(url);
        checkRankings(query);
    }

    private void checkRankings(final String query) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    String newQuery = query.replaceAll("\\s+", "+");
                    String searchUri = "https://www.google.com/search?q=" + newQuery + "&num=20";
                    Connection.Response response = Jsoup.connect(searchUri).ignoreContentType(true)
                            .userAgent("bot/Mosaic GhostStudiosBot")
                            .timeout(12000).followRedirects(true).execute();

                    if(response.statusCode() == 200) {

                        Document source = response.parse();
                        Elements search = source.select("div#search");
                        Elements cites = search.first().select("cite");

                        ArrayList<String> resultArr = new ArrayList<>();

                        for(Element cite : cites) {
                            if(cite.text().matches("^(http|https)://.*$")) {
                                URL url = new URL(cite.text());
                                String tempUrl = url.getProtocol() +"://" + url.getHost();
                                if( checkBlackList(tempUrl) && checkDup(tempUrl, resultArr)) {
                                    resultArr.add(tempUrl);
                                }
                            } else {
                                URL url = new URL("http://" + cite.text());
                                String tempUrl = url.getProtocol() +"://" + url.getHost();
                                if( checkBlackList(tempUrl) && checkDup(tempUrl, resultArr)) {
                                    resultArr.add(tempUrl);
                                }
                            }
                        }

                        final ArrayList<String> finalTopResults = resultArr;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                TextView siteOne = findViewById(R.id.top_one_url);
                                TextView siteTwo = findViewById(R.id.top_two_url);
                                TextView siteThree = findViewById(R.id.top_three_url);

                                siteOne.setText(finalTopResults.get(0));
                                siteTwo.setText(finalTopResults.get(1));
                                siteThree.setText(finalTopResults.get(2));

                                Button btn = findViewById(R.id.btn_site_check);
                                btn.setVisibility(View.VISIBLE);
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        checkSites(finalTopResults.get(0), finalTopResults.get(1), finalTopResults.get(2));
                                    }
                                });
                            }
                        });
                    } else {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                AlertDialog alertDialog = new AlertDialog.Builder(Rankings.this).create();
                                alertDialog.setTitle("Alert");
                                alertDialog.setMessage("Error processing Rankings. Please Try Again.");
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
                } catch (final IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            AlertDialog alertDialog = new AlertDialog.Builder(Rankings.this).create();
                            alertDialog.setTitle("Error");
                            alertDialog.setMessage("There was an Error : " + e.getMessage());
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
            }
        }).start();
    }
    // Check if tempUrl contains a Blacklist item
    public boolean checkBlackList (String url) {

        ArrayList<String> blackList = new ArrayList<>();
        ArrayList<Boolean> checkList = new ArrayList<>();

        boolean check = true;

        // TODO create user edited settings to generate the blacklist
        blackList.add("yelp");
        blackList.add("facebook");
        blackList.add("healthgrades");

        for(String blackListItem : blackList) { // for each blacklist item
            if (url.toLowerCase().contains(blackListItem.toLowerCase())) { // check to see if the url contains a blacklist item
                checkList.add(true); // it does so we add true to the temp check list
            } else {
                checkList.add(false); // it doesn't so we add false to the temp check list
            }
        }

        for(Boolean bool : checkList) { // check the check list for any true statements
            if(bool) { // there was a true, so this url tested can't be added
                check = false;
                break;
            }
        }

        return check;
    }

    // check is tempUrl is duplicate
    public boolean checkDup(String url, ArrayList<String> resultArr) {

        ArrayList<Boolean> checkList = new ArrayList<>();

        boolean check = true;

        for(String result : resultArr) {
            if (url.toLowerCase().equals(result.toLowerCase())) {
                checkList.add(true);
            } else {
                checkList.add(false);
            }
        }

        for(Boolean bool : checkList) {
            if(bool) {
                check = false;
                break;
            }
        }

        return check;
    }

    //start new intent on to check the results
    public void checkSites(String topOneUrl, String topTwoUrl, String topThreeUrl) {

        Intent intent = getIntent();
        String siteToCheckUrl = intent.getStringExtra("url");

        Website siteToCheck = new Website(siteToCheckUrl, 0);
        siteToCheck.setCompared(true);

        Website topOne = new Website(topOneUrl, 1);
        topOne.setCompared(true);

        Website topTwo = new Website(topTwoUrl, 2);
        topTwo.setCompared(true);

        Website topThree = new Website(topThreeUrl, 3);
        topThree.setCompared(true);

        Intent newIntent = new Intent(this, Results.class);

        newIntent.putExtra("siteToCheck", siteToCheck);
        newIntent.putExtra("topOne", topOne);
        newIntent.putExtra("topTwo", topTwo);
        newIntent.putExtra("topThree", topThree);
        startActivity(newIntent);
    }

}
