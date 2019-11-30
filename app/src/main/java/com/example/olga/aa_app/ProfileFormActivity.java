package com.example.olga.aa_app;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class ProfileFormActivity extends AppCompatActivity {

    DatePickerDialog birthdayPicker;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_form);

        final TextView textView = findViewById(R.id.birthday);

        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        birthdayPicker = new DatePickerDialog(ProfileFormActivity.this, R.style.BirthdayPickerStyle,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        final Calendar calendar = Calendar.getInstance();
                        calendar.set(y, m, d);
                        DateFormat formatter = DateFormat.getDateInstance(DateFormat.MEDIUM);
                        String formattedDate = formatter.format(calendar.getTime());
                        textView.setText(formattedDate);
                        birthdayPicker.onSaveInstanceState();
                    }
                }, year, month, day);
        birthdayPicker.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedInstanceState != null) {
                    birthdayPicker.onRestoreInstanceState(savedInstanceState);
                }
                birthdayPicker.show();
            }
        });
    }

    public void cancelProfileChanges(View view) {
        //TODO: Feedback (saved/not saved)
        super.onBackPressed();
    }

    public void saveProfileChanges(View view) {
        //TODO: Feedback (saved/not saved)
    }
}
