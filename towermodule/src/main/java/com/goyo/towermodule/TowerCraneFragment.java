package com.goyo.towermodule;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.goyo.towermodule.adapter.TowerCraneAdapter;
import com.goyo.towermodule.base.BaseRetrofitFragment;
import com.goyo.towermodule.entitiy.TowerImageEntity;
import com.goyo.towermodule.listener.OnTowerCraneClickListener;
import com.goyo.towermodule.net.RetrofitUtils;
import com.goyo.towermodule.util.Toaster;
import com.goyo.towermodule.widget.scale.PhotoGesture;
import com.goyo.towermodule.widget.tower.TowerCraneMonitorView;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by JarvisLau on 2018/5/7.
 * Description :
 */
public class TowerCraneFragment extends BaseRetrofitFragment {

    private RecyclerView rvDetailList;
    private Call<ResponseBody> call;
    private CallBack<TowerImageEntity> callBack;
    private TowerCraneMonitorView towerCraneView;
    private PhotoGesture photoGesture;
    private TowerCraneAdapter towerCraneAdapter;
    private TextView tvRefreshDate;
    private TextView tvOnlineCount;
    private TextView tvOfflineCount;
    private LinearLayoutManager linearLayoutManager;
    private final static String ARGS_EXTRAS_KEY_PRO_ID = "args_extras_key_pro_id";
    private String proId;

    public static TowerCraneFragment newInstance(String proId) {
        Bundle args = new Bundle();
        args.putString(ARGS_EXTRAS_KEY_PRO_ID, proId);
        TowerCraneFragment fragment = new TowerCraneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (call != null && tvRefreshDate != null && callBack != null) {
            call = RetrofitUtils.getInstance().getTowerImage("6260");
            call.enqueue(callBack);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tower_crane_monitor;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            proId = arguments.getString(ARGS_EXTRAS_KEY_PRO_ID);
        } else if (TextUtils.isEmpty(proId)) {
            try {
                throw new Exception("参数错误!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentActivity activity = getActivity();
            if (activity != null) {
                getActivity().finish();
            }
        }
        rvDetailList = getRootView().findViewById(R.id.rvDetailList);
        initRecyclerView();
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header_tower_crane_monitor, null, false);
        towerCraneView = header.findViewById(R.id.towerCraneView);
        tvRefreshDate = header.findViewById(R.id.tvRefreshDate);
        tvOnlineCount = header.findViewById(R.id.tvOnlineCount);
        tvOfflineCount = header.findViewById(R.id.tvOfflineCount);
        towerCraneAdapter.setHeaderView(header);
        photoGesture = new PhotoGesture(getContext(), towerCraneView);
        getTowerImage();
        loopRequest();
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvDetailList.setLayoutManager(linearLayoutManager);
        towerCraneAdapter = new TowerCraneAdapter(getContext(), null);
        rvDetailList.setAdapter(towerCraneAdapter);
        rvDetailList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return TowerCraneFragment.this.onTouch(event);
            }
        });
        towerCraneAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TowerImageEntity.DataBean.ListTodayBean item = (TowerImageEntity.DataBean.ListTodayBean) adapter.getItem(position);
                if (item != null) {
                    TowerDetailActivity.start(getContext(), proId, item.getCraneNoName());
                }
            }
        });
    }

    private void loopRequest() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                call = RetrofitUtils.getInstance().getTowerImage("6260");
                call.enqueue(callBack);
            }
        }, 15 * 1000, 15 * 1000);
    }

    private void getTowerImage() {
        call = RetrofitUtils.getInstance().getTowerImage(proId);
        callBack = new CallBack<TowerImageEntity>() {
            @Override
            public void onSuccess(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response, @NonNull final TowerImageEntity data, @NonNull String json) {
                towerCraneAdapter.setNewData(data.getData().getListToday());
                tvRefreshDate.setText(String.format(getString(R.string.unit_refresh_date), data.getData().getSystemTime()));
                tvOnlineCount.setText(String.format(getString(R.string.unit_online), String.valueOf(data.getData().getPhototPath().getOnLine())));
                tvOfflineCount.setText(String.format(getString(R.string.unit_offline), String.valueOf(data.getData().getPhototPath().getOffLine())));
                Glide.with(TowerCraneFragment.this).load(data.getData().getPhototPath().getURL()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        towerCraneView.setBackground(resource);
                        towerCraneView.setEquipment(data.getData().getPhototPath(), data.getData().getAddressList());
                        towerCraneView.setOnTowerCraneClickListener(new OnTowerCraneClickListener() {
                            @Override
                            public void onClick(View v, TowerImageEntity.DataBean.AddressListBean bean) {
                                TowerDetailActivity.start(getContext(), proId, bean.getCraneNo());
                            }
                        });
                    }
                });
            }

            @Override
            public void onJson(String json) {
                super.onJson(json);
            }
        };
        call.enqueue(callBack);
    }

    public boolean onTouch(MotionEvent event) {
        return event.getY() >= towerCraneView.getTop()
                && event.getY() <= towerCraneView.getTop() + towerCraneView.getHeight()
                && linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0
                && photoGesture.onTouch(event);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            call.cancel();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        call.cancel();
    }

    @Override
    public void onPause() {
        super.onPause();
        call.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        call.cancel();
    }
}