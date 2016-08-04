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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;
import com.estimote.sdk.connection.internal.protocols.Operation;

import java.util.List;
import java.util.Timer;
import java.util.UUID;

public class MyApplication extends Application {

    //private BeaconManager beaconManagerBlueberry;
    //private BeaconManager beaconManagerMint;
    //private BeaconManager beaconManagerIce;
    private BeaconManager beaconManagerBeetroot;
    private BeaconManager beaconManagerLemon;
    private BeaconManager beaconManagerCandy;

    private Region region;

    private Long tsBlueberry, tsMint, tsIce;
    private Long tsBeetroot, tsLemon, tsCandy;

    @Override
    public void onCreate() {
        super.onCreate();

        /***********************************************************************
         *
         * Gestion du beacon estimote Sweet Beetroot (rouge foncé)
         *
         **********************************************************************/
        beaconManagerBeetroot = new BeaconManager(getApplicationContext());
        beaconManagerBeetroot.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                tsBeetroot = System.currentTimeMillis()/1000;
                showNotification("Beetroot Beacon: ", "onEnteredRegion");

            }
            @Override
            public void onExitedRegion(Region region) {
                tsBeetroot = System.currentTimeMillis()/1000 - tsBeetroot;
                showNotification("Beetroot Beacon: " + tsBeetroot + "sec", "onExitedRegion");
            }
        });
        beaconManagerBeetroot.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManagerBeetroot.startMonitoring(new Region(
                        "monitored region",
                        UUID.fromString("DC3492BF-4123-62E9-9E24-A9C0AD9247E7"),
                        1, 20));
            }
        });

        /***********************************************************************
         *
         * Gestion du beacon estimote Lemon Tart (jaune)
         *
         **********************************************************************/
        beaconManagerLemon = new BeaconManager(getApplicationContext());
        beaconManagerLemon.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                tsLemon = System.currentTimeMillis()/1000;
                showNotification("Lemon Beacon: ", "onEnteredRegion");

            }
            @Override
            public void onExitedRegion(Region region) {
                tsLemon = System.currentTimeMillis()/1000 - tsLemon;
                showNotification("Lemon Beacon: " + tsLemon + "sec", "onExitedRegion");
            }
        });
        beaconManagerLemon.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManagerLemon.startMonitoring(new Region(
                        "monitored region",
                        UUID.fromString("DC3492BF-4123-62E9-9E24-A9C0AD9247E7"),
                        1, 10));
            }
        });

        /***********************************************************************
         *
         * Gestion du beacon estimote Candy Floss (rose)
         *
         **********************************************************************/
        beaconManagerCandy = new BeaconManager(getApplicationContext());
        beaconManagerCandy.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                tsCandy = System.currentTimeMillis()/1000;
                showNotification("Candy Beacon: ", "onEnteredRegion");

            }
            @Override
            public void onExitedRegion(Region region) {
                tsCandy = System.currentTimeMillis()/1000 - tsCandy;
                showNotification("Candy Beacon: " + tsCandy + "sec", "onExitedRegion");
            }
        });
        beaconManagerCandy.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManagerCandy.startMonitoring(new Region(
                        "monitored region",
                        UUID.fromString("DC3492BF-4123-62E9-9E24-A9C0AD9247E7"),
                        2, 10));
            }
        });
        /***********************************************************************
         *
         * Gestion du beacon estimote Blueberry Pie (mauve)
         *
         **********************************************************************/
//        beaconManagerBlueberry = new BeaconManager(getApplicationContext());
//        beaconManagerBlueberry.setMonitoringListener(new BeaconManager.MonitoringListener() {
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
//        beaconManagerBlueberry.connect(new BeaconManager.ServiceReadyCallback() {
//            @Override
//            public void onServiceReady() {
//                beaconManagerBlueberry.startMonitoring(new Region(
//                        "monitored region",
//                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
//                        46272, 30700)); //TODO: changer Major et Minor
//            }
//        });

        /***********************************************************************
         *
         * Gestion du beacon estimote Mint Cocktail (vert)
         *
         **********************************************************************/
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

        /***********************************************************************
         *
         * Gestion du beacon estimote Icy Marshmallow (bleu)
         *
         **********************************************************************/
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
                new Intent[]{notifyIntent}, PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setLargeIcon(bm)
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