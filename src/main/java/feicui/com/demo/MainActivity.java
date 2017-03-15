package feicui.com.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    ListView mLv;
    ProgressBar mPb;
    List<String> mData;
    ArrayAdapter<String> arrayAdapter;//数组适配器
    Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        mBt = (Button) findViewById(R.id.main_btn);
        mLv = (ListView) findViewById(R.id.main_lv);
        mPb = (ProgressBar) findViewById(R.id.main_prb);
        mData = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData);
        mLv.setAdapter(arrayAdapter);

        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainBase(MainActivity.this).loadData();
            }
        });
    }

    @Override
    public void show() {
        Toast.makeText(MainActivity.this, "为什么没有显示呢????", Toast.LENGTH_SHORT).show();
        mPb.setVisibility(View.VISIBLE);
        mLv.setVisibility(View.GONE);
    }

    @Override
    public void hide() {
        mPb.setVisibility(View.GONE);
        mLv.setVisibility(View.VISIBLE);
    }

    public void getData(List<String> strings) {
        arrayAdapter.addAll(strings);
        arrayAdapter.notifyDataSetChanged();
    }

}
