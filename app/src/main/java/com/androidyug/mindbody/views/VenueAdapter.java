package com.androidyug.mindbody.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidyug.mindbody.R;
import com.androidyug.mindbody.model.venuelist.Group;
import com.androidyug.mindbody.model.venuelist.Item;
import com.androidyug.mindbody.model.venuelist.Location;
import com.androidyug.mindbody.model.venuelist.Venue;
import com.androidyug.mindbody.utils.FontsFactory;
import com.androidyug.mindbody.utils.Utils;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by IAMONE on 2/17/2016.
 */
public class VenueAdapter extends BaseAdapter {

    List<Item> mVenueList;
    Context ctx;

    VenueAdapter(Context context, List<Item> venuList) {
        this.ctx = context;
        this.mVenueList = venuList;
    }


    @Override
    public int getCount() {
        return mVenueList.size();
    }

    @Override
    public Object getItem(int position) {
        return mVenueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.row_venue_list, parent, false);

            vh = new ViewHolder(convertView, ctx);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }





        Venue venue = ((Item)getItem(position)).getVenue();
        Location location = venue.getLocation();
        vh.tvVenuName.setText(venue.getName());
        if (location.getAddress()!=null){
            vh.tvAddress.setText(location.getAddress());
        } else {
            vh.tvAddress.setVisibility(View.INVISIBLE);
        }

        double distance = Utils.toKm(location.getDistance());
        vh.tvDistance.setText(String.valueOf(distance) + " km");
        if (venue.getRating()!=null) {
            vh.tvRating.setText("" + venue.getRating());
        } else {
            vh.tvRating.setText("--");
        }


        return convertView;

    }

    static class ViewHolder {
        @Bind(R.id.tv_venue_name)
        TextView tvVenuName;

        @Bind(R.id.tv_rating)
        TextView tvRating;

        @Bind(R.id.tv_address)
        TextView tvAddress;

        @Bind(R.id.tv_distance)
        TextView tvDistance;

        ViewHolder(View view, Context c){
            ButterKnife.bind(this, view);


            // font style for textviews
            tvVenuName.setTypeface(FontsFactory.robotoCondensedBold(c));
            tvRating.setTypeface(FontsFactory.robotoCondensedBold(c));
            tvAddress.setTypeface(FontsFactory.robotoLight(c));
            tvDistance.setTypeface(FontsFactory.robotoLight(c));
        }
    }
}
