// Generated view binding class for activity_browser.xml
package com.axpr0n.databinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.axpr0n.R;

public class ActivityBrowserBinding implements ViewBinding {
    private final android.widget.FrameLayout rootView;
    public final Button controlBack;
    public final Button controlHome;
    public final Button controlReload;
    public final TextView controlTitle;
    public final ProgressBar loadingIndicator;
    public final WebView webView;

    private ActivityBrowserBinding(android.widget.FrameLayout rootView, Button controlBack, Button controlHome, Button controlReload, TextView controlTitle, ProgressBar loadingIndicator, WebView webView) {
        this.rootView = rootView;
        this.controlBack = controlBack;
        this.controlHome = controlHome;
        this.controlReload = controlReload;
        this.controlTitle = controlTitle;
        this.loadingIndicator = loadingIndicator;
        this.webView = webView;
    }

    @Override
    public android.widget.FrameLayout getRoot() {
        return rootView;
    }

    public static ActivityBrowserBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBrowserBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        android.view.View root = inflater.inflate(R.layout.activity_browser, parent, false);
        if (attachToParent) parent.addView(root);
        return bind(root);
    }

    public static ActivityBrowserBinding bind(android.view.View rootView) {
        Button controlBack = rootView.findViewById(R.id.control_back);
        Button controlHome = rootView.findViewById(R.id.control_home);
        Button controlReload = rootView.findViewById(R.id.control_reload);
        TextView controlTitle = rootView.findViewById(R.id.control_title);
        ProgressBar loadingIndicator = rootView.findViewById(R.id.loading_indicator);
        WebView webView = rootView.findViewById(R.id.web_view);
        return new ActivityBrowserBinding((android.widget.FrameLayout) rootView, controlBack, controlHome, controlReload, controlTitle, loadingIndicator, webView);
    }
}
