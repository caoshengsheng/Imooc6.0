package com.example.sean.imooc60;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

/**
 * Created by sean on 2017/2/15.
 */

public class BaseActivitry extends AppCompatActivity {

    private static final String TAG = "BaseActivitry";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 为子类提供一个权限检查方法
     *
     * @param permissions
     * @return
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 为子类权限请求方法
     *
     * @param code
     * @param permissions
     */
    public void requestPermissions(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constants.WRITE_EXTERNAL_STORAGE_CODE:
                Log.d(TAG, "onRequestPermissionsResult: ");
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doSdCardPermission();
                } else {
                    Log.d(TAG, "onRequestPermissionsResult: " + "哈哈哈");
                    Toast.makeText(this, "读写sd卡权限被拒绝", Toast.LENGTH_SHORT).show();
                }
                break;
            case Constants.CALL_PHONE_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doCallPhone();
                } else {
                    Log.d(TAG, "onRequestPermissionsResult: " + "嘿嘿嘿");
                    Toast.makeText(this, "打电话权限被拒绝", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 默认的打电话权限处理，会被重写
     */
    public void doCallPhone() {

    }

    /**
     * 默认的写sd卡权限处理，会被重写
     */
    public void doSdCardPermission() {

    }
}
