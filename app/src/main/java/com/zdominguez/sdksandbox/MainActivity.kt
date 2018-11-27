package com.zdominguez.sdksandbox

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.zdominguez.sdksandbox.bottomsheet.BottomSheetShare
import com.zdominguez.sdksandbox.databinding.ActivityMainBinding
import com.zdominguez.sdksandbox.databinding.DialogDataBindingDemoBinding
import com.zdominguez.sdksandbox.models.AdventureTimeCharacters

import org.parceler.Parcels

import java.util.ArrayList
import java.util.Random

import butterknife.OnClick
import com.zdominguez.sdksandbox.R.string
import java.io.File

class MainActivity : AppCompatActivity() {

    private val random = Random()

    private val randomCharacter: AdventureTimeCharacters
        get() {
            val characters = AdventureTimeCharacters.values()
            return characters[random.nextInt(characters.size)]
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.handlers = this

        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("is_launched", false).apply()
        getSharedPreferences("${BuildConfig.APPLICATION_ID}_info", Context.MODE_PRIVATE).edit().putString("hello", "hi").apply()
        getSharedPreferences("${BuildConfig.APPLICATION_ID}_more_prefs", Context.MODE_PRIVATE).edit().putString("hello", "hi").apply()
    }

    fun goToResourceAnnotations() {
        val intent = Intent(this, ResourceAnnotationsActivity::class.java)
        startActivity(intent)
    }

    fun goToLinearLayoutDemo() {
        val intent = Intent(this, LinearLayoutActivity::class.java)
        startActivity(intent)
    }

    fun readableTextDemo() {
        val intent = Intent(this, ReadableTextActivity::class.java)
        startActivity(intent)
    }

    fun resetPreferences() {
        val dataDir = File("${filesDir.parent}/shared_prefs")

        // Get the prefs files we are concerned with
        val sharedPrefsFiles = dataDir.listFiles().filter { file ->
            file.name.startsWith(BuildConfig.APPLICATION_ID)
        }

        // Get the nice displayable name
        val sharedPrefsFileNames = sharedPrefsFiles.map {
            it.name
                .removeSuffix(".xml")
                .removePrefix(BuildConfig.APPLICATION_ID)
                .removePrefix("_")
        }.toTypedArray()

        val selectedItems = mutableListOf<Int>()
        val alert = AlertDialog.Builder(this).apply {
            setTitle("Reset Preferences")
            setMultiChoiceItems(sharedPrefsFileNames, null
            ) { _, which, isChecked ->
                when {
                    isChecked -> selectedItems.add(which)
                    selectedItems.contains(which) -> selectedItems.remove(which)
                }
            }
            setPositiveButton("Clear", null)
            setNegativeButton("Cancel", null)
        }.create()

        alert.setOnShowListener {
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener { _ ->
                selectedItems.forEach { prefIndex ->
                    val prefName = sharedPrefsFiles[prefIndex].name.split(".xml").first()
                    getSharedPreferences(prefName, Context.MODE_PRIVATE).edit().clear().apply()
                    alert.dismiss()
                }
            }
        }
        alert.show()
    }

    fun onSendNotificationClick() {
//        val builder = NotificationCompat.Builder(this)
//        val title = "This is a test notification"
//        builder
//            .setSmallIcon(R.drawable.ic_notification)
//            .setContentTitle(Html.fromHtml(title))
//            .setContentText(Html.fromHtml("<i>Wow</i> I am from HTML!"))
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setTicker("Testing notifications!")
//            .setLights(Color.GREEN, 1000, 1000)
//            .setNumber(30)
//            .setGroupSummary(true)
//            .setAutoCancel(true)
//            .setCategory(NotificationCompat.CATEGORY_PROMO)
//        builder.setLocalOnly(true)
//        val style = NotificationCompat.InboxStyle()
//        val inboxLines = ArrayList<String>()
//        inboxLines.add("Line 1")
//        inboxLines.add("Line 2")
//        inboxLines.add("Line 3")
//        inboxLines.add("Line 4")
//        inboxLines.add("Line 5")
//        inboxLines.add("Line 6")
//        inboxLines.add("Line 7")
//        inboxLines.add("Line 8")
//        inboxLines.add("Line 9")
//        inboxLines.add("Line 10")
//        for (inboxLine in inboxLines) {
//            style.addLine(inboxLine)
//        }
//        style.setBigContentTitle(Html.fromHtml(title))
//            .setSummaryText("Tiny summary text!")
//        builder.setStyle(style)
//
//        val notificationIntent = Intent(this, MainActivity::class.java)
//        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//
//        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
//        builder.setContentIntent(pendingIntent)
//
//        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        nm.cancel(NOTIFICATION_TAG, NOTIFICATION_REQUEST_CODE)
//        nm.notify(NOTIFICATION_TAG, NOTIFICATION_REQUEST_CODE, builder.build())

        sendBagReminder()
    }

