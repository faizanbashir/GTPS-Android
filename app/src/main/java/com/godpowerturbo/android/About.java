package com.godpowerturbo.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import api.Connection;
import api.Resource;

public class About extends AppCompatActivity{

    DrawerLayout dw;
    PercentRelativeLayout rl, l;
    android.support.v4.app.ActionBarDrawerToggle mDrawerToggle;
    EditText searchbox;
    ImageButton nav, nav_home, nav_catalogue, nav_upcomingevents,
            nav_newlaunch, nav_turbofailure, nav_aboutus, nav_enquiry,
            nav_faq, nav_feedback, nav_search;
    private static final String TAG = About.class.getSimpleName();
    private Context mCtx = this;
    TextView txtAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Log.e(TAG, "onCreate");
        registerViews();
    }

    private void registerViews(){
        txtAbout = (TextView) findViewById(R.id.txtAbout);
        txtAbout.setMovementMethod(LinkMovementMethod.getInstance());
        txtAbout.setText(Html.fromHtml(getString(R.string.aboutText)));
        //Drawer Buttons
        nav_home = (ImageButton) findViewById(R.id.nav_home);
        nav_catalogue = (ImageButton) findViewById(R.id.nav_catalogue);
        nav_upcomingevents = (ImageButton) findViewById(R.id.nav_upcomingevents);
        nav_newlaunch = (ImageButton) findViewById(R.id.nav_newlaunch);
        nav_turbofailure = (ImageButton) findViewById(R.id.nav_turbofailure);
        nav_aboutus = (ImageButton) findViewById(R.id.nav_aboutus);
        nav_enquiry = (ImageButton) findViewById(R.id.nav_enquiry);
        nav_faq = (ImageButton) findViewById(R.id.nav_faq);
        nav_feedback = (ImageButton) findViewById(R.id.nav_feedback);
        nav_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(About.this, Main.class);
                startActivity(i);
            }
        });
        nav_catalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(About.this, CompanyListing.class);
                startActivity(i);
            }
        });
        nav_upcomingevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Connection.getConnection(mCtx)){
                    Intent i = new Intent(About.this, EventsListing.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        nav_newlaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Connection.getConnection(mCtx)){
                    Intent i = new Intent(About.this, HotProductsListing.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        nav_turbofailure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(About.this, Troubleshootlisting.class);
                startActivity(i);
            }
        });
        nav_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(About.this, About.class);
                startActivity(i);
            }
        });
        nav_enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(About.this, Contact.class);
                startActivity(i);
            }
        });
        nav_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(About.this, Faq.class);
                startActivity(i);
            }
        });
        nav_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(About.this, Feedback.class);
                startActivity(i);
            }
        });
        dw = (DrawerLayout) findViewById(R.id.nav_main);
        rl = (PercentRelativeLayout) findViewById(R.id.nav_left);
        pk();
    }


    private void pk(){
        ActionBar idk = getSupportActionBar();
        idk.setDisplayHomeAsUpEnabled(false);
        idk.setDisplayShowTitleEnabled(false);
        idk.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        idk.setStackedBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.header, null);
        idk.setCustomView(mCustomView);
        idk.setDisplayShowCustomEnabled(true);
        mDrawerToggle = new android.support.v4.app.ActionBarDrawerToggle(this, dw, R.drawable.option, 0, 0){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                l.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                l.setVisibility(View.VISIBLE);
            }
        };
        dw.setDrawerListener(mDrawerToggle);
        l = (PercentRelativeLayout) mCustomView.findViewById(R.id.linear_search);
        nav = (ImageButton) mCustomView.findViewById(R.id.nav_menu);
        nav_search = (ImageButton) mCustomView.findViewById(R.id.search);
        searchbox = (EditText) mCustomView.findViewById(R.id.query);
        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "NAV MENU CLICK");
                if(!dw.isDrawerOpen(Gravity.LEFT)){
                    l.setVisibility(View.INVISIBLE);
                    dw.openDrawer(rl);
                }else{
                    l.setVisibility(View.VISIBLE);
                    dw.closeDrawer(rl);
                }

            }
        });
        nav_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchbox.getText().toString();
                Log.e(TAG, "NAV SEARCH QUERY: " + query);
                Bundle bd = new Bundle();
                bd.putString("query", query);
                Intent i = new Intent(About.this, PartNumberListing.class);
                i.putExtras(bd);
                startActivity(i);
            }
        });
    }

}
