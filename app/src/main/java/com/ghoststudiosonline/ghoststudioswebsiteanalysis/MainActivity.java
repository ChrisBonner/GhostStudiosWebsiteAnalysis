package com.ghoststudiosonline.ghoststudioswebsiteanalysis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkURL(View view) {

        EditText enter_url = findViewById(R.id.enter_url);
        String url = enter_url.getText().toString();

        EditText enter_search = findViewById(R.id.enter_search);
        String query = enter_search.getText().toString();

        if (isValid(url)) {
            checkRankings(url, query);
        } else {
            enter_url.setText("");
            enter_url.setHint("Invalid URL. Include http://");
            enter_url.setHintTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    /* Returns true if url is valid */
    public static boolean isValid(String url) {
        /* Try creating a valid URL */
        try {
            return URLUtil.isValidUrl(url);
        }

        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }

    public void checkRankings(String url, String query) {

        if(query != null && !query.trim().equals("")) {
            Intent intent = new Intent(this, Rankings.class);
            intent.putExtra("query", query);
            intent.putExtra("url", url);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, Results.class);

            Website siteToCheck = new Website(url, 0);
            intent.putExtra("siteToCheck", siteToCheck);
            startActivity(intent);
        }
    }
}


