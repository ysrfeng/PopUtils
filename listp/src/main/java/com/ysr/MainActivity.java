package com.ysr;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyAdapter.onPopupWindowItemClickListener {
    private TextView mButton1, mButton2;
    private CustomPopWindow mListPopWindow;
    private String[] mlistText = {"男", "女"};
    private List<String> data;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton1 = (TextView) findViewById(R.id.button1);
        mButton2 = (TextView) findViewById(R.id.button2);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mockData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                showPopListView();
                break;
            case R.id.button2:
                View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
                wv.setOffset(1);
                wv.setItems(Arrays.asList(mlistText));
                wv.setSeletion(1);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        Log.d("tag", "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                    }
                });

                AlertDialog dialog=     new AlertDialog.Builder(this)
                        .setTitle(null)
                        .setView(outerView)
                        .setPositiveButton("OK", null)
                        .show();
                //设置弹窗在底部
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                WindowManager m = getWindowManager();
                Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
                WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
                p.width = d.getWidth(); //宽度设置为屏幕
                dialog.getWindow().setAttributes(p); //设置生效
                break;
        }
    }

    private void showPopListView() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_list, null);
        //处理popWindow 显示内容
        handleListView(contentView);
        //创建并显示popWindow
        mListPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()
                .showAtLocation(mButton1, Gravity.BOTTOM, 0, 0);
    }

    private void handleListView(View contentView) {
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(this);
        adapter.notifyDataSetChanged();

    }

    private List<String> mockData() {
        data = new ArrayList<>();
        for (int i = 0; i < mlistText.length; i++) {
            data.add(mlistText[i]);
        }
        return data;
    }

    @Override
    public void onItemTextClick(String text) {
        Toast.makeText(MainActivity.this, "" + text, Toast.LENGTH_SHORT).show();
        mListPopWindow.dissmiss();

    }
}
