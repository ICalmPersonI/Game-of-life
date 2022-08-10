package com.example.gameoflife.gneration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.Arrays;
import java.util.Formatter;

@SuppressLint("ViewConstructor")
public class GenerationView extends View {
    public static final int CELL_HEIGHT = 10;
    public static final int CELL_WIDTH = 10;

    private final int DEAD_CELL_COLOR;
    private final int ALIVE_CELL_COLOR;
    private final int TEXT_COLOR;

    private final StringBuilder statusMsg = new StringBuilder();
    private final Formatter formatter = new Formatter(statusMsg);
    private final Rect rect;
    private final Paint paint;
    private Cell[][] filed;

    private int rectY = 0;
    private int rectX = 0;
    private long generationNumber = 0;


    public GenerationView(Context context, int deadCellColor, int aliveCellColor,
                          int textColor, int height, int width) {
        super(context);
        this.DEAD_CELL_COLOR = deadCellColor;
        this.ALIVE_CELL_COLOR = aliveCellColor;
        this.rect = new Rect();
        this.paint = new Paint();
        this.TEXT_COLOR = textColor;
        this.filed = new Cell[height][width];
        for (Cell[] cell : this.filed) Arrays.fill(cell, Cell.DEAD);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Cell[] cells : filed) {
            for (Cell cell : cells) {
                rect.set(rectX, rectY, rectX + CELL_WIDTH, rectY + CELL_HEIGHT);
                int color = cell == Cell.ALIVE ? ALIVE_CELL_COLOR : DEAD_CELL_COLOR;
                paint.setColor(color);
                canvas.drawRect(rect, paint);
                rectX += CELL_WIDTH;
            }
            rectX = 0;
            rectY += CELL_HEIGHT;
        }
        rectY = 0;
        rectX = 0;

        paint.setColor(TEXT_COLOR);
        paint.setTextSize(34f);
        canvas.drawText(statusMsg.toString(), 10, 30, paint);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        invalidate();
    }

    public void update(Cell[][] generation) {
        this.filed = generation;
        generationNumber++;
        statusMsg.delete(0, statusMsg.length());
        formatter.format("Generation = %d", generationNumber);

    }
}
