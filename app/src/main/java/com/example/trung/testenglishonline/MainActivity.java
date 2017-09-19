package com.example.trung.testenglishonline;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.nkzawa.engineio.client.Socket;
import com.github.nkzawa.socketio.client.IO;

import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_QUIZ = 0;

    @BindView(R.id.appBar) Toolbar mToolbar;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;

    @BindView(R.id.btnQuiz) Button _btnQuiz;
    @BindView(R.id.btnRank) Button _btnRank;
    @BindView(R.id.btnAbount) Button _btnAbount;


    private com.github.nkzawa.socketio.client.Socket socket;
    {
        try {
            socket = IO.socket("http://192.168.1.191:5000");
        } catch (URISyntaxException e) {}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        socket.connect();

        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        //setSupportActionBar(mToolbar);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();




        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.menu_inbox) {
                            Toast.makeText(MainActivity.this,
                                    "Selected Inbox",
                                    Toast.LENGTH_SHORT).show();
                            mDrawerLayout.closeDrawer(mNavigationView);
                        }
                        return false;
                    }
                });

        View view = getLayoutInflater().inflate(R.layout.layout_drawer_header,
                mNavigationView, false);

        mNavigationView.addHeaderView(view);

        /*Intent itLogin = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(itLogin);*/

        /*btnItlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                *//*Toast.makeText(getApplicationContext(), "Dc  roi", Toast.LENGTH_LONG).show();*//*
            }
        });*/

        _btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivityForResult(intent, REQUEST_QUIZ);
            }
        });

    }

}
