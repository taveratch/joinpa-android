package ske.joinpa.joinpa.ui.views;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ske.joinpa.joinpa.R;
import ske.joinpa.joinpa.models.Event;
import ske.joinpa.joinpa.ui.adapters.EventUserListPagerAdapter;

public class WhoComingActivity extends FragmentActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.btn_joined)
    Button btnJoined;

    @BindView(R.id.btn_pending)
    Button btnPending;

    @BindView(R.id.btn_declined)
    Button btnDeclined;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private final int PAGE_COUNT = 3;

    private PagerAdapter adapter;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_coming);
        ButterKnife.bind(this);
        event = (Event)getIntent().getSerializableExtra("event");
        initComponents();
    }

    public void initComponents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter = new EventUserListPagerAdapter(getSupportFragmentManager(),PAGE_COUNT,event);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        showJoinedList();
                        break;
                    case 1:
                        showPendingList();
                        break;
                    case 2:
                        showDeclinedList();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.btn_joined)
    public void showJoinedList() {
        viewPager.setCurrentItem(0);
        btnJoined.setBackgroundResource(R.drawable.rounded_button_white);
        btnPending.setBackgroundResource(R.drawable.rounded_button_white_alpha45);
        btnDeclined.setBackgroundResource(R.drawable.rounded_button_white_alpha45);
    }

    @OnClick(R.id.btn_pending)
    public void showPendingList() {
        viewPager.setCurrentItem(1);
        btnJoined.setBackgroundResource(R.drawable.rounded_button_white_alpha45);
        btnPending.setBackgroundResource(R.drawable.rounded_button_white);
        btnDeclined.setBackgroundResource(R.drawable.rounded_button_white_alpha45);
    }

    @OnClick(R.id.btn_declined)
    public void showDeclinedList() {
        viewPager.setCurrentItem(2);
        btnJoined.setBackgroundResource(R.drawable.rounded_button_white_alpha45);
        btnPending.setBackgroundResource(R.drawable.rounded_button_white_alpha45);
        btnDeclined.setBackgroundResource(R.drawable.rounded_button_white);
    }

}
