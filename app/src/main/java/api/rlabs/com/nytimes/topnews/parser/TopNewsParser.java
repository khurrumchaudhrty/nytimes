package api.rlabs.com.nytimes.topnews.parser;



import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import api.rlabs.com.nytimes.controller.CallBack;
import api.rlabs.com.nytimes.controller.NetworkController;
import api.rlabs.com.nytimes.topnews.model.TopNews;
import api.rlabs.com.nytimes.topnews.model.Results;
import api.rlabs.com.nytimes.utility.Constants;
import api.rlabs.com.nytimes.utility.Source;
import api.rlabs.com.nytimes.topnews.UICallBacks;
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

                TopNewsParser.this.processResponse(null,200,Source.FILE);

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
        System.out.print(source.toString());
        TopNews localtpNews = new TopNews();
        //we can use status code here to determine if we need to proceed or not for now ignoring it altogether

        List<Results> data = new ArrayList<Results>();
        int size = 0;
        String lastReadTimeStamp = null;

        try {
            if(statusCode==200){
                if(source.equals(Source.INTERNET)){
                    dumpFile(content);
                }
                //loadFromCACHE if it exists
                content = new FileInputStream(new File(Utility.getCacheFolder((Context)context) +Constants.fileName));

            }

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
            if(content!=null)
                try {
                    content.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            //dumpfile only for the valid one at the end
            if(context!=null){
                context.updateData(data);
                //update UI
            }

        }

    }

    private void dumpFile(InputStream content) {
        //Save the file
        OutputStream os = null;

        try {

            os = new FileOutputStream(Utility.getCacheFolder((Context) context) + Constants.fileName);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = content.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                content.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
