package com.feima.calendar;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements CalendarLayout.OnCalendarWindowChangeListener {
    private static final String TAG = "测试";
    private CalendarLinearLayout mCalendarLinearLayout;
    private CalendarLayout mCalendarLayout;
    private ImageView mImgCalendarStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCalendarLinearLayout = findViewById(R.id.calendar_linear_layout);
        mCalendarLayout = findViewById(R.id.calendar_layout);
        mCalendarLayout.setOnCalendarWindowChangeListener(this);
        mImgCalendarStatus = findViewById(R.id.img_calendar_status);
    }

    /**
     * 展开
     */
    @Override
    public void expand() {
        mImgCalendarStatus.setImageResource(R.drawable.icon_calendar_off);
    }

    /**
     * 折叠
     */
    @Override
    public void shrink() {
        mImgCalendarStatus.setImageResource(R.drawable.shape_calendar_open);
    }
}