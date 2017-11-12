package asu.whirlpool.psychewhirlpool.timeline;

import android.graphics.Canvas;
import java.util.ArrayList;

/**
 * The TimelineManager class uses {@link TimeNode} to compose the timeline displayed to the User.
 * Since the timeline will be sectioned off into the different Psyche mission phases, this class
 * will be in charge of drawing the section chosen by the User.
 *
 * @author  Erick Ramirez Cordero
 * @date    11/10/2017
 */

public class TimelineManager
{
    ArrayList<TimeNode> timeNodes;
    private final int NODE_TOTAL = 3;

    public TimelineManager()
    {
        timeNodes = new ArrayList<TimeNode>();

        for (int index = 0; index < NODE_TOTAL; index++)
        {
            timeNodes.add(new TimeNode());
        }
    }

    /**
     * When called, the manager will draw all TimeNodes.
     * NOTE: This draw method is currently hard-coded
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
            y /= 2;
        }
    }

    protected void setTimeColor(int color)
    {
        for (int index = 0; index < NODE_TOTAL; index++)
        {
            timeNodes.get(index).setNodeColor(color);
        }
    }
}
