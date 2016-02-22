package com.xtjr.application.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.os.Handler;


import com.xtjr.application.R;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class View4 extends Activity implements View.OnClickListener {

    private EditText editText;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private String stxt1;
    private String stxt2;
    private String stxt3;
    List<mccItem> lists = new ArrayList<>();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            txt1.setText(stxt1);
            txt2.setText(stxt2);
            txt3.setText(stxt3);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view4);
        ((TextView) findViewById(R.id.tittleTxt)).setText("MCC查询");
        findViewById(R.id.backButton).setOnClickListener(this);
        editText = (EditText) findViewById(R.id.editMcc);
        txt1 = (TextView) findViewById(R.id.txtMcc1);
        txt2 = (TextView) findViewById(R.id.txtMcc2);
        txt3 = (TextView) findViewById(R.id.txtMcc3);
        editText.addTextChangedListener(txtwatcher);
        readExcel();

    }

    public void readExcel() {
        try {
            InputStream in = getResources().getAssets().open("mcc.xls");
            Workbook workbook = Workbook.getWorkbook(in);
            Sheet sheet = workbook.getSheet("sheet");

            for (int i =0;i<sheet.getRows();i++){
                mccItem mcc = new mccItem(sheet.getRow(i)[0].getContents(),
                        sheet.getRow(i)[1].getContents(),
                        sheet.getRow(i)[2].getContents());
                lists.add(mcc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backButton:
                onBackPressed();
                break;
        }
    }

    private TextWatcher txtwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            for (int i = 0;i<lists.size();i++){
                if (editText.getText().toString().equals(lists.get(i).getMccId().toString())){
                    stxt1 = lists.get(i).getMccId();
                    stxt2 = lists.get(i).getMccContent();
                    stxt3 = lists.get(i).getMccRatio();
                    Message.obtain(handler).sendToTarget();
                    break;
                }
            }
        }
    };

    class mccItem {
        private String mccId;
        private String mccContent;
        private String mccRatio;

        mccItem(String i, String con, String rat) {
            mccId = i;
            mccContent = con;
            mccRatio = rat;
        }
        public String getMccId() {
            return mccId;
        }
        public String getMccContent() {
            return mccContent;
        }
        public String getMccRatio() {
            return mccRatio;
        }
    }
}
