package asu.whirlpool.psychewhirlpool.facebookClasses;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import asu.whirlpool.psychewhirlpool.R;

/**
 * Created by jperez60 on 11/27/2017.
 * Edited by nfleisc on 11.29.2017
 */

/**
 *   Custom base adapter used to set UI components, uses fbFrame to make UI look similar
 *   to typical facebook posts
 *
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.myholder>
{
    Context contexts;
    public static ArrayList<FacebookfeedList> fbList;
    int w;
    public ListAdapter(Context contexts, ArrayList<FacebookfeedList> fbList)
    {

        this.contexts = contexts;
        this.fbList = fbList;
    }
    public Object getItem(int i)
    {
        return fbList.get(i);
    }
    @Override
    public long getItemId(int i)
    {
        return fbList.indexOf(getItem(i));
    }

    @Override
    public int getItemCount() {
        return fbList.size();
    }

    @Override
    public ListAdapter.myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(contexts);
        View v = inf.inflate(R.layout.fbframe,parent,false);
        myholder m = new myholder(v);
        return m;
    }

    @Override
    public void onBindViewHolder(ListAdapter.myholder holder, final int position)
    {


        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            holder.divider.setBackgroundColor(Color.parseColor("#302144"));

        }
        else
            holder.divider.setBackgroundColor(Color.parseColor("#f9a000"));

        if(!fbList.get(position).getId().isEmpty())
        {

        }
        if(!fbList.get(position).getMessage().isEmpty())
        {

            holder.msg.setText(fbList.get(position).getMessage());
        }
        if(!(fbList.get(position).getStory().isEmpty()) || !(fbList.get(position).getStory() == ""))
        {

            holder.story.setText(fbList.get(position).getStory());
        }
        else
        {
            holder.story.setText("NASA Psyche Mission");
        }
        if(!fbList.get(position).getTime().isEmpty())
        {
            String facebookTime = fbList.get(position).getTime();
            Calendar calendar = toCalendar(facebookTime);
            Calendar currentCal = Calendar.getInstance();
            String monthName = monthName(calendar);
            String amOrPm;
            if (calendar.get(Calendar.AM_PM) == 1)
                amOrPm = "pm";
            else
                amOrPm = "am";

            String textToDisplay = monthName + " " +
                    calendar.get(Calendar.DAY_OF_MONTH) + " at " +
                    calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) +
                    amOrPm;

            if (currentCal.get(Calendar.YEAR) < calendar.get(Calendar.YEAR)) {
                textToDisplay = textToDisplay + ", " + calendar.get(Calendar.YEAR);
            }

            holder.times.setText(textToDisplay);
        }
        if(!fbList.get(position).getPicture().isEmpty())
        {
            Glide.with(contexts).load(fbList.get(position).getPicture()).fitCenter().into(holder.pic);
            holder.pic.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    int pos = position;
                    Intent in = new Intent(contexts, Display2.class);
                    in.putExtra("Pos", pos);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    contexts.startActivity(in);
                }
            });
        }
        else
        {
            holder.pic.setVisibility(View.GONE);
        }
        holder.profilePic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://www.facebook.com/NASAPsyche/");
                Intent in = new Intent(Intent.ACTION_VIEW, uri);
                contexts.startActivity(in);
            }
        });
    }
    /**
     * Takes in the time format received from facebook and returns a calendar object in
     * the default time zone.
     * @param facebookTime
     * @return calendar
     */
    public Calendar toCalendar(String facebookTime) {
        TimeZone currentZone = TimeZone.getDefault();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        formatter.setLenient(false);
        Date date;
        Calendar calendar = GregorianCalendar.getInstance();

        try {
            date = formatter.parse(facebookTime);
            calendar.setTime(date);
            calendar.setTimeZone(currentZone);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    public String monthName(Calendar calendar) {
        String wordMonth;

        switch (calendar.get(Calendar.MONTH))
        {
            case 0:
                wordMonth = "January";
                break;
            case 1:
                wordMonth = "February";
                break;
            case 2:
                wordMonth = "March";
                break;
            case 3:
                wordMonth = "April";
                break;
            case 4:
                wordMonth = "May";
                break;
            case 5:
                wordMonth = "June";
                break;
            case 6:
                wordMonth = "July";
                break;
            case 7:
                wordMonth = "August";
                break;
            case 8:
                wordMonth = "September";
                break;
            case 9:
                wordMonth = "October";
                break;
            case 10:
                wordMonth = "November";
                break;
            case 11:
                wordMonth = "December";
                break;
            default:
                wordMonth = String.valueOf(calendar.get(Calendar.MONTH));
        }

        return wordMonth;
    }
    class myholder extends RecyclerView.ViewHolder
    {
        TextView ids,msg,story,times;
        ImageView pic;
        ImageView profilePic;
        int w;
        RelativeLayout rlayout;
        RelativeLayout divider;
        public myholder(View itemView)
        {
            super(itemView);
            rlayout = (RelativeLayout) itemView.findViewById(R.id.rlayout);
            w = rlayout.getWidth();
            msg = (TextView) itemView.findViewById(R.id.msg);
            story = (TextView) itemView.findViewById(R.id.story);
            times = (TextView) itemView.findViewById(R.id.times);
            pic = (ImageView) itemView.findViewById(R.id.pic);
            profilePic =   (ImageView) itemView.findViewById(R.id.profilePic);
            divider = (RelativeLayout) itemView.findViewById(R.id.fbdivider);


        }
    }
}
