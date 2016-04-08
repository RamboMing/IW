package com.rambo.iw.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import okhttp3.OkHttpClient;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-07
 */
public class HttpManager {
    private static HttpManager mInstance;
    private Context mContext;
    private RequestQueue mQueue;

    private HttpManager(Context context) {
        this.mContext = context;
        mQueue = Volley.newRequestQueue(context, new OkHttp3Stack(new OkHttpClient()));
    }

    public static HttpManager getInstance(Context context) {
        if (null == mInstance) {
            synchronized (HttpManager.class) {
                if (null == mInstance) {
                    mInstance = new HttpManager(context);
                }
            }
        }
        return mInstance;
    }

    public <T> void addRequest(Request<T> request, Object tag) {
        request.setTag(tag);
        if (mQueue != null) {
            mQueue.add(request);
        }
    }

    public <T> void addRequest(Request<T> request) {
        addRequest(request, this);
    }

    public void cancelRequest(Object tag) {
        if (mQueue != null) {
            mQueue.cancelAll(tag);
        }
    }
}
