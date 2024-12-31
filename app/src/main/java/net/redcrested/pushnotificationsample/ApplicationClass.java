package net.redcrested.pushnotificationsample;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;
import com.onesignal.Continue;


public class ApplicationClass extends Application {
    // NOTE: Replace the below with your own ONESIGNAL_APP_ID
    private static final String ONESIGNAL_APP_ID = "3aae2d76-d239-410f-8e6e-88793354627a"; // "########-####-####-####-############";
    private static final String TAG = "net.RC.PNS.ApplicationClass";
    @Override
    public void onCreate() {
        super.onCreate();

        // Verbose Logging set to help debug issues, remove before releasing your app.
        OneSignal.getDebug().setLogLevel(LogLevel.DEBUG);

        // OneSignal Initialization
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID);

        // requestPermission will show the native Android notification permission prompt.
        // NOTE: It's recommended to use a OneSignal In-App Message to prompt instead.
        OneSignal.getNotifications().requestPermission(false, Continue.none());

        // Add a listener to receive notification click
        OneSignal.getNotifications().addClickListener(notificationOpenedEvent -> {
            String url = notificationOpenedEvent.getNotification().getLaunchURL();
            if (url != null) {
                Toast.makeText(getApplicationContext(), "Deeplink received: " + url, Toast.LENGTH_LONG).show();
                Log.d(TAG,"Deeplink received: " + url);
            } else {
                Toast.makeText(getApplicationContext(), "DeepLink not received", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
