package com.example.maqiang8.recyclerviewmultilayout;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    PullToRefreshRecyclerView pullToRefreshRecyclerView;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("l", "onCreate: ");
        setContentView(R.layout.activity_main);
        pullToRefreshRecyclerView=findViewById(R.id.recyclerview);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                pullToRefreshRecyclerView.onRefreshComplete();
                int a=0;
                int b=0;
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                pullToRefreshRecyclerView.onRefreshComplete();
                pullToRefreshRecyclerView.setIsComplete(true);
                int a=9;
            }
        });
        rv=pullToRefreshRecyclerView.getRefreshableView();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        ArrayList<String> picUrl=new ArrayList<>();
        //String s="https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8D%A1%E9%80%9A%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=16&spn=0&di=15780526960&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=743977388%2C2480247905&os=4096312630%2C591824346&simid=4134185220%2C664180285&adpicid=0&lpn=0&ln=1936&fr=&fmq=1533113315764_R&fm=rs5&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Ffe.topitme.com%2Fe%2F13%2F41%2F11315694278344113eo.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bp5rtp_z%26e3B4jAzdH3Fwsk74AzdH3F8bc9ln9AzdH3Ftpj4AzdH3F99mb0ncc&gsm=0&rpstart=0&rpnum=0&islist=&querylist=";
        String s=getResourcesUri(R.mipmap.ic_launcher);
        for (int i=0;i<4;i++){
            picUrl.add(s);
        }
         List<Map<String, Object>> channelList=new ArrayList<Map<String, Object>>();
        for (int i=0;i<10;i++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("pic",s);
            map.put("title","频道"+i);
            channelList.add(map);
        }
        ArrayList<Integer> girlList=new ArrayList<>();
        for (int i=0;i<10;i++){
            int path=R.mipmap.ic_launcher;
            girlList.add(path);
        }
        ArrayList<Integer> manList=new ArrayList<>();
        for (int i=0;i<10;i++){
            int path=R.mipmap.ic_launcher;
            manList.add(path);
        }
        ArrayList<String> normalList=new ArrayList<>();
        for (int i=0;i<10;i++){
            String ss="normal"+i;
            normalList.add(ss);
        }
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,picUrl,channelList,girlList,manList,normalList);
        rv.setAdapter(adapter);
    }

    private String getResourcesUri(@DrawableRes int id) {
        Resources resources = getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        return uriPath;
    }
}
