package com.weibo.meishijie.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Administrator on 2017/12/29.
 */

public interface BaseView {
    <T> LifecycleTransformer<T> bindLifecycle();
}