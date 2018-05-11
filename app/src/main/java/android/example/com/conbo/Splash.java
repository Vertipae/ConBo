package android.example.com.conbo;
// Sallamaarit Jaako 1601459

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {


    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hiding the action bar in activity splash
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash);
        tv = (TextView) findViewById(R.id.text_tv);
        iv = (ImageView) findViewById(R.id.image_iv);
        // Finding the transition.xml for the animation
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.transition);
        // Starting TextView and ImageView animation
        tv.startAnimation(anim);
        iv.startAnimation(anim);

        // Starting MainActivity after the animation
        final Intent intent = new Intent(this, MainActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
