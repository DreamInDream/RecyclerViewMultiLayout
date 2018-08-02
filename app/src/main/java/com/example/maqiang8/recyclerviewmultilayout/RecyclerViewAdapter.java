package com.example.maqiang8.recyclerviewmultilayout;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> picList;
    private List<Map<String, Object>> channelList;
    private List<Integer> girlList;
    private List<Integer> manList;
    private List<String> normalList;
    private final int BANNER_VIEW_TYPE = 0;//轮播图
    private final int CHANNEL_VIEW_TYPE = 1;//频道
    private final int GIRL_VIEW_TYPE = 2;//美女
    private final int NORMAL_VIEW_TYPE = 3;//正常布局
    private final int THREE_VIEW_TYPE = 4;//

    public RecyclerViewAdapter(Context context, List<String> picList, List<Map<String, Object>> channelList,
                           List<Integer> girlList,List<Integer> manList, List<String> normalList) {
        this.context = context;
        this.picList = picList;
        this.channelList = channelList;
        this.girlList = girlList;
        this.normalList = normalList;
        this.manList=manList;
    }

    /**
     * 获取item的类型
     *
     * @param position item的位置
     * @return item的类型
     * 有几种type就回在onCreateViewHolder方法中引入几种布局,也就是创建几个ViewHolder
     */
    @Override
    public int getItemViewType(int position) {
        /*
        区分item类型,返回不同的int类型的值
        在onCreateViewHolder方法中用viewType来创建不同的ViewHolder
         */
        if (position == 0) {//第0个位置是轮播图
            return BANNER_VIEW_TYPE;
        } else if (position == 1) {//第一个是频道布局
            return CHANNEL_VIEW_TYPE;
        } else if (position == 2) {//第2个位置是美女布局
            return GIRL_VIEW_TYPE;
        } else if (position==3){
            return THREE_VIEW_TYPE;
        }else {//其他位置返回正常的布局
            return NORMAL_VIEW_TYPE;
        }
    }

    /**
     * 创建ViewHolder,根据getItemViewType方法里面返回的几种类型来创建
     *
     * @param viewType 就是getItemViewType返回的type
     * @return 返回自己创建的ViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == BANNER_VIEW_TYPE) {//如果viewType是轮播图就去创建轮播图的viewHolder
            view = getView(R.layout.item_banner);
            BannerHolder bannerHolder = new BannerHolder(view);
            return bannerHolder;
        } else if (viewType == CHANNEL_VIEW_TYPE) {//频道的type
            view = getView(R.layout.item_channel);
            return new ChannelHolder(view);
        } else if (viewType == GIRL_VIEW_TYPE) {//美女
            view = getView(R.layout.item_girl);
            return new GirlHolder(view);
        }else if (viewType == THREE_VIEW_TYPE){
            view=getView(R.layout.item_three);
            return new ManHolder(view);
        } else {//正常
            view = getView(R.layout.item_normal);
            return new NormalHolder(view);
        }
    }

    /**
     * 用来引入布局的方法
     */
    private View getView(int view) {
        View view1 = View.inflate(context, view, null);
        return view1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //判断不同的ViewHolder做不同的处理
        if (holder instanceof BannerHolder) {//轮播图
            BannerHolder bannerHolder = (BannerHolder) holder;
            //调用设置轮播图相关方法
            setBanner(bannerHolder);
        } else if (holder instanceof ChannelHolder) {//频道
            ChannelHolder channelHolder = (ChannelHolder) holder;
            //设置频道
            setChannel(channelHolder);
        } else if (holder instanceof GirlHolder) {//美女
            GirlHolder girlHolder = (GirlHolder) holder;
            setGirl(girlHolder);
        } else if (holder instanceof NormalHolder) {//正常布局
            NormalHolder normalHolder = (NormalHolder) holder;
            normalHolder.textView.setText(normalList.get(position - 3));
            normalHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击了" + normalList.get(position - 3), Toast.LENGTH_SHORT).show();
                }
            });
        }else if (holder instanceof ManHolder){
            ManHolder manHolder=(ManHolder)holder;
            setMan(manHolder);
        }

    }
    private void setMan(ManHolder girlHolder){
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,12);
        girlHolder.rv_man.setLayoutManager(gridLayoutManager);
        ManAdapter adapter = new ManAdapter(context, manList);
        girlHolder.rv_man.setAdapter(adapter);

    }
    private void setGirl(GirlHolder girlHolder){
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,12);
        girlHolder.rv_girl.setLayoutManager(gridLayoutManager);
        GirlAdapter adapter = new GirlAdapter(context, girlList);
        girlHolder.rv_girl.setAdapter(adapter);

    }
    /**
     * 设置频道
     *
     * @param channelHolder
     */
    private void setChannel(ChannelHolder channelHolder) {
        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        channelHolder.rv_channel.setLayoutManager(manager);
        ChannelRVAdapter adapter=new ChannelRVAdapter(context,channelList);
        channelHolder.rv_channel.setAdapter(adapter);
    }

    /**
     * 设置轮播图
     *
     * @param bannerHolder
     */
    private void setBanner(final BannerHolder bannerHolder) {
        //设置banner样式
        bannerHolder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        bannerHolder.banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        bannerHolder.banner.setImages(picList);
        //设置banner动画效果
        bannerHolder.banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//            bannerHolder.banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        bannerHolder.banner.isAutoPlay(true);
        bannerHolder.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(context,"click"+position,Toast.LENGTH_SHORT).show();
            }
        });
        //设置轮播时间
//            bannerHolder.banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        bannerHolder.banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        bannerHolder.banner.start();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return normalList.size() + 3;
    }

    /*****************************************下面是为不同的布局创建不同的ViewHolder*******************************************************/
    /**
     * 轮播图的ViewHolder
     */
    public static class BannerHolder extends RecyclerView.ViewHolder {
        Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);

        }
    }

    /**
     * 频道列表的ViewHolder
     */
    public static class ChannelHolder extends RecyclerView.ViewHolder {
        RecyclerView rv_channel;

        public ChannelHolder(View itemView) {
            super(itemView);
            rv_channel =  itemView.findViewById(R.id.rv_channel);

        }
    }

    /**
     * 美女的ViewHolder
     */
    public static class GirlHolder extends RecyclerView.ViewHolder {
        RecyclerView rv_girl;

        public GirlHolder(View itemView) {
            super(itemView);
            rv_girl = itemView.findViewById(R.id.rv_girl);
        }
    }
    public static class ManHolder extends RecyclerView.ViewHolder {
        RecyclerView rv_man;

        public ManHolder(View itemView) {
            super(itemView);
            rv_man = itemView.findViewById(R.id.rv_man);
        }
    }
    /**
     * 正常布局的ViewHolder
     */
    public static class NormalHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public NormalHolder(View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.text);
        }
    }

}