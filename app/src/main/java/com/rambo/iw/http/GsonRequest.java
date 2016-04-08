package com.rambo.iw.http;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-07
 */
public class GsonRequest<T> extends Request<T> {
    private Gson mGson;
    private Class<T> mClazz;
    private Response.Listener<T> mListener;
    private Map<String, String> mParams;

    //get
    public GsonRequest(String url, Response.Listener listener, Response.ErrorListener errorListener, Class<T> clazz) {
        this(Method.GET, url, listener, errorListener, clazz);
    }

    public GsonRequest(int method, String url, Response.Listener listener, Response.ErrorListener errorListener, Class<T> clazz) {
        this(method, url, null, listener, errorListener, clazz);
    }

    //带参数
    public GsonRequest(int method, String url, Map<String, String> params, Response.Listener listener, Response.ErrorListener errorListener, Class<T> clazz) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.mClazz = clazz;
        this.mParams = params;
        mGson = new Gson();
    }

    @Override
    public Map<String, String> getParams() {
        return mParams;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String responseMsg = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            return Response.success(mGson.fromJson(responseMsg, this.mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(T response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }
}
