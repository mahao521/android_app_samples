package com.example.yabinc.linkgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MainActivity";
    private GameView mGameView;
    private String[] levels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        levels = getResources().getStringArray(R.array.level_names);
        Bitmap animalBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.animals);
        Bitmap winBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.win);
        Bitmap loseBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.lose);
        mGameView = (GameView) findViewById(R.id.gameView);
        mGameView.init(animalBitmap, winBitmap, loseBitmap, levels.length);

    }

    public void setTitleByLevel(int level) {
        setTitle(levels[level - 1]);
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
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_hint) {
            mGameView.hint();
            return true;
        }
        if (id == R.id.action_restart) {
            mGameView.restart(false);
            return true;
        }
        if (id == R.id.action_set_level_2) {
            mGameView.setLevel(2);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGameView != null) {
            mGameView.startTimer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGameView != null) {
            mGameView.stopTimer();
        }
    }
}