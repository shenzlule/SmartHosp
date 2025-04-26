package org.hospital.hospitalbookup.ui.pages;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.hospital.hospitalbookup.R;
import org.hospital.hospitalbookup.ui.session.SessionManager;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Set status bar color to deep cyan
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.deepcayn)); // Deep cyan
            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.deepcayn));


        }
            // Make sure status bar icons are white (default for dark background)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(0); // No LIGHT_STATUS_BAR flag
            }

        ImageView logo = findViewById(R.id.logo);

// Load animations
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        Animation zoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);

// Chain animations
        zoomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.startAnimation(zoomOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        zoomOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.startAnimation(shake);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                SessionManager sessionManager = new SessionManager(SplashActivity.this);
                Intent intent = new Intent(SplashActivity.this,
                        sessionManager.isLoggedIn() ? MainActivity.class : LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

// Start the first animation
        logo.startAnimation(zoomIn);


        new Handler().postDelayed(() -> {
            SessionManager sessionManager = new SessionManager(this);
            Intent intent = new Intent(this,
                    sessionManager.isLoggedIn() ? MainActivity.class : LoginActivity.class);
            startActivity(intent);
            finish();
        }, 3500); // 3.5 seconds
    }
}
