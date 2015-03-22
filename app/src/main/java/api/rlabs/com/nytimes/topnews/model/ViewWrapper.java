package api.rlabs.com.nytimes.topnews.model;

import android.view.View;
import android.widget.TextView;

import api.rlabs.com.nytimes.R;

/**
 * Created by khurrum on 3/22/15.
 */
public class ViewWrapper {
    TextView section;
    TextView subSection;
    TextView byLine;
    TextView abstractText;

    ViewWrapper(View view){
        section = (TextView) view.findViewById(R.id.section);
        subSection = (TextView) view.findViewById(R.id.subsection);
        byLine = (TextView) view.findViewById(R.id.byline);
        abstractText = (TextView) view.findViewById(R.id.abstracttext);

    }

}
