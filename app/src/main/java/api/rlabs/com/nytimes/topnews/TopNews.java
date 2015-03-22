package api.rlabs.com.nytimes.topnews;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import api.rlabs.com.nytimes.R;
import api.rlabs.com.nytimes.topnews.model.FeedAdaptor;
import api.rlabs.com.nytimes.topnews.model.Results;
import api.rlabs.com.nytimes.topnews.parser.TopNewsParser;

/**
 * Working with the NYT Api
 */
public class TopNews extends ActionBarActivity implements UICallBacks {


    private ListView feedlist;
    private FeedAdaptor feedAdaptor;
    //add some adapter

    private TextView msgText;
    private LinearLayout panel;

    private FeedHandler feedhandler;

    private final static int UPDATELIST = 0;
    private final static int INITIALSETUP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news);
        feedhandler = new FeedHandler();


        setupScreen();

    }

    public void sendMessage(int what){
        feedhandler.sendEmptyMessage(what);
    }

    private void setupScreen() {
        sendMessage(INITIALSETUP);

        // add adaptor
    }

    class FeedHandler extends Handler {
        public synchronized void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATELIST:
                    feedlist.setAdapter(feedAdaptor);
                    if(feedAdaptor.getCount()>0) {
                        feedlist.setVisibility(View.VISIBLE);
                        panel.setVisibility(View.GONE);
                    }else{
                        //write now just relying a message the
                        // scenario could be improved with loading or problem message seperately
                        msgText.setText(R.string.noresults);
                    }

                    break;
                case INITIALSETUP:
                    // add list layout
                    feedlist = (ListView) TopNews.this.findViewById(R.id.feed);
                    msgText = (TextView) TopNews.this.findViewById(R.id.msg);
                    panel = (LinearLayout) TopNews.this.findViewById(R.id.panel);

                    // start show the data from the parser
                    TopNewsParser tpn = new TopNewsParser(TopNews.this);
                    tpn.Start(getApplicationContext());
                    break;

            }
            //this is where we update the content and jump to UI thread BINGO !
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateData(List<Results> data) {
        feedAdaptor = new FeedAdaptor(getApplicationContext(), data);

        sendMessage(UPDATELIST);
    }
}
