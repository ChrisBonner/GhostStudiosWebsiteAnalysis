package com.ghoststudiosonline.ghoststudioswebsiteanalysis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void generalSettings(View view){
        Intent intent = new Intent(this, GeneralSettingsActivity.class);

        startActivity(intent);
    }

    public void blacklistSettings(View view) {
        Intent intent = new Intent(this, BlacklistActivity.class);

        startActivity(intent);
    }


}
