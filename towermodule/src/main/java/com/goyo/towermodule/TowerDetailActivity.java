package com.goyo.towermodule;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.*;
import com.goyo.towermodule.bean.TowerDetailBean;
import com.goyo.towermodule.bean.TowerReallBean;
import com.goyo.towermodule.net.RetrofitUtils;
import com.goyo.towermodule.util.DateUtils;
import com.goyo.towermodule.util.JsonUtil;
import com.goyo.towermodule.util.LogUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.List;


public class TowerDetailActivity extends AppCompatActivity implements View.OnClickListener {

    //正式地址
    private String host = "tcp://emq.igongdi.cn";
    private String userName = "admin";
    private String passWord = "Goyo_87987771";

    private String myTopic = "";
    private String myTopic0 = "/root/";
    private String myTopic2 = "/current";
    private String clientId = "clientId000";
    private boolean isGoing = false;
    private MqttClient client, clientTower;
    private MqttConnectOptions options, optionsTower;
    //    private ScheduledExecutorService scheduler;
    private double armFor;
    private double totalHeight;
    public static final int MSG_CONNECT = 4;
    public static final int MSG_CONNECT_DATA = 1;
    public static final int MSG_CONNECT_SUSS = 2;
    public static final int MSG_CONNECT_FAILD = 3;
    public static final int MSG_TIME_RECYCLER = 5;
    public int timeRecylerValue = 10;
    private Handler mHandler;
    private SparseArray<ImageView> mSparseArray = new SparseArray<>();
    ;
    private int[] mRadiusArr;

    //动画相关的变量
    private float randomX1;
    private float randomX2;
    private float randomY1;
    private float randomY2;
    private float mCurrScaleY;
    private float mNewScaleY;
    private float mImplCenter;
    private float mDensity;
    private int mImplHeightHuff = 5;
    private int mRoundHeight = 100;
    private int mRoundWidth = 137;

    private float mLeftScaleY, mLeftNewScaleY;
    private float mRightScaleY, mRightNewScaleY;
    private int mZhuHeight = 195;
    private float speedLeftMax = 25;
    private int mLeftlHeightHuff = 5;

    private List<TowerDetailBean.DataBean.ListBean> listInfo;
    private String CrnoId, CrnoName;
    private View lastClickView;
    private String craneNo, craneName;

    private boolean needPaint = true;

    private String isStreaming;
    private String urlVideo;
    private String videoId;
    private String videoStream;
    public static final String INTENT_ID = "intent_id";
    public static final String INTENT_NAME = "intent_name";
    public static final String INTENT_Stream = "intent_stream";
    public static final String INTENT_START_TIME = "intent_start_time";
    private long mStartTime;

    private double realWidthMax = 984;
    private double realHeightMax = 720;
    private double realRate;
    private DisplayMetrics dm;
    private double localXyRate;
    private String circleLength;
    private String circleUrl;

