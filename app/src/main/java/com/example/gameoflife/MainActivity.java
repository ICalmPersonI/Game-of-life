package com.example.gameoflife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.github.dhaval2404.colorpicker.ColorPickerDialog;
import com.github.dhaval2404.colorpicker.model.ColorShape;

import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AtomicInteger deadCellColor = new AtomicInteger(Color.parseColor("#4C6472"));
        AtomicInteger aliveCallColor = new AtomicInteger(Color.parseColor("#57A4B1"));
        AtomicInteger textColor = new AtomicInteger(Color.WHITE);
        EditText seedValue = findViewById(R.id.seed);

        findViewById(R.id.dead_cell_color)
                .setOnClickListener(v -> pickColor(deadCellColor, v));
        findViewById(R.id.alive_cell_color)
                .setOnClickListener(v -> pickColor(aliveCallColor, v));
        findViewById(R.id.text_color)
                .setOnClickListener(v -> pickColor(textColor, v));

        findViewById(R.id.start_button).setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("deadCellColor", deadCellColor.get());
            intent.putExtra("aliveCallColor", aliveCallColor.get());
            intent.putExtra("textColor", textColor.get());
            int seed = seedValue.getText().length() == 0 ? 1 : Integer.parseInt(seedValue.getText().toString());
            intent.putExtra("seed", seed);
            startActivity(intent);
        });
    }

    private void pickColor(AtomicInteger integer, View button) {
        new ColorPickerDialog
                .Builder(this)
                .setTitle("Pick Theme")
                .setColorShape(ColorShape.SQAURE)
                .setDefaultColor(R.color.black)
                .setColorListener((color, colorHex) -> {
                    integer.set(color);
                    button.setBackgroundColor(color);
                })
                .show();
    }
}