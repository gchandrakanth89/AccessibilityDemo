package com.gck.accessibilitydemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String service = getPackageName() + "/" + MyAccessibilityService.class.getCanonicalName();


        Settings.Secure.putString(this.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, service);
        Settings.Secure.putInt(this.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED, 1);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String operator = telephonyManager.getNetworkOperator();
        String networkOperatorName = telephonyManager.getNetworkOperatorName();

        Log.d(TAG, operator + "==" + networkOperatorName);

        Log.d(TAG, "");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!MyAccessibilityService.isAccessibilitySettingsOn(this)) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        }
    }
}
