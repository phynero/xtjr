package com.xtjr.application.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xtjr.application.R;
import com.xtjr.application.webActivity;

public class View2 extends Activity implements View.OnClickListener {
    GridView gridView;
    private String[] imageIds = new String[]{
            "drawable://" + R.drawable.guangfa, "drawable://" + R.drawable.guangfa, "drawable://" + R.drawable.guangfa, "drawable://" + R.drawable.guangfa,
            "drawable://" + R.drawable.guangfa, "drawable://" + R.drawable.guangfa, "drawable://" + R.drawable.guangfa, "drawable://" + R.drawable.guangfa,
            "drawable://" + R.drawable.pingan, "drawable://" + R.drawable.pingan, "drawable://" + R.drawable.guangda, "drawable://" + R.drawable.guangda,
            "drawable://" + R.drawable.jiaotong, "drawable://" + R.drawable.zhongxin, "drawable://" + R.drawable.zhongxin, "drawable://" + R.drawable.minsheng,
            "drawable://" + R.drawable.nongye, "drawable://" + R.drawable.zhaoshang, "drawable://" + R.drawable.zhaoshang, "drawable://" + R.drawable.zhongguo,
            "drawable://" + R.drawable.pufa, "drawable://" + R.drawable.xingye, "drawable://" + R.drawable.jianshe, "drawable://" + R.drawable.wangshang,
            "drawable://" + R.drawable.jiaotong
    };
    private String[] names = new String[]{
            "广发\n万宁卡", "广发\n新聪明卡", "广发\nDIY卡", "广发\n真情卡",
            "广发\n淘宝型男卡","广发\n淘宝潮女卡", "广发\n携程卡", "广发\n臻尚白金卡",
            "平安\n快卡", "平安\n金卡", "光大\n快卡", "光大\n福卡",
            "交通\n普卡", "中信\n(QQ应用版)", "中信\n快卡", "民生\n快卡(IN卡)",
            "农业\n普卡", "招行\n快卡", "招行快卡\n(大学生卡)", "中行\n信用卡",
            "浦发\n快卡", "兴业\n快卡", "建行\n快卡", "网商\n淘客贷",
            "交通\n活动查询 ",
    };
    private String[] urls = new String[]{
            "https://wap.cgbchina.com.cn/creditCardApplyIn.do?shortUrl=BKbTz9Y",
            "https://wap.cgbchina.com.cn/creditCardApplyIn.do?shortUrl=BKbH5mo",
            "https://wap.cgbchina.com.cn/creditCardApplyIn.do?shortUrl=BKbH5mn",
            "https://wap.cgbchina.com.cn/creditCardApplyIn.do?shortUrl=BKbH5mf",

            "http://cgbchina.cn/BKbH5l6",
            "http://cgbchina.cn/BKbH5mc",
            "http://cgbchina.cn/BKbH5md",
            "http://cgbchina.cn/BKbH5ms",

            "https://wap-ebank.pingan.com/weixin/modules/online_apply_card/index.html?scc=920000002#/showCards",
            "https://xyk.cebbank.com/cebmms/apply/fz/card-apply-sim-req.htm?pro_code=xmvx",
            "https://xyk.cebbank.com/cebmms/apply/fz/card-apply-index.htm?req_card_id=22&pro_code=xmvx",
            "https://creditcardapp.bankcomm.com/applynew/front/apply/new/index.html?plg_nld=1&plg_uin=1&plg_nld=1&isappinstalled=0&commercial_id=null&from=timeline&recomType=01&trackCode=A061614057749&plg_auth=1&plg_dev=1&plg_usr=1&plg_vkey=1&recomNumber=18737948000",

            "https://creditcardapp.bankcomm.com/applynew/front/apply/new/index.html?trackCode=A051117234843&cardId=83",
            "https://creditcard.ecitic.com/citiccard/newwap/pages/AppCreditCard/applayCard-process.html?sid=SJYLZX&pid=CS0094",
            "http://creditcard.ecitic.com/new_wap_ts/pages/ProposalCard/app-greadcard.html?sid=SJWXBK",
            "https://creditcard.cmbc.com.cn/online/Mobile/login.aspx?id=6&apptype=zx&sign=",

            "https://eapply.abchina.com/onlinetrade/CreditcardsInfo/CardRequestion?ctypeid=352&orgcode=0323",
            "https://ccclub.cmbchina.com/mca/Mprecontract.aspx?cardsel=uc&WT.mc_id=N1700WX304A9032100CC#rd",
            "https://ccclub.cmbchina.com/mca/student/sselectcard.aspx?cardsel=2100",
            "https://jf365.boc.cn/applyonline/online/iapsSearchImportProductAction_searchImportProduct.action?appiGetFlag=null",

            "https://mbank.spdbccc.com.cn/creditcard/indexActivity.htm?data=001284",
            "https://e.cib.com.cn/app/quickApplyCard/quickApplyCard!custIdentityPage.do",
            "http://creditcard.ccb.com/",
            "https://www.aliloan.com/act/taoke.html?spm=a219t.7473494.1998155397.4.NeEHJ1&tracelog=aliloan_taoke_3",

            "http://creditcard.bankcomm.com/content/dam/pc/images/apply_card/cards/cardother/wem.html"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view2);
        ((TextView) findViewById(R.id.tittleTxt)).setText("在线办卡");
        findViewById(R.id.backButton).setOnClickListener(this);
        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(new ImageAdapter(View2.this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(View2.this, webActivity.class);
                intent.putExtra("url", urls[i]);
                startActivity(intent);
            }
        });
    }

    class ImageAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private DisplayImageOptions options;

        ImageAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.ic_stub)
                    .showImageForEmptyUri(R.drawable.ic_empty)
                    .showImageOnFail(R.drawable.ic_error)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }

        @Override
        public int getCount() {
            return imageIds.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View view = convertView;
            if (null == view) {
                view = inflater.inflate(R.layout.griditem, parent, false);
                viewHolder = new ViewHolder();
                assert view != null;
                viewHolder.imageView = (ImageView) view.findViewById(R.id.gridImageItem);
                viewHolder.textView = (TextView) view.findViewById(R.id.gridTxtItem);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            ImageLoader.getInstance().displayImage(imageIds[position], viewHolder.imageView, options);
            viewHolder.textView.setText(names[position]);
            return view;
        }

        public class ViewHolder {
            ImageView imageView;
            TextView textView;
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
}
