package api.rlabs.com.nytimes.controller;

import java.io.InputStream;

import api.rlabs.com.nytimes.utility.Source;

/**
 * Created by khurrum on 3/20/15.
 */
public interface CallBack {

    public String getUrl();
    public void processResponse(InputStream Content, int statusCode, Source source);

}
