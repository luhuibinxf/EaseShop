package feicui.com.demo.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import feicui.com.demo.R;

public class FirstActivity extends AppCompatActivity {
    Button mbt;
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        EventBus.getDefault().register(this);
        mbt = (Button) findViewById(R.id.first_bu);
        mTv = (TextView) findViewById(R.id.first_tv);

        mbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        Toast.makeText(this, "这只是一个测试", Toast.LENGTH_SHORT).show();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(MessageEvent messageEvent) {
        String msg = "收到了吗？？？？" + messageEvent.message;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        mTv.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
