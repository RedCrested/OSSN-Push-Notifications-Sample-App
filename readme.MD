# Push Notification Sample App

This project is a sample Android application demonstrating the integration of OneSignal SDK for push notifications and deeplinks. It includes necessary configurations and implementations to handle notifications and display deeplinks upon interaction.

## Features
- **Push Notifications**: Integration with OneSignal SDK for sending and receiving notifications.
- **Deeplink Handling**: Displays a toast message with the deeplink URL when a notification is clicked.
- **Edge-to-Edge UI**: Ensures compatibility with edge-to-edge designs using WindowInsets.

## Prerequisites
1. A valid OneSignal account and application.
2. Firebase project setup with the `google-services.json` file included in the project's `app/` directory.

## Configuration
### 1. OneSignal Setup
Follow the [OneSignal Android SDK Setup](https://documentation.onesignal.com/docs/android-sdk-setup) guide to configure the OneSignal application and obtain your `ONESIGNAL_APP_ID`.

### 2. Firebase Setup
1. Create a Firebase project at [Firebase Console](https://console.firebase.google.com/).
2. Download the `google-services.json` file for your project.
3. Place the `google-services.json` file in the `app/` directory of your Android project.

### 3. Update Manifest File
Ensure the `AndroidManifest.xml` file includes the necessary intent filters for handling deeplinks:
```xml
<intent-filter>
    <action android:name="android.intent.action.VIEW" />
    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />
    <data android:scheme="[YOUR_APP_SCHEME]" android:host="[YOUR_OSSN_SITE]" />
</intent-filter>
```

## Code Overview
### MainActivity
The `MainActivity` initializes the UI, enabling edge-to-edge compatibility and setting up the layout padding based on system bars. It also logs in the user with OneSignal:
```java
String externalId = "´[OSSN ID NUMBER]";
OneSignal.login(externalId);
```

### ApplicationClass
The `ApplicationClass` initializes the OneSignal SDK, requests notification permissions, and handles notification click events:
```java
OneSignal.getNotifications().addClickListener(notificationOpenedEvent -> {
    String url = notificationOpenedEvent.getNotification().getLaunchURL();
    if (url != null) {
        Toast.makeText(getApplicationContext(), "Deeplink received: " + url, Toast.LENGTH_LONG).show();
        Log.d(TAG, "Deeplink received: " + url);
    } else {
        Toast.makeText(getApplicationContext(), "DeepLink not received", Toast.LENGTH_SHORT).show();
    }
});
```

### Manifest File
The manifest includes the necessary configuration for OneSignal and declares `ApplicationClass` as the application class:
```xml
<application
    android:name=".ApplicationClass"
    ...
</application>
```

## How to Run
1. Clone this repository.
2. Configure Firebase and OneSignal as described above.
3. Place the `google-services.json` file in the `app/` directory.
4. Build and run the project on an Android device or emulator.

## Resources
- [OneSignal Documentation](https://documentation.onesignal.com/docs/android-sdk-setup)
- [Firebase Console](https://console.firebase.google.com/)

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

