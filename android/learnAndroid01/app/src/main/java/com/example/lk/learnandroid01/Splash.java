package com.example.lk.learnandroid01;




import com.example.lk.learnandroid01.R;
import com.slion.sdk.SplashADInfo;
import com.slion.sdk.SplashFullScreenAD;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Splash extends Activity {


    
    ImageView imgSplash;
    boolean isClickedAD = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
//        DXUtils.prepareDXData(this, "332115720000251722", "91195a57341f16c6488156a3ddf1e67b");
//        Util.initSDK(this);
        imgSplash = (ImageView)findViewById(R.id.imgSplash);
        
        // 获得开屏广告对象
        final SplashFullScreenAD splashFullScreenAD = new SplashFullScreenAD(this);
        final SplashADInfo splashAD = splashFullScreenAD.getSplashAD("F8849929D4814AAABF30F98293CEAFAB");

        if (splashAD == null) // 没有开屏广告
        {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    gotoMain();
                }

            }, 3000);
        } else {

            imgSplash.setImageBitmap(BitmapFactory.decodeFile(splashAD.picLocalPath));
            
            // 展示开屏广告处理
            splashFullScreenAD.sendSplashADShowLog(splashAD);
            
            imgSplash.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    isClickedAD = true;
                    
                    // 点击开屏广告处理
                    splashFullScreenAD.clickSplashAD(splashAD);
                }
            });

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (!isClickedAD) {
                        gotoMain();
                    }
                }

            }, 3000);
        }
    }

    void gotoMain() {

        Intent mainIntent = new Intent(Splash.this, MainActivity.class);
        startActivity(mainIntent);
        Splash.this.finish();
    }
    
    /*
     * 开屏广告点击弹出的Activity关闭之后的回调
     * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {
            case Activity.RESULT_OK:
                gotoMain();
                break;
            default:
                break;
        }
    }
}
