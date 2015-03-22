package api.rlabs.com.nytimes.topnews;

import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import api.rlabs.com.nytimes.R;
import api.rlabs.com.nytimes.topnews.parser.TopNewsParser;
import api.rlabs.com.nytimes.utility.UICallBacks;

/**
 * Working with the NYT Api
 */
public class TopNews extends ActionBarActivity implements UICallBacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news);
        setupScreen();

    }

    private void setupScreen() {
        // add list layout
        // add adaptor
        // initialize handler
        // start show the data from the parser


        TopNewsParser tpn = new TopNewsParser((UICallBacks) this);
        tpn.Start(getApplicationContext());
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
    public void updateData(Message msg) {
        //send message to handler for updating the UI
    }
}
