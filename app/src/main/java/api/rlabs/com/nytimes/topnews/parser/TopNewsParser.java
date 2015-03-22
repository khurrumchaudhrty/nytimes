package api.rlabs.com.nytimes.topnews.parser;



import android.app.Application;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import api.rlabs.com.nytimes.controller.CallBack;
import api.rlabs.com.nytimes.controller.NetworkController;
import api.rlabs.com.nytimes.topnews.model.TopNews;
import api.rlabs.com.nytimes.topnews.model.Results;
import api.rlabs.com.nytimes.utility.Constants;
import api.rlabs.com.nytimes.utility.Source;
import api.rlabs.com.nytimes.utility.UICallBacks;
import api.rlabs.com.nytimes.utility.Utility;

/**
 * Created by khurrum on 3/20/15.
 * show the data if it exists in file and also
 * schedule the latest download, while would be updated in file and rendered again in UI
 */
public class TopNewsParser implements CallBack{

    UICallBacks context = null;

    TopNews tpNews = new TopNews();

    public TopNewsParser(UICallBacks context){
        this.context = context;
    }

    public void Start(final Context appcontext){
        new Thread(){
            public void run(){

                Utility.getCacheFolder(appcontext);
                //if file exists in check
                // if not schedule a download;
                NetworkController nwct = NetworkController.getController();
                nwct.startDownloading(TopNewsParser.this);
            }
        }.start();
    }


    @Override
    public String getUrl() {
        return Constants.TOP_NEWS_ENDPOINT;
    }

    /**
     * two processing happeneing from old data and network data
     * making it synchronized.
     *
     * dont want user to wait for slow data connection ,
     * and we can just show the old data from file,
     * while also schedulling a request for new one
     *
     * @param Content
     * @param statusCode
     */
    @Override
    public synchronized void processResponse(InputStream content, int statusCode, Source source) {
        TopNews localtpNews = new TopNews();
        //we can use status code here to determine if we need to proceed or not for now ignoring it altogether

        List<Results> data = new ArrayList<Results>();
        int size = 0;
        String lastReadTimeStamp = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            StringBuilder builder = new StringBuilder();
            String fulljson = "";
            String inputline = null;
            while((inputline = reader.readLine())!=null){
                fulljson+=inputline;
            }

            JSONObject jsonObject = new JSONObject(fulljson);

            localtpNews.setNum_results(jsonObject.getInt("num_results"));
            localtpNews.setLast_updated(jsonObject.getString("last_updated"));

            JSONArray results = jsonObject.getJSONArray("results");

            for(int i=0;i< results.length();i++){
                Results resultModel = new Results();
                JSONObject result  = results.getJSONObject(i);
                resultModel.setAbstract(result.getString("abstract"));
                resultModel.setByline(result.getString("byline"));
                resultModel.setSection(result.getString("section"));
                resultModel.setSubsection(result.getString("subsection"));
                resultModel.setTitle(result.getString("title"));
                resultModel.setUrl(result.getString("url"));
                resultModel.setMaterial_type_facet(result.getString("material_type_facet"));
                data.add(resultModel);
            }
            localtpNews.setResults(data);
            tpNews = localtpNews;

            //parse the document
        }catch (Exception exception){
            exception.printStackTrace();
        }finally{
            //dumpfile only for the valid one at the end
            if(source.equals(Source.INTERNET)){
                //Save the file
            }

            if(context!=null){
                //update UI
            }
        }

    }


}
