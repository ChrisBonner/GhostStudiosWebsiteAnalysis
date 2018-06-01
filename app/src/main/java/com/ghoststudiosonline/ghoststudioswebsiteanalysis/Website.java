package com.ghoststudiosonline.ghoststudioswebsiteanalysis;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.Log;
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



public class Website implements Parcelable {

    // Parcelable Creator
    public static final Parcelable.Creator CREATOR = new Creator<Website>() {

        public Website createFromParcel(Parcel in) {
            return new Website(in);
        }

        public Website[] newArray(int size) {
            return new Website[size];
        }
    };

    //enum for MobileFriendlyTest
    private enum EMobileFriendlyCheck {
        INIT,
        PASS,
        FAIL,
        ERROR
    }

    //Private Variables
    private String url;
    private String websiteEvalError;
    private String mobileFriendlyMessage;
    private EMobileFriendlyCheck mobileFriendlyCheck = EMobileFriendlyCheck.INIT;
    private boolean mobileFriendlyFinish = false; // initialized as false
    private boolean websiteEvalFinish = false; // initialized as false
    private String title;
    private boolean titleCheck;
    private String titleMessage;
    private String description;
    private boolean descriptionCheck;
    private String descriptionMessage;
    private String h1;
    private boolean h1Check;
    private String h1Message;
    private int imgCount;
    private int imgAltCount;
    private int imgMissingAltCount;
    private boolean imgCheck;
    private String imgMessage;


    // Uri Getter
    public String getUrl() {
        return url;
    }

    // website eval error message
    public String getWebsiteEvalError() { return websiteEvalError; }

    // Mobile Friendly Check Getter
    public String getMobileFriendlyMessage() { return mobileFriendlyMessage; }

    public EMobileFriendlyCheck getMobileFriendlyCheck() { return mobileFriendlyCheck; }

    public boolean getMobileFriendlyFinish() { return mobileFriendlyFinish; }

    public boolean getWebsiteEvalFinish() { return websiteEvalFinish; }

    // Title Variables Getters
    public String getTitle() {
        return title;
    }

    public boolean getTitleCheck(){
        return titleCheck;
    }

    public String getTitleMessage() {
        return titleMessage;
    }

    // Description Variables Getters/Setters
    public String getDescription(){
        return description;
    }

    public boolean getDescriptionCheck() {
        return descriptionCheck;
    }

    public String getDescriptionMessage() {
        return descriptionMessage;
    }

    // H1 Variables Getters
    public String getH1(){
        return h1;
    }

    public boolean getH1Check() {
        return h1Check;
    }

    public String getH1Message() {
        return h1Message;
    }

    // Image Variables Getters
    public int getImgCount() {
        return imgCount;
    }

    public int getImgAltCount() {
        return imgAltCount;
    }

    public int getImgMissingAltCount() {
        return imgMissingAltCount;
    }

    public boolean getImgCheck() {
        return imgCheck;
    }

    public String getImgMessage() {
        return imgMessage;
    }


    // Constructor
    public Website(String url) {
        this.url = url;
    }

    // Second Constructor
//    public Website(String url, String websiteEvalError, String mobileFriendlyMessage, EMobileFriendlyCheck mobileFriendlyCheck, boolean mobileFriendlyFinish, boolean websiteEvalFinish, String title, boolean titleCheck, String titleMessage,
//                   String description, boolean descriptionCheck, String descriptionMessage, String h1, boolean h1Check, String h1Message,
//                   int imgCount, int imgAltCount, int imgMissingAltCount, boolean imgCheck, String imgMessage) {
//        this.url = url;
//        this.websiteEvalError = websiteEvalError;
//        this.mobileFriendlyMessage = mobileFriendlyMessage;
//        this.mobileFriendlyCheck = mobileFriendlyCheck;
//        this.mobileFriendlyFinish = mobileFriendlyFinish;
//        this.websiteEvalFinish = websiteEvalFinish;
//        this.title = title;
//        this.titleCheck = titleCheck;
//        this.titleMessage = titleMessage;
//        this.description = description;
//        this.descriptionCheck = descriptionCheck;
//        this.descriptionMessage = descriptionMessage;
//        this.h1 = h1;
//        this.h1Check = h1Check;
//        this.h1Message = h1Message;
//        this.imgCount = imgCount;
//        this.imgAltCount = imgAltCount;
//        this.imgMissingAltCount = imgMissingAltCount;
//        this.imgCheck = imgCheck;
//        this.imgMessage = imgMessage;
//    }

