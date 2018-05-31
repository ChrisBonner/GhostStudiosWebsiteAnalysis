package com.ghoststudiosonline.ghoststudioswebsiteanalysis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class ComparedResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compared_results);

        //Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        //my site
        String mySite = intent.getStringExtra("url");

        ImageView msMobileFriendlyPassIcon = findViewById(R.id.msMobileFriendlyPassIcon);
        ImageView msMobileFriendlyFailIcon = findViewById(R.id.msMobileFriendlyFailIcon);
        ProgressBar msMobileFriendlyLoadingPanel = findViewById(R.id.msMobileFriendlyLoadingPanel);

        ImageView msTitlePassIcon = findViewById(R.id.msTitlePassIcon);
        ImageView msTitleFailIcon = findViewById(R.id.msTitleFailIcon);
        ProgressBar msTitleLoadingPanel = findViewById(R.id.msTitleLoadingPanel);

        ImageView msDescPassIcon = findViewById(R.id.msDescPassIcon);
        ImageView msDescFailIcon = findViewById(R.id.msDescFailIcon);
        ProgressBar msDescLoadingPanel = findViewById(R.id.msDescLoadingPanel);

        ImageView msH1PassIcon = findViewById(R.id.msH1PassIcon);
        ImageView msH1FailIcon = findViewById(R.id.msH1FailIcon);
        ProgressBar msH1LoadingPanel = findViewById(R.id.msH1LoadingPanel);

        ImageView msAltPassIcon = findViewById(R.id.msAltPassIcon);
        ImageView msAltFailIcon = findViewById(R.id.msAltFailIcon);
        ProgressBar msAltLoadingPanel = findViewById(R.id.msAltLoadingPanel);

        //site one
        String topOneUrl = intent.getStringExtra("topOneUrl");

        ImageView soMobileFriendlyPassIcon = findViewById(R.id.soMobileFriendlyPassIcon);
        ImageView soMobileFriendlyFailIcon = findViewById(R.id.soMobileFriendlyFailIcon);
        ProgressBar soMobileFriendlyLoadingPanel = findViewById(R.id.soMobileFriendlyLoadingPanel);

        ImageView soTitlePassIcon = findViewById(R.id.soTitlePassIcon);
        ImageView soTitleFailIcon = findViewById(R.id.soTitleFailIcon);
        ProgressBar soTitleLoadingPanel = findViewById(R.id.soTitleLoadingPanel);

        ImageView soDescPassIcon = findViewById(R.id.soDescPassIcon);
        ImageView soDescFailIcon = findViewById(R.id.soDescFailIcon);
        ProgressBar soDescLoadingPanel = findViewById(R.id.soDescLoadingPanel);

        ImageView soH1PassIcon = findViewById(R.id.soH1PassIcon);
        ImageView soH1FailIcon = findViewById(R.id.soH1FailIcon);
        ProgressBar soH1LoadingPanel = findViewById(R.id.soH1LoadingPanel);

        ImageView soAltPassIcon = findViewById(R.id.soAltPassIcon);
        ImageView soAltFailIcon = findViewById(R.id.soAltFailIcon);
        ProgressBar soAltLoadingPanel = findViewById(R.id.soAltLoadingPanel);

        //site two
        String topTwoUrl = intent.getStringExtra("topTwoUrl");

        ImageView stMobileFriendlyPassIcon = findViewById(R.id.stMobileFriendlyPassIcon);
        ImageView stMobileFriendlyFailIcon = findViewById(R.id.stMobileFriendlyFailIcon);
        ProgressBar stMobileFriendlyLoadingPanel = findViewById(R.id.stMobileFriendlyLoadingPanel);

        ImageView stTitlePassIcon = findViewById(R.id.stTitlePassIcon);
        ImageView stTitleFailIcon = findViewById(R.id.stTitleFailIcon);
        ProgressBar stTitleLoadingPanel = findViewById(R.id.stTitleLoadingPanel);

        ImageView stDescPassIcon = findViewById(R.id.stDescPassIcon);
        ImageView stDescFailIcon = findViewById(R.id.stDescFailIcon);
        ProgressBar stDescLoadingPanel = findViewById(R.id.stDescLoadingPanel);

        ImageView stH1PassIcon = findViewById(R.id.stH1PassIcon);
        ImageView stH1FailIcon = findViewById(R.id.stH1FailIcon);
        ProgressBar stH1LoadingPanel = findViewById(R.id.stH1LoadingPanel);

        ImageView stAltPassIcon = findViewById(R.id.stAltPassIcon);
        ImageView stAltFailIcon = findViewById(R.id.stAltFailIcon);
        ProgressBar stAltLoadingPanel = findViewById(R.id.stAltLoadingPanel);

        //site three
        String topThreeUrl = intent.getStringExtra("topThreeUrl");

        ImageView steMobileFriendlyPassIcon = findViewById(R.id.steMobileFriendlyPassIcon);
        ImageView steMobileFriendlyFailIcon = findViewById(R.id.steMobileFriendlyFailIcon);
        ProgressBar steMobileFriendlyLoadingPanel = findViewById(R.id.steMobileFriendlyLoadingPanel);

        ImageView steTitlePassIcon = findViewById(R.id.steTitlePassIcon);
        ImageView steTitleFailIcon = findViewById(R.id.steTitleFailIcon);
        ProgressBar steTitleLoadingPanel = findViewById(R.id.steTitleLoadingPanel);

        ImageView steDescPassIcon = findViewById(R.id.steDescPassIcon);
        ImageView steDescFailIcon = findViewById(R.id.steDescFailIcon);
        ProgressBar steDescLoadingPanel = findViewById(R.id.steDescLoadingPanel);

        ImageView steH1PassIcon = findViewById(R.id.steH1PassIcon);
        ImageView steH1FailIcon = findViewById(R.id.steH1FailIcon);
        ProgressBar steH1LoadingPanel = findViewById(R.id.steH1LoadingPanel);

        ImageView steAltPassIcon = findViewById(R.id.steAltPassIcon);
        ImageView steAltFailIcon = findViewById(R.id.steAltFailIcon);
        ProgressBar steAltLoadingPanel = findViewById(R.id.steAltLoadingPanel);


        mobileFriendlyTest(mySite, msMobileFriendlyPassIcon, msMobileFriendlyFailIcon, msMobileFriendlyLoadingPanel);

        checkWebsite(mySite, msTitlePassIcon, msTitleFailIcon, msTitleLoadingPanel, msDescPassIcon, msDescFailIcon, msDescLoadingPanel,
                msH1PassIcon, msH1FailIcon, msH1LoadingPanel, msAltPassIcon, msAltFailIcon, msAltLoadingPanel);

        mobileFriendlyTest(topOneUrl, soMobileFriendlyPassIcon, soMobileFriendlyFailIcon, soMobileFriendlyLoadingPanel);

        checkWebsite(topOneUrl, soTitlePassIcon, soTitleFailIcon, soTitleLoadingPanel, soDescPassIcon, soDescFailIcon, soDescLoadingPanel,
                soH1PassIcon, soH1FailIcon, soH1LoadingPanel, soAltPassIcon, soAltFailIcon, soAltLoadingPanel);

        mobileFriendlyTest(topTwoUrl, stMobileFriendlyPassIcon, stMobileFriendlyFailIcon, stMobileFriendlyLoadingPanel);

        checkWebsite(topTwoUrl, stTitlePassIcon, stTitleFailIcon, stTitleLoadingPanel, stDescPassIcon, stDescFailIcon, stDescLoadingPanel,
                stH1PassIcon, stH1FailIcon, stH1LoadingPanel, stAltPassIcon, stAltFailIcon, stAltLoadingPanel);

        mobileFriendlyTest(topThreeUrl, steMobileFriendlyPassIcon, steMobileFriendlyFailIcon, steMobileFriendlyLoadingPanel);

        checkWebsite(topThreeUrl, steTitlePassIcon, steTitleFailIcon, steTitleLoadingPanel, steDescPassIcon, steDescFailIcon, steDescLoadingPanel,
                steH1PassIcon, steH1FailIcon, steH1LoadingPanel, steAltPassIcon, steAltFailIcon, steAltLoadingPanel);


    }

   public void mobileFriendlyTest(final String url, final ImageView passIcon, final ImageView failIcon, final ProgressBar mobileFriendlyLoadingIcon) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;

                try {

                    HttpURLConnection myConnection = null;
                    int attempts = 0;
                    while (attempts < 10) {

                            String myData = "{\n" +
                                    "  \"requestScreenshot\": false,\n" +
                                    "  \"url\": \"" + url + "\"\n" +
                                    "}";
                        String apiKey = BuildConfig.API_KEY;
                            URL GoogleMobileFriendlyTester = new URL("https://searchconsole.googleapis.com/v1/urlTestingTools/mobileFriendlyTest:run?key=" + apiKey);

                            myConnection = (HttpURLConnection) GoogleMobileFriendlyTester.openConnection();
                            myConnection.setConnectTimeout(5000);
                            myConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                            myConnection.setRequestProperty("User-Agent", "Ghost-Studios-Website-Analysis-App-v0.1");
                            myConnection.setRequestProperty("Accept", "application/json");
                            myConnection.setDoOutput(true);
                            myConnection.setDoInput(true);
                            myConnection.setRequestMethod("POST");

                            OutputStream outputStream = myConnection.getOutputStream();
                            outputStream.write(myData.getBytes("UTF-8"));
                            outputStream.close();

                        Log.d("myTag", "Google API Attempt: " + attempts + "@ " + url  + " Message: " + myConnection.getResponseMessage());

                        if(myConnection.getResponseCode() != 200) {
                            try {
                                Random rand = new Random();
                                int waitTime = rand.nextInt(50000) + 1000;

                                Thread.sleep(waitTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            attempts++;
                        } else {
                            attempts = 10;
                        }
                    }

                    if (myConnection.getResponseCode() == 200) {
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");

                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginObject();

                        while (jsonReader.hasNext()) { // Loop through all keys
                            String key = jsonReader.nextName(); // Fetch the next key
                            if (key.equals("mobileFriendliness")) { // Check if desired key
                                // Fetch the value as a String
                                String value = jsonReader.nextString();

                                // Do something with the value
                                // ...
                                if (value == null) {
                                    result = "MOBILE_FRIENDLY_TEST_RESULT_UNSPECIFIED";
                                } else {
                                    result = value;
                                }

                                break; // Break out of the loop
                            } else {
                                jsonReader.skipValue(); // Skip values of other keys
                            }
                        }
                        responseBody.close();
                    } else {
                        // Error handling code goes here

                        // Error handling code goes here
                        Log.d("Error", "There was an Error in the code." + myConnection.getResponseCode() + " @ " + url + " Message: " + myConnection.getResponseMessage());
                        result = "MOBILE_FRIENDLY_TEST_RESULT_UNSPECIFIED";
                    }

                    myConnection.disconnect();
                } catch (IOException e) {

                    // Error handling code goes here
                    Log.d("Error", "Error Message: " + e.getMessage());
                    result = "MOBILE_FRIENDLY_TEST_RESULT_UNSPECIFIED";
                }

                final String finalResult = result;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // Capture the layout's TextView and set the string as its text

                         if (finalResult != null && finalResult.equals("MOBILE_FRIENDLY")) {
                            passIcon.setVisibility(View.VISIBLE);
                        } else if (finalResult != null && finalResult.equals("NOT_MOBILE_FRIENDLY")) {
                            failIcon.setVisibility(View.VISIBLE);
                        } else if (finalResult != null && finalResult.equals("MOBILE_FRIENDLY_TEST_RESULT_UNSPECIFIED")) {
                             failIcon.setVisibility(View.VISIBLE);
                             Log.d("Error", "Status Returned: MOBILE_FRIENDLY_TEST_RESULT_UNSPECIFIED");
                        }
                        mobileFriendlyLoadingIcon.setVisibility(View.GONE);
                    }
                });
            }
        }).start();

   }

    public void checkWebsite(final String url, final ImageView  titlePassIcon, final ImageView  titleFailIcon, final ProgressBar titleLoadingPanel,
                             final ImageView  descPassIcon, final ImageView  descFailIcon, final ProgressBar  descLoadingPanel,
                             final ImageView  h1PassIcon, final ImageView  h1FailIcon, final ProgressBar  h1LoadingPanel,
                             final ImageView  altPassIcon, final ImageView  altFailIcon, final ProgressBar  altLoadingPanel) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {


                    Connection.Response response = null;
                    int attempts = 0;
                    while (attempts < 10) {
                        Log.d("myTag", "Website Attempt: " + attempts);

                        response = Jsoup.connect(url).ignoreContentType(true).userAgent("Ghost-Studios-Website-Analysis-App-v0.1").timeout(12000).followRedirects(true).execute();

                        if(response.statusCode() != 200) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                Log.d("myTag", "Thread Sleep Error: " + e.getMessage());
                            }
                            attempts++;
                        } else {
                            attempts = 10;
                        }
                    }

                    if (response.statusCode() == 200) {

                        Document source = response.parse();

                        final Title titleCheck = checkTitle(source);
                        final Description descCheck = checkDesc(source);
                        final H1 h1Check = checkH1(source);
                        final Images altCheck = checkAlt(source);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (titleCheck.getBool()) {
                                    titlePassIcon.setVisibility(View.VISIBLE);
                                } else {
                                    titleFailIcon.setVisibility(View.VISIBLE);
                                }
                                titleLoadingPanel.setVisibility(View.GONE);
                            }
                        });

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (descCheck.getBool()) {
                                    descPassIcon.setVisibility(View.VISIBLE);
                                } else {
                                    descFailIcon.setVisibility(View.VISIBLE);
                                }
                                descLoadingPanel.setVisibility(View.GONE);
                            }
                        });

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (h1Check.getBool()) {
                                    h1PassIcon.setVisibility(View.VISIBLE);
                                } else {
                                    h1FailIcon.setVisibility(View.VISIBLE);
                                }
                                h1LoadingPanel.setVisibility(View.GONE);
                            }
                        });

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (altCheck.getBool()) {
                                    altPassIcon.setVisibility(View.VISIBLE);
                                } else {
                                    altFailIcon.setVisibility(View.VISIBLE);
                                }
                                altLoadingPanel.setVisibility(View.GONE);
                            }
                        });
                    } else {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                AlertDialog alertDialog = new AlertDialog.Builder(ComparedResults.this).create();
                                alertDialog.setTitle("Alert");
                                alertDialog.setMessage("Error processing website. Please Try Again.");
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

                            AlertDialog alertDialog = new AlertDialog.Builder(ComparedResults.this).create();
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

    public class Title {
        private Boolean bool;
        private String message;
        private String title;

        public Boolean getBool() {
            return bool;
        }

        public void setBool(Boolean bool) {
            this.bool = bool;
        }

        public String getMessage() {
            return  message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTitle() {
            return  title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }

    private Title checkTitle(Document source) {

        Title check = new Title();

        if(source.title() != null) {
            if (source.title().length() < 40) {
                check.setMessage("Title to Short");
                check.setBool(false);
            } else if (source.title().length() > 75) {
                check.setMessage("Title To Long");
                check.setBool(false);
            } else {
                check.setMessage("pass");
                check.setBool(true);
            }
            check.setTitle(source.title());
        } else {
            check.setMessage("No Title");
            check.setBool(false);
        }

        return check;
    }

    public class Description {
        private Boolean bool;
        private String message;
        private String description;

        public Boolean getBool() {
            return bool;
        }

        public void setBool(Boolean bool) {
            this.bool = bool;
        }

        public String getMessage() {
            return  message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDescription() {
            return  description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

    private Description checkDesc(Document source) {

        Description check = new Description();

        Element desc = source.select("meta[name=description]").first();

        if(desc != null) {
            String strDesc = desc.attr("content");

            if(strDesc != null) {
                if (strDesc.length() < 75) {
                    check.setMessage("Description to Short");
                    check.setBool(false);
                } else if (strDesc.length() > 155) {
                    check.setMessage("Description To Long");
                    check.setBool(false);
                } else {
                    check.setMessage("pass");
                    check.setBool(true);
                }

                check.setDescription(strDesc);

            } else {
                check.setMessage("No Description");
                check.setBool(false);
            }
        } else {
            check.setMessage("No Description");
            check.setBool(false);
        }

        return check;
    }

    public class H1 {
        private Boolean bool;
        private String message;
        private String h1;

        public Boolean getBool() {
            return bool;
        }

        private void setBool(Boolean bool) {
            this.bool = bool;
        }

        public String getMessage() {
            return  message;
        }

        private void setMessage(String message) {
            this.message = message;
        }

        public String getH1() {
            return  h1;
        }

        private void setH1(String h1) {
            this.h1 = h1;
        }
    }

    private H1 checkH1(Document source) {

        Elements h1s = source.select("h1");

        H1 check = new H1();

        if(h1s.size() > 1) {
            check.setMessage("To Many H1's");
            check.setBool(false);
        } else if(h1s.size() < 1) {
            check.setMessage("Missing H1");
            check.setBool(false);
        } else {
            check.setMessage("pass");
            check.setBool(true);
            check.setH1(h1s.first().text());
        }

        return  check;
    }

    public class Images {
        private int imgCount;
        private int altCount;
        private int missingAltCount;
        private boolean bool;
        private String message;

        public boolean getBool() {
            return bool;
        }

        private void setBool (boolean bool) {
            this.bool = bool;
        }

        public String getMessage() {
            return message;
        }

        private void setMessage (String message) {
            this.message = message;
        }

        public int getImgCount() {
            return imgCount;
        }

        private void setImgCount(int imgCount) {
            this.imgCount = imgCount;
        }

        public int getAltCount() {
            return  altCount;
        }

        private void setAltCount(int altCount) {
            this.altCount = altCount;
        }

        public int getMissingAltCount() {
            return missingAltCount;
        }

        private void setMissingAltCount(int missingAltCount) {
            this.missingAltCount = missingAltCount;
        }

    }

    private Images checkAlt(Document source) {

        Images check = new Images();

        Elements imgs = source.select("img");

        int missingAltCount = 0;
        for (Element img : imgs) {
            if(img.attr("alt") == null || img.attr("alt").equals("")) {
                missingAltCount++;
            }
        }

        if(missingAltCount > 0 && imgs.size() > 0) {
            check.setBool(false);
            String msg = missingAltCount + " Images Missing Alt attr";
            check.setMessage(msg);
        } else {
            check.setBool(true);
        }

        check.setMissingAltCount(missingAltCount);
        check.setImgCount(imgs.size());
        check.setAltCount(imgs.size() - missingAltCount);

        return check;
    }

}
