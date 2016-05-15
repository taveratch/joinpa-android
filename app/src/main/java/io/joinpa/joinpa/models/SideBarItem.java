package io.joinpa.joinpa.models;

/**
 * Created by TAWEESOFT on 5/15/16 AD.
 */
public class SideBarItem {

    private String title;
    private int icon;

    public SideBarItem(String title,int icon) {
        this.icon = icon;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
