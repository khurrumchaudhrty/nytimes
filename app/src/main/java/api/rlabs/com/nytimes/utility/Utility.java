package api.rlabs.com.nytimes.utility;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by khurrum on 3/20/15.
 */
public class Utility {

    public static File getCacheFolder(Context context){

        if(Constants.CacheFolder==null){

            Constants.CacheFolder = new File(Environment.getExternalStorageDirectory(), "internal");
            if (!Constants.CacheFolder.isDirectory()) {
                Constants.CacheFolder.mkdirs();
            }

           if (!Constants.CacheFolder.isDirectory()) {
                    Constants.CacheFolder = context.getCacheDir(); //get system cache folder
                }
            }
            System.out.println(Constants.CacheFolder);
        return Constants.CacheFolder;
    }
}
