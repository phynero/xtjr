package com.xtjr.application.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.xtjr.application.R;
import com.xtjr.application.webActivity;

import java.util.ArrayList;

public class View3 extends Activity implements View.OnClickListener{
    GridView gridView;
    private int[] imageIds = new int[]{
            R.drawable.guangfa, R.drawable.jiaotong, R.drawable.jiaotong, R.drawable.guangfa
    };
    private String[] names = new String[]{
            "广发\n提额", "交通\n提额", "交通\n好享贷", "广发\n财智金申请"
    };
    private String[] urls = new String[]{
            "https://wap.cgbchina.com.cn/mbChangeLimits.do?seqno=0D1xfRUTwdfPuvpst-eycQS44BBb9B_11419BmB94&from=singlemessage&isappinstalled=0",
            "https://creditcardapp.bankcomm.com/idm/sso/login.html?service=https://creditcardapp.bankcomm.com/member/shiro-cas",
            "http://creditcard.bankcomm.com/content/pccc-phone/lcxd/hx.html",
            "https://wap.cgbchina.com.cn/eppcEW.do?shortCode=C6emje&from=singlemessage&isappinstalled=1#rd"
    };
    private ArrayList<gridItem> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view3);
        ((TextView) findViewById(R.id.tittleTxt)).setText("在线提额");
        findViewById(R.id.backButton).setOnClickListener(this);
        gridView = (GridView) findViewById(R.id.gridView2);

        initItem();
        MyAdapter my = new MyAdapter(this);
        gridView.setAdapter(my);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(View3.this, webActivity.class);
                intent.putExtra("url",urls[i]);
                startActivity(intent);
            }
        });

    }

    private void initItem() {
        for (int i = 0; i < 4; ++i) {
            items.add(new gridItem(names[i], imageIds[i]));
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

    class MyAdapter extends BaseAdapter {
        Context context;

        public MyAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (null == view) {
                holder = new ViewHolder();
                view = LayoutInflater.from(context).inflate(R.layout.griditem, null);
                holder.hImage = (ImageView) view.findViewById(R.id.gridImageItem);
                holder.hText = (TextView) view.findViewById(R.id.gridTxtItem);
                view.setTag(holder);
            }else {
                holder= (ViewHolder) view.getTag();
            }
            holder.hText.setText(items.get(i).name);
            holder.hImage.setImageResource(items.get(i).imageId);

            return view;
        }
    }

    class ViewHolder {
        ImageView hImage;
        TextView hText;
    }

    class gridItem {
        private String name;
        private int imageId;

        public gridItem(String n, int i) {
            name = n;
            imageId = i;
        }
    }

}
