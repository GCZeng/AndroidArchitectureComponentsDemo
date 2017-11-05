package zgc.architecturecomponents;

import android.app.Application;

import zgc.architecturecomponents.util.LogUtil;

/**
 * Created by Nick on 2017/11/5
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init();
    }
}
