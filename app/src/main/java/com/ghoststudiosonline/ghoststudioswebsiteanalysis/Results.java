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
import android.widget.TextView;

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


public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        TextView msg = findViewById(R.id.mobileFriendly);
        ImageView passIcon = findViewById(R.id.mobileFriendlyPassIcon);
        ImageView failIcon = findViewById(R.id.mobileFriendlyFailIcon);
        ProgressBar mobileFriendlyLoadingIcon = findViewById(R.id.mobileFriendlyLoadingPanel);

        TextView title = findViewById(R.id.title);
        ImageView titlePassIcon = findViewById(R.id.titlePassIcon);
        ImageView titleFailIcon = findViewById(R.id.titleFailIcon);
        ProgressBar titleLoadingPanel = findViewById(R.id.titleLoadingPanel);

        TextView desc = findViewById(R.id.desc);
        ImageView descPassIcon = findViewById(R.id.descPassIcon);
        ImageView descFailIcon = findViewById(R.id.descFailIcon);
        ProgressBar descLoadingPanel = findViewById(R.id.descLoadingPanel);

        TextView h1 = findViewById(R.id.h1);
        ImageView h1PassIcon = findViewById(R.id.h1PassIcon);
        ImageView h1FailIcon = findViewById(R.id.h1FailIcon);
        ProgressBar h1LoadingPanel = findViewById(R.id.h1LoadingPanel);

        TextView alt = findViewById(R.id.alt);
        ImageView altPassIcon = findViewById(R.id.altPassIcon);
        ImageView altFailIcon = findViewById(R.id.altFailIcon);
        ProgressBar altLoadingPanel = findViewById(R.id.altLoadingPanel);


        mobileFriendlyTest(url, msg, passIcon, failIcon, mobileFriendlyLoadingIcon);

        checkWebsite(url, title, titlePassIcon, titleFailIcon, titleLoadingPanel, desc, descPassIcon, descFailIcon, descLoadingPanel,
                h1, h1PassIcon, h1FailIcon, h1LoadingPanel, alt, altPassIcon, altFailIcon, altLoadingPanel);
    }

    public void mobileFriendlyTest(final String url, final TextView msg, final ImageView passIcon, final ImageView failIcon, final ProgressBar mobileFriendlyLoadingIcon) {


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
                        Log.d("Error", "There was an Error in the code." + myConnection.getResponseCode() + " @ " + url + " Message: " + myConnection.getResponseMessage());
                        result = "Error, Response Code: " + myConnection.getResponseCode();
                    }

                    myConnection.disconnect();
                } catch (IOException e) {

                    // Error handling code goes here

                    result = "There was an Error : " + e.getMessage();

                }

                final String finalResult = result;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // Capture the layout's TextView and set the string as its text


                        String errMsg = "Error, Try Again";

                        if (finalResult != null && finalResult.equals("MOBILE_FRIENDLY_TEST_RESULT_UNSPECIFIED")) {
                            msg.setText(errMsg);
                            failIcon.setVisibility(View.VISIBLE);
                            mobileFriendlyLoadingIcon.setVisibility(View.GONE);
                        } else if (finalResult != null && finalResult.equals("MOBILE_FRIENDLY")) {
                            passIcon.setVisibility(View.VISIBLE);
                            mobileFriendlyLoadingIcon.setVisibility(View.GONE);
                        } else if (finalResult != null && finalResult.equals("NOT_MOBILE_FRIENDLY")) {
                            failIcon.setVisibility(View.VISIBLE);
                            mobileFriendlyLoadingIcon.setVisibility(View.GONE);
                        }

                    }
                });
            }
        }).start();
    }

    public void checkWebsite(final String url, final TextView title, final ImageView  titlePassIcon, final ImageView  titleFailIcon, final ProgressBar  titleLoadingPanel,
                              final TextView desc, final ImageView  descPassIcon, final ImageView  descFailIcon, final ProgressBar  descLoadingPanel,
                              final TextView h1, final ImageView  h1PassIcon, final ImageView  h1FailIcon, final ProgressBar  h1LoadingPanel,
                              final TextView alt, final ImageView  altPassIcon, final ImageView  altFailIcon, final ProgressBar  altLoadingPanel) {
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
                                    title.setText(titleCheck.getMessage());
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
                                    desc.setText(descCheck.getMessage());
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
                                    h1.setText(h1Check.getMessage());
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
                                    alt.setText(altCheck.getMessage());
                                }
                                altLoadingPanel.setVisibility(View.GONE);
                            }
                        });
                    } else {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                AlertDialog alertDialog = new AlertDialog.Builder(Results.this).create();
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

                            AlertDialog alertDialog = new AlertDialog.Builder(Results.this).create();
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
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }

    private Title checkTitle(Document source) {

        Title check = new Title();

        if (source.title() != null) {
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
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

    private Results.Description checkDesc(Document source) {

        Description check = new Description();

        Element desc = source.select("meta[name=description]").first();

        if(desc != null) {
            String strDesc = desc.attr("content");

            if (strDesc != null) {
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
            return message;
        }

        private void setMessage(String message) {
            this.message = message;
        }

        public String getH1() {
            return h1;
        }

        private void setH1(String h1) {
            this.h1 = h1;
        }
    }

    private H1 checkH1(Document source) {

        Elements h1s = source.select("h1");

        H1 check = new H1();

        if (h1s.size() > 1) {
            check.setMessage("To Many H1's");
            check.setBool(false);
        } else if (h1s.size() < 1) {
            check.setMessage("Missing H1");
            check.setBool(false);
        } else {
            check.setMessage("pass");
            check.setBool(true);
            check.setH1(h1s.first().text());
        }

        return check;
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

        private void setBool(boolean bool) {
            this.bool = bool;
        }

        public String getMessage() {
            return message;
        }

        private void setMessage(String message) {
            this.message = message;
        }

        public int getImgCount() {
            return imgCount;
        }

        private void setImgCount(int imgCount) {
            this.imgCount = imgCount;
        }

        public int getAltCount() {
            return altCount;
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
            if (img.attr("alt") == null || img.attr("alt").equals("")) {
                missingAltCount++;
            }
        }

        if (missingAltCount > 0 && imgs.size() > 0) {
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