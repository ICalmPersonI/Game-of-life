package com.example.gameoflife;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameoflife.gneration.Generation;
import com.example.gameoflife.gneration.GenerationController;
import com.example.gameoflife.gneration.GenerationView;


public class GameActivity extends AppCompatActivity {

    private Handler handler;
    private GenerationController controller;

    private final Runnable generatorGenerations = new Runnable() {
        @Override
        public void run() {
            controller.updateView();
            controller.generateGeneration();
            handler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        handler = new Handler();
        FrameLayout frameLayout = findViewById(R.id.game_field);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            DisplayMetrics metrics = getDisplayMetrics(this);
            int screenHeight = metrics.heightPixels / GenerationView.CELL_HEIGHT;
            int screenWidth = metrics.widthPixels / GenerationView.CELL_WIDTH;
            int deadCellColor = extras.getInt("deadCellColor");
            int aliveCallColor = extras.getInt("aliveCallColor");
            int textColor = extras.getInt("textColor");
            int seed = extras.getInt("seed");

            Generation generation = new Generation(seed, screenHeight, screenWidth);
            GenerationView view = new GenerationView(
                    this,
                    deadCellColor,
                    aliveCallColor,
                    textColor,
                    screenHeight,
                    screenWidth
            );
            view.setEnabled(false);
            controller = new GenerationController(generation, view);
            frameLayout.addView(view);
            generatorGenerations.run();
        }

        findViewById(R.id.back_button).setOnClickListener(v -> finish());
    }

    private DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics;
    }
}
