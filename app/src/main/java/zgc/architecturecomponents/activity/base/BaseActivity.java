package zgc.architecturecomponents.activity.base;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

/**
 * Created by Nick on 2017/11/5
 */
public abstract class BaseActivity extends AppCompatActivity implements LifecycleOwner {
    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    public static final String ACTIVIT_TITLE = "activity_title";

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    protected abstract int providerLayout();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(providerLayout());

        if (!TextUtils.isEmpty(getIntent().getStringExtra(ACTIVIT_TITLE))) {
            setTitle(getIntent().getStringExtra(ACTIVIT_TITLE));
        }

        initView();
        initData();
    }

    protected void go2Activity(Class clazz) {
        go2Activity(clazz, null);
    }

    protected void go2Activity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

}
