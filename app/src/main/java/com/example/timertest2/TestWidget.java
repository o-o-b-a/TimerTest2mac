package com.example.timertest2;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RemoteViews;

import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 */
public class TestWidget extends AppWidgetProvider {

    //ウィジェットを更新するメソッドです。
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);//ここでStringの文字呼んで
        //RemoteViewsオブジェクトを構築します
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.test_widget);
        views.setTextViewText(R.id.textView, "あああ");//ここでlayoutのidに差し込んでる（layoutでのtextは表示されない）

        //set alarm
        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final Calendar TIME = Calendar.getInstance();
        TIME.set(Calendar.MINUTE, 0);
        TIME.set(Calendar.SECOND, 0);
        TIME.set(Calendar.MILLISECOND, 0);
        final Intent in = new Intent(context, NoteConfigureActivity.class);
//        if (service == null) {
//            service = PendingIntent.getService(context, 0, in, PendingIntent.FLAG_CANCEL_CURRENT);
//        }
        PendingIntent  service = PendingIntent.getService(context, 0, in, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC, 0, 1000 * 120,service);

//        エラーはでないけど動かない…
//        Animator anim= AnimatorInflater.loadAnimator(context,R.animator.anim);
//        anim.setTarget(R.layout.test_widget);
//        anim.start();


//        ↓画像の差し替え方
//        views.setImageViewResource(R.id.actionlimit_base,R.drawable.al_image_sample);

        // ウィジェットを更新するようにウィジェットマネージャーに指示する
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    //インスタンスが生成された時に、あるいはあらかじめ設定したandroid:updatePeriodMillisで呼ばれます。
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    //ウィジェットがホーム画面に追加された時、端末が起動した時に呼ばれます。
    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    //ウィジェットがホーム画面から削除された時、端末がシャットダウンした時に呼ばれます。
    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

