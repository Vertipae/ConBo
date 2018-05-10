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

        // Initializing contact helper and then passing it on to fragments
        // Added fragments to adapter
        ContactHelper ch = new ContactHelper(this);
        FragmentContact fc = new FragmentContact();
        fc.initializeContactHelper(ch);
        mAdapter.AddFragment(fc,"Contact");
        FragmentAdd fa = new FragmentAdd();
        fa.initializeContactHelper(ch);
        mAdapter.AddFragment(fa,"Add");
        mAdapter.AddFragment(new FragmentAbout(),"About");
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        // Adding icons to tabLayout

        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_person);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_person_add);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_info);


        //Remove Shadow From the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }
}
