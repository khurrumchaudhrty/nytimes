package api.rlabs.com.nytimes.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;

import api.rlabs.com.nytimes.utility.Source;
import bolts.Task;

/**
 * Created by khurrum on 3/20/15.
 */
public class NetworkController {

    private static NetworkController controller = null;

    public static NetworkController getController() {
        if(controller==null){
            controller = new NetworkController();
        }
        return controller;
    }

    private NetworkController(){}

    public void startDownloading(CallBack parser) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet hc = new HttpGet();
        HttpContext context = new BasicHttpContext();
        InputStream streamContent = null;
        int respCode = 0;
        try {

            hc.setURI(new URI(parser.getUrl()));
            HttpResponse response = httpClient.execute(hc, context);
            if (response == null || response.getStatusLine() == null) {
                respCode = 200;
            } else
                respCode = response.getStatusLine().getStatusCode();
            streamContent = response.getEntity().getContent();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            parser.processResponse(streamContent,respCode, Source.INTERNET);
            if (streamContent != null) {
                try {
                    streamContent.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
