package com.example.asaf.countdown;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class Countdown extends Activity {

    private List<Timer> timers = new ArrayList<Timer>();
    ArrayAdapter<Timer> adapter;
    MainTimer timer = new MainTimer(10000000, 1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        timer.start();
        setData();
        updateListView();
    }

    private void setData(){
        Date date = new Date();
        date.setTime(1839);
        timers.add(new Timer(10, date, "try"));
        timers.add(new Timer(100, new Date(), "first"));
        timers.add(new Timer(99, new Date(), "first"));
        timers.add(new Timer(32, new Date(), "first"));
        timers.add(new Timer(43, new Date(), "first"));
        timers.add(new Timer(43, new Date(), "first"));
        timers.add(new Timer(44, new Date(), "first"));
    }

    private void updateListView() {
        adapter = new TimerAdapter(Countdown.this, R.layout.timer_item, timers);
        ListView list = (ListView)findViewById(R.id.lstTimers);
        list.setAdapter(adapter);
    }


    public class MainTimer extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MainTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            ((TimerAdapter)adapter).UpdateTimers();
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onFinish() {

        }
    }

    public class TimerAdapter extends ArrayAdapter<Timer>
    {
        public TimerAdapter(Context context, int resource, List<Timer> objects) {
            super(context, resource, objects);
            m_workingContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;

            if (itemView == null)
            {
                itemView = getLayoutInflater().inflate(R.layout.timer_item, parent, false);
            }

            Timer currentTimer = timers.get(position);

            // Fill the timer
            TextView time = (TextView)itemView.findViewById(R.id.txtTime);
            Date currDate = currentTimer.getEndDate();

            int seconds = currentTimer.getSeconds();
            time.setText("" + seconds);

            TextView label = (TextView)itemView.findViewById(R.id.txtStatus);
            label.setText(currentTimer.getTaskName());

            return itemView;
        }

        public void UpdateTimers()
        {
            Date now = new Date();
            for (Timer currentTimer : timers)
            {
                currentTimer.Decrease();
            }
        }
        Context m_workingContext;
    }
}