    // Parcelable Constructor
    public Website(Parcel in) {
        this.url = in.readString();
        this.websiteEvalError = in.readString();
        this.mobileFriendlyMessage = in.readString();
        this.mobileFriendlyCheck = EMobileFriendlyCheck.valueOf(in.readString());
        this.mobileFriendlyFinish = (in.readInt() != 0);
        this.websiteEvalFinish = (in.readInt() != 0);
        this.title = in.readString();
        this.titleCheck = (in.readInt() != 0);
        this.titleMessage = in.readString();
        this.description = in.readString();
        this.descriptionCheck = (in.readInt() != 0);
        this.descriptionMessage = in.readString();
        this.h1 = in.readString();
        this.h1Check = (in.readInt() != 0);
        this.h1Message = in.readString();
        this.imgCount = in.readInt();
        this.imgAltCount = in.readInt();
        this.imgMissingAltCount = in.readInt();
        this.imgCheck = (in.readInt() != 0);
        this.imgMessage = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel desc, int flags) {
        //uri
        desc.writeString(url);
        //website eval error
        desc.writeString(websiteEvalError);
        //mobileFriendly
        desc.writeString(mobileFriendlyMessage);
        desc.writeString(mobileFriendlyCheck.name());
        desc.writeInt(mobileFriendlyFinish ? 1 : 0);
        desc.writeInt(websiteEvalFinish ? 1 : 0);
        //title
        desc.writeString(title);
        desc.writeInt(titleCheck ? 1 : 0);
        desc.writeString(titleMessage);
        //desc
        desc.writeString(description);
        desc.writeInt(descriptionCheck ? 1 : 0);
        desc.writeString(descriptionMessage);
        //h1
        desc.writeString(h1);
        desc.writeInt(h1Check ? 1 : 0);
        desc.writeString(h1Message);
        //img
        desc.writeInt(imgCount);
        desc.writeInt(imgAltCount);
        desc.writeInt(imgMissingAltCount);
        desc.writeInt(imgCheck ? 1 : 0);
        desc.writeString(imgMessage);
    }