    fun sendBagReminder() {
        val context = this
        createNotificationChannel()

        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val bagReminderSetup = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setColor(ContextCompat.getColor(context, R.color.color_accent))
            .setContentTitle("Never forget your bags")
            .setContentText("We can help you to bring your reusable bags. Tap here to set up notifications today.")
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.intro_2))
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("We can help you to bring your reusable bags. Tap here to set up notifications today."))
            .setContentIntent(pendingIntent)
            .setDefaults(Notification.DEFAULT_ALL)
            .setAutoCancel(true)

        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.cancel(NOTIFICATION_TAG, NOTIFICATION_REQUEST_CODE)
        nm.notify(NOTIFICATION_TAG, NOTIFICATION_REQUEST_CODE, bagReminderSetup.build())
    }

    val NOTIFICATION_CHANNEL_ID = "429017"

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(NOTIFICATION_CHANNEL_ID, "Bag Reminder",
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                enableLights(true)
                enableVibration(true)

                val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                nm.createNotificationChannel(this)
            }
        }
    }

    fun onSendNotificationPeekClick() {
        val builder = NotificationCompat.Builder(this)
        val title = "Peeking notification"
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
            .setCategory(NotificationCompat.CATEGORY_PROMO)
        builder.setLocalOnly(true)

        val alarmSound = PreferenceManager.getDefaultSharedPreferences(this)
            .getString("notifications_new_message_ringtone", null)
        builder.setSound(Uri.parse(alarmSound))

        val style = NotificationCompat.InboxStyle()
        val inboxLines = ArrayList<String>()
        inboxLines.add("3/60 Warialda Street, Kogarah, NSW")
        inboxLines.add("1 bed, 1 bath, 1 carspace")
        for (inboxLine in inboxLines) {
            style.addLine(inboxLine)
        }
        style.setBigContentTitle(Html.fromHtml(title))
            .setSummaryText("Tiny summary text!")
        builder.setStyle(style)

        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        builder.setContentIntent(pendingIntent)

        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.cancel(NOTIFICATION_TAG, NOTIFICATION_REQUEST_CODE)
        nm.notify(NOTIFICATION_TAG, NOTIFICATION_REQUEST_CODE, builder.build())
    }

    fun onRecyclerViewViewStub() {
        val intent = Intent(this, RecyclerViewViewStubs::class.java)
        startActivity(intent)
    }

    fun onSendDataBoundAlert() {
        val builder = AlertDialog.Builder(this@MainActivity, R.style.MaterialAlertDialog)
        builder.setPositiveButton(android.R.string.ok, null)
            .setNegativeButton("Not now", null)
        val view = LayoutInflater.from(this@MainActivity)
            .inflate(R.layout.dialog_data_binding_demo, null)
        val binding = DataBindingUtil.bind<DialogDataBindingDemoBinding>(view)
        binding!!.character = randomCharacter
        builder.setView(view)

        builder.create()
            .show()
    }

    fun onDataBindingSpansClick() {
        val intent = Intent(this, DataBindingSpans::class.java)
        intent.putExtra("extra", Parcels.wrap(randomCharacter))
        startActivity(intent)
    }

    fun onShowConstraintLayout() {
        val intent = Intent(this, ConstraintLayoutDemo::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.bottom_sheet_share)
    fun onBottomSheetShare(view: View) {
        val intent = Intent(this, BottomSheetShare::class.java)
        startActivity(intent)
    }

    fun onDataBindingLambda() {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {

        val NOTIFICATION_TAG = "notification_tag"
        val NOTIFICATION_REQUEST_CODE = 100
    }
}
