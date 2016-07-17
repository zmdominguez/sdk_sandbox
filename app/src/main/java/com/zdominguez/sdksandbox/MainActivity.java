package com.zdominguez.sdksandbox;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.zdominguez.sdksandbox.databinding.DialogDataBindingDemoBinding;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_TAG = "notification_tag";
    public static final int NOTIFICATION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    @Nullable
    @OnClick(R.id.button_pct_in_list)
    public void goToPercentInList(View view) {
        startActivity(new Intent(this, PercentInListActivity.class));
    }

    @Nullable @OnClick(R.id.button_pct_in_list_min_height)
    public void goToPercentInListMinHeight(View view) {
        Intent intent = new Intent(this, PercentInListActivity.class);
        intent.putExtra(PercentInListActivity.EXTRA_WITH_MIN_HEIGHT, true);
        startActivity(intent);
    }

    @Nullable @OnClick(R.id.button_resource_annotations)
    public void goToResourceAnnotations(View view) {
        Intent intent = new Intent(this, ResourceAnnotationsActivity.class);
        startActivity(intent);
    }

    @Nullable @OnClick(R.id.button_pct_33)
    public void goToPercentLayout(View view) {
        Intent intent = new Intent(this, PercentLayoutActivity.class);
        startActivity(intent);
    }

    @Nullable @OnClick(R.id.button_linear_layouts)
    public void goToLinearLayoutDemo(View view) {
        Intent intent = new Intent(this, LinearLayoutActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_send_notification)
    public void onSendNotificationClick(View view) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        String title = "This is a test notification";
        builder
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(Html.fromHtml(title))
                .setContentText(Html.fromHtml("<i>Wow</i> I am from HTML!"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setTicker("Testing notifications!")
                .setLights(Color.GREEN, 1000, 1000)
                .setNumber(30)
                .setGroupSummary(true)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_PROMO);
        builder.setLocalOnly(true);
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        ArrayList<String> inboxLines = new ArrayList<>();
        inboxLines.add("Line 1");
        inboxLines.add("Line 2");
        inboxLines.add("Line 3");
        inboxLines.add("Line 4");
        inboxLines.add("Line 5");
        inboxLines.add("Line 6");
        inboxLines.add("Line 7");
        inboxLines.add("Line 8");
        inboxLines.add("Line 9");
        inboxLines.add("Line 10");
        for(String inboxLine : inboxLines) {
            style.addLine(inboxLine);
        }
        style.setBigContentTitle(Html.fromHtml(title))
                .setSummaryText("Tiny summary text!");
        builder.setStyle(style);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder.setContentIntent(pendingIntent);

        final NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(NOTIFICATION_TAG, NOTIFICATION_REQUEST_CODE);
        nm.notify(NOTIFICATION_TAG, NOTIFICATION_REQUEST_CODE, builder.build());
    }


    @OnClick(R.id.button_send_peek_notification)
    public void onSendNotificationPeekClick(View view) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        String title = "Peeking notification";
        builder
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(Html.fromHtml(title))
                .setContentText(Html.fromHtml("<b>Homepass:</b> Welcome to your inspection!"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setTicker("You are pre-registered for this inspection")
                .setLights(Color.GREEN, 1000, 1000)
                .setNumber(30)
                .setGroupSummary(true)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_PROMO);
        builder.setLocalOnly(true);

        String alarmSound = PreferenceManager.getDefaultSharedPreferences(this).getString("notifications_new_message_ringtone", null);
        builder.setSound(Uri.parse(alarmSound));

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        ArrayList<String> inboxLines = new ArrayList<>();
        inboxLines.add("3/60 Warialda Street, Kogarah, NSW");
        inboxLines.add("1 bed, 1 bath, 1 carspace");
        for(String inboxLine : inboxLines) {
            style.addLine(inboxLine);
        }
        style.setBigContentTitle(Html.fromHtml(title))
                .setSummaryText("Tiny summary text!");
        builder.setStyle(style);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder.setContentIntent(pendingIntent);

        final NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(NOTIFICATION_TAG, NOTIFICATION_REQUEST_CODE);
        nm.notify(NOTIFICATION_TAG, NOTIFICATION_REQUEST_CODE, builder.build());
    }

    @OnClick(R.id.rv_viewstubs)
    public void onRecyclerViewViewStub(View view) {
        Intent intent = new Intent(this, RecyclerViewViewStubs.class);
        startActivity(intent);
    }

    @OnClick(R.id.data_binding_alert)
    public void onSendDataBoundAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MaterialAlertDialog);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "OK clicked.", Toast.LENGTH_SHORT).show();
            }
        })
                .setNegativeButton("Not now", null);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_data_binding_demo, null);
        DialogDataBindingDemoBinding binding = DataBindingUtil.bind(view);
        binding.setCharacter(ResourceAnnotationsActivity.AdventureTimeCharacters.LEMONGRAB);
        builder.setView(view);

        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
