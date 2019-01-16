package com.mmlotte.lottery.helper

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import com.mmlotte.lottery.R
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/18
 */

class NotificationChannelHelper @Inject constructor(private val context: Context) {

  fun initChannels() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

      //Create new result channel
      val notificationChannel = NotificationChannel(
        context.getString(R.string.channel_new_result),
        context.getString(R.string.channel_new_result_title),
        NotificationManager.IMPORTANCE_DEFAULT
      )
      notificationChannel.description = context.getString(R.string.channel_new_result_description)
      notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
      notificationChannel.lightColor = ContextCompat.getColor(context, R.color.primaryColor)
      manager.createNotificationChannel(notificationChannel)
    }
  }

}
