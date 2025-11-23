package site.odhier.masjiddisplay

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (Intent.ACTION_BOOT_COMPLETED == action ||
            Intent.ACTION_LOCKED_BOOT_COMPLETED == action ||
            Intent.ACTION_MY_PACKAGE_REPLACED == action) {
            val i = Intent(context, MainActivity::class.java)
            val pi = PendingIntent.getActivity(
                context,
                1001,
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            val am = context.getSystemService(AlarmManager::class.java)
            am.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 10000,
                pi
            )
        }
    }
}

