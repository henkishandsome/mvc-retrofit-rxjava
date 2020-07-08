package com.example.azheng.rxjavamvpdemo.contract;

import com.example.azheng.rxjavamvpdemo.base.BaseView;
import com.example.azheng.rxjavamvpdemo.bean.BaseArrayBean;
import com.example.azheng.rxjavamvpdemo.bean.BaseObjectBean;
import com.example.azheng.rxjavamvpdemo.bean.LoginBean;
import com.example.azheng.rxjavamvpdemo.bean.StationBean;

import io.reactivex.rxjava3.core.Observable;


/**
 * @author azheng
 * @date 2018/6/4.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public interface StationContract {
    interface Model {
        Observable<BaseObjectBean<StationBean>> getStationInfo(String imei);
        Observable<BaseArrayBean<StationBean>> getAllStation();
    }

    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(String errMessage);

        void onSuccess(BaseObjectBean<StationBean> bean);

        void onSuccess2(BaseArrayBean<StationBean> bean);
    }

    interface Presenter {
        /**
         * 登陆
         *
         */
        void getStationInfo(String imei);
        void getAllStation();
    }
}
