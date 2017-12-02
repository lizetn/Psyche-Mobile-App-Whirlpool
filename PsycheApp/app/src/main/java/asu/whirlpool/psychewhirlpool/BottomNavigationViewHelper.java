package asu.whirlpool.psychewhirlpool;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import java.lang.reflect.Field;
import android.util.Log;

/**
 * This helper class removes the animation effect from the bottom navigation view.
 * Created by Natalie on 12/1/2017.
 */

public class BottomNavigationViewHelper {
    @SuppressLint("RestrictedApi")
    public static void disableAnimation(BottomNavigationView navigation) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        try {
            Field animation = menuView.getClass().getDeclaredField("mShiftingMode");
            animation.setAccessible(true);
            animation.setBoolean(menuView, false);
            animation.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }

        } catch (NoSuchFieldException e1) {
            Log.e("BottomNavHelper","Caught no such field exception.");
        } catch (IllegalAccessException e2) {
            Log.e("BottomNavHelper","Illegal access exception.");
        }
    }
}

