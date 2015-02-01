package com.inspiron.tharun26.saaral15;

/**
 * Created by tharun26 on 1/2/15.
 */
public class EventItem {


    private String event_title;
    private int icon;

    public EventItem(){}

    public EventItem(String event_title, int icon) {
        this.event_title = event_title;
        this.icon = icon;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}