package asu.whirlpool.psychewhirlpool.timeline;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * The TimelineManager class uses {@link TimeNode} to compose the timeline displayed to the User.
 *
 * @author  Erick Ramirez Cordero
 * @date    11/10/2017
 */

public class TimelineManager
{
    ArrayList<TimeNode> timeNodes;
    TimeNode firstNode;
    TimeNode secondNode;

    public TimelineManager()
    {
        // TODO: Remove these nodes when done testing
        firstNode = new TimeNode();
        secondNode = new TimeNode();

        timeNodes = new ArrayList<TimeNode>();
        timeNodes.add(firstNode);
        timeNodes.add(secondNode);
    }

    /**
     * When called, the manager will draw all TimeNodes.
     * NOTE: This method is currently hard-coded to draw two nodes.
     *
     * @param canvas
     */
    protected void drawTimeline(Canvas canvas)
    {
        int x = canvas.getWidth() / 2;
        int y = canvas.getHeight() / 2;
        int radius = y / 4;

        for(int index = 0; index < timeNodes.size(); index++)
        {
            timeNodes.get(index).drawNode(canvas, x, y, radius);
            x /= 2;
            y /= 2;
        }
    }
}
