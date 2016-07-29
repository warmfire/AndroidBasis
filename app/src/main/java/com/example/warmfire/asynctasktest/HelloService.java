package com.example.warmfire.asynctasktest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by warmfire_2 on 2016/7/29.
 */
public class HelloService extends Service {
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {

            Resources res=getResources();
            Bitmap bmp= BitmapFactory.decodeResource(res, R.drawable.notification);

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(HelloService.this, 0,
                    new Intent(HelloService.this, MainActivity.class), 0);
            // 通过Notification.Builder来创建通知，注意API Level
            // API11之后才支持
            Notification notify2 = new Notification.Builder(HelloService.this)
                    .setSmallIcon(R.drawable.notification) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap
                    // icon)
                    .setLargeIcon(bmp)
                    // bar上显示的提示文字
                    .setContentTitle("通知")// 设置在下拉status
                    // bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
                    //.setContentIntent(pendingIntent2)
                    .setContentText("服务已启动")// TextView中显示的详细内容
                    .getNotification(); // 需要注意build()是在API level
            // 16及之后增加的，在API11中可以使用getNotificatin()来代替
            notify2.flags |= Notification.FLAG_AUTO_CANCEL;
            manager.notify(1, notify2);

            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread thread = new HandlerThread("ServiceStartArguments");
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}