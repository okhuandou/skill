import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.sdk_demo.DynamicBanner;
import com.example.sdk_demo.InfoActivtiy;
import com.example.sdk_demo.StaticBanner;
import com.slion.sdk.DXUtils;
import com.slion.sdk.InfoAd;
import com.slion.sdk.InfoCallBack;
import com.slion.sdk.PopAd;
import com.slion.sdk.Util;
import com.slion.sdk.InfoAd.InfoItem;

public class MainActivity extends Activity {

    Button btnStaticBanner;

    Button btnDynamicBanner;

    private View btnPopAD;

    private Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 准备开屏的广告
//        com.slion.sdk.Ad.prepareSplashAd(this,"B1B6878E28444A55A6B0241E906BBF60", "F8849929D4814AAABF30F98293CEAFAB");
        com.slion.sdk.Ad.prepareSplashAd(this,"B1B6878E28444A55A6B0241E906BBF60", "F8849929D4814AAABF30F98293CEAFAB");
        // 准备插屏的广告
//      com.slion.sdk.Ad.prepareInterstitialAd(this,"0F7682F11CA24099BECAE9C49FB7609B", "8EC518AF5CD942C383AD05630876DA9C");
        com.slion.sdk.Ad.prepareInterstitialAd(this,"B1B6878E28444A55A6B0241E906BBF60", "547EBF78A4F54A5E9BAAAA1DB6409EA5");

        btnStaticBanner = (Button)findViewById(R.id.btnStaticBanner);
        btnDynamicBanner = (Button)findViewById(R.id.btnDynamicBanner);
        btnPopAD = (Button)findViewById(R.id.btnpopad);
        btnInfo = (Button)findViewById(R.id.btn_info);
        btnInfo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //点击进入信息流展示界面
                Intent intentInfo = new Intent(MainActivity.this, InfoActivtiy.class);
                startActivity(intentInfo);

            }
        });
        btnPopAD.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                com.slion.sdk.PopAd popADClick = new com.slion.sdk.PopAd(MainActivity.this,
                        com.slion.sdk.PopAd.PopAdType.FitSize, new PopADMonitor(MainActivity.this,
                        com.slion.sdk.PopAd.PopAdType.FitSize));
                popADClick.showPopAD();

                // 准备下一插屏广告
                com.slion.sdk.Ad.prepareInterstitialAd(MainActivity.this,"B1B6878E28444A55A6B0241E906BBF60", "547EBF78A4F54A5E9BAAAA1DB6409EA5");
//				 com.slion.sdk.Ad.prepareInterstitialAd(MainActivity.this,"0F7682F11CA24099BECAE9C49FB7609B", "8EC518AF5CD942C383AD05630876DA9C");
            }
        });
        btnStaticBanner.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(MainActivity.this, StaticBanner.class);
                arg0.getContext().startActivity(intent);
            }
        });

        btnDynamicBanner.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, DynamicBanner.class);
                arg0.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            // 退出App的时候，显示插屏
            com.slion.sdk.PopAd popAD = new com.slion.sdk.PopAd(this,
                    com.slion.sdk.PopAd.PopAdType.FitSize, new PopADMonitor(this,
                    com.slion.sdk.PopAd.PopAdType.FitSize));
            popAD.showPopAD();
        }

        return super.onKeyDown(keyCode, event);
    }

    public class PopADMonitor implements com.slion.sdk.PopADListener {
        PopAd.PopAdType m_adType;

        Context m_context;

        public PopADMonitor(Context context, PopAd.PopAdType adType) {
            m_adType = adType;
            m_context = context;
        }

        @Override
        public void popADClicked() {
            if (m_adType.equals(PopAd.PopAdType.FitSize)) {
                Toast.makeText(m_context, "插屏广告点击了", Toast.LENGTH_SHORT).show();
            }
            if (m_adType.equals(PopAd.PopAdType.FullScreen)) {
                Toast.makeText(m_context, "全屏广告点击了", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void popADClosed() {

            if (m_adType.equals(PopAd.PopAdType.FitSize)) {
                Toast.makeText(m_context, "插屏广告关闭了", Toast.LENGTH_SHORT).show();
            }
            if (m_adType.equals(PopAd.PopAdType.FullScreen)) {
                Toast.makeText(m_context, "全屏广告关闭了", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void adpageClosed() {
            if (m_adType.equals(PopAd.PopAdType.FitSize)) {
                Toast.makeText(m_context, "来自插屏广告详情页关闭了", Toast.LENGTH_SHORT).show();
            }
            if (m_adType.equals(PopAd.PopAdType.FullScreen)) {
                Toast.makeText(m_context, "来自全屏广告详情页关闭了", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void noAdFound() {
            if (m_adType.equals(PopAd.PopAdType.FitSize)) {
                Toast.makeText(m_context, "插屏无广告", Toast.LENGTH_SHORT).show();
            } else if (m_adType.equals(PopAd.PopAdType.FullScreen)) {
                Toast.makeText(m_context, "全屏无广告", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void networkNotAvailable() {

            if (m_adType.equals(PopAd.PopAdType.FitSize)) {
                Toast.makeText(m_context, "无网络,不展示插屏", Toast.LENGTH_SHORT).show();
            } else if (m_adType.equals(PopAd.PopAdType.FullScreen)) {
                Toast.makeText(m_context, "无网络，不展示全屏", Toast.LENGTH_SHORT).show();
            }
        }

    }


}
