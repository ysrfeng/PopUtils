package com.ysr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyAdapter.onPopupWindowItemClickListener {
    private TextView mButton4;
    private CustomPopWindow mListPopWindow;
    private String[] mlistText = {"陶然亭街道", "朝外街道", "安贞街道", "三里屯街道"};
    private List<String> data;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton4 = (TextView) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        mockData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button4:
                showPopListView();
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
                .showAtLocation(mButton4, Gravity.BOTTOM, 0, 0);
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
