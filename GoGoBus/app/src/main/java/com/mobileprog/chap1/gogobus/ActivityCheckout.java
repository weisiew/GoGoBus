package com.mobileprog.chap1.gogobus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityCheckout extends AppCompatActivity {

    private TextView booked, depart, roomNum, totalPrice, timeA, timeB, timeC, timeD;
    private FloatingActionButton btnConfirm;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        booked = findViewById(R.id.book);
        depart = findViewById(R.id.depart);
        roomNum = findViewById(R.id.checkoutRoomNumber);
        totalPrice = findViewById(R.id.tvTotalPrice);
        timeA = findViewById(R.id.timeA);
        timeB = findViewById(R.id.timeB);
        timeC = findViewById(R.id.timeC);
        timeD = findViewById(R.id.timeD);
        btnConfirm = findViewById(R.id.btnConfirm);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("ClassBooking").child("booking1");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String bookdate = dataSnapshot.child("bookDate").getValue().toString();
                String departdate = dataSnapshot.child("departDate").getValue().toString();
                String Q1 = "Quantity: " + Integer.valueOf(dataSnapshot.child("time1").getValue().toString());
                String Q2 = "Quantity: " + Integer.valueOf(dataSnapshot.child("time2").getValue().toString());
                String Q3 = "Quantity: " + Integer.valueOf(dataSnapshot.child("time3").getValue().toString());
                String Q4 = "Quantity: " + Integer.valueOf(dataSnapshot.child("time4").getValue().toString());
                String room = dataSnapshot.child("bookingNum").getValue().toString();

                booked.setText(bookdate);
                depart.setText(departdate);
                timeA.setText(Q1);
                timeB.setText(Q2);
                timeC.setText(Q3);
                timeD.setText(Q4);
                roomNum.setText(room);

                ClassBooking classBooking = dataSnapshot.getValue(ClassBooking.class);
                double bus8am = 25.00, bus12pm = 32.00, bus4pm = 25.00, bus8pm = 35.00;

                double totalBus8am = bus8am * classBooking.getTime1();
                double totalBus12pm = bus12pm * classBooking.getTime2();
                double totalBus4pm = bus4pm * classBooking.getTime3();
                double totalBus8pm = bus8pm * classBooking.getTime4();

                double totalAll = totalBus8am + totalBus12pm + totalBus4pm + totalBus8pm;
                totalPrice.setText("Total RM " + String.format("%.2f", totalAll));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ActivityCheckout.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCheckout.this, SecondActivity.class));
            }
        });
    }
}
