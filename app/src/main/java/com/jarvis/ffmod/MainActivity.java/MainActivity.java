package com.jarvis.ffmod;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int OVERLAY_PERMISSION_REQ_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button startBtn = new Button(this);
        startBtn.setText("⚡ LAUNCH JAR VIS AI MOD ⚡");
        startBtn.setOnClickListener(v -> checkOverlayPermission());
        setContentView(startBtn);
    }

    private void checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        } else {
            startModService();
        }
    }

    private void startModService() {
        Intent serviceIntent = new Intent(this, FloatingModService.class);
        startService(serviceIntent);
        Toast.makeText(this, "JAR VIS MOD Activated!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
