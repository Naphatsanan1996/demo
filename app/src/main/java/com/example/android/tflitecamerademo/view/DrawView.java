package com.example.android.tflitecamerademo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.tflitecamerademo.Camera2BasicFragment;
import com.example.android.tflitecamerademo.R;

public class
DrawView extends View {
    private static final int HEATMAPWIDTH = 96;
    private static final int HEATMAPHEIGHT = 96;
    private static final int NUMJOINT = 14;

    /**
     * Max preview width that is guaranteed by Camera2 API
     */
    public static int MAX_PREVIEW_HEIGHT;

    /**
     * Max preview height that is guaranteed by Camera2 API
     */
    public static int MAX_PREVIEW_WIDTH;
    private static int count = 0;
    private static int count1 = 0;

    static View myView;
    private Paint[] paint = new Paint[14];
    private Paint ppaint;
    private Paint pointp;
    private static float[][] position = new float[14][2];
    private static float[][] arr = new float[14][2];


    double X = 0,
            Y = 0,
            x1[] = new double[8],
            x2[] = new double[8],
            y1[] = new double[8],
            y2[] = new double[8];
    double result[] = new double[8];
    boolean ch[] = new boolean[8];


    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        myView = DrawView.this;
        ppaint = new Paint();
        ppaint.setStrokeWidth(15);
        ppaint.setColor(Color.WHITE);
        pointp = new Paint();
        pointp.setStrokeWidth(30);
        pointp.setColor(Color.RED);
        for (int i = 0; i < NUMJOINT; i++) {
            paint[i] = new Paint();
            paint[i].setStyle(Paint.Style.STROKE);
            paint[i].setStrokeWidth(30);
        }
        paint[0].setColor(Color.BLACK);
        paint[1].setColor(Color.RED);
        paint[2].setColor(Color.GREEN);
        paint[3].setColor(Color.YELLOW);
        paint[4].setColor(Color.BLUE);
        paint[5].setColor(Color.DKGRAY);
        paint[6].setColor(Color.LTGRAY);
        paint[7].setColor(Color.MAGENTA);
        paint[8].setColor(Color.WHITE);
        paint[9].setColor(Color.BLUE);
        paint[10].setColor(Color.RED);
        paint[11].setColor(Color.GREEN);
        paint[12].setColor(Color.YELLOW);
        paint[13].setColor(Color.BLUE);

        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("count Check, onDraw ");
//        canvas.drawText("A",250, 250, paint[0]);
//        canvas.drawRect(300,500,750,1020,paint[0]);
        count++;
        canvas.drawLine(arr[0][1], arr[0][0], arr[1][1], arr[1][0], ppaint);
        canvas.drawLine(arr[1][1], arr[1][0], arr[2][1], arr[2][0], ppaint);
        canvas.drawLine(arr[1][1], arr[1][0], arr[5][1], arr[5][0], ppaint);
        canvas.drawLine(arr[2][1], arr[2][0], arr[3][1], arr[3][0], ppaint);
        canvas.drawLine(arr[3][1], arr[3][0], arr[4][1], arr[4][0], ppaint);
        canvas.drawLine(arr[5][1], arr[5][0], arr[6][1], arr[6][0], ppaint);
        canvas.drawLine(arr[6][1], arr[6][0], arr[7][1], arr[7][0], ppaint);

        for (int i = 0; i < 8; i++) {
//            Log.d("check1", i + ": X= " + arr[i][0] + " Y= " + arr[i][1]);
            canvas.drawPoint(arr[i][1], arr[i][0], paint[i]);

            Log.d("check2", i + ": y= " + arr[i][0] + "---" + position[i][0] + " x= " + arr[i][1] + "---" + position[i][1]);
        }
       Position1(canvas);

    }

    public static void setArr(float[][] inputArr) {
        for (int index = 0; index < NUMJOINT; index++) {
            arr[index][0] = inputArr[index][0] / HEATMAPHEIGHT * MAX_PREVIEW_HEIGHT;
            arr[index][1] = inputArr[index][1] / HEATMAPWIDTH * MAX_PREVIEW_WIDTH;
        }
        myView.invalidate();
    }


    public void Position1(Canvas canvas) {
        int x = (int) arr[1][1];
        int y = (int) arr[1][0];
        /*(i,1) = Y,                    (i,0) = X*/
        position[0][1] = x;
        position[0][0] = y - 198;
        position[1][1] = x;
        position[1][0] = y;
        position[2][1] = x - 180;
        position[2][0] = y + 50;
        position[3][1] = x - 225;
        position[3][0] = y + 219;
        position[4][1] = x - 180;
        position[4][0] = y + 398;
        position[5][1] = x + 135;
        position[5][0] = y + 50;
        position[6][1] = x + 225;
        position[6][0] = y + 219;
        position[7][1] = x + 180;
        position[7][0] = y + 398;

        for (int i = 0; i < 8; i++) {
            x1[i] = position[i][0];
            x2[i] = arr[i][0];
            y1[i] = position[i][1];
            y2[i] = arr[i][1];
            X = (x1[i] - x2[i]) * (x1[i] - x2[i]);
            Y = (y1[i] - y2[i]) * (y1[i] - y2[i]);
            result[i] = Math.sqrt(X + Y);
            Log.d("Sqrt ", "index : " + i + " == " + result[i] + " ");
//            int x = 540;
//            int y = 645;


            canvas.drawCircle(x, y - 198, 30, pointp);
            canvas.drawCircle(x, y, 30, pointp);
            canvas.drawCircle(x - 180, y + 50, 30, pointp);
            canvas.drawCircle(x - 225, y + 219, 30, pointp);
            canvas.drawCircle(x - 180, y + 398, 30, pointp);
            canvas.drawCircle(x + 135, y + 50, 30, pointp);
            canvas.drawCircle(x + 225, y + 219, 30, pointp);
            canvas.drawCircle(x + 180, y + 398, 30, pointp);


            canvas.drawLine(position[0][1], position[0][0], position[1][1], position[1][0], ppaint);
            canvas.drawLine(position[1][1], position[1][0], position[2][1], position[2][0], ppaint);
            canvas.drawLine(position[1][1], position[1][0], position[5][1], position[5][0], ppaint);
            canvas.drawLine(position[2][1], position[2][0], position[3][1], position[3][0], ppaint);
            canvas.drawLine(position[3][1], position[3][0], position[4][1], position[4][0], ppaint);
            canvas.drawLine(position[5][1], position[5][0], position[6][1], position[6][0], ppaint);
            canvas.drawLine(position[6][1], position[6][0], position[7][1], position[7][0], ppaint);
        }

        if (result[0] >= 0 && result[0] <= 50) {
            canvas.drawCircle(arr[0][1], arr[0][0], 40, paint[11]);
            ch[0] = true;
        }
        if (result[1] >= 0 && result[1] <= 50) {
            canvas.drawCircle(arr[1][1], arr[1][0], 40, paint[11]);
            ch[1] = true;
        }
        if (result[2] >= 0 && result[2] <= 50) {
            canvas.drawCircle(arr[2][1], arr[2][0], 40, paint[11]);
            ch[2] = true;
        }
        if (result[3] >= 0 && result[3] <= 80) {
            canvas.drawCircle(arr[3][1], arr[3][0], 40, paint[11]);
            ch[3] = true;
        }
        if (result[4] >= 0 && result[4] <= 80) {
            canvas.drawCircle(arr[4][1], arr[4][0], 40, paint[11]);
            ch[4] = true;
        }
        if (result[5] >= 0 && result[5] <= 80) {
            canvas.drawCircle(arr[5][1], arr[5][0], 40, paint[11]);
            ch[5] = true;
        }
        if (result[6] >= 0 && result[6] <= 80) {
            canvas.drawCircle(arr[6][1], arr[6][0], 40, paint[11]);
            ch[6] = true;
        }
        if (result[7] >= 0 && result[7] <= 80) {
            canvas.drawCircle(arr[7][1], arr[7][0], 40, paint[11]);
            ch[7] = true;
        }

        if (ch[0] == true && ch[1] == true) {
            TextView textView = myView.findViewById(R.id.texttime);
////            textView.setText("OK Let go!!!!");
//            Paint p = new Paint();
//            p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//            canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(), p);
//
//                Position1_2(canvas);




        }
    }

    /*  จับท่าที่ 2 ใหม่  ท่ายกมือขึ้น */
    public void Position1_2(Canvas canvas) {
        int x = (int) arr[1][1];
        int y = (int) arr[1][0];
//        int x = 540;
//        int y = 645;
        position[0][1] = x;
        position[0][0] = y - 248;
        position[1][1] = x;
        position[1][0] = y;
        position[2][1] = x - 180;
        position[2][0] = y;
        position[3][1] = x - 405;
        position[3][0] = y;
        position[4][1] = x - 315;
        position[4][0] = y - 198;
        position[5][1] = x + 180;
        position[5][0] = y;
        position[6][1] = x + 405;
        position[6][0] = y - 49;
        position[7][1] = x + 360;
        position[7][0] = y - 198;

        for (int i = 0; i < 8; i++) {
            x1[i] = position[i][0];
            x2[i] = arr[i][0];
            y1[i] = position[i][1];
            y2[i] = arr[i][1];
            X = (x1[i] - x2[i]) * (x1[i] - x2[i]);
            Y = (y1[i] - y2[i]) * (y1[i] - y2[i]);
            result[i] = Math.sqrt(X + Y);
            Log.d("Sqrt ", "index : " + i + " == " + result[i] + " ");
        }

        canvas.drawCircle(x, y - 248, 40, pointp);
        canvas.drawCircle(x, y, 40, pointp);
        canvas.drawCircle(x - 180, y, 40, pointp);
        canvas.drawCircle(x - 405, y, 40, pointp);
        canvas.drawCircle(x - 315, y - 198, 40, pointp);
        canvas.drawCircle(x + 180, y, 40, pointp);
        canvas.drawCircle(x + 405, y - 49, 40, pointp);
        canvas.drawCircle(x + 360, y - 198, 40, pointp);


        canvas.drawLine(position[0][1], position[0][0], position[1][1], position[1][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[2][1], position[2][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[5][1], position[5][0], ppaint);
        canvas.drawLine(position[2][1], position[2][0], position[3][1], position[3][0], ppaint);
        canvas.drawLine(position[3][1], position[3][0], position[4][1], position[4][0], ppaint);
        canvas.drawLine(position[5][1], position[5][0], position[6][1], position[6][0], ppaint);
        canvas.drawLine(position[6][1], position[6][0], position[7][1], position[7][0], ppaint);

        if (result[0] >= 0 && result[0] <= 50) {
            canvas.drawCircle(arr[0][1], arr[0][0], 40, paint[11]);
            ch[0] = true;
        }
        if (result[1] >= 0 && result[1] <= 50) {
            canvas.drawCircle(arr[1][1], arr[1][0], 40, paint[11]);
            ch[1] = true;
        }
        if (result[2] >= 0 && result[2] <= 50) {
            canvas.drawCircle(arr[2][1], arr[2][0], 40, paint[11]);
            ch[2] = true;
        }
        if (result[3] >= 0 && result[3] <= 50) {
            canvas.drawCircle(arr[3][1], arr[3][0], 40, paint[11]);
            ch[3] = true;
        }
        if (result[4] >= 0 && result[4] <= 50) {
            canvas.drawCircle(arr[4][1], arr[4][0], 40, paint[11]);
            ch[4] = true;
        }
        if (result[5] >= 0 && result[5] <= 50) {
            canvas.drawCircle(arr[5][1], arr[5][0], 40, paint[11]);
            ch[5] = true;
        }
        if (result[6] >= 0 && result[6] <= 50) {
            canvas.drawCircle(arr[6][1], arr[6][0], 40, paint[11]);
            ch[6] = true;
        }
        if (result[7] >= 0 && result[7] <= 50) {
            canvas.drawCircle(arr[7][1], arr[7][0], 40, paint[11]);
            ch[7] = true;
        }


//        CheckPosition();

    }

    public void Position1_3(Canvas canvas) {
        int x = (int) arr[1][1];
        int y = (int) arr[1][0];
//        int x = 540;
//        int y = 596;

        position[0][1] = x;
        position[0][0] = y - 143;
        position[1][1] = x;
        position[1][0] = y;
        position[2][1] = x + 180;
        position[2][0] = y;
        position[3][1] = x + 360;
        position[3][0] = y - 100;
        position[4][1] = x + 180;
        position[4][0] = y - 100;
        position[5][1] = x - 180;
        position[5][0] = y;
        position[6][1] = x - 315;
        position[6][0] = y - 100;
        position[7][1] = x + 180;
        position[7][0] = y - 100;

        for (int i = 0; i < 8; i++) {
            x1[i] = position[i][0];
            x2[i] = arr[i][0];
            y1[i] = position[i][1];
            y2[i] = arr[i][1];
            X = (x1[i] - x2[i]) * (x1[i] - x2[i]);
            Y = (y1[i] - y2[i]) * (y1[i] - y2[i]);
            result[i] = Math.sqrt(X + Y);
            Log.d("Sqrt ", "index : " + i + " == " + result[i] + " ");

        }
        /*draw position */
        canvas.drawCircle(x, y - 199, 40, pointp);
        canvas.drawCircle(x, y, 40, pointp);
        canvas.drawCircle(x + 180, y, 40, pointp);
        canvas.drawCircle(x + 360, y - 100, 40, pointp);
        canvas.drawCircle(x - 180, y - 100, 40, pointp);
        canvas.drawCircle(x - 180, y, 40, pointp);
        canvas.drawCircle(x - 315, y - 100, 40, pointp);
        canvas.drawCircle(x + 180, y - 100, 40, pointp);

        canvas.drawLine(position[0][1], position[0][0], position[1][1], position[1][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[2][1], position[2][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[5][1], position[5][0], ppaint);
        canvas.drawLine(position[2][1], position[2][0], position[3][1], position[3][0], ppaint);
        canvas.drawLine(position[3][1], position[3][0], position[4][1], position[4][0], ppaint);
        canvas.drawLine(position[5][1], position[5][0], position[6][1], position[6][0], ppaint);
        canvas.drawLine(position[6][1], position[6][0], position[7][1], position[7][0], ppaint);

        if (result[0] >= 0 && result[0] <= 50) {
            canvas.drawCircle(arr[0][1], arr[0][0], 40, paint[11]);
            ch[0] = true;
        }
        if (result[1] >= 0 && result[1] <= 50) {
            canvas.drawCircle(arr[1][1], arr[1][0], 40, paint[11]);
            ch[1] = true;
        }
        if (result[2] >= 0 && result[2] <= 50) {
            canvas.drawCircle(arr[2][1], arr[2][0], 40, paint[11]);
            ch[2] = true;
        }
        if (result[3] >= 0 && result[3] <= 50) {
            canvas.drawCircle(arr[3][1], arr[3][0], 40, paint[11]);
            ch[3] = true;
        }
        if (result[4] >= 0 && result[4] <= 50) {
            canvas.drawCircle(arr[4][1], arr[4][0], 40, paint[11]);
            ch[4] = true;
        }
        if (result[5] >= 0 && result[5] <= 50) {
            canvas.drawCircle(arr[5][1], arr[5][0], 40, paint[11]);
            ch[5] = true;
        }
        if (result[6] >= 0 && result[6] <= 50) {
            canvas.drawCircle(arr[6][1], arr[6][0], 40, paint[11]);
            ch[6] = true;
        }
        if (result[7] >= 0 && result[7] <= 50) {
            canvas.drawCircle(arr[7][1], arr[7][0], 40, paint[11]);
            ch[7] = true;
        }

//        CheckPosition();
    }

    public void Position2_1(Canvas canvas) {
        int x = (int) arr[1][1];
        int y = (int) arr[1][0];

        /*x=540  y=596*/

        position[0][1] = x;
        position[0][0] = y - 149;
        position[1][1] = x;
        position[1][0] = y;
        position[2][1] = x + 135;
        position[2][0] = y + 49;
        position[3][1] = x + 315;
        position[3][0] = y + 99;
        position[4][1] = x + 90;
        position[4][0] = y + 99;
        position[5][1] = x - 135;
        position[5][0] = y + 49;
        position[6][1] = x - 345;
        position[6][0] = y + 99;
        position[7][1] = x - 90;
        position[7][0] = y + 99;

        for (int i = 0; i < 8; i++) {
            x1[i] = position[i][0];
            x2[i] = arr[i][0];
            y1[i] = position[i][1];
            y2[i] = arr[i][1];
            X = (x1[i] - x2[i]) * (x1[i] - x2[i]);
            Y = (y1[i] - y2[i]) * (y1[i] - y2[i]);
            result[i] = Math.sqrt(X + Y);
            Log.d("Sqrt ", "index : " + i + " == " + result[i] + " ");
        }

        /*draw position */
        canvas.drawCircle(x, y - 149, 40, pointp);
        canvas.drawCircle(x, y, 40, pointp);
        canvas.drawCircle(x + 135, y + 49, 40, pointp);
        canvas.drawCircle(x + 315, y + 99, 40, pointp);
        canvas.drawCircle(x + 90, y + 99, 40, pointp);
        canvas.drawCircle(x - 135, y + 49, 40, pointp);
        canvas.drawCircle(x - 345, y + 99, 40, pointp);
        canvas.drawCircle(x - 90, y + 99, 40, pointp);

        canvas.drawLine(position[0][1], position[0][0], position[1][1], position[1][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[2][1], position[2][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[5][1], position[5][0], ppaint);
        canvas.drawLine(position[2][1], position[2][0], position[3][1], position[3][0], ppaint);
        canvas.drawLine(position[3][1], position[3][0], position[4][1], position[4][0], ppaint);
        canvas.drawLine(position[5][1], position[5][0], position[6][1], position[6][0], ppaint);
        canvas.drawLine(position[6][1], position[6][0], position[7][1], position[7][0], ppaint);

        if (result[0] >= 0 && result[0] <= 50) {
            canvas.drawCircle(arr[0][1], arr[0][0], 40, paint[11]);
            ch[0] = true;
        }

        if (result[1] >= 0 && result[1] <= 50) {
            canvas.drawCircle(arr[1][1], arr[1][0], 40, paint[11]);
            ch[1] = true;
        }

        if (result[2] >= 0 && result[2] <= 50) {
            canvas.drawCircle(arr[2][1], arr[2][0], 40, paint[11]);
            ch[2] = true;
        }

        if (result[3] >= 0 && result[3] <= 50) {
            canvas.drawCircle(arr[3][1], arr[3][0], 40, paint[11]);
            ch[3] = true;
        }

        if (result[4] >= 0 && result[4] <= 50) {
            canvas.drawCircle(arr[4][1], arr[4][0], 40, paint[11]);
            ch[4] = true;
        }

        if (result[5] >= 0 && result[5] <= 50) {
            canvas.drawCircle(arr[5][1], arr[5][0], 40, paint[11]);
            ch[5] = true;
        }

        if (result[6] >= 0 && result[6] <= 50) {
            canvas.drawCircle(arr[6][1], arr[6][0], 40, paint[11]);
            ch[6] = true;
        }

        if (result[7] >= 0 && result[7] <= 50) {
            canvas.drawCircle(arr[7][1], arr[7][0], 40, paint[11]);
            ch[7] = true;
        }
//        CheckPosition();
    }

    public void Position2_2(Canvas canvas) {

        int x = (int) arr[1][1];
        int y = (int) arr[1][0];

        /*x=585  y=546*/

        position[0][1] = x;
        position[0][0] = y - 199;
        position[1][1] = x;
        position[1][0] = y;
        position[2][1] = x - 135;
        position[2][0] = y - 50;
        position[3][1] = x - 135;
        position[3][0] = y - 199;

        position[4][1] = x - 135;
        position[4][0] = y - 397;
        position[5][1] = x + 135;
        position[5][0] = y - 50;
        position[6][1] = x + 135;
        position[6][0] = y - 199;
        position[7][1] = x + 135;
        position[7][0] = y - 397;


        for (int i = 0; i < 8; i++) {
            x1[i] = position[i][0];
            x2[i] = arr[i][0];
            y1[i] = position[i][1];
            y2[i] = arr[i][1];
            X = (x1[i] - x2[i]) * (x1[i] - x2[i]);
            Y = (y1[i] - y2[i]) * (y1[i] - y2[i]);
            result[i] = Math.sqrt(X + Y);
            Log.d("Sqrt ", "index : " + i + " == " + result[i] + " ");

        }
        canvas.drawCircle(x, y - 199, 40, pointp);
        canvas.drawCircle(x, y, 40, pointp);
        canvas.drawCircle(x - 135, y - 50, 40, pointp);
        canvas.drawCircle(x - 135, y - 199, 40, pointp);
        canvas.drawCircle(x - 135, y - 397, 40, pointp);
        canvas.drawCircle(x + 135, y - 50, 40, pointp);
        canvas.drawCircle(x + 135, y - 199, 40, pointp);
        canvas.drawCircle(x + 135, y - 397, 40, pointp);
        canvas.drawLine(position[0][1], position[0][0], position[1][1], position[1][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[2][1], position[2][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[5][1], position[5][0], ppaint);
        canvas.drawLine(position[2][1], position[2][0], position[3][1], position[3][0], ppaint);
        canvas.drawLine(position[3][1], position[3][0], position[4][1], position[4][0], ppaint);
        canvas.drawLine(position[5][1], position[5][0], position[6][1], position[6][0], ppaint);
        canvas.drawLine(position[6][1], position[6][0], position[7][1], position[7][0], ppaint);

        if (result[0] >= 0 && result[0] <= 50) {
            canvas.drawCircle(arr[0][1], arr[0][0], 40, paint[11]);
            ch[0] = true;
        }
        if (result[1] >= 0 && result[1] <= 50) {
            canvas.drawCircle(arr[1][1], arr[1][0], 40, paint[11]);
            ch[1] = true;
        }
        if (result[2] >= 0 && result[2] <= 50) {
            canvas.drawCircle(arr[2][1], arr[2][0], 40, paint[11]);
            ch[2] = true;
        }
        if (result[3] >= 0 && result[3] <= 50) {
            canvas.drawCircle(arr[3][1], arr[3][0], 40, paint[11]);
            ch[3] = true;
        }
        if (result[4] >= 0 && result[4] <= 50) {
            canvas.drawCircle(arr[4][1], arr[4][0], 40, paint[11]);
            ch[4] = true;
        }
        if (result[5] >= 0 && result[5] <= 50) {
            canvas.drawCircle(arr[5][1], arr[5][0], 40, paint[11]);
            ch[5] = true;
        }
        if (result[6] >= 0 && result[6] <= 50) {
            canvas.drawCircle(arr[6][1], arr[6][0], 40, paint[11]);
            ch[6] = true;
        }
        if (result[7] >= 0 && result[7] <= 50) {
            canvas.drawCircle(arr[7][1], arr[7][0], 40, paint[11]);
            ch[7] = true;
        }
//        CheckPosition();

    }

    public void Position2_3(Canvas canvas) {
        int x = (int) arr[1][1];
        int y = (int) arr[1][0];



        /*x=585  y=596*/

        position[0][1] = x;
        position[0][0] = y - 199;
        position[1][1] = x;
        position[1][0] = y;
        position[2][1] = x - 180;
        position[2][0] = y + 49;
        position[3][1] = x - 315;
        position[3][0] = y + 198;
        position[4][1] = x - 45;
        position[4][0] = y + 198;
        position[5][1] = x + 180;
        position[5][0] = y + 49;
        position[6][1] = x + 315;
        position[6][0] = y + 198;
        position[7][1] = x + 45;
        position[7][0] = y + 198;

        for (int i = 0; i < 8; i++) {
            x1[i] = position[i][0];
            x2[i] = arr[i][0];
            y1[i] = position[i][1];
            y2[i] = arr[i][1];
            X = (x1[i] - x2[i]) * (x1[i] - x2[i]);
            Y = (y1[i] - y2[i]) * (y1[i] - y2[i]);
            result[i] = Math.sqrt(X + Y);
            Log.d("Sqrt ", "index : " + i + " == " + result[i] + " ");

        }

        canvas.drawCircle(x, y - 199, 40, pointp);
        canvas.drawCircle(x, y, 40, pointp);
        canvas.drawCircle(x - 180, y + 49, 40, pointp);
        canvas.drawCircle(x - 315, y + 198, 40, pointp);
        canvas.drawCircle(x - 45, y + 198, 40, pointp);
        canvas.drawCircle(x + 180, y + 49, 40, pointp);
        canvas.drawCircle(x + 315, y + 198, 40, pointp);
        canvas.drawCircle(x + 45, y + 198, 40, pointp);


        canvas.drawLine(position[0][1], position[0][0], position[1][1], position[1][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[2][1], position[2][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[5][1], position[5][0], ppaint);
        canvas.drawLine(position[2][1], position[2][0], position[3][1], position[3][0], ppaint);
        canvas.drawLine(position[3][1], position[3][0], position[4][1], position[4][0], ppaint);
        canvas.drawLine(position[5][1], position[5][0], position[6][1], position[6][0], ppaint);
        canvas.drawLine(position[6][1], position[6][0], position[7][1], position[7][0], ppaint);

        if (result[0] >= 0 && result[0] <= 50) {
            canvas.drawCircle(arr[0][1], arr[0][0], 40, paint[11]);
            ch[0] = true;
        }
        if (result[1] >= 0 && result[1] <= 50) {
            canvas.drawCircle(arr[1][1], arr[1][0], 40, paint[11]);
            ch[1] = true;
        }
        if (result[2] >= 0 && result[2] <= 50) {
            canvas.drawCircle(arr[2][1], arr[2][0], 40, paint[11]);
            ch[2] = true;
        }
        if (result[3] >= 0 && result[3] <= 50) {
            canvas.drawCircle(arr[3][1], arr[3][0], 40, paint[11]);
            ch[3] = true;
        }
        if (result[4] >= 0 && result[4] <= 50) {
            canvas.drawCircle(arr[4][1], arr[4][0], 40, paint[11]);
            ch[4] = true;
        }
        if (result[5] >= 0 && result[5] <= 50) {
            canvas.drawCircle(arr[5][1], arr[5][0], 40, paint[11]);
            ch[5] = true;
        }
        if (result[6] >= 0 && result[6] <= 50) {
            canvas.drawCircle(arr[6][1], arr[6][0], 40, paint[11]);
            ch[6] = true;
        }
        if (result[7] >= 0 && result[7] <= 50) {
            canvas.drawCircle(arr[7][1], arr[7][0], 40, paint[11]);
            ch[7] = true;
        }
//        CheckPosition();
    }

    public void Position4(Canvas canvas) {
        int x = (int) arr[1][1];
        int y = (int) arr[1][0];

        /*x = 540  y=496*/

        position[0][1] = x;
        position[0][0] = y - 199;
        position[1][1] = x;
        position[1][0] = y;
        position[2][1] = x - 180;
        position[2][0] = y + 49;
        position[3][1] = x - 315;
        position[3][0] = y + 198;
        position[4][1] = x - 45;
        position[4][0] = y + 198;
        position[5][1] = x + 180;
        position[5][0] = y + 49;
        position[6][1] = x + 315;
        position[6][0] = y + 198;
        position[7][1] = x + 45;
        position[7][0] = y + 198;

        for (int i = 0; i < 8; i++) {
            x1[i] = position[i][0];
            x2[i] = arr[i][0];
            y1[i] = position[i][1];
            y2[i] = arr[i][1];
            X = (x1[i] - x2[i]) * (x1[i] - x2[i]);
            Y = (y1[i] - y2[i]) * (y1[i] - y2[i]);
            result[i] = Math.sqrt(X + Y);
            Log.d("Sqrt ", "index : " + i + " == " + result[i] + " ");

        }
        canvas.drawCircle(x, y - 199, 40, pointp);
        canvas.drawCircle(x, y, 40, pointp);
        canvas.drawCircle(x - 180, y + 49, 40, pointp);
        canvas.drawCircle(x - 315, y + 198, 40, pointp);
        canvas.drawCircle(x - 45, y + 198, 40, pointp);
        canvas.drawCircle(x + 180, y + 49, 40, pointp);
        canvas.drawCircle(x + 315, y + 198, 40, pointp);
        canvas.drawCircle(x + 45, y + 198, 40, pointp);
        canvas.drawLine(position[0][1], position[0][0], position[1][1], position[1][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[2][1], position[2][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[5][1], position[5][0], ppaint);
        canvas.drawLine(position[2][1], position[2][0], position[3][1], position[3][0], ppaint);
        canvas.drawLine(position[3][1], position[3][0], position[4][1], position[4][0], ppaint);
        canvas.drawLine(position[5][1], position[5][0], position[6][1], position[6][0], ppaint);
        canvas.drawLine(position[6][1], position[6][0], position[7][1], position[7][0], ppaint);

        if (result[0] >= 0 && result[0] <= 50) {
            canvas.drawCircle(arr[0][1], arr[0][0], 40, paint[11]);
            ch[0] = true;
        }
        if (result[1] >= 0 && result[1] <= 50) {
            canvas.drawCircle(arr[1][1], arr[1][0], 40, paint[11]);
            ch[1] = true;
        }
        if (result[2] >= 0 && result[2] <= 50) {
            canvas.drawCircle(arr[2][1], arr[2][0], 40, paint[11]);
            ch[2] = true;
        }
        if (result[3] >= 0 && result[3] <= 50) {
            canvas.drawCircle(arr[3][1], arr[3][0], 40, paint[11]);
            ch[3] = true;
        }
        if (result[4] >= 0 && result[4] <= 50) {
            canvas.drawCircle(arr[4][1], arr[4][0], 40, paint[11]);
            ch[4] = true;
        }
        if (result[5] >= 0 && result[5] <= 50) {
            canvas.drawCircle(arr[5][1], arr[5][0], 40, paint[11]);
            ch[5] = true;
        }
        if (result[6] >= 0 && result[6] <= 50) {
            canvas.drawCircle(arr[6][1], arr[6][0], 40, paint[11]);
            ch[6] = true;
        }
        if (result[7] >= 0 && result[7] <= 20) {
            canvas.drawCircle(arr[7][1], arr[7][0], 40, paint[11]);
            ch[7] = true;
        }
//        CheckPosition();

    }

    public void Position5_2(Canvas canvas) {
        int x = (int) arr[1][1];
        int y = (int) arr[1][0];
        /*x = 540  y=546*/
        position[0][1] = x;
        position[0][0] = y - 199;
        position[1][1] = x;
        position[1][0] = y;
        position[2][1] = x + 135;
        position[2][0] = y - 90;
        position[3][1] = x + 180;
        position[3][0] = y - 338;
        position[4][1] = x + 180;
        position[4][0] = y - 447;
        position[5][1] = x - 135;
        position[5][0] = y - 50;
        position[6][1] = x - 180;
        position[6][0] = y + 248;
        position[7][1] = x - 180;
        position[7][0] = y + 397;


        for (int i = 0; i < 8; i++) {
            x1[i] = position[i][0];
            x2[i] = arr[i][0];
            y1[i] = position[i][1];
            y2[i] = arr[i][1];
            X = (x1[i] - x2[i]) * (x1[i] - x2[i]);
            Y = (y1[i] - y2[i]) * (y1[i] - y2[i]);
            result[i] = Math.sqrt(X + Y);
            Log.d("Sqrt ", "index : " + i + " == " + result[i] + " ");

        }
        canvas.drawCircle(x, y - 199, 40, pointp);
        canvas.drawCircle(x, y, 40, pointp);
        canvas.drawCircle(x + 135, y - 90, 40, pointp);
        canvas.drawCircle(x + 180, y - 338, 40, pointp);
        canvas.drawCircle(x + 180, y - 447, 40, pointp);
        canvas.drawCircle(x - 135, y - 50, 40, pointp);
        canvas.drawCircle(x - 180, y + 248, 40, pointp);
        canvas.drawCircle(x - 180, y + 397, 40, pointp);
        canvas.drawLine(position[0][1], position[0][0], position[1][1], position[1][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[2][1], position[2][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[5][1], position[5][0], ppaint);
        canvas.drawLine(position[2][1], position[2][0], position[3][1], position[3][0], ppaint);
        canvas.drawLine(position[3][1], position[3][0], position[4][1], position[4][0], ppaint);
        canvas.drawLine(position[5][1], position[5][0], position[6][1], position[6][0], ppaint);
        canvas.drawLine(position[6][1], position[6][0], position[7][1], position[7][0], ppaint);

        if (result[0] >= 0 && result[0] <= 50) {
            canvas.drawCircle(arr[0][1], arr[0][0], 40, paint[11]);
            ch[0] = true;
        }
        if (result[1] >= 0 && result[1] <= 50) {
            canvas.drawCircle(arr[1][1], arr[1][0], 40, paint[11]);
            ch[1] = true;
        }
        if (result[2] >= 0 && result[2] <= 50) {
            canvas.drawCircle(arr[2][1], arr[2][0], 40, paint[11]);
            ch[2] = true;
        }
        if (result[3] >= 0 && result[3] <= 50) {
            canvas.drawCircle(arr[3][1], arr[3][0], 40, paint[11]);
            ch[3] = true;
        }
        if (result[4] >= 0 && result[4] <= 50) {
            canvas.drawCircle(arr[4][1], arr[4][0], 40, paint[11]);
            ch[4] = true;
        }
        if (result[5] >= 0 && result[5] <= 50) {
            canvas.drawCircle(arr[5][1], arr[5][0], 40, paint[11]);
            ch[5] = true;
        }
        if (result[6] >= 0 && result[6] <= 50) {
            canvas.drawCircle(arr[6][1], arr[6][0], 40, paint[11]);
            ch[6] = true;
        }
        if (result[7] >= 0 && result[7] <= 50) {
            canvas.drawCircle(arr[7][1], arr[7][0], 40, paint[11]);
            ch[7] = true;
        }
//        CheckPosition();
    }

    public void Position5_3(Canvas canvas) {

        int x = (int) arr[1][1];
        int y = (int) arr[1][0];
        /*x=540 y=546*/

        position[0][1] = x + 135;
        position[0][0] = y - 199;
        position[1][1] = x;
        position[1][0] = y;
        position[2][1] = x + 180;
        position[2][0] = y - 50;
        position[3][1] = x + 225;
        position[3][0] = y - 203;
        position[4][1] = x + 270;
        position[4][0] = y - 387;
        position[5][1] = x - 135;
        position[5][0] = y - 50;
        position[6][1] = x - 315;
        position[6][0] = y + 149;
        position[7][1] = x - 180;
        position[7][0] = y + 199;
        for (int i = 0; i < 8; i++) {
            x1[i] = position[i][0];
            x2[i] = arr[i][0];
            y1[i] = position[i][1];
            y2[i] = arr[i][1];
            X = (x1[i] - x2[i]) * (x1[i] - x2[i]);
            Y = (y1[i] - y2[i]) * (y1[i] - y2[i]);
            result[i] = Math.sqrt(X + Y);
            Log.d("Sqrt ", "index : " + i + " == " + result[i] + " ");

        }
        canvas.drawCircle(x + 135, y - 199, 40, pointp);
        canvas.drawCircle(x, y, 40, pointp);
        canvas.drawCircle(x + 180, y - 50, 40, pointp);
        canvas.drawCircle(x + 225, y - 203, 40, pointp);
        canvas.drawCircle(x + 270, y - 387, 40, pointp);
        canvas.drawCircle(x - 135, y - 50, 40, pointp);
        canvas.drawCircle(x - 315, y + 149, 40, pointp);
        canvas.drawCircle(x - 180, y + 199, 40, pointp);
        canvas.drawLine(position[0][1], position[0][0], position[1][1], position[1][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[2][1], position[2][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[5][1], position[5][0], ppaint);
        canvas.drawLine(position[2][1], position[2][0], position[3][1], position[3][0], ppaint);
        canvas.drawLine(position[3][1], position[3][0], position[4][1], position[4][0], ppaint);
        canvas.drawLine(position[5][1], position[5][0], position[6][1], position[6][0], ppaint);
        canvas.drawLine(position[6][1], position[6][0], position[7][1], position[7][0], ppaint);

        if (result[0] >= 0 && result[0] <= 50) {
            canvas.drawCircle(arr[0][1], arr[0][0], 40, paint[11]);
            ch[0] = true;
        }
        if (result[1] >= 0 && result[1] <= 50) {
            canvas.drawCircle(arr[1][1], arr[1][0], 40, paint[11]);
            ch[1] = true;
        }
        if (result[2] >= 0 && result[2] <= 50) {
            canvas.drawCircle(arr[2][1], arr[2][0], 40, paint[11]);
            ch[2] = true;
        }
        if (result[3] >= 0 && result[3] <= 50) {
            canvas.drawCircle(arr[3][1], arr[3][0], 40, paint[11]);
            ch[3] = true;
        }
        if (result[4] >= 0 && result[4] <= 50) {
            canvas.drawCircle(arr[4][1], arr[4][0], 40, paint[11]);
            ch[4] = true;
        }
        if (result[5] >= 0 && result[5] <= 50) {
            canvas.drawCircle(arr[5][1], arr[5][0], 40, paint[11]);
            ch[5] = true;
        }
        if (result[6] >= 0 && result[6] <= 50) {
            canvas.drawCircle(arr[6][1], arr[6][0], 40, paint[11]);
            ch[6] = true;
        }
        if (result[7] >= 0 && result[7] <= 50) {
            canvas.drawCircle(arr[7][1], arr[7][0], 40, paint[11]);
            ch[7] = true;
        }
//        CheckPosition();
    }

    public void Position6_2(Canvas canvas) {
        int x = (int) arr[1][1];
        int y = (int) arr[1][0];
        /*x=585  y=596*/
        position[0][1] = x;
        position[0][0] = y - 199;
        position[1][1] = x;
        position[1][0] = y;
        position[2][1] = x + 135;
        position[2][0] = y;
        position[3][1] = x + 135;
        position[3][0] = y + 249;
        position[4][1] = x + 135;
        position[4][0] = y + 398;
        position[5][1] = x - 135;
        position[5][0] = y - 49;
        position[6][1] = x - 180;
        position[6][0] = y - 248;
        position[7][1] = x - 135;
        position[7][0] = y - 447;

        for (int i = 0; i < 8; i++) {
            x1[i] = position[i][0];
            x2[i] = arr[i][0];
            y1[i] = position[i][1];
            y2[i] = arr[i][1];
            X = (x1[i] - x2[i]) * (x1[i] - x2[i]);
            Y = (y1[i] - y2[i]) * (y1[i] - y2[i]);
            result[i] = Math.sqrt(X + Y);
            Log.d("Sqrt ", "index : " + i + " == " + result[i] + " ");

        }
        canvas.drawCircle(x, y - 199, 40, pointp);
        canvas.drawCircle(x, y, 40, pointp);

        canvas.drawCircle(x + 135, y, 40, pointp);
        canvas.drawCircle(x + 135, y + 249, 40, pointp);

        canvas.drawCircle(x + 135, y + 398, 40, pointp);
        canvas.drawCircle(x - 135, y - 49, 40, pointp);

        canvas.drawCircle(x - 180, y - 248, 40, pointp);
        canvas.drawCircle(x - 135, y - 447, 40, pointp);
        canvas.drawLine(position[0][1], position[0][0], position[1][1], position[1][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[2][1], position[2][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[5][1], position[5][0], ppaint);
        canvas.drawLine(position[2][1], position[2][0], position[3][1], position[3][0], ppaint);
        canvas.drawLine(position[3][1], position[3][0], position[4][1], position[4][0], ppaint);
        canvas.drawLine(position[5][1], position[5][0], position[6][1], position[6][0], ppaint);
        canvas.drawLine(position[6][1], position[6][0], position[7][1], position[7][0], ppaint);

        if (result[0] >= 0 && result[0] <= 50) {
            canvas.drawCircle(arr[0][1], arr[0][0], 40, paint[11]);
            ch[0] = true;
        }
        if (result[1] >= 0 && result[1] <= 50) {
            canvas.drawCircle(arr[1][1], arr[1][0], 40, paint[11]);
            ch[1] = true;
        }
        if (result[2] >= 0 && result[2] <= 50) {
            canvas.drawCircle(arr[2][1], arr[2][0], 40, paint[11]);
            ch[2] = true;
        }
        if (result[3] >= 0 && result[3] <= 50) {
            canvas.drawCircle(arr[3][1], arr[3][0], 40, paint[11]);
            ch[3] = true;
        }
        if (result[4] >= 0 && result[4] <= 50) {
            canvas.drawCircle(arr[4][1], arr[4][0], 40, paint[11]);
            ch[4] = true;
        }
        if (result[5] >= 0 && result[5] <= 50) {
            canvas.drawCircle(arr[5][1], arr[5][0], 40, paint[11]);
            ch[5] = true;
        }
        if (result[6] >= 0 && result[6] <= 50) {
            canvas.drawCircle(arr[6][1], arr[6][0], 40, paint[11]);
            ch[6] = true;
        }
        if (result[7] >= 0 && result[7] <= 50) {
            canvas.drawCircle(arr[7][1], arr[7][0], 40, paint[11]);
            ch[7] = true;
        }
//        CheckPosition();
    }

    public void Position6_3(Canvas canvas) {
        int x = (int) arr[1][1];
        int y = (int) arr[1][0];
        /*x=585  y=596*/
        position[0][1] = x - 135;
        position[0][0] = y - 149;
        position[1][1] = x;
        position[1][0] = y;
        position[2][1] = x - 135;
        position[2][0] = y;
        position[3][1] = x - 315;
        position[3][0] = y - 149;
        position[4][1] = x - 180;
        position[4][0] = y - 149;
        position[5][1] = x + 135;
        position[5][0] = y + 49;
        position[6][1] = x + 180;
        position[6][0] = y + 248;
        position[7][1] = x + 270;
        position[7][0] = y + 447;
        for (int i = 0; i < 8; i++) {
            x1[i] = position[i][0];
            x2[i] = arr[i][0];
            y1[i] = position[i][1];
            y2[i] = arr[i][1];
            X = (x1[i] - x2[i]) * (x1[i] - x2[i]);
            Y = (y1[i] - y2[i]) * (y1[i] - y2[i]);
            result[i] = Math.sqrt(X + Y);
            Log.d("Sqrt ", "index : " + i + " == " + result[i] + " ");

        }

        canvas.drawCircle(x - 135, y - 149, 40, pointp);
        canvas.drawCircle(x, y, 40, pointp);
        canvas.drawCircle(x - 135, y, 40, pointp);
        canvas.drawCircle(x - 315, y - 149, 40, pointp);
        canvas.drawCircle(x - 180, y - 149, 40, pointp);
        canvas.drawCircle(x + 135, y + 49, 40, pointp);
        canvas.drawCircle(x + 180, y + 248, 40, pointp);
        canvas.drawCircle(x + 270, y + 447, 40, pointp);

        canvas.drawLine(position[0][1], position[0][0], position[1][1], position[1][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[2][1], position[2][0], ppaint);
        canvas.drawLine(position[1][1], position[1][0], position[5][1], position[5][0], ppaint);
        canvas.drawLine(position[2][1], position[2][0], position[3][1], position[3][0], ppaint);
        canvas.drawLine(position[3][1], position[3][0], position[4][1], position[4][0], ppaint);
        canvas.drawLine(position[5][1], position[5][0], position[6][1], position[6][0], ppaint);
        canvas.drawLine(position[6][1], position[6][0], position[7][1], position[7][0], ppaint);

        if (result[0] >= 0 && result[0] <= 50) {
            canvas.drawCircle(arr[0][1], arr[0][0], 40, paint[11]);
            ch[0] = true;
        }
        if (result[1] >= 0 && result[1] <= 50) {
            canvas.drawCircle(arr[1][1], arr[1][0], 40, paint[11]);
            ch[1] = true;
        }
        if (result[2] >= 0 && result[2] <= 50) {
            canvas.drawCircle(arr[2][1], arr[2][0], 40, paint[11]);
            ch[2] = true;
        }
        if (result[3] >= 0 && result[3] <= 50) {
            canvas.drawCircle(arr[3][1], arr[3][0], 40, paint[11]);
            ch[3] = true;
        }
        if (result[4] >= 0 && result[4] <= 50) {
            canvas.drawCircle(arr[4][1], arr[4][0], 40, paint[11]);
            ch[4] = true;
        }
        if (result[5] >= 0 && result[5] <= 50) {
            canvas.drawCircle(arr[5][1], arr[5][0], 40, paint[11]);
            ch[5] = true;
        }
        if (result[6] >= 0 && result[6] <= 50) {
            canvas.drawCircle(arr[6][1], arr[6][0], 40, paint[11]);
            ch[6] = true;
        }
        if (result[7] >= 0 && result[7] <= 50) {
            canvas.drawCircle(arr[7][1], arr[7][0], 40, paint[11]);
            ch[7] = true;
        }
    }









}
