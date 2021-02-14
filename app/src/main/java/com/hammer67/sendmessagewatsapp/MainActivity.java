package com.hammer67.sendmessagewatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSend;
    EditText editTextPhone, editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btnSend);
        editTextMessage = findViewById(R.id.etMessage);
        editTextPhone = findViewById(R.id.etPhone);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = editTextPhone.getText().toString();
                String message = editTextMessage.getText().toString();

                boolean installed = isAppIsntalled("com.whatsapp");

                if (installed) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "507" + phone + "&text=" + message));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No tienes instalado whatsapp en su dispositivo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isAppIsntalled(String s) {
        PackageManager packageManager = getPackageManager();

        boolean is_installed;

        try {
            packageManager.getPackageInfo(s, PackageManager.GET_ACTIVITIES);
            is_installed = true;

        } catch (PackageManager.NameNotFoundException e) {
            is_installed = false;
            e.printStackTrace();
        }

        return is_installed;
    }
}