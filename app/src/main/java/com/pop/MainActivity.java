package com.pop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private BottomPopupOption bottomPopupOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:

                bottomPopupOption = new BottomPopupOption(MainActivity.this);
                bottomPopupOption.setItemText("男", "女","无","逗");
                // bottomPopupOption.setColors();//设置颜色
                bottomPopupOption.showPopupWindow();
                bottomPopupOption.setItemClickListener(new Mylistener());
                break;
        }
    }

    private class Mylistener implements BottomPopupOption.onPopupWindowItemClickListener {
        @Override
        public void onItemPositionClick(int position) {

        }
        @Override
        public void onItemTextClick(String text) {
            Toast.makeText(MainActivity.this,""+text,Toast.LENGTH_SHORT).show();
            bottomPopupOption.dismiss();
        }
    }
}
