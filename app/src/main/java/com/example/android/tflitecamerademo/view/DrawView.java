package com.example.android.tflitecamerademo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tflitecamerademo.Camera2BasicFragment;
import com.example.android.tflitecamerademo.R;

public class DrawView extends View {
    private static final int HEATMAPWIDTH = 96;
    private static final int HEATMAPHEIGHT = 96;
    private static final int NUMJOINT = 14;
    public static int cheak = 1;

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
    int i = 0;
    private Paint[] paint = new Paint[14];
    private Paint ppaint;
    private Paint pointp;
    private static float[][] position = new float[14][2];
    private static float[][] arr = new float[14][2];
    public Boolean positionOK = false;

    Camera2BasicFragment basicFragment = new Camera2BasicFragment();
    static String txtdis, txtnum;
    ImageView imageView;



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
        count++;
        canvas.drawLine(arr[0][1], arr[0][0], arr[1][1], arr[1][0], ppaint);
        canvas.drawLine(arr[1][1], arr[1][0], arr[2][1], arr[2][0], ppaint);
        canvas.drawLine(arr[1][1], arr[1][0], arr[5][1], arr[5][0], ppaint);
        canvas.drawLine(arr[2][1], arr[2][0], arr[3][1], arr[3][0], ppaint);
        canvas.drawLine(arr[3][1], arr[3][0], arr[4][1], arr[4][0], ppaint);
        canvas.drawLine(arr[5][1], arr[5][0], arr[6][1], arr[6][0], ppaint);
        canvas.drawLine(arr[6][1], arr[6][0], arr[7][1], arr[7][0], ppaint);

