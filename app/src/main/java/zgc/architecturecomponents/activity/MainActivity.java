package zgc.architecturecomponents.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zgc.architecturecomponents.R;
import zgc.architecturecomponents.activity.base.BaseActivity;
import zgc.architecturecomponents.adapter.HomeListAdapter;
import zgc.architecturecomponents.adapter.RecyclerItemClickListener;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rv_list) RecyclerView rv_list;

    private List<String> mData = null;
    private HomeListAdapter mHomeListAdapter = null;

    @Override
    protected int providerLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        mData = Arrays.asList(getResources().getStringArray(R.array.home_list));
        mHomeListAdapter = new HomeListAdapter(mData);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(mHomeListAdapter);

        rv_list.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle;
                switch (position) {
                    case 0:
                        bundle = new Bundle();
                        bundle.putString(ACTIVIT_TITLE, mData.get(position));
                        go2Activity(LifeCycleActivity.class, bundle);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onLongClick(View view, int posotion) {

            }
        }));
    }

    @Override
    protected void initData() {

    }
}
