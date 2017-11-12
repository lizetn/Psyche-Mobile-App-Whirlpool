package asu.whirlpool.psychewhirlpool.timeline;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * This class represents an individual node on the timeline.
 * @author  Erick Ramirez Cordero
 * @date    11/10/2017
 */

public class TimeNode
{
    private String title;
    private String info;
    private Paint nodePaint;
    private Paint textPaint;
    private final int TEXT_SIZE = 20;

    public TimeNode()
    {
        this("New Node", "No info available");
    }

    public TimeNode(String title, String info)
    {
        this.title = title;
        this.info = info;

        nodePaint = new Paint();
        nodePaint.setColor(Color.CYAN);
        nodePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * When called by {@link TimelineManager}, the node will be drawn as a
     * circle with a title.
     * @param canvas
     * @param x
     * @param y
     * @param radius
     */
    protected void drawNode(Canvas canvas, int x, int y, int radius)
    {
        canvas.drawText(title, x, y, textPaint);
        canvas.drawCircle(x, y, radius, nodePaint);
    }

    protected void setNodeColor(int color)
    {
        nodePaint.setColor(color);
    }
}
