package com.mmlotte.lottery.messaging

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mmlotte.lottery.R
import com.mmlotte.lottery.view.MainActivity
import timber.log.Timber

/**
 * Created by Vincent on 12/14/18
 */
class MMLotteFirebaseMessagingService : FirebaseMessagingService() {

  val notificationManager by lazy {
    this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
  }

  override fun onNewToken(token: String?) {
    super.onNewToken(token)
  }

  override fun onMessageReceived(remoteMessage: RemoteMessage?) {
    Timber.i(remoteMessage?.messageId ?: "message received")
    super.onMessageReceived(remoteMessage)

    remoteMessage?.let { message ->

      message.data?.let {
        val payloadType = it["type"]


        if (payloadType == "new_result") {

          message.notification?.let { messageNotification ->
            val notificationBuilder = NotificationCompat.Builder(
              applicationContext,
              applicationContext.getString(R.string.channel_new_result)
            )

            notificationBuilder.setContentTitle(messageNotification.title ?: "New Result")
            notificationBuilder.setContentText(messageNotification.body)

            val intent = Intent(applicationContext, MainActivity::class.java)
            val pendingIntent =
              PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_ONE_SHOT)

            notificationBuilder.setContentIntent(pendingIntent)
            notificationBuilder.color = ContextCompat.getColor(this, R.color.primaryColor)
            notificationBuilder.setSmallIcon(R.drawable.ic_notification)
            notificationBuilder.setAutoCancel(false)

            notificationManager.notify(100, notificationBuilder.build())

          }
        }

      }
    }

  }

}

