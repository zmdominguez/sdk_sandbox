package com.zdominguez.sdksandbox

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import butterknife.OnClick
import com.google.firebase.iid.FirebaseInstanceId
import com.zdominguez.sdksandbox.bottomsheet.BottomSheetShare
import com.zdominguez.sdksandbox.databinding.ActivityMainBinding
import com.zdominguez.sdksandbox.databinding.DialogDataBindingDemoBinding
import com.zdominguez.sdksandbox.firebase.ABTestDemoActivity
import com.zdominguez.sdksandbox.firebase.RemoteConfigValues
import com.zdominguez.sdksandbox.models.AdventureTimeCharacters
import org.parceler.Parcels
import timber.log.Timber
import java.io.File
import java.util.Random

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

        (application as SdkSandboxApplication).remoteConfig.fetch().addOnSuccessListener {
            val allowRemoteConfig = (application as SdkSandboxApplication).remoteConfig.getBoolean(RemoteConfigValues.REMOTE_CONFIG_ENABLED.key)
            Timber.i("Should allow remote config? $allowRemoteConfig current version: ${BuildConfig.VERSION_NAME}")
            binding.allowRemoteConfig.visibility = if (allowRemoteConfig) View.VISIBLE else View.GONE
        }
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

    fun logFirebaseToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnSuccessListener { result -> Timber.d("Firebase token: ${result.token}") }
    }

    fun showRemoteConfigDemo() {
        val intent = Intent(this, ABTestDemoActivity::class.java)
        startActivity(intent)
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
}
