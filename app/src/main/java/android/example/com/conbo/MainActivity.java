package android.example.com.conbo;
// Sallamaarit Jaako 1601459

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Adding fragments
        mAdapter.AddFragment(new FragmentCalls(),"Calls");
        mAdapter.AddFragment(new FragmentContact(),"Contact");
        mAdapter.AddFragment(new FragmentAbout(),"About");
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        // Adding icons to tabLayout
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_call);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_contact);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_check);

        //Remove Shadow From the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }
}
