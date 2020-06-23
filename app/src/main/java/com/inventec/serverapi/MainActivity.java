package com.inventec.serverapi;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inventec.entity.pos_shopinfo;
import com.inventec.utils.ServerManager;
import com.yanzhenjie.loading.dialog.LoadingDialog;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private ServerManager mServerManager;

    @Bind(R.id.btn_start)
    Button mBtnStart;

    @Bind(R.id.btn_stop)
    Button mBtnStop;
    @Bind(R.id.tv_message)
    TextView mTvMessage;

    private LoadingDialog mDialog;
    private String mRootUrl;

    @Bind(R.id.btn_add)
    Button btn_add;
    @Bind(R.id.btn_query)
    Button btn_query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mServerManager = new ServerManager(this);
        mServerManager.register();

        // startServer;
        mBtnStart.performClick();
    }

    @OnClick({R.id.btn_start,R.id.btn_stop,R.id.btn_add,R.id.btn_query})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                showDialog();
                mServerManager.startService();
                break;
            case R.id.btn_stop:
                showDialog();
                mServerManager.stopService();
                break;
            case R.id.btn_add:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showDialog();
                            }
                        });

                        long ctime = System.currentTimeMillis();

                        for (int i =0;i<10000;i++) {
                            new pos_shopinfo("1", "" + System.currentTimeMillis()).save();
                        }
                        long ta = ctime - System.currentTimeMillis();
                        Log.i("tags","===="+ta);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                closeDialog();
                            }
                        });
                    }
                }).start();

                break;
            case R.id.btn_query:
                List<pos_shopinfo> allSongs = LitePal.findAll(pos_shopinfo.class);
                Toast.makeText(this,allSongs.size()+"",Toast.LENGTH_SHORT).show();

                break;


        }
    }

    /**
     * Start notify.
     */
    public void serverStart(String ip) {
        closeDialog();
        mBtnStart.setVisibility(View.GONE);
        mBtnStop.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(ip)) {
            List<String> addressList = new LinkedList<>();
            mRootUrl = "http://" + ip + ":8080/";
            addressList.add(mRootUrl);
            mTvMessage.setText(TextUtils.join("\n", addressList));
        } else {
            mRootUrl = null;
            mTvMessage.setText(R.string.server_ip_error);
        }
    }

    /**
     * Error notify.
     */
    public void serverError(String message) {
        closeDialog();
        mRootUrl = null;
        mBtnStart.setVisibility(View.VISIBLE);
        mBtnStop.setVisibility(View.GONE);
        mTvMessage.setText(message);
    }

    /**
     * Stop notify.
     */
    public void serverStop() {
        closeDialog();
        mRootUrl = null;
        mBtnStart.setVisibility(View.VISIBLE);
        mBtnStop.setVisibility(View.GONE);
        mTvMessage.setText(R.string.server_stop_succeed);
    }

    private void showDialog() {
        if (mDialog == null)
            mDialog = new LoadingDialog(this);
        if (!mDialog.isShowing())
            mDialog.show();
    }

    private void closeDialog() {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }

}
