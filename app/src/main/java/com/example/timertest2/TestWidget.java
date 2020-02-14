package com.example.timertest2;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class TestWidget extends AppWidgetProvider {

    //ウィジェットを更新するメソッドです。
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);//ここでStringの文字呼んで
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.test_widget);
        views.setTextViewText(R.id.textView, "あああ");//ここでlayoutのidに差し込んでる（layoutでのtextは表示されない）


//        ↓画像の差し替え方
//        views.setImageViewResource(R.id.actionlimit_base,R.drawable.al_image_sample);

        views.setViewVisibility(R.drawable.ic_actionlimit_base,1);
//        views.setFloat(R.id.actionlimit_sec,"",);

        // Instruct the widget manager to update the widget
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

