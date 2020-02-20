package com.osama.movieshow.utils;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class RecyclerViewUtils {

    private static final String TAG = RecyclerViewUtils.class.getSimpleName();

    public void setupLinearHorizontalRecView(RecyclerView recyclerView, Context context) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setupLinearVerticalRecView(RecyclerView recyclerView, Context context) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setupStaggeredGridRecView(RecyclerView recyclerView, Context context) {
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    public void setupGridRecView(RecyclerView recyclerView, Context context, int spanCount) {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public void setupChatRecyclerView(Context context, RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

//    public void setupViewPager(final Context context, ViewPagerAdapter viewPagerAdapter, final TabLayout tabLayout, ViewPager viewPager, Fragment fragments[], final String fragNames[], int currentItemIndex) {
//        try {
//
//            for (Fragment fragment : fragments) {
//                viewPagerAdapter.addFragment(fragment);
//            }
//
//            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//                @Override
//                public void onTabSelected(TabLayout.Tab tab) {
//                    try {
//                        tab.getCustomView().animate().alpha(1).setDuration(300).setInterpolator(new AccelerateInterpolator(3));
//                    } catch (Exception e) {
//                        Log.e("tabLayout", e.toString());
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onTabUnselected(TabLayout.Tab tab) {
//                    try {
//                        tab.getCustomView().animate().alpha(0.9f).setDuration(100).setInterpolator(new DecelerateInterpolator(2));
//                    } catch (Exception e) {
//                        Log.e("tabLayout", e.toString());
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onTabReselected(TabLayout.Tab tab) {
//
//                }
//            });
//
//            tabLayout.post(new Runnable() {
//                @TargetApi(Build.VERSION_CODES.O)
//                @Override
//                public void run() {
//                    for (int i = 0; i < fragNames.length; i++) {
//                        Objects.requireNonNull(tabLayout.getTabAt(i)).setText(fragNames[i]);
//                        View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
//                        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
//                        p.setMargins(2, 2, 2, 2);
//                        tab.requestLayout();
//                    }
//                }
//            });
//            tabLayout.post(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < fragNames.length; i++) {
//                        final View tab = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
//                        TextView tabText = tab.findViewById(R.id.custom_tab_text);
//                        tabText.setText(fragNames[i]);
//                        tabLayout.getTabAt(i).setCustomView(tab);
//                    }
//                }
//            });
//            viewPager.setAdapter(viewPagerAdapter);
//            tabLayout.setupWithViewPager(viewPager);
//            viewPager.setCurrentItem(currentItemIndex, false);
//
//        } catch (Exception e) {
//            Log.d(TAG, "Exception : " + e.toString());
//        }
//    }
}
