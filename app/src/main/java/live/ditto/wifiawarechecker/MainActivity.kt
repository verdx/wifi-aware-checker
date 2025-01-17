package live.ditto.wifiawarechecker

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener


class MainActivity : AppCompatActivity(), OnRefreshListener {

    private lateinit var hasFeatureTextView: TextView
    private lateinit var featureAvailableTextView: TextView
    private lateinit var unavailabilityTipsTextView: TextView
    private lateinit var iconImageView: ImageView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hasFeatureTextView = findViewById(R.id.has_feature_text_view)
        unavailabilityTipsTextView = findViewById(R.id.unavailable_tips_text_view)
        featureAvailableTextView = findViewById(R.id.feature_available_text_view)
        iconImageView = findViewById(R.id.icon_image_view)

        val modelAndManufacturerTextView: TextView =
            findViewById(R.id.model_and_manufacturer_text_view)
        val androidVersionTextView: TextView = findViewById(R.id.android_version_text_view)

        modelAndManufacturerTextView.text = getDeviceName()
        val version = Build.VERSION.SDK_INT
        val versionRelease = Build.VERSION.RELEASE
        androidVersionTextView.text =
            getString(R.string.android_version_version_release, version.toString(), versionRelease.toString())


        val learnMoreButton: Button = findViewById(R.id.learn_more_button)
        learnMoreButton.setOnClickListener {
            val uri: Uri = Uri.parse("https://developer.android.com/guide/topics/connectivity/wifi-aware")
            val browserIntent = Intent(ACTION_VIEW)
            browserIntent.data = uri
            ContextCompat.startActivity(this, browserIntent, null)
        }

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this)

        checkWifiAware()
    }

    private fun checkWifiAware() {
        val hasSystemFeature = packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI_AWARE)
        if (hasSystemFeature) {
            hasFeatureTextView.text = getString(R.string.the_device_has_wifi_aware)
            hasFeatureTextView.setTextColor(ContextCompat.getColor(this,
                R.color.colorAvailable
            ))

            val wifiAwareManager = getSystemService(WIFI_AWARE_SERVICE) as android.net.wifi.aware.WifiAwareManager
            if (wifiAwareManager.isAvailable) {
                featureAvailableTextView.text = getString(R.string.wifi_aware_currently_available)
                featureAvailableTextView.setTextColor(ContextCompat.getColor(this,
                    R.color.colorAvailable
                ))
                iconImageView.setImageDrawable(getDrawable(R.drawable.ic_wifi_yes))
                unavailabilityTipsTextView.visibility = TextView.GONE
            } else {
                featureAvailableTextView.text = getString(R.string.wifi_aware_currently_unavailable)
                featureAvailableTextView.setTextColor(ContextCompat.getColor(this,
                    R.color.colorUnavailable
                ))
                iconImageView.setImageDrawable(getDrawable(R.drawable.ic_wifi_no))
                unavailabilityTipsTextView.visibility = TextView.VISIBLE
                unavailabilityTipsTextView.text =
                    getString(R.string.please_check_if_wifi_and_location_are_enabled_and_try_again)
            }

        } else {
            hasFeatureTextView.text = getString(R.string.the_device_does_not_have_wifi_aware)
            hasFeatureTextView.setTextColor(ContextCompat.getColor(this,
                R.color.colorUnavailable
            ))
            iconImageView.setImageDrawable(getDrawable(R.drawable.ic_wifi_no))
            unavailabilityTipsTextView.visibility = TextView.GONE
            featureAvailableTextView.visibility = TextView.GONE
        }
    }

    private fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            capitalize(model)
        } else {
            capitalize(manufacturer) + " " + model
        }
    }


    private fun capitalize(s: String?): String {
        if (s.isNullOrEmpty()) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            Character.toUpperCase(first).toString() + s.substring(1)
        }
    }

    override fun onRefresh() {
        checkWifiAware()
        Handler().postDelayed({ swipeRefreshLayout.isRefreshing = false }, 300)
    }
}
