package com.example.mynotes.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.mynotes.R;


/**
 * Created by compind-pc3 on 22/11/17.
 */
public abstract class BasicActivity extends AppCompatActivity
{
    private long lastPressedTime;
    private static final int PERIOD = 2000;
    public static BasicActivity basicActivity;
    Typeface FontLoaderTypeface;
    String ExternalFontPath;

    protected abstract int getContentResId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(getContentResId());

        basicActivity = this;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                navigateToParent();
        }
        return super.onOptionsItemSelected(item);
    }


    public void navigateToParent() {
        Intent in= NavUtils.getParentActivityIntent(this);
        if(in==null)
        {

            this.finish();


        }
        else
        {

            NavUtils.navigateUpFromSameTask(this);
        }

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    if (event.getDownTime() - lastPressedTime < PERIOD) {
                        moveTaskToBack(true);
                    } else {
                        lastPressedTime = event.getEventTime();
                    }
                    return true;
            }
        }
        return false;
    }

    public void setActionBar(String title)
    {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.back);
        TextView TextViewNewFont = new TextView(BasicActivity.this);

        ActionBar.LayoutParams layoutparams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        TextViewNewFont.setLayoutParams(layoutparams);

        TextViewNewFont.setText(title);

        TextViewNewFont.setTextColor(Color.WHITE);

        TextViewNewFont.setGravity(Gravity.LEFT);

        TextViewNewFont.setTextSize(25);

        ExternalFontPath = "carrington.ttf";

        FontLoaderTypeface = Typeface.createFromAsset(getAssets(), ExternalFontPath);

        TextViewNewFont.setTypeface(FontLoaderTypeface);

        actionbar.setCustomView(TextViewNewFont);
    }



}
