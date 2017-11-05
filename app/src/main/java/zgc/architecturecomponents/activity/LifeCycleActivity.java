package zgc.architecturecomponents.activity;

import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zgc.architecturecomponents.R;
import zgc.architecturecomponents.activity.base.BaseActivity;
import zgc.architecturecomponents.util.LocationUtil;
import zgc.architecturecomponents.util.LogUtil;

/**
 * Created by Nick on 2017/11/5
 */
public class LifeCycleActivity extends BaseActivity implements Print {
    @BindView(R.id.tv_content) TextView tv_content;

    private StringBuffer mSB = new StringBuffer();
    private LocationUtil mLocationUtil = null;

    @Override
    protected int providerLayout() {
        return R.layout.activity_lifecycler;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        mLocationUtil = new LocationUtil(this, getLifecycle(), location -> {
            LogUtil.d(location);
            print("result:" + location);
        });

        checkUseState(result -> {
            if (result) {
                mLocationUtil.enable();
            }
        });
    }

    private void checkUseState(Callback callback) {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                callback.result(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void print(String str) {
        runOnUiThread(() -> {
            mSB.append(str);
            mSB.append("\n");
            tv_content.setText(mSB.toString());
        });
    }

    public interface Callback {
        void result(boolean result);
    }

}
