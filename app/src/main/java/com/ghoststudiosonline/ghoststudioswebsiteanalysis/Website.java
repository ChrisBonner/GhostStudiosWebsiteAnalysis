package com.ghoststudiosonline.ghoststudioswebsiteanalysis;


import android.os.Parcel;
import android.os.Parcelable;


public class Website implements Parcelable {

    String uri;
    String mobileFriendlyMessage;
    boolean mobileFriendlyCheck;
    String title;
    boolean titleCheck;
    String titleMessage;
    String description;
    boolean descriptionCheck;
    String descriptionMessage;
    String h1;
    boolean h1Check;
    String h1Message;
    int imgCount;
    int imgAltCount;
    int imgMissingAltCount;
    boolean imgCheck;
    String imgMessage;


    public Website(String uri, String mobileFriendlyMessage, boolean mobileFriendlyCheck, String title, boolean titleCheck, String titleMessage,
                   String description, boolean descriptionCheck, String descriptionMessage, String h1, boolean h1Check, String h1Message,
                   int imgCount, int imgAltCount, int imgMissingAltCount, boolean imgCheck, String imgMessage) {
        this.uri = uri;
        this.mobileFriendlyMessage = mobileFriendlyMessage;
        this.mobileFriendlyCheck = mobileFriendlyCheck;
        this.title = title;
        this.titleCheck = titleCheck;
        this.titleMessage = titleMessage;
        this.description = description;
        this.descriptionCheck = descriptionCheck;
        this.descriptionMessage = descriptionMessage;
        this.h1 = h1;
        this.h1Check = h1Check;
        this.h1Message = h1Message;
        this.imgCount = imgCount;
        this.imgAltCount = imgAltCount;
        this.imgMissingAltCount = imgMissingAltCount;
        this.imgCheck = imgCheck;
        this.imgMessage = imgMessage;
    }

    public Website(Parcel in) {
        this.uri = in.readString();
        this.mobileFriendlyMessage = in.readString();
        this.mobileFriendlyCheck = (in.readInt() != 0);
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
        desc.writeString(uri);
        //mobileFriendly
        desc.writeString(mobileFriendlyMessage);
        desc.writeInt(mobileFriendlyCheck ? 1 : 0);
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

    public static final Creator<Website> CREATOR = new Creator<Website>() {

        public Website createFromParcel(Parcel in) {
            return new Website(in);
        }

        public Website[] newArray(int size) {
            return new Website[size];
        }
    };


    // Uri Getter/Setter
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    // Mobile Friendly Check Getter/Setter
    public String getMobileFriendlyMessage() { return mobileFriendlyMessage; }

    public void setMobileFriendlyMessage(String mobileFriendlyMessage) {
        this.mobileFriendlyMessage = mobileFriendlyMessage;
    }

    public boolean getMobileFriendlyCheck() {
        return mobileFriendlyCheck;
    }

    public void setMobileFriendlyCheck(Boolean mobileFriendlyCheck) {
        this.mobileFriendlyCheck = mobileFriendlyCheck;
    }


    // Title Variables Getters/Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getTitleCheck(){
        return titleCheck;
    }

    public void setTitleCheck(Boolean titleCheck) {
        this.titleCheck = titleCheck;
    }

