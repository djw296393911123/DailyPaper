package com.djw.dailypaper.util;

import com.djw.dailypaper.base.BaseRequest;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/18.
 */

public class RxHelper {

    public static <T> Observable.Transformer<BaseRequest<T>, T> processData() {
        return new Observable.Transformer<BaseRequest<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseRequest<T>> baseRequestObservable) {
                return baseRequestObservable.flatMap(new Func1<BaseRequest<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(final BaseRequest<T> tBaseRequest) {
                        return Observable.create(new Observable.OnSubscribe<T>() {
                            @Override
                            public void call(Subscriber<? super T> subscriber) {
                                if (tBaseRequest.getCode() == 200) {
                                    subscriber.onNext(tBaseRequest.getNewslist());
                                    subscriber.onCompleted();
                                } else {
                                    subscriber.onError(new ApiException(tBaseRequest.getMsg()));
                                }
                            }
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                    }
                });
            }
        };
    }
}
