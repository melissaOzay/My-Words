package com.example.mywords.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.mywords.MainActivity
import com.example.mywords.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService : FirebaseMessagingService() {
    private val TAG = "NotificationService"

    var intent: Intent? = null




    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d(TAG, "Bildirim geldi "  )

        intent = Intent(this, MainActivity::class.java)
        intent!!.putExtra("OPEN_TYPE","NOTIFICATION")
        if (p0!!.data.isNotEmpty()) {
            val notificationData = p0.data
            for (entry in notificationData.entries) {
                intent!!.putExtra(entry.key, entry.value)
            }
            Log.d(TAG, "Message notificationData payload: " + p0.data)


            val message = p0.data


            for ((index, _message) in message.values.withIndex()) {
                if (index == 6) {
                    showNotification(
                        this,
                        getString(R.string.app_name),
                        _message,
                        intent!!
                    )
                    break
                }
            }
        }


        try {
            showNotification(this, p0.notification!!.title!!, p0.notification!!.body!!, intent!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun showNotification(context: Context, title: String, body: String, intent: Intent) {

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val notificationId = 1
        val channelId = "default"
        val channelName = "Channel Name"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)


        var attributes: AudioAttributes? = null
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            attributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                channelId, channelName, importance
            )
            notificationManager.createNotificationChannel(mChannel)
            mChannel.enableLights(true)
            mChannel.enableVibration(true)
            mChannel.setSound(sound, attributes) // This is IMPORTANT
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)


        }

        val mBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
            .setContentText(body)
            .setAutoCancel(true)


        var stackBuilder: TaskStackBuilder? = null
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder = TaskStackBuilder.create(context)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder!!.addNextIntent(intent)
        }


        notificationManager.notify(notificationId, mBuilder.build())


    }

    override fun onNewToken(p0: String) {

    }
}