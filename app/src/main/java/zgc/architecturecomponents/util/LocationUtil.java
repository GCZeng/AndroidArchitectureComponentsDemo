package zgc.architecturecomponents.util;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.text.TextUtils;

import zgc.architecturecomponents.activity.Print;

/**
 * Created by Nick on 2017/11/5
 */
public class LocationUtil implements LifecycleObserver {
    private boolean mEnabled = false;
    private Lifecycle mLifecycle = null;
    private Callback mCallback = null;
    private Print mPrint = null;

    public LocationUtil(Print print, Lifecycle lifecycle, Callback callback) {
        this.mLifecycle = lifecycle;
        this.mCallback = callback;
        this.mPrint = print;

        lifecycle.addObserver(this);
    }

    public void enable() {
        LogUtil.d("enable");
        mEnabled = true;

        if (mLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            onResult();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onany() {
//        print("onAny");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
        print("onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        print("onStart");
        if (mEnabled) {
            onResult();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
        print("onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
        print("onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        print("onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destory() {
        print("onDestory");
    }

    private void onResult() {
        if (mCallback != null) {
            mCallback.onResult("定位信息");
        }
    }

    private void print(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.d(str);
        if (mPrint != null) {
            mPrint.print(str);
        }
    }

    public interface Callback {
        void onResult(String location);
    }

}
