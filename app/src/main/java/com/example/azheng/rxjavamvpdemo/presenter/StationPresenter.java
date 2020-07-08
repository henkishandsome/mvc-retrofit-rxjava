package com.example.azheng.rxjavamvpdemo.presenter;

import com.example.azheng.rxjavamvpdemo.base.BasePresenter;
import com.example.azheng.rxjavamvpdemo.bean.BaseArrayBean;
import com.example.azheng.rxjavamvpdemo.bean.BaseObjectBean;
import com.example.azheng.rxjavamvpdemo.bean.LoginBean;
import com.example.azheng.rxjavamvpdemo.bean.StationBean;
import com.example.azheng.rxjavamvpdemo.contract.MainContract;
import com.example.azheng.rxjavamvpdemo.contract.StationContract;
import com.example.azheng.rxjavamvpdemo.model.MainModel;
import com.example.azheng.rxjavamvpdemo.model.StationModel;
import com.example.azheng.rxjavamvpdemo.net.RxScheduler;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * @author azheng
 * @date 2018/6/4.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public class StationPresenter extends BasePresenter<StationContract.View> implements StationContract.Presenter {

    private StationContract.Model model;

    public StationPresenter() {
        model = new StationModel();
    }

    @Override
    public void getStationInfo(String imei) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        model.getStationInfo(imei)
                .compose(RxScheduler.Obs_io_main())
                .to(mView.bindAutoDispose())//解决内存泄漏
               .subscribe(new Observer<BaseObjectBean<StationBean>>() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {
                       mView.showLoading();
                   }

                   @Override
                   public void onNext(@NonNull BaseObjectBean<StationBean> stationBeanBaseObjectBean) {
                       mView.onSuccess(stationBeanBaseObjectBean);
                   }

                   @Override
                   public void onError(@NonNull Throwable e) {
                       mView.onError(e.getMessage());
                       mView.hideLoading();
                   }

                   @Override
                   public void onComplete() {
                       mView.hideLoading();
                   }
               });

    }
    @Override
    public void getAllStation(){
        if (!isViewAttached()){
            return;
        }
        model.getAllStation()
                .compose(RxScheduler.Obs_io_main())
                .to(mView.bindAutoDispose())
                .subscribe(new Observer<BaseArrayBean<StationBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseArrayBean<StationBean> bean) {
                        mView.onSuccess2(bean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
