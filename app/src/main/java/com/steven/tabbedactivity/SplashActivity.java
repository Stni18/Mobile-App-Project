package com.steven.tabbedactivity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
public class SplashActivity extends AppCompatActivity {
    private static int ROTATE_TIME_OUT = 3000;
    private static int SPLASH_TIME_OUT = 15000;
    private ImageView image_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        image_view = (ImageView) findViewById(R.id.splash_image);
        scrollupAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rotateAnimation();
            }
        }, ROTATE_TIME_OUT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);}
    private void scrollupAnimation(){
        Animation scrollup_animation = AnimationUtils.loadAnimation(this,
                R.anim.alpha);
        scrollup_animation.reset();
        LinearLayout linear_layout = (LinearLayout) findViewById(R.id.linear_layout);

        linear_layout.clearAnimation();
        linear_layout.startAnimation(scrollup_animation);
        scrollup_animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        scrollup_animation.reset();
        image_view.clearAnimation();
        image_view.startAnimation(scrollup_animation);
    }
    private void rotateAnimation(){
        Animation rotate_animation = AnimationUtils.loadAnimation(this,
                R.anim.rotation);
        rotate_animation.reset();
        image_view.clearAnimation();
        image_view.startAnimation(rotate_animation);
//may have to add shortened version of theme
        final MediaPlayer mediaPlayer = MediaPlayer.create(SplashActivity.this,
                R.raw.main_theme_short);
        mediaPlayer.start();
    }
}