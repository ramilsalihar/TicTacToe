package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Objects;

public class ChooseSymbolActivity extends AppCompatActivity implements View.OnTouchListener {


    private ImageView CrossImg;
    private ImageView CrossRadioImg;
    private ImageView CircleImg;
    private ImageView CircleRadioImg;
    private Button ContinueBtn;

    int PICK_SIDE ;
    private String playerOne;
    private String playerTwo;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_choose_symbol);

        playerOne = getIntent().getStringExtra("p1");
        playerTwo = getIntent().getStringExtra("p2");

        ImageView backBtn = findViewById(R.id.pick_side_back_btn);
        CrossImg= findViewById(R.id.pick_side_cross_img);
        CircleImg= findViewById(R.id.pick_side_circle_img);
        CrossRadioImg= findViewById(R.id.pick_side_cross_radio);
        CircleRadioImg= findViewById(R.id.pick_side_circle_radio);

        ContinueBtn = findViewById(R.id.pick_side_continue_btn);

        // CrossRadioImg.setOnTouchListener(this);
        CrossRadioImg.setOnClickListener(v -> {

            PICK_SIDE = 0;
            CrossRadioImg.setImageResource(R.drawable.radio_button_checked);
            CircleRadioImg.setImageResource(R.drawable.radio_button_unchecked);
            CircleImg.setAlpha(0.3f);
            CrossImg.setAlpha(1.0f);
            //Intent intent = new Intent(.this,Ch.class);
            // startActivity(intent);
        });

        // CircleRadioImg.setOnTouchListener(this);
        CircleRadioImg.setOnClickListener(v -> {


            PICK_SIDE= 1;
            CircleRadioImg.setImageResource(R.drawable.radio_button_checked);
            CrossRadioImg.setImageResource(R.drawable.radio_button_unchecked);
            CrossImg.setAlpha(0.3f);
            CircleImg.setAlpha(1.0f);

            //Intent intent = new Intent(.this,Ch.class);
            // startActivity(intent);
        });

        backBtn.setOnClickListener(v -> {



            onBackPressed();
            //Intent intent = new Intent(.this,Ch.class);
            // startActivity(intent);
        });

        ContinueBtn.setOnTouchListener(this);
        ContinueBtn.setOnClickListener(v -> {

            Intent intent = new Intent(    ChooseSymbolActivity.this,OfflineGameActivity.class);
            intent.putExtra("p1",playerOne);
            intent.putExtra("p2",playerTwo);
            intent.putExtra("ps",PICK_SIDE);
            startActivity(intent);
        });
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v == ContinueBtn) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                v.setAlpha(0.5f);
            } else {
                v.setAlpha(1f);
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}