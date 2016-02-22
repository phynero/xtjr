package com.xtjr.application.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xtjr.application.R;


public class View1 extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);


        ((TextView)findViewById(R.id.tittleTxt)).setText("关于我们");
        findViewById(R.id.backButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                onBackPressed();
                break;
        }
    }
}
