package po.beacon_test;

/**
 * Created by PO on 16-06-13.
 */
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.connection.internal.protocols.Operation;

import java.util.List;
import java.util.Timer;
import java.util.UUID;

public class MyApplication extends Application {

    private BeaconManager beaconManagerBlueberry;
    private BeaconManager beaconManagerMint;
    private BeaconManager beaconManagerIce;
    private Long tsBlueberry, tsMint, tsIce;

    @Override
    public void onCreate() {
        super.onCreate();
        //Notification notification = new Notification();
        //TextView view = (TextView) findViewById(R.id.counter);
        //view.setText("Do whatever");

        beaconManagerBlueberry = new BeaconManager(getApplicationContext());
        beaconManagerBlueberry.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                tsBlueberry = System.currentTimeMillis()/1000;
                //showNotification("Blueberry Beacon: ", "onEnteredRegion");

            }
            @Override
            public void onExitedRegion(Region region) {
                tsBlueberry = System.currentTimeMillis()/1000 - tsBlueberry;
                //showNotification("Blueberry Beacon: " + tsBlueberry + "sec", "onExitedRegion");
            }
        });
        beaconManagerBlueberry.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManagerBlueberry.startMonitoring(new Region(
                        "monitored region",
                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
                        20912, 4946));
            }
        });
//        beaconManagerMint = new BeaconManager(getApplicationContext());
//        beaconManagerMint.setMonitoringListener(new BeaconManager.MonitoringListener() {
//            @Override
//            public void onEnteredRegion(Region region, List<Beacon> list) {
//                tsMint = System.currentTimeMillis()/1000;
//                showNotification("Mint Beacon: ", "onEnteredRegion");
//            }
//            @Override
//            public void onExitedRegion(Region region) {
//                tsMint = System.currentTimeMillis()/1000 - tsMint;
//                showNotification("Mint Beacon: " + tsMint + "sec", "onExitedRegion");
//            }
//        });
//        beaconManagerMint.connect(new BeaconManager.ServiceReadyCallback() {
//            @Override
//            public void onServiceReady() {
//                beaconManagerMint.startMonitoring(new Region(
//                        "monitored region",
//                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
//                        46272, 30700));
//            }
//        });
//        beaconManagerIce = new BeaconManager(getApplicationContext());
//        beaconManagerIce.setMonitoringListener(new BeaconManager.MonitoringListener() {
//            @Override
//            public void onEnteredRegion(Region region, List<Beacon> list) {
//                tsIce = System.currentTimeMillis()/1000;
//                showNotification("Mint Beacon: ", "onEnteredRegion");
//            }
//            @Override
//            public void onExitedRegion(Region region) {
//                tsIce = System.currentTimeMillis()/1000 - tsIce;
//                showNotification("Mint Beacon: " + tsIce + "sec", "onExitedRegion");
//            }
//        });
//        beaconManagerIce.connect(new BeaconManager.ServiceReadyCallback() {
//            @Override
//            public void onServiceReady() {
//                beaconManagerIce.startMonitoring(new Region(
//                        "monitored region",
//                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
//                        18485, 1));
//            }
//        });
    }

    public void showNotification(String title, String message) {
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}