    private int screenWidths;
    private int screenHeights;
    private int realCircleBan;
    private ScaleGestureDetector mScaleGestureDetector = null;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    //呼叫状态1为呼叫开始0为呼叫结束
    private int callCount = 1;
    private int callOutCount = 0;
    private boolean isLoopData;
    private float[] mOldAngle;
    private ImageView mCurrentSelect;
    private static final int TOWER_VIDEO_DATA = 8;
    private TextView towerValueOne;
    private TextView towerValueTwo;
    private TextView towerValueThree;
    private TextView towerValueFour;
    private TextView towerValueFive;
    private TextView timeUpdate;
    private LinearLayout requestInfo;
    private ImageView mImpl0;
    private ImageView mImpl;
    private ImageView mImpl2;
    private TextView towerZhuLeftTextValue;
    private TextView towerZhuRightTextValue;
    private TextView mUpdateTime;
    private View towerZhuLeftValue;
    RelativeLayout leftZhu;
    private View towerRightZhuValue;
    private LinearLayout towerButtonValueOne;
    private RelativeLayout rightZhu;
    private LinearLayout towerButtonValueTwo;
    private LinearLayout towerButtonValueThree;
    private LinearLayout towerButtonValueFour;
    private LinearLayout towerButtonValueFive;
    private String proId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tower_ji);
        initView();
    }


    protected void initView() {
        requestInfo = findViewById(R.id.requestInfo);
        towerZhuLeftTextValue = findViewById(R.id.tower_zhu_left_text_value);
        towerZhuRightTextValue = findViewById(R.id.tower_zhu_right_text_value);
        mUpdateTime = findViewById(R.id.tv_update_time);

        towerZhuLeftValue = findViewById(R.id.tower_zhu_left_value);
        leftZhu = findViewById(R.id.left_zhu);
        towerRightZhuValue = findViewById(R.id.tower_right_zhu_value);
        rightZhu = findViewById(R.id.right_zhu);
        towerButtonValueOne = findViewById(R.id.tower_button_value_one);
        towerButtonValueTwo = findViewById(R.id.tower_button_value_two);
        towerButtonValueThree = findViewById(R.id.tower_button_value_three);
        towerButtonValueFour = findViewById(R.id.tower_button_value_four);
        towerButtonValueFive = findViewById(R.id.tower_button_value_five);

        towerValueOne = findViewById(R.id.tower_value_one);
        towerValueTwo = findViewById(R.id.tower_value_two);
        towerValueThree = findViewById(R.id.tower_value_three);
        towerValueFour = findViewById(R.id.tower_value_four);
        towerValueFive = findViewById(R.id.tower_value_four);

        mImpl0 = findViewById(R.id.tower_viewTop);
        mImpl = findViewById(R.id.tower_viewLine);
        mImpl2 = findViewById(R.id.tower_viewBottom);
        timeUpdate = findViewById(R.id.time_update);

        initData();
    }

    protected void initData() {
        Intent intent = getIntent();
        if(intent != null){
            proId = intent.getStringExtra("proId");
            craneNo = intent.getStringExtra("craneNo");
        }

        /*proId = "6260";
        craneNo = "800100";*/

        //订阅
        myTopic = myTopic0 + craneNo + myTopic2;

        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mDensity = dm.density;
        mImplCenter = mDensity * mImplHeightHuff;
        restoreHandler();

        if(proId != null || craneNo == null){
            getNetWork();
        }else {
            Toast.makeText(this,"请传递参数  proId 和 craneNo",Toast.LENGTH_LONG).show();
        }

        requestInfo.setFocusable(true);
        requestInfo.setFocusableInTouchMode(true);
        requestInfo.requestFocus();
    }

    private void getNetWork() {
        Call<TowerDetailBean> call = RetrofitUtils.getInstance().requestTowerDetail(proId,craneNo);
        call.enqueue(new Callback<TowerDetailBean>() {
            @Override
            public void onResponse(Call<TowerDetailBean> call, Response<TowerDetailBean> json) {
                //String str = json.body();
                //Log.i("TTT", "塔机详情: "+str);
                //TowerDetailBean response = JsonUtil.json2Bean(str, TowerDetailBean.class);
                TowerDetailBean response = json.body();
                if(response != null){
                    if ("1".equals(response.getCode())) {
                        if (response.getData() != null) {
                            if (response.getData().getPhototPath() != null) {
                                TowerDetailBean.DataBean.PhototPathBean phototPath = response.getData().getPhototPath();
                                circleLength = phototPath.getLength();
                                circleUrl = phototPath.getURL();

                            }
                            if (response.getData().getList() != null) {
                                listInfo = response.getData().getList();
                            }

                            if (response.getData().getFirstTowerMap() != null) {

                                towerValueOne.setText(response.getData().getFirstTowerMap().getHeight() == null ? "---" : response.getData().getFirstTowerMap().getHeight());
                                towerValueTwo.setText(response.getData().getFirstTowerMap().getRadius() == null ? "---" : response.getData().getFirstTowerMap().getRadius());
                                towerValueThree.setText(response.getData().getFirstTowerMap().getTorquePercent() == null ? "---" : response.getData().getFirstTowerMap().getTorquePercent());
                                towerValueFour.setText(response.getData().getFirstTowerMap().getAngle() == null ? "---" : response.getData().getFirstTowerMap().getAngle());
                                towerValueFive.setText(response.getData().getFirstTowerMap().getWind() == null ? "---" : response.getData().getFirstTowerMap().getWind());
                                towerZhuLeftTextValue.setText(response.getData().getFirstTowerMap().getWind() == null ? "---" : response.getData().getFirstTowerMap().getWind());
                                towerZhuRightTextValue.setText(response.getData().getFirstTowerMap().getWeight() == null ? "---" : response.getData().getFirstTowerMap().getWeight());

                                if (!TextUtils.isEmpty(response.getData().getFirstTowerMap().getVideoId())) {
                                    videoId = response.getData().getFirstTowerMap().getVideoId();
                                }
                                if (!TextUtils.isEmpty(response.getData().getFirstTowerMap().getStream())) {
                                    videoStream = response.getData().getFirstTowerMap().getStream();
                                }

                            }

                            //以下为计算塔基数据  目的实现动画
                            double leftRate = 0;
                            double RightRate = 0;
                            double ddd = 0;
                            double eee = 0;
                            try {
                                String height = response.getData().getFirstTowerMap().getHeight();  //总度
                                String rootHeight = response.getData().getFirstTowerMap().getRootHeight();   //塔高
                                totalHeight = Double.parseDouble(rootHeight);
                                if ((int) (totalHeight * 100) == 0) {
                                    return;
                                }
                                ddd = Double.parseDouble(height) / totalHeight;
                                if (ddd >= 1.0000000) {
                                    ddd = 1;
                                }
                                String armFor2 = response.getData().getFirstTowerMap().getArmFor();  //前臂长
                                String radius = response.getData().getFirstTowerMap().getRadius();    //幅度

                                armFor = Double.parseDouble(armFor2);
                                if ((int) (armFor * 100) == 0) {
                                    return;
                                }
                                eee = Double.parseDouble(radius) / armFor;
                                if (eee >= 1.0000000) {
                                    eee = 1;
                                }

                                leftRate = Double.parseDouble(response.getData().getFirstTowerMap().getWind()) / speedLeftMax;
                                if (leftRate >= 1.0000000) {
                                    leftRate = 1;
                                }

                                RightRate = Double.parseDouble(response.getData().getFirstTowerMap().getWeight()) / Double.parseDouble(response.getData().getFirstTowerMap().getLimitWeight());
                                if (RightRate >= 1.0000000) {
                                    RightRate = 1;
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                mCurrScaleY = (float) ((ddd * mRoundHeight) / (mImplHeightHuff * 2) + 1);
                                randomY1 = (mImplCenter * (mCurrScaleY - 1));
                                randomX1 = (float) (mDensity * mRoundWidth * eee);
                                mLeftScaleY = (float) ((leftRate * mZhuHeight) / (mLeftlHeightHuff) + 1);
                                mRightScaleY = (float) ((RightRate * mZhuHeight) / (mLeftlHeightHuff) + 1);
                                //塔基第一次请求数据,塔基初始动画
                                btnGo1();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<TowerDetailBean> call, Throwable t) {
                LogUtil.i("塔机详情 "+t.getMessage());
            }
        });
    }

    /**
     * 塔机动画--1
     */
    public void btnGo1() {
        String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
        mUpdateTime.setText("上次数据刷新时间：" + currentDate);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(mImpl, "translationX", 0, randomX1),
                ObjectAnimator.ofFloat(mImpl2, "translationX", 0, randomX1),
                ObjectAnimator.ofFloat(mImpl0, "translationX", 0, randomX1),
                ObjectAnimator.ofFloat(mImpl, "translationY", 0, randomY1),
                ObjectAnimator.ofFloat(mImpl2, "translationY", 0, randomY1 * 2),
                ObjectAnimator.ofFloat(mImpl, "scaleY", 1, mCurrScaleY),
                ObjectAnimator.ofFloat(towerZhuLeftValue, "scaleY", 1, 2 * mLeftScaleY),
                ObjectAnimator.ofFloat(towerRightZhuValue, "scaleY", 1, 2 * mRightScaleY)
        );

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isGoing = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isGoing = false;
                if (mHandler != null) {
                    //塔机第一次动画执行完成后，开始通过订阅MQTT协议接收塔机数据
                    mHandler.sendEmptyMessage(MSG_CONNECT);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.setDuration(2 * 1000).start();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }


    public void restoreHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_CONNECT:
                        init();
                        startReconnect();
                        break;
                }
                //通过MQTT协议传输过来的塔机数据，执行塔机动画
                if (msg.what == MSG_CONNECT_DATA) {

                    mUpdateTime.setText("上次数据刷新时间：" + DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));

                    String dateInfo = (String) msg.obj;
                    if (!isGoing) {
                        moveByMqtt(dateInfo);
                    }

                } else if (msg.what == MSG_CONNECT_SUSS) {
                    try {
                        LogUtil.i("订阅   "+myTopic);
                        client.subscribe(myTopic, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (msg.what == MSG_CONNECT_FAILD) {
                    //Toast.makeText(context, "连接失败，系统正在重连-----" + msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    //startReconnect();
                } else if (msg.what == MSG_TIME_RECYCLER) {
                    getNetWork();
                    //mHandler.sendEmptyMessageDelayed(MSG_TIME_RECYCLER, 10 * 1000);
                }
            }
        };
    }

    private void moveByMqtt(String data) {
        String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
        mUpdateTime.setText("上次数据刷新时间：" + currentDate);

        try {
            TowerReallBean bean = JsonUtil.json2Bean(data, TowerReallBean.class);
            towerValueOne.setText(bean.getHeight() == null ? "---" : bean.getHeight());
            towerValueTwo.setText(bean.getRadius() == null ? "---" : bean.getRadius());
            towerValueThree.setText(bean.getTorque() == null ? "---" : bean.getTorque());
            towerValueFour.setText(bean.getAngle() == null ? "---" : bean.getAngle());
            towerValueFive.setText(bean.getWind() == null ? "---" : bean.getWind());
            towerZhuLeftTextValue.setText(bean.getWind() == null ? "---" : bean.getWind());
            towerZhuRightTextValue.setText(bean.getWeight() == null ? "---" : bean.getWeight());

            List<TowerReallBean.DatasBean> datas = bean.getDatas();

            double ddd = 0;
            double eee = 0;
            double leftRate = 0;
            double RightRate = 0;
            try {
                String height = bean.getHeight();
                ddd = Double.parseDouble(height) / totalHeight;
                if (ddd >= 1.0000000) {
                    ddd = 1;
                }
                String radius = bean.getRadius();
                eee = Double.parseDouble(radius) / armFor;
                if (eee >= 1.0000000) {
                    eee = 1;
                }

                leftRate = Double.parseDouble(bean.getWind()) / speedLeftMax;
                if (leftRate >= 1.0000000) {
                    leftRate = 1;
                }

                RightRate = Double.parseDouble(bean.getWeight()) / Double.parseDouble(bean.getSafeWeight());
                if (RightRate >= 1.0000000) {
                    RightRate = 1;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            mNewScaleY = (float) ((ddd * mRoundHeight) / (mImplHeightHuff * 2) + 1);
            randomY2 = randomY1 + (int) ((mNewScaleY - mCurrScaleY) * mImplCenter);//其实randomX2也应该根据randomX1
            randomX2 = (float) (mDensity * mRoundWidth * eee);

            mLeftNewScaleY = (float) ((leftRate * mZhuHeight) / (mLeftlHeightHuff) + 1);
            mRightNewScaleY = (float) ((RightRate * mZhuHeight) / (mLeftlHeightHuff) + 1);
            //塔基第二次动画,数据来源mqtt
            btnGo2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnGo2() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(mImpl, "translationX", randomX1, randomX2),
                ObjectAnimator.ofFloat(mImpl2, "translationX", randomX1, randomX2),
                ObjectAnimator.ofFloat(mImpl0, "translationX", randomX1, randomX2),
                ObjectAnimator.ofFloat(mImpl, "translationY", randomY1, randomY2),
                ObjectAnimator.ofFloat(mImpl2, "translationY", randomY1 * 2, randomY2 * 2),//这个是缩放二倍
                ObjectAnimator.ofFloat(mImpl, "scaleY", mCurrScaleY, mNewScaleY),
                ObjectAnimator.ofFloat(towerZhuLeftValue, "scaleY", 2 * mLeftScaleY, 2 * mLeftNewScaleY),
                ObjectAnimator.ofFloat(towerRightZhuValue, "scaleY", 2 * mRightScaleY, 2 * mRightNewScaleY)
        );

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isGoing = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isGoing = false;
                if (mHandler != null) {
                    mCurrScaleY = mNewScaleY;
                    randomX1 = randomX2;
                    randomY1 = randomY2;
                    mLeftScaleY = mLeftNewScaleY;
                    mRightScaleY = mRightNewScaleY;
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.setDuration(2 * 1000).start();
    }


    private void init() {
        try {
            //host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，
            // MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(host, clientId,
                    new MemoryPersistence());
            //client = new MqttClient(host, clientId);
            //MQTT的连接设置
            options = new MqttConnectOptions();
            //设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            //设置连接的用户名
            options.setUserName(userName);
            //设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            //设置回调
            client.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(Throwable cause) {
                    //连接丢失后，一般在这里面进行重连
                    startReconnect();
                    Log.i("TTT", "connectionLost: ");
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //publish后会执行到这里
                }

                @Override
                public void messageArrived(String topicName, MqttMessage message)
                        throws Exception {
                    //subscribe后得到的消息会执行到这里面
                    Log.i("TTT", "收到的消息: "+message);
                    Message msg = new Message();
                    msg.what = MSG_CONNECT_DATA;
                    msg.obj = message.toString();
                    if (mHandler != null) {
                        mHandler.sendMessage(msg);
                    }

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startReconnect() {
        if (!client.isConnected()) {
            connect();
        }
    }

    private void connect() {
        try {
            client.connect(options);
            Message msg = new Message();
            msg.what = MSG_CONNECT_SUSS;//连接后去订阅
            if (mHandler != null) {
                mHandler.sendMessage(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Message msg = new Message();
            msg.what = MSG_CONNECT_FAILD;
            msg.obj = e.toString();
            if (mHandler != null) {
                mHandler.sendMessage(msg);
            }
        }

    }

    @Override
    public void onDestroy() {
        if (mHandler != null) {
            mHandler.removeMessages(MSG_CONNECT_SUSS);
            mHandler.removeMessages(MSG_CONNECT_FAILD);
            mHandler.removeMessages(MSG_CONNECT_DATA);
            mHandler.removeMessages(MSG_TIME_RECYCLER);
            mHandler = null;
        }
        if (client != null) {
            try {
                client.unsubscribe(myTopic);
                client.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        if (clientTower != null) {
            try {
                releaseMqtt();
                clientTower.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        super.onDestroy();
    }

    public void publish(String mqttPath, String messageInfo) {
        try {
            MqttTopic topic = clientTower.getTopic(mqttPath);
            //LogUtil.i("发送的消息内容为: "+messageInfo);
            MqttMessage message = new MqttMessage(messageInfo.getBytes());
            MqttDeliveryToken token = topic.publish(message);
            while (!token.isComplete()) {
                token.waitForCompletion(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void releaseMqtt() throws MqttException {
        String currentTime = simpleDateFormat.format(System.currentTimeMillis());
        publish("VSG/" + proId + "/Call", "S&" + videoId + "&" + callOutCount + "&" + currentTime + "&E");
        String sinfo = "VSG/" + proId + "/" + videoId + "/PushFlowStatus";
        clientTower.unsubscribe(sinfo);
    }
}
