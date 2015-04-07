package com.example.asaf.countdown;

import android.content.Context;
import android.text.format.Time;
import android.widget.ArrayAdapter;

import java.util.Date;
import java.util.List;

/**
 * Created by Asaf on 07/04/2015.
 */
public class Timer {
    public Timer(int seconds, Date endDate, String name)
    {
        this.seconds = seconds;
        this.endDate = endDate;
        this.taskName = name;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getSeconds() {return seconds;}

    public void Decrease()
    {
        if (seconds > 0)
        {
            seconds--;
        }
    }

    private int seconds = 0;
    private Date endDate;
    private String taskName;
}

