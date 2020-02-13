package com.example.timertest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //gittest

    CountDownTimer timer;
    static long start_time = 0;//設定時間
    long remainingTime = 0;//残り時間
    boolean timerRunning = false;

    EditText timeEditMinute;//分
    EditText timeEditSecond;//秒
    TextView timeDisplay;//時間表示部分
    Button btnStPa;//スタート兼タイマーボタン
    Button btnReset;//リセットボタン

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeDisplay = findViewById(R.id.timeDisplay);
        timeEditMinute = findViewById(R.id.timeEditMinute);
        timeEditSecond = findViewById(R.id.timeEditSecond);
        btnStPa = findViewById(R.id.btnStPa);
        btnReset = findViewById(R.id.btnReset);

        //スタートボタンを押したとき
        btnStPa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timerRunning) {//タイマーが動いてなければ
                    timerStart();//タイマー開始
                } else {//動いていれば
                    timerPause();//一時停止
                }
            }
        });

        //リセットボタンを押したとき
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerReset();
            }
        });
    }

    /**
     * タイマー開始
     */
    public void timerStart() {
        if (remainingTime == 0) {//残り時間が0なら、入力値を設定
            int editMinute = Integer.parseInt(timeEditMinute.getText().toString());
            int editSecond = Integer.parseInt(timeEditSecond.getText().toString());
            editMinute *= 60000;//分の計算
            editSecond *= 1000;//秒の計算

            start_time = editMinute + editSecond;
            remainingTime = start_time;
        }

        timer = new CountDownTimer(remainingTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = millisUntilFinished;
                timeDisplayUpdate();
            }
            @Override
            public void onFinish() {
                timerRunning = false;
            }
        }.start();
        timerRunning = true;
        btnStPa.setText("PAUSE");//ポーズボタンに変更
    }

    /**
     * タイマー停止
     */
    public void timerPause() {
        timer.cancel();
        timerRunning = false;
        btnStPa.setText("START");//スタートボタンに変更
    }

    /**
     * タイマーリセット
     */
    public void timerReset() {
        timerPause();
        remainingTime = 0;
        start_time = 0;
        timeDisplayUpdate();
    }

    /**
     * 時間表示変更
     */
    public void timeDisplayUpdate() {
        int minutes = (int) (remainingTime / 1000) / 60;
        int seconds = (int) (remainingTime / 1000) % 60;
        String timerLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        //時間表示に適応
        timeDisplay.setText(timerLeftFormatted);
    }
}
