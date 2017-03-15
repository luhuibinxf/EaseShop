package feicui.com.demo.mymosby;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by dell on 2017/3/9.
 */

public interface MyView extends MvpView {

    public void show();

    public void hide();

    public void getData(List<String> stringLis);
}
