package feicui.com.demo.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import feicui.com.demo.R;

public class SecondActivity extends AppCompatActivity {
    Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mBt = (Button) findViewById(R.id.main_second);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "我是老葛的大哥？？？";
                EventBus.getDefault().post(new MessageEvent(msg));
                Toast.makeText(SecondActivity.this, msg, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
