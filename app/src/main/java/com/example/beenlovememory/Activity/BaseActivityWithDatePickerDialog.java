package com.example.beenlovememory.Activity;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beenlovememory.R;

import java.util.Calendar;

public class BaseActivityWithDatePickerDialog extends AppCompatActivity {

    public static final int HIEU_CHINH_THOI_GIAN = 60000 * 60;


    protected Calendar mCurrentSelectedDate;


    protected void dialogForCalendar(final MyDateSetListener listener) {

        if (mCurrentSelectedDate == null) {
            mCurrentSelectedDate = Calendar.getInstance();
            mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis());
        }

        DatePickerDialog mDatePicker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int selectedmonth, int dayOfMonth) {
                        int month = selectedmonth + 1;

                        mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis()); //refresh to now

                        mCurrentSelectedDate.set(Calendar.YEAR, year);
                        mCurrentSelectedDate.set(Calendar.MONTH, selectedmonth);
                        mCurrentSelectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        //int monthDebug = Calendar.getInstance().get(Calendar.MONTH);

                        // bat truong hop set qua ngay hien tai

                        if (mCurrentSelectedDate.getTimeInMillis() > (System.currentTimeMillis() + HIEU_CHINH_THOI_GIAN)) {

                            Toast.makeText( getApplicationContext() ,"Không thể chọn ngày tương lai", Toast.LENGTH_LONG).show();

                            //refresh to now
                            mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis());

                            return;
                        }
                        //

                        listener.onDateSet(mCurrentSelectedDate);


                    }
                }, mCurrentSelectedDate.get(Calendar.YEAR), mCurrentSelectedDate.get(Calendar.MONTH), mCurrentSelectedDate.get(Calendar.DAY_OF_MONTH));
        mDatePicker.setTitle("Chọn ngày bắt đầu");
        mDatePicker.show();
    }
}
