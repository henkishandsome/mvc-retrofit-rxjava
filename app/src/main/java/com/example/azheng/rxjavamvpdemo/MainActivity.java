package com.example.azheng.rxjavamvpdemo;


import android.os.Bundle;

import com.example.azheng.rxjavamvpdemo.bean.BaseArrayBean;
import com.example.azheng.rxjavamvpdemo.bean.StationBean;
import com.example.azheng.rxjavamvpdemo.contract.StationContract;
import com.example.azheng.rxjavamvpdemo.presenter.StationPresenter;
import com.google.android.material.textfield.TextInputEditText;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.azheng.rxjavamvpdemo.base.BaseMvpActivity;
import com.example.azheng.rxjavamvpdemo.bean.BaseObjectBean;
import com.example.azheng.rxjavamvpdemo.contract.MainContract;
import com.example.azheng.rxjavamvpdemo.presenter.MainPresenter;

import java.util.List;


public class MainActivity extends BaseMvpActivity<StationPresenter> implements StationContract.View {

    TextInputEditText etUsernameLogin;
    TextInputEditText etPasswordLogin;
    Button btnSigninLogin,btn_test;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        etUsernameLogin = findViewById(R.id.et_username_login);
        etPasswordLogin = findViewById(R.id.et_password_login);
        btnSigninLogin = findViewById(R.id.btn_signin_login);
        btn_test=findViewById(R.id.btn_test);

        mPresenter = new StationPresenter();
        mPresenter.attachView(this);
        btnSigninLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (getUsername().isEmpty() || getPassword().isEmpty()) {
//                    Toast.makeText(MainActivity.this, "帐号密码不能为空", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                mPresenter.getStationInfo("867071048139516");
            }
        });
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getAllStation();
            }
        });

    }

    /**
     * @return 帐号
     */
    private String getUsername() {
        return etUsernameLogin.getText().toString().trim();
    }

    /**
     * @return 密码
     */
    private String getPassword() {
        return etPasswordLogin.getText().toString().trim();
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {

        Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
        etUsernameLogin.setText(bean.getMsg());
        etPasswordLogin.setText(bean.getRv());

    }
    @Override
    public void onSuccess2(BaseArrayBean bean){
        etUsernameLogin.setText(bean.getMsg());
        etPasswordLogin.setText(bean.getRv());
        List<StationBean> stationBeanList=bean.getData();
        for (int i=0;i<stationBeanList.size();i++){
            StationBean info=stationBeanList.get(i);
            System.out.println(info.getStation());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String errMessage) {

    }

}
