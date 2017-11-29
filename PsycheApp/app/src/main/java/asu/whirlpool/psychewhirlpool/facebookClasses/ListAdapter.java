package asu.whirlpool.psychewhirlpool.facebookClasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.facebookClasses.FacebookfeedList;

/**
 * Created by jperez60 on 11/27/2017.
 * Edited by nfleisc on 11.28.2017
 */
/**
 *   Custom base adapter used to set UI components, uses fbFrame to make UI look similar
 *   to typical facebook posts
 *
 */
public class ListAdapter extends BaseAdapter
{
    Context contexts;
    ArrayList<FacebookfeedList> fbList;
    public ListAdapter(Context contexts, ArrayList<FacebookfeedList> fbList)
    {

        this.contexts = contexts;
        this.fbList = fbList;
    }
    @Override
    public int getCount()
    {
        return fbList.size();
    }

    @Override
    public Object getItem(int i) 
    {
        return fbList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return fbList.indexOf(getItem(i));
    }

    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = LayoutInflater.from(contexts).inflate(R.layout.fbframe,viewGroup,false);
        TextView ids,msg,story,times;
        ImageView pic;
        //ids = (TextView) v.findViewById(R.id.ids);
        msg = (TextView) v.findViewById(R.id.msg);
        story = (TextView) v.findViewById(R.id.story);
        times = (TextView) v.findViewById(R.id.times);
        pic = (ImageView) v.findViewById(R.id.pic);

        if(!fbList.get(i).getId().isEmpty()) 
        {

        }
        if(!fbList.get(i).getMessage().isEmpty())
        {
            msg.setText(fbList.get(i).getMessage());
        }
        if(!(fbList.get(i).getStory().isEmpty()) || !(fbList.get(i).getStory() == ""))
        {

            story.setText(fbList.get(i).getStory());
        }
        else
        {
            story.setText("NASA Psyche Mission");
        }
        if(!fbList.get(i).getTime().isEmpty())
        {
            String facebookTime = fbList.get(i).getTime();
            String wordMonth = "";
            Calendar calendar = toCalendar(facebookTime);
            switch (String.valueOf(calendar.get(Calendar.MONTH)))
            {
                case "1":
                    wordMonth = "January";
                case "2":
                    wordMonth = "February";
                case "3":
                    wordMonth = "March";
                case "4":
                    wordMonth = "April";
                case "5":
                    wordMonth = "May";
                case "6":
                    wordMonth = "June";
                case "7":
                    wordMonth = "July";
                case "8":
                    wordMonth = "August";
                case "9":
                    wordMonth = "September";
                case "10":
                    wordMonth = "October";
                case "11":
                    wordMonth = "November";
                case "12":
                    wordMonth = "December";
                default:
                    wordMonth = calendar.get(Calendar.MONTH) + "";
                    Log.d("CALENDAR", String.valueOf(calendar.get(Calendar.MONTH)));
            }
            String textToDisplay = wordMonth + " " +
                    calendar.get(Calendar.DAY_OF_MONTH) + " at " +
                    calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) +
                    calendar.get(Calendar.AM_PM);

            times.setText(textToDisplay);
        }
        if(!fbList.get(i).getPicture().isEmpty())
        {
            //Glide used for the image
            Glide.with(contexts).load(fbList.get(i).getPicture()).into(pic);
        }
        else
        {
            pic.setVisibility(View.GONE);
        }
        return v;
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
}
