package com.goyo.tower;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.goyo.towermodule.TowerCraneFragment;

public class MainActivity extends AppCompatActivity {

    private TowerCraneFragment towerCraneFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        towerCraneFragment = TowerCraneFragment.newInstance("6260");
        transaction.replace(R.id.content, towerCraneFragment).commit();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return towerCraneFragment.onTouch(event);
    }
}
