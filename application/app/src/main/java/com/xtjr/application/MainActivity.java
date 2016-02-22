package com.xtjr.application;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xtjr.application.Util.AuthDialog;
import com.xtjr.application.Util.AuthUtil;
import com.xtjr.application.view.View1;
import com.xtjr.application.view.View2;
import com.xtjr.application.view.View3;
import com.xtjr.application.view.View4;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.layout1).setOnClickListener(this);
        findViewById(R.id.layout2).setOnClickListener(this);
        findViewById(R.id.layout3).setOnClickListener(this);
        findViewById(R.id.layout4).setOnClickListener(this);
        findViewById(R.id.backButton).setOnClickListener(this);
        ((TextView) findViewById(R.id.tittleTxt)).setText("主页");
        ((TextView) findViewById(R.id.backButton)).setText("QUIT");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout1:
                navigate(View1.class);
                break;
            case R.id.layout2:
                if (AuthUtil.isAuth(this)) {
                    navigate(View2.class);
                } else
                    new AuthDialog(this).show();
                break;
            case R.id.layout3:
                if (AuthUtil.isAuth(this)) {
                    navigate(View3.class);
                } else
                    new AuthDialog(this).show();
                break;
            case R.id.layout4:
                if (AuthUtil.isAuth(this)) {
                    navigate(View4.class);
                } else
                    new AuthDialog(this).show();
                break;
            case R.id.backButton:
                new AlertDialog.Builder(this)
                        .setTitle("确定退出?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
                break;
        }
    }

    private void navigate(Class cls) {
        startActivity(new Intent(MainActivity.this, cls));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("确定退出?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("NO", null)
                .show();
    }
}
