package feicui.com.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyViewActivity extends AppCompatActivity {
    RecyclerView mRv;
    List<String> list;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy_view);
        mRv = (RecyclerView) findViewById(R.id.main_rv);
        adapter = new MyAdapter();

        getData();
        mRv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        this.setOnItemClickedListener(new OnItemClickedListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(RecyViewActivity.this, "点击了" + postion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int postion) {
                Toast.makeText(RecyViewActivity.this, "长按了" + postion, Toast.LENGTH_SHORT).show();
            }
        });

        mRv.setAdapter(adapter);
    }

    private void getData() {
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("我是" + i);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_activity, parent, false);

            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            //设置随机高度
            ViewGroup.LayoutParams params = holder.view.getLayoutParams();
            Random random = new Random();
            params.height = random.nextInt(1000) + 50;
            holder.view.setLayoutParams(params);
            holder.view.setText(list.get(position));

            if (listener != null) {//点击与长按
                holder.itemView.setOnClickListener(new View.OnClickListener() {//注意这监听的点击事件
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(v, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        listener.onItemLongClick(v, position);
                        return true;
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView view;

            public MyViewHolder(View itemView) {
                super(itemView);
                view = (TextView) itemView.findViewById(R.id.main_rv_tv);
            }
        }
    }

    private OnItemClickedListener listener;

    public interface OnItemClickedListener {
        //点击触发方法
        void onItemClick(View view, int postion);

        //长按事件
        void onItemLongClick(View view, int postion);

    }

    public void setOnItemClickedListener(OnItemClickedListener listener) {
        this.listener = listener;
    }

}
