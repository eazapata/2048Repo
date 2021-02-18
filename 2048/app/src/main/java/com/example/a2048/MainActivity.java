package com.example.a2048;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener, SwipeCallback {

    private float firstTouchX;
    private float firstTouchY;
    private TextView[][] textViews;
    private int[][] textViewValues = new int[4][4];
    private GridLayout grid;
    private SwipeListener swipeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeListener = new SwipeListener(this, this);

        this.textViews = fillArray();
        this.grid = (GridLayout) findViewById(R.id.grid);
        this.grid.setOnTouchListener(this);
        setRandomNumber();
    }

    @Override
    public void onSwipe(Direction direction) {

        switch (direction) {
            case UP:
                for (int i = 1; i < this.textViewValues.length; i++) {
                    boolean operated = false;
                    for (int j = 0; j < this.textViewValues[i].length; j++) {
                        for (int k = i; k > 0; k--) {
                            if (this.textViewValues[k][j] != 0 && this.textViewValues[k - 1][j] == 0) {
                                this.textViewValues[k - 1][j] = this.textViewValues[k][j];
                                this.textViews[k - 1][j].setText(String.valueOf(this.textViewValues[k - 1][j]));
                                this.textViewValues[k][j] = 0;
                                this.textViews[k][j].setText("");
                            }
                            if (this.textViewValues[k][j] != 0 && this.textViewValues[k - 1][j] == this.textViewValues[k][j]
                                    && !operated) {
                                this.textViewValues[k - 1][j] += this.textViewValues[k][j];
                                // setBackground(this.textViewValues[k - 1][j], this.textViews[k - 1][j]);
                                this.textViews[k - 1][j].setText(String.valueOf(this.textViewValues[k - 1][j]));
                                this.textViewValues[k][j] = 0;
                                this.textViews[k][j].setText("");
                                operated = true;
                            }
                        }
                    }
                }
                setRandomNumber();
                break;
            case DOWN:
                for (int i = this.textViewValues.length - 2; i >= 0; i--) {
                    for (int j = 0; j < this.textViewValues[i].length; j++) {
                        boolean operated = false;
                        for (int k = i; k < this.textViewValues.length - 1; k++) {
                            if (this.textViewValues[k][j] != 0 && this.textViewValues[k + 1][j] == 0) {
                                this.textViewValues[k + 1][j] = this.textViewValues[k][j];
                                this.textViews[k + 1][j].setText(String.valueOf(this.textViewValues[k + 1][j]));
                                this.textViewValues[k][j] = 0;
                                this.textViews[k][j].setText("");
                            }
                            if (this.textViewValues[k][j] != 0 && this.textViewValues[k + 1][j] == this.textViewValues[k][j]
                                    && !operated) {
                                this.textViewValues[k + 1][j] += this.textViewValues[k][j];
                                //setBackground(this.textViewValues[k + 1][j], this.textViews[k + 1][j]);
                                this.textViews[k + 1][j].setText(String.valueOf(this.textViewValues[k + 1][j]));
                                this.textViewValues[k][j] = 0;
                                this.textViews[k][j].setText("");
                                operated = true;
                            }
                        }
                    }
                }
                setRandomNumber();
                break;
            case LEFT:
                for (int i = 0; i < this.textViewValues.length; i++) {
                    boolean operated = false;
                    for (int j = 1; j < this.textViewValues[i].length; j++) {
                        for (int k = j; k > 0; k--) {
                            if (this.textViewValues[i][k] != 0 && this.textViewValues[i][k - 1] == 0) {
                                this.textViewValues[i][k - 1] = this.textViewValues[i][k];
                                this.textViews[i][k - 1].setText(String.valueOf(this.textViewValues[i][k - 1]));
                                this.textViewValues[i][k] = 0;
                                this.textViews[i][k].setText("");
                            }
                            if (this.textViewValues[i][k] != 0 && this.textViewValues[i][k - 1] == this.textViewValues[i][k]
                                    && !operated) {
                                this.textViewValues[i][k - 1] += this.textViewValues[i][k];
                                // setBackground(this.textViewValues[i][k - 1], this.textViews[i][k - 1]);
                                this.textViews[i][k - 1].setText(String.valueOf(this.textViewValues[i][k - 1]));
                                this.textViewValues[i][k] = 0;
                                this.textViews[i][k].setText("");
                                operated = true;
                            }
                        }
                    }
                }
                setRandomNumber();
                break;
            case RIGHT:
                for (int i = 0; i < this.textViewValues.length; i++) {
                    boolean operated = false;
                    for (int j = this.textViewValues.length - 2; j >= 0; j--) {
                        for (int k = j; k < this.textViewValues.length - 1; k++) {
                            if (this.textViewValues[i][k] != 0 && this.textViewValues[i][k + 1] == 0) {
                                this.textViewValues[i][k + 1] = this.textViewValues[i][k];
                                this.textViews[i][k + 1].setText(String.valueOf(this.textViewValues[i][k + 1]));
                                this.textViewValues[i][k] = 0;
                                this.textViews[i][k].setText("");
                            }
                            if (this.textViewValues[i][k] != 0 && this.textViewValues[i][k + 1] == this.textViewValues[i][k]
                                    && !operated) {
                                this.textViewValues[i][k + 1] += this.textViewValues[i][k];
                                //setBackground(this.textViewValues[i][k + 1], this.textViews[i][k + 1]);
                                this.textViews[i][k + 1].setText(String.valueOf(this.textViewValues[i][k + 1]));
                                this.textViewValues[i][k] = 0;
                                this.textViews[i][k].setText("");
                                operated = true;
                            }
                        }
                    }
                }
                setRandomNumber();
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        swipeListener.onTouchEvent(event);
        return true;
    }

    public void setRandomNumber() {
        Random random = new Random();
        int pos1 = random.nextInt(4);
        int pos2 = random.nextInt(4);
        int[] numbers = {2, 4};
        int value = numbers[random.nextInt(2)];
        while (this.textViewValues[pos1][pos2] != 0) {
            pos1 = random.nextInt(4);
            pos2 = random.nextInt(4);

        }
        this.textViewValues[pos1][pos2] = value;
        this.textViews[pos1][pos2].setText(String.valueOf(value));

     /*   if (value == 2) {
            this.textViewValues[pos1][pos2] = value;
            this.textViews[pos1][pos2].setCompoundDrawablesWithIntrinsicBounds(R.mipmap.two, 0, 0, 0);
        } else {
            this.textViewValues[pos1][pos2] = value;
            this.textViews[pos1][pos2].setCompoundDrawablesWithIntrinsicBounds(R.mipmap.four, 0, 0, 0);
        }*/

    }

    private TextView[][] fillArray() {
        TextView textView1 = (TextView) findViewById(R.id.one);
        TextView textView2 = (TextView) findViewById(R.id.two);
        TextView textView3 = (TextView) findViewById(R.id.three);
        TextView textView4 = (TextView) findViewById(R.id.four);
        TextView textView5 = (TextView) findViewById(R.id.five);
        TextView textView6 = (TextView) findViewById(R.id.six);
        TextView textView7 = (TextView) findViewById(R.id.seven);
        TextView textView8 = (TextView) findViewById(R.id.eight);
        TextView textView9 = (TextView) findViewById(R.id.nine);
        TextView textView10 = (TextView) findViewById(R.id.ten);
        TextView textView11 = (TextView) findViewById(R.id.eleven);
        TextView textView12 = (TextView) findViewById(R.id.twelve);
        TextView textView13 = (TextView) findViewById(R.id.thirteen);
        TextView textView14 = (TextView) findViewById(R.id.fourteen);
        TextView textView15 = (TextView) findViewById(R.id.fifteen);
        TextView textView16 = (TextView) findViewById(R.id.sixteen);
        TextView[][] arrayText = {
                {textView1, textView2, textView3, textView4},
                {textView5, textView6, textView7, textView8},
                {textView9, textView10, textView11, textView12},
                {textView13, textView14, textView15, textView16}};
        return arrayText;
    }

    private void setBackground(int number, TextView textView) {

        switch (number) {
            case 4:
                textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.four, 0, 0, 0);
                break;
            case 8:
                textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.eight, 0, 0, 0);
                break;
            case 16:
                textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.sixteen, 0, 0, 0);
                break;
            case 32:
                textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.thirtytwo, 0, 0, 0);
                break;
            case 64:
                textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.sixty, 0, 0, 0);
                break;
            case 128:
                textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.hundred, 0, 0, 0);
                break;
            case 256:
                textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.twohundred, 0, 0, 0);
                break;
            case 512:
                textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.fivehundred, 0, 0, 0);
                break;
            case 1024:
                textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.thousand, 0, 0, 0);
                break;
            case 2048:
                textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.twothousand, 0, 0, 0);
                break;

        }

    }

}