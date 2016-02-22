package com.xtjr.application.Util;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xtjr.application.R;


/**
 * Created by Administrator on 2016/1/14.
 */
public class AuthDialog extends Dialog {
    private String macCode;
    TextView textView;
    EditText editText;
    Button btnCopy;
    Button btnVal;

    public AuthDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_auth);
        setTitle("注册");
        textView= (TextView) findViewById(R.id.txtCode);
        editText= (EditText) findViewById(R.id.edit_key);
        btnCopy= (Button) findViewById(R.id.btnCode);
        btnVal= (Button) findViewById(R.id.btnKey);

        macCode = AuthUtil.getMacCode(getContext());
        textView.setText(macCode);
        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData cd = ClipData.newPlainText("Text",macCode);
                cm.setPrimaryClip(cd);
                Toast.makeText(getContext(),"机器码已复制到剪贴板",Toast.LENGTH_LONG).show();
            }
        });
        btnVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = editText.getText().toString().trim();
                if (AuthUtil.isAuth(getContext(),key)){
                    AuthUtil.saveKey(getContext(),key);
                    Toast.makeText(getContext(),"验证成功",Toast.LENGTH_LONG).show();
                    dismiss();
                }else
                    Toast.makeText(getContext(),"验证失败",Toast.LENGTH_LONG).show();
            }
        });
    }
}
