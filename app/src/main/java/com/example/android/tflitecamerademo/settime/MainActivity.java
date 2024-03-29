package com.example.android.tflitecamerademo.settime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.android.tflitecamerademo.R;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends FragmentActivity implements TimePickerDialog.OnTimeSetListener {
    private TextView mTextView;
    private NumberPicker np;
    private TextView sTextView;
    private String switchOn = "สถานะ: เปิด";
    private String switchOff = "สถานะ: ปิด";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mTextView.setTextColor(Color.BLACK);
        np = findViewById(R.id.numberPicker1);
        np.setWrapSelectorWheel(false);
        np.setMinValue(1);
        np.setMaxValue(3);
        np.setWrapSelectorWheel(false);
        Button buttonTimePicker = findViewById(R.id.button_timepicker);
        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Time");
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

    public void setTime() {

        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,
                1,
                intent,
                0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 60000*np.getValue(),
                60000*np.getValue(),
                pi);


//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
//                System.currentTimeMillis() + (AlarmManager.INTERVAL_HALF_HOUR*2)*np.getValue(),
//                (AlarmManager.INTERVAL_HALF_HOUR*2)*np.getValue(),
//                pi);
    }



    private void setSwitch( ) {
        Switch simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);
        sTextView = (TextView) findViewById(R.id.textViewSw);
        simpleSwitch.setChecked(false);
        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked ) {
                if (bChecked) {
                    sTextView.setText(switchOn);
                    sTextView.setTextColor(Color.parseColor("#01BE84"));
                    setTime();
                } else {
                    sTextView.setText(switchOff);
                    sTextView.setTextColor(Color.BLACK);
                    cancelAlarm();
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
        mTextView.setTextColor(Color.RED);
        mTextView.setText(timeText);
        YoYo.with(Techniques.Tada)
                .duration(700)
                .repeat(1)
                .playOn(mTextView);
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
        mTextView.setTextColor(Color.BLACK);
        mTextView.setText("ไม่มีการแจ้งเตือน");
    }

}
