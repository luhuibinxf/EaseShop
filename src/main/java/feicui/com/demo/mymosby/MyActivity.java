package feicui.com.demo.mymosby;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import feicui.com.demo.R;

public class MyActivity extends MvpActivity<MyView, MysPresenter> implements MyView {//注意这些的方法(继承谁，泛型是谁，实现谁)
    ListView mLv;
    ProgressBar mPb;
    List<String> mData;
    ArrayAdapter<String> arrayAdapter;//数组适配器
    Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        init();
    }

    void init() {
        mBt = (Button) findViewById(R.id.main_btn);
        mLv = (ListView) findViewById(R.id.main_lv);
        mPb = (ProgressBar) findViewById(R.id.main_prb);
        mData = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData);
        mLv.setAdapter(arrayAdapter);

        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MysPresenter().loadData();
                getPresenter().loadData();
            }
        });
    }

    @NonNull
    @Override
    public MysPresenter createPresenter() {
        return new MysPresenter();
    }

    @Override
    public void show() {
        Toast.makeText(this, "为什么没有显示呢????", Toast.LENGTH_SHORT).show();
        mPb.setVisibility(View.VISIBLE);
        mLv.setVisibility(View.GONE);
    }

    @Override
    public void hide() {
        mPb.setVisibility(View.GONE);
        mLv.setVisibility(View.VISIBLE);
    }

    @Override
    public void getData(List<String> stringLis) {
        arrayAdapter.addAll(stringLis);
        arrayAdapter.notifyDataSetChanged();
    }
}
