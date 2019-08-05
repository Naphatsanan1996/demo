package com.example.android.tflitecamerademo.settime;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.android.tflitecamerademo.R;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends FragmentActivity implements TimePickerDialog.OnTimeSetListener {
    private TextView mTextView;

    private TextView sTextView;
    private String switchOn = "สถานะ: เปิด";
    private String switchOff = "สถานะ: ปิด";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);

        Button buttonTimePicker = findViewById(R.id.button_timepicker);
        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Time");
            }
        });

        Button buttonCancelAlarm = findViewById(R.id.button_cancel);
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
        setSwitch();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        updateTimeText(c);
        startAlarm(c);
    }

//    public void onTimeSetone() {
//
//
//
//            Calendar c = Calendar.getInstance();
//            int hourspus = new Time(System.currentTimeMillis()).getHours();
//            int minutes = new Time(System.currentTimeMillis()).getMinutes();
//
//            //int SumTime = minutes;
////            int hourspus = hours+1;
////            if(hourspus == 24){
////                hourspus = 0;
////            }
//            /*int hourspus =2;
//            int minutes=48;*/
//            minutes = minutes+1;
//
//
//            c.set(Calendar.HOUR_OF_DAY, hourspus);
//            c.set(Calendar.MINUTE, minutes);
//            c.set(Calendar.SECOND, 0);
//            Log.i("Getdata", hourspus + ":" + minutes);
//            startAlarm(c);
//
//
//    }

    private void setSwitch( ) {
        Switch simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);
        sTextView = (TextView) findViewById(R.id.textViewSw);
        simpleSwitch.setChecked(false);
        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked ) {
                if (bChecked) {
                    sTextView.setText(switchOn);
                    //onTimeSetone();

                } else {
                    sTextView.setText(switchOff);
                }
            }
        });
        if (simpleSwitch.isChecked()) {
            sTextView.setText(switchOn);
        } else {
            sTextView.setText(switchOff);
        }
    }

    private void updateTimeText(Calendar c) {
        String timeText = "แจ้งเตือนเวลา: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        mTextView.setText(timeText);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        mTextView.setText("ไม่มีการแจ้งเตือน");
    }
}
