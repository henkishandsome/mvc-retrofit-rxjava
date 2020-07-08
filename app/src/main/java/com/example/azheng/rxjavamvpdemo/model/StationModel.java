package com.example.azheng.rxjavamvpdemo.model;


import com.example.azheng.rxjavamvpdemo.bean.BaseArrayBean;
import com.example.azheng.rxjavamvpdemo.bean.BaseObjectBean;
import com.example.azheng.rxjavamvpdemo.bean.LoginBean;
import com.example.azheng.rxjavamvpdemo.bean.StationBean;
import com.example.azheng.rxjavamvpdemo.contract.MainContract;
import com.example.azheng.rxjavamvpdemo.contract.StationContract;
import com.example.azheng.rxjavamvpdemo.net.RetrofitClient;

import io.reactivex.rxjava3.core.Observable;


/**
 * @author azheng
 * @date 2018/6/4.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public class StationModel implements StationContract.Model {
    @Override
    public Observable<BaseObjectBean<StationBean>> getStationInfo(String imei) {
        return RetrofitClient.getInstance().getApi().getStationInfo(imei);
    }
    @Override
    public Observable<BaseArrayBean<StationBean>> getAllStation(){
        return RetrofitClient.getInstance().getApi().getAllStation();
    }
}
