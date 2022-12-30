package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Objects;

public class OfflineGetPlayersNamesActivity extends AppCompatActivity implements View.OnTouchListener {


    private String playerOne, playerTwo;

    private EditText playerOneName, playerTwoName;
    private Button playerOneButton, playerTwoButton;
    private LinearLayout playerOneLayout, playerTwoLayout;

    boolean isLayout = true;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_offline_get_players_names);


        ImageView backBtn = findViewById(R.id.player_names_back_btn);
        playerOneName = findViewById(R.id.player_one_name_edttxt);
        playerTwoName = findViewById(R.id.player_two_name_edttxt);
        playerOneButton = findViewById(R.id.player_one_btn);
        playerTwoButton = findViewById(R.id.player_two_btn);
        playerOneLayout = findViewById(R.id.player_one_layout);
        playerTwoLayout = findViewById(R.id.player_two_layout);


        playerOneButton.setOnTouchListener(this);
        playerOneButton.setOnClickListener(v -> {

            if (TextUtils.isEmpty(playerOneName.getText().toString())) {
                Toast.makeText(getBaseContext(), "Enter Name", Toast.LENGTH_LONG).show();
            } else {
                isLayout = false;
                playerOneLayout.setVisibility(View.GONE);
                playerTwoLayout.setVisibility(View.VISIBLE);
                slideUp(playerTwoLayout);
                playerOne = playerOneName.getText().toString();
            }
        });

        backBtn.setOnClickListener(v -> onBackPressed());


        playerTwoButton.setOnTouchListener(this);
        playerTwoButton.setOnClickListener(v -> {

            if (TextUtils.isEmpty(playerTwoName.getText().toString())) {
                Toast.makeText(getBaseContext(), "Enter Name", Toast.LENGTH_LONG).show();
            } else {

                playerTwo = playerTwoName.getText().toString();
                Intent intent = new Intent(OfflineGetPlayersNamesActivity.this,ChooseSymbolActivity.class);
                intent.putExtra("p1",playerOne);
                intent.putExtra("p2",playerTwo);


                startActivity(intent);
            }
        });

    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(isLayout)
        {
            if (v == playerOneButton) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setAlpha(0.5f);
                }   else {
                    v.setAlpha(1f);
                }
            }
        }
        else {
            if (v == playerTwoButton) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setAlpha(0.5f);
                }   else {
                    v.setAlpha(1f);
                }
            }
        }

        return false;
    }


    // slide the view from below itself to the current position
    public void slideUp(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

