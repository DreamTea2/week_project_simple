package com.example.myapplication.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.onItemClickListener;
import com.example.myapplication.fragments.adapter.GridSpaceDecoration;
import com.example.myapplication.fragments.adapter.HomeAdapter;
import com.example.myapplication.fragments.adapter.ImageAdapter;
import com.example.myapplication.fragments.bean.BannerBean;
import com.example.myapplication.fragments.bean.DataBean;
import com.example.myapplication.utils.ScreenUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By shaodong on 2021/8/3 9:25
 */
public class MyRecyclerActivity extends AppCompatActivity {

    private static final String TAG = "MyRecyclerActivity";
    /*标题*/
    private final int TYPE_TITLE = 1;
    /*热门推荐*/
    private final int TYPE_HOT = 2;
    /*订阅视频*/
    private final int TYPE_SUBSCRIPT = 3;
    /*公开课*/
    private final int TYPE_OPEN = 4;
    /*讲师*/
    private final int TYPE_TEACHER = 5;
    /* Banner 数据*/
    private List<BannerBean> bannerData = new ArrayList<>();
    /* 主页列表 数据*/
    private List<Object> homeData = new ArrayList<>();
    private Banner banner;
    private RecyclerView homeRv;
    private ImageAdapter bannerAdapter;
    private HomeAdapter homeAdapter;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MyRecyclerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        banner = findViewById(R.id.banner);
        homeRv = findViewById(R.id.rv);
        bannerAdapter = new ImageAdapter(bannerData, this);
        banner.setAdapter(bannerAdapter);

        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                DataBean data = (DataBean) homeData.get(position);
                switch (data.getItemType()) {
                    case 0:
                    case 1:
                    case 4:
                    case 5:
                        return 4;
                    case 2:
                    case 3:
                        return 2;
                }
                return 0;
            }
        };

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        manager.setSpanSizeLookup(spanSizeLookup);
        homeRv.setLayoutManager(manager);

        homeAdapter = new HomeAdapter(homeData, this, new onItemClickListener<Object>() {
            @Override
            public void setOnItemClickListener(Object o, int position, View view) {

            }
        });
        homeRv.setAdapter(homeAdapter);
        homeRv.addItemDecoration(new GridSpaceDecoration(ScreenUtils.dip2px(MyRecyclerActivity.this, 10)));

        initData();
    }

    public void initData() {
        bannerData.clear();
        bannerData.add(new BannerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnimg.ws.126.net%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F0723%252F4fd583f6j00qwoei0000jc000hs0072g.jpg%26thumbnail%3D650x2147483647%26quality%3D80%26type%3Djpg&refer=http%3A%2F%2Fnimg.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630547894&t=d5002caebd821e88ee7dfb447f261f82"));
        bannerData.add(new BannerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnimg.ws.126.net%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F0723%252F4fd583f6j00qwoei0000jc000hs0072g.jpg%26thumbnail%3D650x2147483647%26quality%3D80%26type%3Djpg&refer=http%3A%2F%2Fnimg.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630547894&t=d5002caebd821e88ee7dfb447f261f82"));
        bannerData.add(new BannerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnimg.ws.126.net%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F0723%252F4fd583f6j00qwoei0000jc000hs0072g.jpg%26thumbnail%3D650x2147483647%26quality%3D80%26type%3Djpg&refer=http%3A%2F%2Fnimg.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630547894&t=d5002caebd821e88ee7dfb447f261f82"));
        bannerData.add(new BannerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnimg.ws.126.net%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F0723%252F4fd583f6j00qwoei0000jc000hs0072g.jpg%26thumbnail%3D650x2147483647%26quality%3D80%26type%3Djpg&refer=http%3A%2F%2Fnimg.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630547894&t=d5002caebd821e88ee7dfb447f261f82"));
        bannerData.add(new BannerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnimg.ws.126.net%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F0723%252F4fd583f6j00qwoei0000jc000hs0072g.jpg%26thumbnail%3D650x2147483647%26quality%3D80%26type%3Djpg&refer=http%3A%2F%2Fnimg.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630547894&t=d5002caebd821e88ee7dfb447f261f82"));
        bannerAdapter.notifyDataSetChanged();

        DataBean.TitleBean titleBean = new DataBean.TitleBean("热门推荐");
        DataBean dataBean = new DataBean(TYPE_TITLE, titleBean);
        homeData.add(dataBean);

        DataBean.HotBean hotBean = new DataBean.HotBean("热门推荐视频");
        DataBean hotData = new DataBean(TYPE_HOT, hotBean);
        homeData.add(hotData);
//        homeData.add(hotData);
//        homeData.add(hotData);
//        homeData.add(hotData);

        DataBean.TitleBean titleBean_1 = new DataBean.TitleBean("订阅视频");
        DataBean dataBean_1 = new DataBean(TYPE_TITLE, titleBean_1);
        homeData.add(dataBean_1);

        DataBean.SubBean hotBean_1 = new DataBean.SubBean("订阅视频Video");
        DataBean hotData_1 = new DataBean(TYPE_SUBSCRIPT, hotBean_1);
        homeData.add(hotData_1);
        homeData.add(hotData_1);
        homeData.add(hotData_1);
        homeData.add(hotData_1);

        DataBean.TitleBean titleBean_2 = new DataBean.TitleBean("公开课");
        DataBean dataBean_2 = new DataBean(TYPE_TITLE, titleBean_2);
        homeData.add(dataBean_2);

        DataBean.OpenBean titleBean_3 = new DataBean.OpenBean("公开课Video");
        DataBean dataBean_3 = new DataBean(TYPE_OPEN, titleBean_3);
        homeData.add(dataBean_3);

        DataBean.TitleBean titleBean_4 = new DataBean.TitleBean("讲师");
        DataBean dataBean_4 = new DataBean(TYPE_TITLE, titleBean_4);
        homeData.add(dataBean_4);

        DataBean.TeachBean titleBean_5 = new DataBean.TeachBean("讲师");
        DataBean dataBean_5 = new DataBean(TYPE_TEACHER, titleBean_5);
        homeData.add(dataBean_5);
        homeData.add(dataBean_5);
        homeData.add(dataBean_5);

        homeAdapter.notifyDataSetChanged();
//        setEmptyManager();
    }

//    private void setEmptyManager(int itemType) {
//        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                DataBean data = (DataBean) homeData.get(position);
//                switch (data.getItemType()) {
//                    case 0:
//                    case 1:
//                    case 4:
//                    case 5:
//                    case 2:
//                        return 4;
//                    case 3:
//                        return 2;
//                }
//                return 0;
//            }
//        };
//        GridLayoutManager manager = new GridLayoutManager(this, 4);
//        manager.setSpanSizeLookup(spanSizeLookup);
//        homeRv.setLayoutManager(manager);
//        homeAdapter.notifyDataSetChanged();
//    }
}