    public void mobileFriendlyTest() {

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
                        // API Key stored in secrets.properties in root folder
                        String apiKey = BuildConfig.API_KEY;
                        URL GoogleMobileFriendlyTester = new URL("https://searchconsole.googleapis.com/v1/urlTestingTools/mobileFriendlyTest:run?key=" + apiKey);

                        myConnection = (HttpURLConnection) GoogleMobileFriendlyTester.openConnection();
                        myConnection.setConnectTimeout(5000);
                        myConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        myConnection.setRequestProperty("User-Agent", "Ghost-Studios-Website-Analysis-App");
                        myConnection.setRequestProperty("Accept", "application/json");
                        myConnection.setDoOutput(true);
                        myConnection.setDoInput(true);
                        myConnection.setRequestMethod("POST");

                        OutputStream outputStream = myConnection.getOutputStream();
                        outputStream.write(myData.getBytes("UTF-8"));
                        outputStream.close();

                        // log number of attempts for the given uri and the response message
                        Log.d("myTag", "Google API Attempt: " + attempts + "@ " + url  + " Message: " + myConnection.getResponseMessage());

                        //random wait time for common to many requests at the same time.
                        if(myConnection.getResponseCode() == 200) {
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
                                    if (value == null) {
                                        result = "Error Null Value Try Again.";
                                    } else {
                                        result = value;
                                    }
                                    break; // Break out of the loop
                                } else {
                                    jsonReader.skipValue(); // Skip values of other keys
                                }
                            }
                            attempts = 10; // end attempts to end while loop
                            responseBody.close(); // close response
                        } else {
                            try {
                                Random rand = new Random();
                                int waitTime = rand.nextInt(50000) + 1000;
                                Thread.sleep(waitTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            attempts++;
                        }
                    }
                    myConnection.disconnect(); // disconnect connection

                    //set results
                    if (result != null && result.equals("MOBILE_FRIENDLY")) {
                        mobileFriendlyCheck = EMobileFriendlyCheck.PASS;
                        mobileFriendlyMessage = "Your Website is Mobile Friendly!";
                    } else if (result != null && result.equals("NOT_MOBILE_FRIENDLY")) {
                        mobileFriendlyCheck = EMobileFriendlyCheck.FAIL;
                        mobileFriendlyMessage = "Your Website is NOT Mobile Friendly. :(";
                    } else {
                        mobileFriendlyCheck = EMobileFriendlyCheck.ERROR;
                        mobileFriendlyMessage = result;
                    }
                    mobileFriendlyFinish = true;

                } catch (IOException e) {

                    // Log exception and return error
                    Log.d("Error", "Error Message: " + e.getMessage());
                    mobileFriendlyCheck = EMobileFriendlyCheck.ERROR;
                    mobileFriendlyMessage = "Error Try Again.";
                }
            }
        }).start();
    }

    public void checkWebsite() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Connection.Response response;
                    int attempts = 0;
                    while (attempts <= 10) {
                        Log.d("myTag", "Website Attempt: " + attempts);

                        response = Jsoup.connect(url).ignoreContentType(true).userAgent("Ghost-Studios-Website-Analysis-App-v0.1").timeout(12000).followRedirects(true).execute();

                        if (response.statusCode() == 200) {

                            Document source = response.parse();

                            checkTitle(source); // evaluate title
                            checkDesc(source); // evaluate description
                            checkH1(source); // evaluate h1
                            checkAlt(source); // evaluate alt attr


                            break; // break out of while loop

                        } else if (response.statusCode() != 200 && attempts == 10) {
                            // no good response after 10 attempts, error message.
                            websiteEvalError = "Unable to retrieve url: " + url + " Response Code: " + response.statusCode() + " Response Message: " + response.statusMessage();
                            break;
                        } else {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                Log.d("myTag", "Thread Sleep Error: " + e.getMessage());
                            }
                            attempts++;
                        }
                    }

                    websiteEvalFinish = true; // finish used for while loop
                } catch (IOException e) {
                    Log.d("myTag", "Website Eval Error: " + e.getMessage());
                    websiteEvalError = "There was an error: " + e;
                    websiteEvalFinish = true; // finish used for while loop
                }
            }
        }).start();
    }

    private void checkTitle(Document source) {

        if(source.title() != null) {
            if (source.title().length() < 40) {
                titleMessage = "Title to Short";
                titleCheck = false;
            } else if (source.title().length() > 75) {
                titleMessage = "Title To Long";
                titleCheck = false;
            } else {
                titleMessage = "Pass";
                titleCheck = true;
            }
            title = source.title();
        } else {
            titleMessage = "No Title";
            titleCheck = false;
        }
    }

    private void checkDesc(Document source) {

        Element desc = source.select("meta[name=description]").last();

        if(desc != null) {
            String strDesc = desc.attr("content");

            if(strDesc != null) {
                if (strDesc.length() < 75) {
                    descriptionMessage = "Description to Short";
                    descriptionCheck = false;
                } else if (strDesc.length() > 155) {
                    descriptionMessage = "Description To Long";
                    descriptionCheck = false;
                } else {
                    descriptionMessage = "Pass";
                    descriptionCheck = true;
                }

                description = strDesc;

            } else {
                descriptionMessage = "No Description";
                descriptionCheck = false;
            }
        } else {
            descriptionMessage = "No Description";
            descriptionCheck = false;
        }
    }

    private void checkH1(Document source) {

        Elements h1s = source.select("h1");

        if(h1s.size() > 1) {
            h1Message = "To Many H1's";
            h1Check = false;
        } else if(h1s.size() < 1) {
            h1Message = "Missing H1";
            h1Check = false;
        } else {
            h1Message = "Pass";
            h1Check = true;
            h1 = h1s.first().text();
        }
    }

    private void checkAlt(Document source) {

        Elements imgs = source.select("img");

        int missingAltCount = 0;
        for (Element img : imgs) {
            if(img.attr("alt") == null || img.attr("alt").equals("")) {
                missingAltCount++;
            }
        }

        if(missingAltCount > 0 && imgs.size() > 0) {
            imgCheck = false;
            imgMessage = missingAltCount + " Images Missing Alt attr";
        } else {
            imgCheck = true;
        }

        imgMissingAltCount = missingAltCount;
        imgCount = imgs.size();
        imgAltCount = (imgs.size() - missingAltCount);
    }
}
