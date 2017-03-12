package cn.magic.rain.com.firstcode.activity.broadcast;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import cn.magic.rain.com.firstcode.R;
import cn.magic.rain.com.firstcode.activity.BaseActivity;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    private SharedPreferences sharedPreferences;

    private EditText accountEdit;

    private EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 第一个参数指定 SharedPreferences 文件的名称
//        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        // 当前 Activity 的类名作为 SharedPreferences 的文件名
//        sharedPreferences = getPreferences(MODE_PRIVATE);
        // 应用程序的包名作为前缀来命名 SharedPreferences 文件
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        final CheckBox rememberPassCheckBox = (CheckBox) findViewById(R.id.remember_pass);
        Button loginButton = (Button) findViewById(R.id.login);

        boolean isRemember = sharedPreferences.getBoolean("remember_password", false);
        if (isRemember) {
            String account = sharedPreferences.getString("account", "");
            String password = sharedPreferences.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPassCheckBox.setChecked(true);
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (account.equals("admin") && password.equals("123456")) {
                    SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                    if (rememberPassCheckBox.isChecked()) {
                        sharedPreferencesEditor.putBoolean("remember_password", true);
                        sharedPreferencesEditor.putString("account", account);
                        sharedPreferencesEditor.putString("password", password);
                    } else {
                        sharedPreferencesEditor.clear();
                    }
                    sharedPreferencesEditor.apply();

                    Intent intent = new Intent(LoginActivity.this, BroadCastPerfectActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button callPhoneButton = (Button) findViewById(R.id.button_call_phone);
        callPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(LoginActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(LoginActivity.this, new String[]{ Manifest.permission.CALL_PHONE}, 1);
                } else {
                    call();
                }
            }
        });
    }

    private void call() {
        try {
//            Intent intent = new Intent(Intent.ACTION_CALL);
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:10010"));
            startActivity(intent);
        } catch (SecurityException e) {
            Log.e(TAG, "call: We meet a problem.");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
