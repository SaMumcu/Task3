package mumcu.sabiha.task3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mumcu.sabiha.task3.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread background = new Thread() {
            public void run() {

                try {
                    sleep(5 * 1000);

                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);

                    finish();

                } catch (Exception e) {

                }
            }
        };
        background.start();
    }
}