    public String getTitleMessage() {
        return titleMessage;
    }

    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
    }

    // Description Variables Getters/Setters
    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getDescriptionCheck() {
        return descriptionCheck;
    }

    public void setDescriptionCheck (Boolean descriptionCheck) {

        this.descriptionCheck = descriptionCheck;
    }

    public String getDescriptionMessage() {
        return descriptionMessage;
    }

    public void setDescriptionMessage(String descriptionMessage) {

        this.descriptionMessage = descriptionMessage;
    }

    // H1 Variables Getters/Setters
    public String getH1(){
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public boolean getH1Check() {
        return h1Check;
    }

    public void setH1Check(Boolean h1Check) {
        this.h1Check = h1Check;
    }

    public String getH1Message() {
        return h1Message;
    }

    public void setH1Message(String h1Message) {
        this.h1Message = h1Message;
    }

    // Image Variables Getters/Setters
    public int getImgCount() {
        return imgCount;
    }

    public void setImgCount(int imgCount) {
        this.imgCount = imgCount;
    }

    public int getImgAltCount() {
        return imgAltCount;
    }

    public void setImgAltCount(int imgAltCount) {
        this.imgAltCount = imgAltCount;
    }

    public int getImgMissingAltCount() {
        return imgMissingAltCount;
    }

    public void setImgMissingAltCount(int imgMissingAltCount) {
        this.imgMissingAltCount = imgMissingAltCount;
    }

    public boolean getImgCheck() {
        return imgCheck;
    }

    public void setImgCheck(Boolean imgCheck) {
        this.imgCheck = imgCheck;
    }

    public String getImgMessage() {
        return imgMessage;
    }

    public void setImgMessage() {
        this.imgMessage = imgMessage;
    }


//    public void mobileFriendlyTest(final String url, final TextView msg, final ImageView passIcon, final ImageView failIcon, final ProgressBar mobileFriendlyLoadingIcon) {
//
//
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                String result = null;
//
//                try {
//                    String tempURL = getURL(url);
//                    String myData = "{\n" +
//                            "  \"requestScreenshot\": false,\n" +
//                            "  \"url\": \"" + tempURL + "\"\n" +
//                            "}";
//                    String apiKey = BuildConfig.API_KEY;
//                    URL GoogleMobileFriendlyTester = new URL("https://searchconsole.googleapis.com/v1/urlTestingTools/mobileFriendlyTest:run?key=" + apiKey);
//
//                    HttpURLConnection myConnection = (HttpURLConnection) GoogleMobileFriendlyTester.openConnection();
//                    myConnection.setConnectTimeout(5000);
//                    myConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                    myConnection.setRequestProperty("User-Agent", "Ghost-Studios-Website-Analysis-App-v0.1");
//                    myConnection.setRequestProperty("Accept", "application/json");
//                    myConnection.setDoOutput(true);
//                    myConnection.setDoInput(true);
//                    myConnection.setRequestMethod("POST");
//
//                    OutputStream outputStream = myConnection.getOutputStream();
//                    outputStream.write(myData.getBytes("UTF-8"));
//                    outputStream.close();
//
//                    if (myConnection.getResponseCode() == 200) {
//                        InputStream responseBody = myConnection.getInputStream();
//                        InputStreamReader responseBodyReader =
//                                new InputStreamReader(responseBody, "UTF-8");
//
//                        JsonReader jsonReader = new JsonReader(responseBodyReader);
//                        jsonReader.beginObject();
//
//                        while (jsonReader.hasNext()) { // Loop through all keys
//                            String key = jsonReader.nextName(); // Fetch the next key
//                            if (key.equals("mobileFriendliness")) { // Check if desired key
//                                // Fetch the value as a String
//                                String value = jsonReader.nextString();
//
//                                // Do something with the value
//                                // ...
//                                if (value == null) {
//                                    result = "MOBILE_FRIENDLY_TEST_RESULT_UNSPECIFIED";
//                                } else {
//                                    result = value;
//                                }
//
//                                break; // Break out of the loop
//                            } else {
//                                jsonReader.skipValue(); // Skip values of other keys
//                            }
//                        }
//                        responseBody.close();
//                    } else {
//                        // Error handling code goes here
//                        result = "There was an Error in the code." + myConnection.getResponseCode();
//                    }
//
//                    myConnection.disconnect();
//                } catch (IOException e) {
//
//                    // Error handling code goes here
//                    result = "There was an Error : " + e.getMessage();
//
//                }
//
//                final String finalResult = result;
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        // Capture the layout's TextView and set the string as its text
//
//
//                        String errMsg = "Error, Try Again";
//
//                        if (finalResult != null && finalResult.equals("MOBILE_FRIENDLY_TEST_RESULT_UNSPECIFIED")) {
//                            msg.setText(errMsg);
//                        } else if (finalResult != null && finalResult.equals("MOBILE_FRIENDLY")) {
//                            passIcon.setVisibility(View.VISIBLE);
//                        } else if (finalResult != null && finalResult.equals("NOT_MOBILE_FRIENDLY")) {
//                            failIcon.setVisibility(View.VISIBLE);
//                        }
//                        mobileFriendlyLoadingIcon.setVisibility(View.GONE);
//                    }
//                });
//            }
//
//        }).start();
//    }
//
//    public void checkWebsite(final String url, final TextView title, final ImageView  titlePassIcon, final ImageView  titleFailIcon, final ProgressBar  titleLoadingPanel,
//                              final TextView desc, final ImageView  descPassIcon, final ImageView  descFailIcon, final ProgressBar  descLoadingPanel,
//                              final TextView h1, final ImageView  h1PassIcon, final ImageView  h1FailIcon, final ProgressBar  h1LoadingPanel,
//                              final TextView alt, final ImageView  altPassIcon, final ImageView  altFailIcon, final ProgressBar  altLoadingPanel) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    String tempURL = getURL(url);
//                    Connection.Response response = Jsoup.connect(tempURL).ignoreContentType(true).userAgent("Ghost-Studios-Website-Analysis-App-v0.1").timeout(12000).followRedirects(true).execute();
//
//                    if (response.statusCode() == 200) {
//
//                        Document source = response.parse();
//
//                        final Title titleCheck = checkTitle(source);
//                        final Description descCheck = checkDesc(source);
//                        final H1 h1Check = checkH1(source);
//                        final Images altCheck = checkAlt(source);
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                if (titleCheck.getBool()) {
//                                    titlePassIcon.setVisibility(View.VISIBLE);
//                                } else {
//                                    titleFailIcon.setVisibility(View.VISIBLE);
//                                    title.setText(titleCheck.getMessage());
//                                }
//                                titleLoadingPanel.setVisibility(View.GONE);
//                            }
//                        });
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                if (descCheck.getBool()) {
//                                    descPassIcon.setVisibility(View.VISIBLE);
//                                } else {
//                                    descFailIcon.setVisibility(View.VISIBLE);
//                                    desc.setText(descCheck.getMessage());
//                                }
//                                descLoadingPanel.setVisibility(View.GONE);
//                            }
//                        });
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                if (h1Check.getBool()) {
//                                    h1PassIcon.setVisibility(View.VISIBLE);
//                                } else {
//                                    h1FailIcon.setVisibility(View.VISIBLE);
//                                    h1.setText(h1Check.getMessage());
//                                }
//                                h1LoadingPanel.setVisibility(View.GONE);
//                            }
//                        });
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                if (altCheck.getBool()) {
//                                    altPassIcon.setVisibility(View.VISIBLE);
//                                } else {
//                                    altFailIcon.setVisibility(View.VISIBLE);
//                                    alt.setText(altCheck.getMessage());
//                                }
//                                altLoadingPanel.setVisibility(View.GONE);
//                            }
//                        });
//                    } else {
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                AlertDialog alertDialog = new AlertDialog.Builder(activity.this).create();
//                                alertDialog.setTitle("Alert");
//                                alertDialog.setMessage("Error processing website. Please Try Again.");
//                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                                        new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                                startActivity(intent);
//                                                dialog.dismiss();
//                                            }
//                                        });
//                                alertDialog.show();
//                            }
//                        });
//                    }
//                } catch (final IOException e) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            AlertDialog alertDialog = new AlertDialog.Builder(Results.this).create();
//                            alertDialog.setTitle("Error");
//                            alertDialog.setMessage("There was an Error : " + e.getMessage());
//                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                                    new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                            startActivity(intent);
//                                            dialog.dismiss();
//                                        }
//                                    });
//                            alertDialog.show();
//                        }
//                    });
//                }
//            }
//        }).start();
//    }
//
//    public String getURL(String uri) {
//
//
//        String tempUrl;
//        if (!uri.matches("^(http|https|ftp)://.*$")) {
//            tempUrl = "http://" + uri;
//        } else {
//            tempUrl = uri;
//        }
//
//        return tempUrl;
//    }
//
//    public class Title {
//        private Boolean bool;
//        private String message;
//        private String title;
//
//        public Boolean getBool() {
//            return bool;
//        }
//
//        public void setBool(Boolean bool) {
//            this.bool = bool;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//    }
//
//    private Title checkTitle(Document source) {
//
//        Title check = new Title();
//
//        if (source.title() != null) {
//            if (source.title().length() < 40) {
//                check.setMessage("Title to Short");
//                check.setBool(false);
//            } else if (source.title().length() > 75) {
//                check.setMessage("Title To Long");
//                check.setBool(false);
//            } else {
//                check.setMessage("pass");
//                check.setBool(true);
//            }
//            check.setTitle(source.title());
//        } else {
//            check.setMessage("No Title");
//            check.setBool(false);
//        }
//
//        return check;
//    }
//
//    public class Description {
//        private Boolean bool;
//        private String message;
//        private String description;
//
//        public Boolean getBool() {
//            return bool;
//        }
//
//        public void setBool(Boolean bool) {
//            this.bool = bool;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//    }
//
//    private Description checkDesc(Document source) {
//
//        Description check = new Description();
//
//        String desc = source.select("meta[name=description]").get(0).attr("content");
//
//        if (desc != null) {
//            if (desc.length() < 100) {
//                check.setMessage("Description to Short");
//                check.setBool(false);
//            } else if (source.title().length() > 300) {
//                check.setMessage("Description To Long");
//                check.setBool(false);
//            } else {
//                check.setMessage("pass");
//                check.setBool(true);
//            }
//            check.setDescription(desc);
//        } else {
//            check.setMessage("No Description");
//            check.setBool(false);
//        }
//
//        return check;
//    }
//
//    public class H1 {
//        private Boolean bool;
//        private String message;
//        private String h1;
//
//        public Boolean getBool() {
//            return bool;
//        }
//
//        private void setBool(Boolean bool) {
//            this.bool = bool;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        private void setMessage(String message) {
//            this.message = message;
//        }
//
//        public String getH1() {
//            return h1;
//        }
//
//        private void setH1(String h1) {
//            this.h1 = h1;
//        }
//    }
//
//    private H1 checkH1(Document source) {
//
//        Elements h1s = source.select("h1");
//
//        H1 check = new H1();
//
//        if (h1s.size() > 1) {
//            check.setMessage("To Many H1's");
//            check.setBool(false);
//        } else if (h1s.size() < 1) {
//            check.setMessage("Missing H1");
//            check.setBool(false);
//        } else {
//            check.setMessage("pass");
//            check.setBool(true);
//            check.setH1(h1s.first().text());
//        }
//
//        return check;
//    }
//
//    public class Images {
//        private int imgCount;
//        private int altCount;
//        private int missingAltCount;
//        private boolean bool;
//        private String message;
//
//        public boolean getBool() {
//            return bool;
//        }
//
//        private void setBool(boolean bool) {
//            this.bool = bool;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        private void setMessage(String message) {
//            this.message = message;
//        }
//
//        public int getImgCount() {
//            return imgCount;
//        }
//
//        private void setImgCount(int imgCount) {
//            this.imgCount = imgCount;
//        }
//
//        public int getAltCount() {
//            return altCount;
//        }
//
//        private void setAltCount(int altCount) {
//            this.altCount = altCount;
//        }
//
//        public int getMissingAltCount() {
//            return missingAltCount;
//        }
//
//        private void setMissingAltCount(int missingAltCount) {
//            this.missingAltCount = missingAltCount;
//        }
//
//    }
//
//    private Images checkAlt(Document source) {
//
//        Images check = new Images();
//
//        Elements imgs = source.select("img");
//
//        int missingAltCount = 0;
//        for (Element img : imgs) {
//            if (img.attr("alt") == null || img.attr("alt").equals("")) {
//                missingAltCount++;
//            }
//        }
//
//        if (missingAltCount > 0 && imgs.size() > 0) {
//            check.setBool(false);
//            String msg = missingAltCount + " Images Missing Alt attr";
//            check.setMessage(msg);
//        } else {
//            check.setBool(true);
//        }
//
//        check.setMissingAltCount(missingAltCount);
//        check.setImgCount(imgs.size());
//        check.setAltCount(imgs.size() - missingAltCount);
//
//        return check;
//    }
}
