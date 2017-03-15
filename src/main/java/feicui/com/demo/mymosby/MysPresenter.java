package feicui.com.demo.mymosby;

import android.os.AsyncTask;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/3/9.
 */

public class MysPresenter extends MvpNullObjectBasePresenter<MyView> {

    List<String> mData;

    public void loadData() {
        mData = new ArrayList<>();
        getView().show();
        MyTask myTask = new MyTask();
        myTask.execute();
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 注意异步任务的四个方法的作用和用法
         *
         * @param aVoid
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (int i = 0; i < 50; i++) {
                mData.add("这是测试" + i);
            }
            getView().hide();
            getView().getData(mData);
        }
    }
}