        for (int i = 0; i < 8; i++) {
            canvas.drawPoint(arr[i][1], arr[i][0], paint[i]);
            Log.d("check2", i + ": y= " + arr[i][0] + "---"
                    + position[i][0] + " x= " + arr[i][1] + "---" + position[i][1]);
        }
        switch (this.cheak) {
            case 1:
                Position1(canvas);
                break;
            case 2:
                Position1_2(canvas);
                break;
            case 3:
                Position1_3(canvas);
                break;
            case 4:
                Position2(canvas);
                break;
            case 5:
                Position2_1(canvas);
                break;
            case 6:
                Position2_2(canvas);
                break;
            case 7:
                Position3(canvas);
                break;
            case 8:
                Position3_1(canvas);
                break;
            case 9:
                Position4(canvas);
                break;
            case 10:
                Position4_1(canvas);
                break;
            case 11:
                Position5(canvas);
                break;
            case 12:
                Position5_1(canvas);
                break;
            case 13:
                Position5_2(canvas);
                break;
            case 14:
                Position5_3(canvas);
                break;
            case 15:
                Position6(canvas);
                break;
            case 16:
                Position6_1(canvas);
                break;
            case 17:
                Position6_2(canvas);
                break;
            case 18:
                Position6_3(canvas);
                break;
            case 19:
                Position1(canvas);
                break;
        }
    }

    public static void setArr(float[][] inputArr) {
        for (int index = 0; index < NUMJOINT; index++) {
            arr[index][0] = inputArr[index][0] / HEATMAPHEIGHT * MAX_PREVIEW_HEIGHT;
            arr[index][1] = inputArr[index][1] / HEATMAPWIDTH * MAX_PREVIEW_WIDTH;
        }
        myView.invalidate();
    }

    /*ท่าออกกำลังกาย*/
    public void Position1(Canvas canvas) {
        if (this.cheak == 1) {
            txtdis = "ท่าเตรียม นั่งหลังตรง วางมือบนหน้าตัก";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*(i,1) = Y,                    (i,0) = X*/
            position[0][1] = x;            position[0][0] = y - 198;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 180;            position[2][0] = y + 50;
            position[3][1] = x - 225;            position[3][0] = y + 219;
            position[4][1] = x - 180;            position[4][0] = y + 398;
            position[5][1] = x + 135;            position[5][0] = y + 50;
            position[6][1] = x + 225;            position[6][0] = y + 219;
            position[7][1] = x + 180;            position[7][0] = y + 398;
            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true &&
                    ch[2] == true && ch[3] == true &&
                    ch[4] == true && ch[5] == true &&
                    ch[6] == true && ch[7] == true) {

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                        Camera2BasicFragment.textView.setText("" + millis / 1000);
                    }
                    @Override
                    public void onFinish() {
                        cheak = 2;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);

                    }
                }.start();

            }
        }
    }

    /*  จับท่าที่ 2 ใหม่  ท่ายกมือขึ้น */
    public void Position1_2(Canvas canvas) {

        if (this.cheak == 2) {
            txtdis = "ยกมือขึ้นให้แขนตั้งฉากขนานกับพื้น";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];

            position[0][1] = x;            position[0][0] = y - 248;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 180;            position[2][0] = y;
            position[3][1] = x - 405;            position[3][0] = y;
            position[4][1] = x - 315;            position[4][0] = y - 198;
            position[5][1] = x + 180;            position[5][0] = y;
            position[6][1] = x + 405;            position[6][0] = y;
            position[7][1] = x + 360;            position[7][0] = y - 198;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                        Camera2BasicFragment.textView.setText("" + millis / 1000);
                        //here you can have your logic to set text to edittext
                    }
                    @Override
                    public void onFinish() {
                        cheak = 3;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);

                    }
                }.start();

            }

        }
    }

    public void Position1_3(Canvas canvas) {


        if (cheak == 3) {
            txtdis = "ประสานมือทั้งสองข้างไว้ที่ท้ายทอย ให้รู้สึกตึงบริเวณกล้ามเนื้อไหล่และต้นแขนเล็กน้อย";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
//        int x = 540;
//        int y = 596;

            position[0][1] = x;            position[0][0] = y - 150;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 180;            position[2][0] = y;
            position[3][1] = x - 380;            position[3][0] = y - 100;
            position[4][1] = x - 180;            position[4][0] = y - 100;
            position[5][1] = x + 180;            position[5][0] = y;
            position[6][1] = x + 380;            position[6][0] = y - 100;
            position[7][1] = x + 180;            position[7][0] = y - 100;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {


                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                        //here you can have your logic to set text to edittext
                        Camera2BasicFragment.textView.setText("" + millis / 1000);
                    }

                    @Override
                    public void onFinish() {
                        cheak = 4;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);
                    }
                }.start();
            }
        }
    }

    public void Position2(Canvas canvas) {
        if (this.cheak == 4) {
            txtdis = "ท่าเตรียม นั่งหลังตรง วางมือบนหน้าตัก";

            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*(i,1) = Y,                    (i,0) = X*/
            position[0][1] = x;            position[0][0] = y - 198;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 180;            position[2][0] = y + 50;
            position[3][1] = x - 225;            position[3][0] = y + 219;
            position[4][1] = x - 180;            position[4][0] = y + 398;
            position[5][1] = x + 135;            position[5][0] = y + 50;
            position[6][1] = x + 225;            position[6][0] = y + 219;
            position[7][1] = x + 180;            position[7][0] = y + 398;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);

            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {


                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                         Camera2BasicFragment.textView.setText("" + millis / 1000);
                    }

                    @Override
                    public void onFinish() {
                        cheak = 5;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);
                    }
                }.start();

            }
        }
    }

    public void Position2_1(Canvas canvas) {

        if (cheak == 5) {
            txtdis = "ประสานมือไว้ที่กลางอก";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*x=540  y=596*/
            position[0][1] = x;            position[0][0] = y - 149;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 135;            position[2][0] = y + 49;
            position[3][1] = x - 315;            position[3][0] = y + 99;
            position[4][1] = x - 90;            position[4][0] = y + 99;
            position[5][1] = x + 135;            position[5][0] = y + 49;
            position[6][1] = x + 345;            position[6][0] = y + 99;
            position[7][1] = x + 90;            position[7][0] = y + 99;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);



            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {
                TextView textView = myView.findViewById(R.id.texttime);

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                         Camera2BasicFragment.textView.setText("" + millis / 1000);
                        //here you can have your logic to set text to edittext
                    }

                    @Override
                    public void onFinish() {
                        cheak = 6;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);


                    }
                }.start();
            }

        }
    }

    public void Position2_2(Canvas canvas) {

        if (cheak == 6) {
            txtdis = "ประสานมือทั้งสองข้างไว้แล้วค่อยๆยกแขนขึ้น แขนทั้งสองข้างแบบบริเวณศีรษะ ให้รู้สึกตึงบริเวณกล้ามเนื้อไหล่และต้นแขนเล็กน้อย";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];

            position[0][1] = x;            position[0][0] = y - 199;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 135;            position[2][0] = y + 10;
            position[3][1] = x - 135;            position[3][0] = y - 199;
            position[4][1] = x - 135;            position[4][0] = y - 397;
            position[5][1] = x + 135;            position[5][0] = y + 10;
            position[6][1] = x + 135;            position[6][0] = y - 199;
            position[7][1] = x + 135;            position[7][0] = y - 397;


            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);

            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {
                TextView textView = myView.findViewById(R.id.texttime);


                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                       Camera2BasicFragment.textView.setText("" + millis / 1000);
                        //here you can have your logic to set text to edittext
                    }

                    @Override
                    public void onFinish() {
                        cheak = 7;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);

                    }
                }.start();

            }
        }
    }
    public void Position3(Canvas canvas) {

        if (this.cheak == 7) {
            txtdis = "ท่าเตรียม นั่งหลังตรง วางมือบนหน้าตัก";

            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*(i,1) = Y,                    (i,0) = X*/
            position[0][1] = x;            position[0][0] = y - 198;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 180;            position[2][0] = y + 50;
            position[3][1] = x - 225;            position[3][0] = y + 219;
            position[4][1] = x - 180;            position[4][0] = y + 398;
            position[5][1] = x + 135;            position[5][0] = y + 50;
            position[6][1] = x + 225;            position[6][0] = y + 219;
            position[7][1] = x + 180;            position[7][0] = y + 398;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {

//                cheak = 2;
//                Position1_2(canvas);
                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                     Camera2BasicFragment.textView.setText("" + millis / 1000);
                    }

                    @Override
                    public void onFinish() {
                        cheak = 8;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);
                    }
                }.start();

            }
        }
    }

    public void Position3_1(Canvas canvas) {

        if (cheak == 8) {
            txtdis = "ประสานฝ่ากันตรงบริเวณหน้าอก ดันฝ่ามือเข้าหากันเล็กน้อย ให้ท่อนแขนขนานกับพื้น";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*x=585  y=596*/
            position[0][1] = x;            position[0][0] = y - 199;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 180;            position[2][0] = y + 49;
            position[3][1] = x - 315;            position[3][0] = y + 198;
            position[4][1] = x - 45;            position[4][0] = y + 198;
            position[5][1] = x + 180;            position[5][0] = y + 49;
            position[6][1] = x + 315;            position[6][0] = y + 198;
            position[7][1] = x + 45;            position[7][0] = y + 198;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);


            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {
                TextView textView = myView.findViewById(R.id.texttime);

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                        //here you can have your logic to set text to edittext
                         Camera2BasicFragment.textView.setText("" + millis / 1000);
                    }

                    @Override
                    public void onFinish() {
                        cheak = 9;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);
                    }
                }.start();

            }
        }
    }

    public void Position4(Canvas canvas) {
        if (this.cheak == 9) {
            txtdis = "ท่าเตรียม นั่งหลังตรง วางมือบนหน้าตัก";

            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*(i,1) = Y,                    (i,0) = X*/
            position[0][1] = x;            position[0][0] = y - 198;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 180;            position[2][0] = y + 50;
            position[3][1] = x - 225;            position[3][0] = y + 219;
            position[4][1] = x - 180;            position[4][0] = y + 398;
            position[5][1] = x + 135;            position[5][0] = y + 50;
            position[6][1] = x + 225;            position[6][0] = y + 219;
            position[7][1] = x + 180;            position[7][0] = y + 398;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {

//                cheak = 2;
//                Position1_2(canvas);
                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                        Camera2BasicFragment.textView.setText("" + millis / 1000);
                    }
                    @Override
                    public void onFinish() {
                        cheak = 10;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);
                    }
                }.start();

            }
        }
    }

    public void Position4_1(Canvas canvas) {

        if (this.cheak == 10) {
            txtdis = "เปลี่ยนจากประกบฝ่ามือด้านบน เป็นด้านล่างแทนบ้าง ท่อนแขนขนานพื้นเหมือนเดิม";

            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*x = 540  y=496*/
            position[0][1] = x;            position[0][0] = y - 199;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 180;            position[2][0] = y + 49;
            position[3][1] = x - 315;            position[3][0] = y + 198;
            position[4][1] = x - 45;            position[4][0] = y + 198;
            position[5][1] = x + 180;            position[5][0] = y + 49;
            position[6][1] = x + 315;            position[6][0] = y + 198;
            position[7][1] = x + 45;            position[7][0] = y + 198;
            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                        Camera2BasicFragment.textView.setText("" + millis / 1000);
                        //here you can have your logic to set text to edittext
                    }

                    @Override
                    public void onFinish() {
                        cheak = 11;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);
                    }
                }.start();

            }
        }
    }

    public void Position5(Canvas canvas) {
        if (this.cheak == 11) {
            txtdis = "ท่าเตรียม นั่งหลังตรง วางมือบนหน้าตัก";

            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*(i,1) = Y,                    (i,0) = X*/
            position[0][1] = x;            position[0][0] = y - 198;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 180;            position[2][0] = y + 50;
            position[3][1] = x - 225;            position[3][0] = y + 219;
            position[4][1] = x - 180;            position[4][0] = y + 398;
            position[5][1] = x + 135;            position[5][0] = y + 50;
            position[6][1] = x + 225;            position[6][0] = y + 219;
            position[7][1] = x + 180;            position[7][0] = y + 398;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                        Camera2BasicFragment.textView.setText("" + millis / 1000);
                    }

                    @Override
                    public void onFinish() {
                        cheak = 12;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);
                    }
                }.start();

            }
        }
    }

    /*add picture*/
    public void Position5_1(Canvas canvas) {

        if (this.cheak == 12) {
            txtdis = "ยกมือขึ้น อีกข้างวางที่หน้าตัก";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*x = 540  y=546*/
            position[0][1] = x;            position[0][0] = y - 199;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 135;            position[2][0] = y /*- 90*/;
            position[3][1] = x - 180;            position[3][0] = y - 248;
            position[4][1] = x - 180;            position[4][0] = y - 447;
            position[5][1] = x + 135;            position[5][0] = y /*- 50*/;
            position[6][1] = x + 180;            position[6][0] = y + 248;
            position[7][1] = x + 180;            position[7][0] = y + 397;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true  && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {
                TextView textView = myView.findViewById(R.id.texttime);

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                        Camera2BasicFragment.textView.setText("" + millis / 1000);
                        //here you can have your logic to set text to edittext
                    }

                    @Override
                    public void onFinish() {
                        cheak = 13;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);
                    }
                }.start();
            }
        }
    }

    public void Position5_2(Canvas canvas) {

        if (this.cheak == 13) {
            txtdis = "นำมือที่ยกขึ้นมาแตะที่หูฝั่งตรงข้าม";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*x = 540  y=546*/
            position[0][1] = x;            position[0][0] = y - 199;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 180;            position[2][0] = y - 30;
            position[3][1] = x - 225;            position[3][0] = y - 203;
            position[4][1] = x - 10;            position[4][0] = y - 199;
            position[5][1] = x + 135;            position[5][0] = y + 50;
            position[6][1] = x + 225;            position[6][0] = y + 219;
            position[7][1] = x + 180;            position[7][0] = y + 398;


            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true  && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {
                TextView textView = myView.findViewById(R.id.texttime);

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
//                        here you can have your logic to set text to edittext
                        Camera2BasicFragment.textView.setText("" + millis / 1000);
                    }

                    @Override
                    public void onFinish() {
                        cheak = 14;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);
                    }
                }.start();
            }
        }
    }

    public void Position5_3(Canvas canvas) {

        if (this.cheak == 14) {
            txtdis = "โน้นหัวไปที่หัวไหล่เล็กน้อยพอรู้สึกสบาย";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*x=540 y=546*/
            position[0][1] = x - 135;            position[0][0] = y - 199;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 180;            position[2][0] = y - 30;
            position[3][1] = x - 225;            position[3][0] = y - 203;
            position[4][1] = x - 10;            position[4][0] = y - 199;
        position[5][1] = x + 135;            position[5][0] = y + 50;
        position[6][1] = x + 225;            position[6][0] = y + 219;
        position[7][1] = x + 180;            position[7][0] = y + 398;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {


                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                        Camera2BasicFragment.textView.setText("" + millis / 1000);
                        //here you can have your logic to set text to edittext
                    }

                    @Override
                    public void onFinish() {
                        cheak = 15;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);
                    }
                }.start();
            }
        }
    }

    public void Position6(Canvas canvas) {
        if (this.cheak == 15) {
            txtdis = "ท่าเตรียม นั่งหลังตรง วางมือบนหน้าตัก";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*(i,1) = Y,                    (i,0) = X*/
            position[0][1] = x;            position[0][0] = y - 198;
            position[1][1] = x;            position[1][0] = y;
            position[2][1] = x - 180;            position[2][0] = y + 50;
            position[3][1] = x - 225;            position[3][0] = y + 219;
            position[4][1] = x - 180;            position[4][0] = y + 398;
            position[5][1] = x + 135;            position[5][0] = y + 50;
            position[6][1] = x + 225;            position[6][0] = y + 219;
            position[7][1] = x + 180;            position[7][0] = y + 398;
            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {

//                cheak = 2;
//                Position1_2(canvas);
                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                        Camera2BasicFragment.textView.setText("" + millis / 1000);
                    }

                    @Override
                    public void onFinish() {
                        cheak = 16;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);

                    }
                }.start();

            }
        }
    }

    /*add picture*/
    public void Position6_1(Canvas canvas) {

        if (this.cheak == 16) {
            txtdis = "ยกมือขึ้น อีกข้างวางที่หน้าตัก";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*x=585  y=596*/

            position[0][1] = x;            position[0][0] = y - 199;
            position[1][1] = x;            position[1][0] = y;
        position[2][1] = x - 180;            position[2][0] = y + 50;
        position[3][1] = x - 225;            position[3][0] = y + 219;
        position[4][1] = x - 180;            position[4][0] = y + 398;
        position[5][1] = x + 135;            position[5][0] = y + 10;
        position[6][1] = x + 135;            position[6][0] = y - 199;
        position[7][1] = x + 135;            position[7][0] = y - 397;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {
                TextView textView = myView.findViewById(R.id.texttime);

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                         Camera2BasicFragment.textView.setText("" + millis / 1000);
                        //here you can have your logic to set text to edittext
                    }

                    @Override
                    public void onFinish() {
                        cheak = 17;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);

                    }
                }.start();
            }
        }
    }
    public void Position6_2(Canvas canvas) {

        if (this.cheak == 17) {

            txtdis = "นำมือที่ยกขึ้นมาแตะที่หูฝั่งตรงข้าม";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*x=585  y=596*/

            position[0][1] = x;            position[0][0] = y - 199;
            position[1][1] = x;            position[1][0] = y;
        position[2][1] = x - 180;            position[2][0] = y + 50;
        position[3][1] = x - 225;            position[3][0] = y + 219;
        position[4][1] = x - 180;            position[4][0] = y + 398;
            position[5][1] = x + 180;            position[5][0] = y;
            position[6][1] = x + 225;            position[6][0] = y - 203;
            position[7][1] = x + 10;            position[7][0] = y - 199;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {
                TextView textView = myView.findViewById(R.id.texttime);

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                        Camera2BasicFragment.textView.setText("" + millis / 1000);
                        //here you can have your logic to set text to edittext
                    }

                    @Override
                    public void onFinish() {
                        cheak = 18;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);

                    }
                }.start();
            }
        }
    }
    public void Position6_3(Canvas canvas) {

        if (this.cheak == 18) {
            txtdis = "โน้นหัวไปที่หัวไหล่เล็กน้อยพอรู้สึกสบาย";
            int x = (int) arr[1][1];
            int y = (int) arr[1][0];
            /*x=585  y=596*/
            position[0][1] = x + 135;            position[0][0] = y - 199;
            position[1][1] = x;            position[1][0] = y;
        position[2][1] = x - 180;            position[2][0] = y + 50;
        position[3][1] = x - 225;            position[3][0] = y + 219;
        position[4][1] = x - 180;            position[4][0] = y + 398;
            position[5][1] = x + 180;            position[5][0] = y;
            position[6][1] = x + 225;            position[6][0] = y - 203;
            position[7][1] = x + 10;            position[7][0] = y - 199;

            distanceCompare();
            drawPosition(canvas);
            checkPositioncom(canvas);
            if (ch[0] == true && ch[1] == true && ch[2] == true && ch[3] == true && ch[4] == true && ch[5] == true && ch[6] == true && ch[7] == true) {
                TextView textView = myView.findViewById(R.id.texttime);

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millis) {
                         Camera2BasicFragment.textView.setText("" + millis / 1000);
                        //here you can have your logic to set text to edittext
                    }
                    @Override
                    public void onFinish() {
                        cheak = 1;
                        Camera2BasicFragment.runtimePosition(txtdis, cheak);
                    }
                }.start();
            }
        }
    }

    public void distanceCompare()  {
        for (int i = 0; i < 8; i++) {
            x1[i] = position[i][0];
            x2[i] = arr[i][0];
            y1[i] = position[i][1];
            y2[i] = arr[i][1];
            X = Math.pow((x1[i] - x2[i]),2);
            Y = Math.pow((y1[i] - y2[i]),2);
            result[i] = Math.sqrt(X + Y);
            Log.d("Sqrt ", "index : " + i + " == " + result[i] + " ");
        }
    }

    public void drawPosition(Canvas canvas) {
        canvas.drawLine(position[0][1], position[0][0], position[1][1], position[1][0], paint[0]);
        canvas.drawLine(position[1][1], position[1][0], position[2][1], position[2][0], paint[0]);
        canvas.drawLine(position[1][1], position[1][0], position[5][1], position[5][0], paint[0]);
        canvas.drawLine(position[2][1], position[2][0], position[3][1], position[3][0], paint[0]);
        canvas.drawLine(position[3][1], position[3][0], position[4][1], position[4][0], paint[0]);
        canvas.drawLine(position[5][1], position[5][0], position[6][1], position[6][0], paint[0]);
        canvas.drawLine(position[6][1], position[6][0], position[7][1], position[7][0], paint[0]);
        canvas.drawCircle(position[0][1], position[0][0], 30, pointp);
        canvas.drawCircle(position[1][1], position[1][0], 30, pointp);
        canvas.drawCircle(position[2][1], position[2][0], 30, pointp);
        canvas.drawCircle(position[3][1], position[3][0], 30, pointp);
        canvas.drawCircle(position[4][1], position[4][0], 30, pointp);
        canvas.drawCircle(position[5][1], position[5][0], 30, pointp);
        canvas.drawCircle(position[6][1], position[6][0], 30, pointp);
        canvas.drawCircle(position[7][1], position[7][0], 30, pointp);
    }

    public void checkPositioncom(Canvas canvas) {
        if (result[0] >= 0 && result[0] <= 80) {
            canvas.drawCircle(arr[0][1], arr[0][0], 40, paint[11]);
            ch[0] = true;
        } else {
            ch[0] = false;
        }
        if (result[1] >= 0 && result[1] <= 80) {
            canvas.drawCircle(arr[1][1], arr[1][0], 40, paint[11]);
            ch[1] = true;
        } else {
            ch[1] = false;
        }
        if (result[2] >= 0 && result[2] <= 80) {
            canvas.drawCircle(arr[2][1], arr[2][0], 40, paint[11]);
            ch[2] = true;
        } else {
            ch[2] = false;
        }
        if (result[3] >= 0 && result[3] <= 80) {
            canvas.drawCircle(arr[3][1], arr[3][0], 40, paint[11]);
            ch[3] = true;
        } else {
            ch[3] = false;
        }
        if (result[4] >= 0 && result[4] <= 80) {
            canvas.drawCircle(arr[4][1], arr[4][0], 40, paint[11]);
            ch[4] = true;
        } else {
            ch[4] = false;
        }
        if (result[5] >= 0 && result[5] <= 80) {
            canvas.drawCircle(arr[5][1], arr[5][0], 40, paint[11]);
            ch[5] = true;
        } else {
            ch[5] = false;
        }
        if (result[6] >= 0 && result[6] <= 80) {
            canvas.drawCircle(arr[6][1], arr[6][0], 40, paint[11]);
            ch[6] = true;
        } else {
            ch[6] = false;
        }
        if (result[7] >= 0 && result[7] <= 80) {
            canvas.drawCircle(arr[7][1], arr[7][0], 40, paint[11]);
            ch[7] = true;
        } else {
            ch[7] = false;
        }
    }


}
