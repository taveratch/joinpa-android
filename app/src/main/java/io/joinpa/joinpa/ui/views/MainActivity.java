package io.joinpa.joinpa.ui.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import net.simonvt.menudrawer.MenuDrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.models.Friend;
import io.joinpa.joinpa.models.NotificationReceiver;
import io.joinpa.joinpa.models.SideBarItem;
import io.joinpa.joinpa.ui.adapters.SideBarAdapter;
import io.joinpa.joinpa.ui.dialogs.FriendRequestDialog;
import io.joinpa.joinpa.ui.dialogs.PartialEventDialog;
import io.joinpa.joinpa.ui.dialogs.PartialFriendRequestDialog;
import io.joinpa.joinpa.ui.fragments.ExploreFragment;
import io.joinpa.joinpa.ui.fragments.FriendFragment;
import io.joinpa.joinpa.ui.fragments.InvitesFragment;
import io.joinpa.joinpa.ui.fragments.MyEventFragment;
import io.joinpa.joinpa.ui.fragments.ObservableFragment;
import io.joinpa.joinpa.ui.fragments.RecentEventsFragment;
import io.joinpa.joinpa.util.PermissionUtil;

public class MainActivity extends AppCompatActivity implements Observer {

    private final int SIGNOUT_POSITION = 5;

    @BindView(R.id.lv_sidebar)
    ListView lvSideBar;

    @BindView(R.id.img_avatar)
    ImageView imgAvatar;

    @BindView(R.id.tv_username)
    TextView tvUsername;

    private App app;
    private NotificationReceiver notificationReceiver;
    private MenuDrawer menuDrawer;
    private List<ObservableFragment> fragmentList;
    private List<SideBarItem> sideBarItems;
    private SideBarAdapter sideBarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuDrawer = MenuDrawer.attach(this);
        menuDrawer.setContentView(R.layout.activity_main);
        menuDrawer.setMenuView(R.layout.sidebar);
        ButterKnife.bind(menuDrawer);
        ButterKnife.bind(this);
        app = App.getInstance();
        notificationReceiver = NotificationReceiver.getInstance();
        PermissionUtil.canAccessLocation(this); //check permission for location service
        initComponents();
        checkNotification();
    }

    /**
     * To toggle menu drawer from another fragment
     * @param observable
     * @param o
     */
    @Override
    public void update(Observable observable, Object o) {
        toggleMenu();
    }

    private void initComponents() {
        //Show username in tv_username
        tvUsername.setText(app.getUser().getUsername());
        //Show avatar
        imgAvatar.setImageResource(app.getInternalData().avatarNormal[app.getUser().getAvatar()]);
        //Create explore fragment and add this as observer to toggle left menu drawer.
        fragmentList = new ArrayList<ObservableFragment>();
        fragmentList.add(new ExploreFragment());
        fragmentList.add(new MyEventFragment());
        fragmentList.add(new InvitesFragment());
        fragmentList.add(new RecentEventsFragment());
        fragmentList.add(new FriendFragment());

        for (ObservableFragment fm : fragmentList) fm.addObserver(this);

        sideBarItems = new ArrayList<>();
        sideBarItems.add(new SideBarItem(getString(R.string.explore), R.drawable.sidebar_explore_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.my_events), R.drawable.sidebar_myevent_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.invites), R.drawable.sidebar_invites_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.recent_events), R.drawable.sidebar_recent_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.friends), R.drawable.sidebar_friend_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.sign_out), R.drawable.sidebar_signout_icon));

        sideBarAdapter = new SideBarAdapter(this, R.layout.item_sidebar, sideBarItems);
        lvSideBar.setAdapter(sideBarAdapter);

        switchFragment(0); //Show primary fragment which is Explore
    }

    private void replaceFragment(Fragment fragmnet) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framel_fragments, fragmnet)
                .addToBackStack(null)
                .commit();
    }

    private void switchFragment(int id) {
        // TODO: 5/15/16 AD Add more fragment
        ObservableFragment fm = fragmentList.get(id);
        replaceFragment(fm);
    }

    public void toggleMenu() {
        menuDrawer.toggleMenu(true);
    }

    @OnItemClick(R.id.lv_sidebar)
    public void onItemClicked(int position) {
        if (position == sideBarItems.size() - 1) signOut();
        else {
            sideBarAdapter.setSelectedItem(position);
            switchFragment(position);
            toggleMenu();
        }
    }

    public void signOut() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(getString(R.string.sign_out));
        dialogBuilder.setMessage(getString(R.string.sign_out_dialog_message));
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                app.clear(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        dialogBuilder.setNegativeButton("No", null);
        dialogBuilder.create().show();
    }

    public void checkNotification() {
        if (notificationReceiver.isEventNotified()) {
            // TODO: 6/1/16 AD show event dialog
            Event event = notificationReceiver.getEvent();
            notificationReceiver.setEvent(null);
            PartialEventDialog dialog = new PartialEventDialog(this,event);
            dialog.show();
        }else if (notificationReceiver.isFriendNotified()) {
            Friend friend = notificationReceiver.getFriend();
            notificationReceiver.setFriend(null);
            PartialFriendRequestDialog dialog = new PartialFriendRequestDialog(this,friend);
            dialog.show();
        }
    }


}
