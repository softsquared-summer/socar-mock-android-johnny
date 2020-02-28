package com.softsquared.template.src.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TimeSetActivity extends BaseActivity {

    public boolean isBorrowBoxExpanded = false;
    public boolean isReturnBoxExpanded = false;
    public boolean isConfirmedEveryting = false;
    String pickedBorrowDate;
    int pickedBorrowDateIndex;
    int pickedBorrowHour;
    int pickedBorrowMin;
    String pickedReturnDate;
    int pickedReturnDateIndex;
    int pickedReturnHour;
    int pickedReturnMin;

    TextView mTvTotalTime;
    TextView mTvBorrowTotalTime;
    TextView mTvReturnTotalTime;
    TextView mTvTotalTimeDescription;

    Button mBtnConfirm;

    public String[] mDates;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set);

        mDates = getDatesFromCalender();

        mTvTotalTime = findViewById(R.id.time_set_tv_total_time);
        mTvBorrowTotalTime = findViewById(R.id.time_set_tv_borrow_time);
        mTvReturnTotalTime = findViewById(R.id.time_set_tv_return_time);
        mTvTotalTimeDescription = findViewById(R.id.time_set_tv_time_description);

        mBtnConfirm = findViewById(R.id.time_set_btn_confirm);

        ImageButton cancelButton = findViewById(R.id.time_set_btn_cancel);
        final ImageButton borrowPickerButton = findViewById(R.id.time_set_btn_borrow_time);
        final ImageButton returnPickerButton = findViewById(R.id.time_set_btn_return_time);
        final View borrowPickerBox = findViewById(R.id.time_set_borrow_number_picker);
        final View returnPickerBox = findViewById(R.id.time_set_return_number_picker);

        NumberPicker borrow_date_picker = findViewById(R.id.time_set_np_borrow_date);
        NumberPicker borrow_hour_picker = findViewById(R.id.time_set_np_borrow_hour);
        NumberPicker borrow_min_picker = findViewById(R.id.time_set_np_borrow_min);
        NumberPicker return_date_picker = findViewById(R.id.time_set_np_return_date);
        NumberPicker return_hour_picker = findViewById(R.id.time_set_np_return_hour);
        NumberPicker return_min_picker = findViewById(R.id.time_set_np_return_min);
        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d",value);
            }
        };
        NumberPicker.Formatter dateFormatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return mDates[value];
            }
        };
        String[] minValue = {"00","10","20","30","40","50"};

        borrow_date_picker.setMinValue(0);
        borrow_date_picker.setMaxValue(mDates.length -1);
        borrow_date_picker.setDisplayedValues(mDates);
        borrow_date_picker.setFormatter(dateFormatter);
        return_date_picker.setMinValue(0);
        return_date_picker.setMaxValue(mDates.length -1);
        return_date_picker.setDisplayedValues(mDates);
        return_date_picker.setFormatter(dateFormatter);

        borrow_hour_picker.setMinValue(0);
        borrow_hour_picker.setMaxValue(24);
        borrow_hour_picker.setFormatter(formatter);
        return_hour_picker.setMinValue(0);
        return_hour_picker.setMaxValue(24);
        return_hour_picker.setFormatter(formatter);

        borrow_min_picker.setMinValue(0);
        borrow_min_picker.setMaxValue(minValue.length-1);
        borrow_min_picker.setDisplayedValues(minValue);
        return_min_picker.setMinValue(0);
        return_min_picker.setMaxValue(minValue.length-1);
        return_min_picker.setDisplayedValues(minValue);

        //키보드 수정 금지
        borrow_date_picker.setWrapSelectorWheel(false);
        return_date_picker.setWrapSelectorWheel(false);
        borrow_date_picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        return_date_picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        borrow_hour_picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        return_hour_picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        borrow_min_picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        return_min_picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        //NumberPicker 안에 있는 값들
        pickedBorrowDateIndex = borrow_date_picker.getValue();
        pickedReturnDateIndex = return_date_picker.getValue();

        pickedBorrowDate = mDates[borrow_date_picker.getValue()];
        pickedBorrowHour = borrow_hour_picker.getValue();
        pickedBorrowMin = borrow_min_picker.getValue();
        pickedReturnDate = mDates[return_date_picker.getValue()];
        pickedReturnHour = return_hour_picker.getValue();
        pickedReturnMin = return_min_picker.getValue();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        borrowPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBorrowBoxExpanded){
                    borrowPickerBox.setVisibility(View.GONE);
                    borrowPickerButton.setImageResource(R.drawable.down_side_arrow_icon);
                    isBorrowBoxExpanded=false;
                }else{
                    borrowPickerBox.setVisibility(View.VISIBLE);
                    returnPickerBox.setVisibility(View.GONE);
                    borrowPickerButton.setImageResource(R.drawable.upside_arrow_icon);
                    returnPickerButton.setImageResource(R.drawable.down_side_arrow_icon);
                    isBorrowBoxExpanded=true;
                }
            }
        });

        returnPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isReturnBoxExpanded){
                    returnPickerBox.setVisibility(View.GONE);
                    returnPickerButton.setImageResource(R.drawable.down_side_arrow_icon);
                    isReturnBoxExpanded=false;
                }else{
                    borrowPickerBox.setVisibility(View.GONE);
                    returnPickerBox.setVisibility(View.VISIBLE);
                    borrowPickerButton.setImageResource(R.drawable.down_side_arrow_icon);
                    returnPickerButton.setImageResource(R.drawable.upside_arrow_icon);
                    isReturnBoxExpanded=true;
                }
            }
        });

        borrow_date_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pickedBorrowDateIndex = newVal;
                pickedBorrowDate = mDates[newVal];
                adjustStrings();
            }
        });
        borrow_hour_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pickedBorrowHour = newVal;
                adjustStrings();
            }
        });
        borrow_min_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pickedBorrowMin = newVal;
                adjustStrings();
            }
        });
        return_date_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pickedReturnDateIndex = newVal;
                pickedReturnDate = mDates[newVal];
                adjustStrings();
            }
        });
        return_hour_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pickedReturnHour = newVal;
                adjustStrings();
            }
        });
        return_min_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pickedReturnMin = newVal;
                adjustStrings();
            }
        });

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConfirmedEveryting){
                    Intent backToBasicTaskIntent = new Intent();
                    backToBasicTaskIntent.putExtra("borrowDate",pickedBorrowDate);
                    backToBasicTaskIntent.putExtra("borrowHour",pickedBorrowHour);
                    backToBasicTaskIntent.putExtra("borrowMin",pickedBorrowMin);
                    backToBasicTaskIntent.putExtra("returnDate",pickedReturnDate);
                    backToBasicTaskIntent.putExtra("returnHour",pickedReturnHour);
                    backToBasicTaskIntent.putExtra("returnMin",pickedReturnMin);
                    backToBasicTaskIntent.putExtra("totalTime",mTvTotalTimeDescription.getText());
                    setResult(RESULT_OK,backToBasicTaskIntent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"입력이 잘못되었습니다. 수정 후 다시 시도해 주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void adjustStrings(){
        int dayLong = 0 ;
        int minLong = 0 ;
        int hourLong = 0 ;
        boolean periodCheck = true;
        boolean allZeroCheck = true;
        String dayLongString ;
        String minLongString ;
        String hourLongString ;
        String totalTimeText ;

        String borrowTotalText;
        String returnTotalText;
        String totalTimeDescription;

        String borrowHead="";
        String returnHead="";

        //한번만 제대로 했다가 풀리면 안되니 일단 false 로 기본값을 둔다.
        isConfirmedEveryting = false;

        for(int i=0; i<5; i++){ borrowHead = borrowHead+pickedBorrowDate.charAt(i); }
        for(int i=0; i<5; i++){ returnHead = returnHead+pickedReturnDate.charAt(i); }

        borrowTotalText = pickedBorrowDate+" "+String.format("%02d",pickedBorrowHour)+":"+String.format("%02d",pickedBorrowMin);
        returnTotalText = pickedReturnDate+" "+String.format("%02d",pickedReturnHour)+":"+String.format("%02d",pickedReturnMin);
        totalTimeDescription = borrowHead+" "+String.format("%02d",pickedBorrowHour)+":"+String.format("%02d",pickedBorrowMin)+" - "+returnHead+" "+String.format("%02d",pickedReturnHour)+":"+String.format("%02d",pickedReturnMin);

        if(pickedReturnMin-pickedBorrowMin > 0){
            minLong = pickedReturnMin - pickedBorrowMin;
            minLongString = " "+minLong*10+"분";
        }else if(pickedReturnMin-pickedBorrowMin == 0){
            minLongString = "";
        }else{
            minLong = pickedBorrowMin - pickedBorrowMin;
            minLongString = " "+minLong*10+"분";
            pickedReturnHour -= 1;
        }

        if(pickedReturnHour - pickedBorrowHour > 0){
            hourLong = pickedReturnHour - pickedBorrowHour;
            hourLongString = " "+hourLong+"시간";
        }else if(pickedReturnHour - pickedBorrowHour == 0){
            hourLongString = "";
        }else{
            hourLong = pickedBorrowHour - pickedReturnHour;
            hourLongString = " "+hourLong+"시간";
            if(pickedReturnDateIndex != 0){
                pickedReturnDateIndex-=1;
            }else{
                periodCheck = false;
            }
            pickedReturnDate = mDates[pickedReturnDateIndex];
        }

        if(pickedReturnDateIndex - pickedBorrowDateIndex > 0) {
            dayLong = pickedReturnDateIndex - pickedBorrowDateIndex;
            dayLongString = " "+dayLong+"일";
        }else if(pickedReturnDateIndex - pickedBorrowDateIndex == 0){
            dayLongString = "";
        }else{
            dayLongString = "";
            periodCheck = false;
        }

        if((dayLong==0)&&(hourLong==0)&&(minLong==0)){ allZeroCheck = false; }

        if(periodCheck&&allZeroCheck){
            totalTimeText = "총"+dayLongString+hourLongString+minLongString+" 이용";
            mBtnConfirm.setBackgroundColor(getColor(R.color.colorAccent));
            isConfirmedEveryting = true;
        }else if(!periodCheck&&allZeroCheck){
            totalTimeDescription = getString(R.string.time_set_period_warning);
            totalTimeText = getString(R.string.basic_task_time_setting);
            mBtnConfirm.setBackgroundColor(getColor(R.color.colorPrimaryDark));
        }else{
            totalTimeText = getString(R.string.basic_task_time_setting);
            totalTimeDescription = getString(R.string.time_set_time_set);
            mBtnConfirm.setBackgroundColor(getColor(R.color.colorPrimaryDark));
        }

        mTvBorrowTotalTime.setText(borrowTotalText);
        mTvReturnTotalTime.setText(returnTotalText);
        mTvBorrowTotalTime.setTextColor(getColor(R.color.colorAccent));
        mTvReturnTotalTime.setTextColor(getColor(R.color.colorAccent));
        mTvTotalTimeDescription.setText(totalTimeDescription);
        mTvTotalTime.setText(totalTimeText);
    }

    private String[] getDatesFromCalender() {
        Calendar c1 = Calendar.getInstance();

        ArrayList<String> dates = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd (E) ");
        dates.add(dateFormat.format(c1.getTime()));

        for(int i=0; i<60; i++){
            c1.add(Calendar.DATE,1);
            dates.add(dateFormat.format(c1.getTime()));
        }

        return dates.toArray(new String[dates.size() -1]);
    }
}
