// Generated view binding class for activity_main.xml
package com.axpr0n.databinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.axpr0n.R;

public class ActivityMainBinding implements ViewBinding {
    private final androidx.constraintlayout.widget.ConstraintLayout rootView;
    public final RecyclerView gridRecyclerView;
    public final TextView homeTitle;

    private ActivityMainBinding(androidx.constraintlayout.widget.ConstraintLayout rootView, RecyclerView gridRecyclerView, TextView homeTitle) {
        this.rootView = rootView;
        this.gridRecyclerView = gridRecyclerView;
        this.homeTitle = homeTitle;
    }

    @Override
    public androidx.constraintlayout.widget.ConstraintLayout getRoot() {
        return rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        android.view.View root = inflater.inflate(R.layout.activity_main, parent, false);
        if (attachToParent) parent.addView(root);
        return bind(root);
    }

    public static ActivityMainBinding bind(android.view.View rootView) {
        RecyclerView gridRecyclerView = rootView.findViewById(R.id.grid_recycler_view);
        TextView homeTitle = rootView.findViewById(R.id.home_title);
        return new ActivityMainBinding((androidx.constraintlayout.widget.ConstraintLayout) rootView, gridRecyclerView, homeTitle);
    }
}
