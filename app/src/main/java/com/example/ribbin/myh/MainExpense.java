package com.example.ribbin.myh;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by Ribbin on 07-Nov-17.
 */

public class MainExpense extends AppCompatActivity {
    PieChart pieChart;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_main);
        pieChart = findViewById(R.id.pie_chart01);
        pieChart.setRotationEnabled(true);
        pieChart.setTransparentCircleAlpha(0);

        addDataSet();
        ClickMe();

    }

    private void ClickMe() {
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


        Calendar aST = Calendar.getInstance();
        aST.set(Calendar.HOUR_OF_DAY, 14);
        aST.set(Calendar.MINUTE, 59);
        Log.d("antt","=ppp="+aST);


        PendingIntent piR;
        piR = PendingIntent.getActivity(this, (int) aST.getTimeInMillis(), resultIntent
                , 0);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Test");
        inboxStyle.addLine("1");
        inboxStyle.addLine("2");
        inboxStyle.setSummaryText("+2 More");
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.gift)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setVibrate(new long[]{50, 50})
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentTitle("Inbox").setContentText("cc").setStyle(inboxStyle).addAction(R.drawable.home, "hh", piR);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());

    }


    private void addDataSet() {


        ArrayList<PieEntry> entries = new ArrayList<>();
        TextView in;
        in = (TextView) findViewById(R.id.tv2);
        TextView out;
        out = (TextView) findViewById(R.id.tv1);
        for (int i = 0; i < 2; i++) {


            if (i == 0) {
                entries.add(new PieEntry(20, "รับ"));
                in.setText("รายรับรวม" + " : " + 20);
            } else {
                entries.add(new PieEntry(30, "จ่าย"));
                out.setText("รายจ่ายรวม" + " : " + 30);
            }
        }


        PieDataSet pieDataSet = new PieDataSet(entries, "ภาพรวม");

        pieDataSet.setSliceSpace(4);
        pieDataSet.setValueTextSize(20);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(197, 255, 148));
        colors.add(Color.rgb(255, 180, 156));


        pieDataSet.setColors(colors);
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.animateY(3000);


    }


}
