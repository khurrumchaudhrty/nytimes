package api.rlabs.com.nytimes.topnews;

import android.os.Message;

import java.util.List;

import api.rlabs.com.nytimes.topnews.model.Results;

/**
 * Created by khurrum on 3/20/15.
 */
public interface UICallBacks {

    public void updateData(List<Results> data);
}
