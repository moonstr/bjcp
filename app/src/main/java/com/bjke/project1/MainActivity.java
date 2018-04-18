package com.bjke.project1;

import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.widget.RadioGroup;

import com.bjke.project1.base.BaseActivity;
import com.bjke.project1.base.BaseFragment;
import com.bjke.project1.fragment.HotFragment;
import com.bjke.project1.fragment.LotteryFragment;
import com.bjke.project1.fragment.SscFragment;
import com.bjke.project1.fragment.WinFragment;

public class MainActivity extends BaseActivity {
    private RadioGroup mRadioGroup;
    private SparseArray<BaseFragment> fragments;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mRadioGroup = findViewById(R.id.radioGroup);
    }

    @Override
    public void initData() {
        fragments = new SparseArray<>();
        fragments.append(0, new LotteryFragment());
        fragments.append(1, new HotFragment());
        fragments.append(2, new SscFragment());
        fragments.append(3, new WinFragment());
        switchFragment(0);
    }

    @Override
    public void initListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.lottery:
                        switchFragment(0);
                        break;
                    case R.id.hot:
                        switchFragment(1);
                        break;
                    case R.id.win:
                        switchFragment(2);
                        break;
                    case R.id.ssc:

                        switchFragment(3);
                        break;
                }
            }
        });
    }

    private void switchFragment(int position) {
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        if (!fragments.get(position).isAdded()) {
            trx.add(R.id.container, fragments.get(position));
        }
        for (int i = 0; i < fragments.size(); i++) {
            if (position == i) {
                trx.show(fragments.get(i));
            } else {
                trx.hide(fragments.get(i));
            }
        }
        trx.commitAllowingStateLoss();
    }

}
