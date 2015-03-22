package api.rlabs.com.nytimes.topnews.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import api.rlabs.com.nytimes.R;

/**
 * Created by khurrum on 3/22/15.
 */
public class FeedAdaptor extends BaseAdapter {

    private List<Results> data = new ArrayList<Results>();
    private Context context;
    private LayoutInflater inflater;

    public FeedAdaptor(Context applicationContext, List<Results> data) {
        super();
        this.context = applicationContext;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Results getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(R.layout.rowview, parent, false);
            ViewWrapper wrapper = new ViewWrapper(convertView);
            convertView.setTag(wrapper);
        }
        ViewWrapper wrapper = (ViewWrapper) convertView.getTag();
        wrapper.abstractText.setText(getItem(position).getAbstract());
        wrapper.section.setText(getItem(position).getSection());
        wrapper.subSection.setText(getItem(position).getSubsection());
        wrapper.byLine.setText(getItem(position).getByline());

        return convertView;
    }
}
