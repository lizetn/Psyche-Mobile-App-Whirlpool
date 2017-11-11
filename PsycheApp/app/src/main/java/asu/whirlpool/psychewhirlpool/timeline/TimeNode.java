package asu.whirlpool.psychewhirlpool.timeline;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Erick Ramirez Cordero on 11/10/2017.
 *
 * This class represents an individual node on the timeline
 */

public class TimeNode
{
    private String title;
    private String info;
    Paint paint;

    public TimeNode()
    {
        title = "New Node";
        info = "No info available";
        paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public TimeNode(String title, String info)
    {
        this.title = title;
        this.info = info;
    }

    protected void drawNode(Canvas canvas, int x, int y, int radius)
    {
        canvas.drawCircle(x, y, radius, paint);
    }
}
