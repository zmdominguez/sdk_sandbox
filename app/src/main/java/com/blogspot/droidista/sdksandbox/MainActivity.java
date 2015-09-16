package com.blogspot.droidista.sdksandbox;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    public static final String NOTIFICATION_TAG = "notification_tag";
    public static final int NOTIFICATION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

    @OnClick(R.id.button_send_notification)
    public void onSendNotificationClick(View view) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        String title = "<b>82910</b> properties found";
        builder
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(Html.fromHtml(title))
                .setContentText(Html.fromHtml("<i>Wow</i> I am from HTML!"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setTicker("Properties found in your saved searches")
                .setLights(Color.GREEN, 1000, 1000)
                .setNumber(30)
                .setGroupSummary(true)
                .setAutoCancel(true);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
