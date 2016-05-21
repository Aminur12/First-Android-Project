package com.anjon.myapplication;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreateToDoItemActivity extends AppCompatActivity implements View.OnClickListener {

    StringBuilder dateTimeString = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_to_do_item);
        TextView tvDateTimePicker = (TextView) findViewById(R.id.tv_date_time);
        tvDateTimePicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_date_time) {
            final View dialogView = View.inflate(this, R.layout.date_time_picker, null);
            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            dialogView.findViewById(R.id.date_time_set).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
                    TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);

                    dateTimeString.append(datePicker.getDayOfMonth());
                    dateTimeString.append("/");
                    dateTimeString.append(datePicker.getMonth());
                    dateTimeString.append("/");
                    dateTimeString.append(datePicker.getYear());
                    dateTimeString.append(" ");
                    dateTimeString.append(timePicker.getCurrentHour());
                    dateTimeString.append(":");
                    dateTimeString.append(timePicker.getCurrentMinute());
                    TextView tvDateTime=(TextView) findViewById(R.id.tv_date_time);
                    tvDateTime.setText(dateTimeString);
                    alertDialog.dismiss();
                }
            });
            alertDialog.setView(dialogView);
            alertDialog.show();
        }
    }
}
