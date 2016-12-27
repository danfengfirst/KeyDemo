package com.efly.map.keydemo.coordinate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.efly.map.keydemo.R;

/**
 * Created by danfeng.wang on 2016/12/23.
 */

public class CoordinateLayoutActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coor);
        mTabLayout= (TabLayout) findViewById(R.id.tablayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("分类"));
        mTabLayout.addTab(mTabLayout.newTab().setText("排序"));
        mTabLayout.addTab(mTabLayout.newTab().setText("价格"));
        mTabLayout.setTabTextColors(Color.BLACK,Color.RED);
        mTabLayout.setSelectedTabIndicatorColor(Color.RED);
        mTabLayout.setBackgroundColor(Color.GREEN);

    }
}
