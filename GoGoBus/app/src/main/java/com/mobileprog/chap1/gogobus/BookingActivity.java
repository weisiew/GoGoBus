package com.mobileprog.chap1.gogobus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class BookingActivity extends AppCompatActivity {

    private TextView CID, COD, title, randomNumber;
    private ImageView popcid, popcod;
    private FloatingActionButton confirm;
    private Dialog popupCID, popupCOD;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Dialog popup;
    private ElegantNumberButton numBtn1, numBtn2, numBtn3, numBtn4;
    CalendarView calendarViewCID, calendarViewCOD;
    ClassBooking classBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        confirm = findViewById(R.id.btnConfirm);
        numBtn1 = findViewById(R.id.num1);
        numBtn2 = findViewById(R.id.num2);
        numBtn3 = findViewById(R.id.num3);
        numBtn4 = findViewById(R.id.num4);
        classBooking = new ClassBooking();
        popup = new Dialog(this);
        popupCID = new Dialog(this);
        popupCOD = new Dialog(this);
        CID = findViewById(R.id.tvCID);
        COD = findViewById(R.id.tvCOD);
        popcid = findViewById(R.id.popupCID);
        popcod = findViewById(R.id.popupCOD);
        randomNumber = findViewById(R.id.randomNumber);
        classBooking = new ClassBooking();

        Random r = new Random();
        int minNumber = 10000;
        int maxNumber = 100000;
        int random = r.nextInt((maxNumber - minNumber) + 1) + minNumber;
        randomNumber.setText(String.valueOf(random));

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassBooking");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("ClassBooking");

        numBtn1.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                int num = Integer.parseInt(numBtn1.getNumber());
                classBooking.setTime1(num);
            }
        });

        numBtn2.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                int num = Integer.parseInt(numBtn2.getNumber());
                classBooking.setTime2(num);
            }
        });

        numBtn3.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                int num = Integer.parseInt(numBtn3.getNumber());
                classBooking.setTime3(num);
            }
        });

        numBtn4.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                int num = Integer.parseInt(numBtn4.getNumber());
                classBooking.setTime4(num);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classBooking.setCheckindate(CID.getText().toString());
                classBooking.setCheckoutdate(COD.getText().toString());
                classBooking.setBookingNum(randomNumber.getText().toString());
                if(classBooking.getTime1() != 0 || classBooking.getTime2() != 0 || classBooking.getTime3() != 0 || classBooking.getTime4() != 0){
                    databaseReference.child("booking1").setValue(classBooking);
                    Toast.makeText(BookingActivity.this, "Sent to database", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookingActivity.this, ActivityCheckout.class));
                } else {
                    Toast.makeText(BookingActivity.this, "Please add room", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showPopupCID(View v){
        Button saveCID;

        popupCID.setContentView(R.layout.popup_checkindate);
        saveCID = popupCID.findViewById(R.id.saveCID);
        title = popupCID.findViewById(R.id.tvTitle1);
        calendarViewCID = popupCID.findViewById(R.id.calendarViewCOD);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_checkindate, (ViewGroup)findViewById(R.id.layoutCID));

        calendarViewCID.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String dateCID = (dayOfMonth + 1) + "/" + month + "/" + year;
                title.setText(dateCID);
                CID.setText(dateCID);
            }
        });

        saveCID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupCID.dismiss();
            }
        });
        popupCID.show();
    }

    public void showPopupCOD(View v){
        Button saveCOD;

        popupCOD.setContentView(R.layout.popup_checkoutdate);
        saveCOD = popupCOD.findViewById(R.id.saveCOD);
        title = popupCOD.findViewById(R.id.tvTitle2);
        calendarViewCOD = popupCOD.findViewById(R.id.calendarViewCOD);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_checkoutdate, (ViewGroup)findViewById(R.id.layoutCOD));

        calendarViewCOD.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String dateCOD = (dayOfMonth + 1) + "/" + month + "/" + year;
                title.setText(dateCOD);
                COD.setText(dateCOD);
            }
        });

        saveCOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupCOD.dismiss();
            }
        });
        popupCOD.show();
    }


}
