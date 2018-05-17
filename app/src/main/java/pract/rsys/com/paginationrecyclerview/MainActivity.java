package pract.rsys.com.paginationrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<MenuBean.Item> listdatal;
    boolean isScroll = false;
    PaginationAdapter paginationAdapter;
    LinearLayoutManager linearLayoutManager;
    int totalItem,visibleItem,shownItem;
    int itemCount=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fillInitialData();
        getResponse();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerviwe);
        linearLayoutManager = new LinearLayoutManager(this);
        listdatal = new ArrayList<>();


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScroll=true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItem = linearLayoutManager.getItemCount();
                shownItem= linearLayoutManager.findFirstVisibleItemPosition();
                visibleItem = linearLayoutManager.getChildCount();

                if(isScroll && (shownItem+visibleItem==totalItem))
                {
                    itemCount++;
                    getResponse();
                }
            }
        });
    }

    public void getResponse()
    {
          RetrofitApiInterface retrofitApiInterface = RetrofitApiClient.getApiClient().create(RetrofitApiInterface.class);
          Call<MenuBean> menuBeanCall = retrofitApiInterface.getMenuItems(itemCount+"");
          menuBeanCall.enqueue(new Callback<MenuBean>() {
              @Override
              public void onResponse(Call<MenuBean> call, Response<MenuBean> response) {

                 List<MenuBean.Item> menuItemListdatal = response.body().getItemList();
                  listdatal.addAll(menuItemListdatal);
                  if(itemCount==1)
                     inflateList();
                  else
                      fetchNewdata();
              }

              @Override
              public void onFailure(Call<MenuBean> call, Throwable t) {

                  Toast.makeText(MainActivity.this,"Hell9o",Toast.LENGTH_SHORT).show();
              }
          });
    }



    void inflateList()
    {
        paginationAdapter   = new PaginationAdapter(this,listdatal);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(paginationAdapter);
        fetchNewdata();
    }




   /* void fillInitialData(){

        String arraydata[]  = {"112","56","67","546","564","345","34","56","56","56","34","56","56","56"};
        listdatal = new ArrayList<>(Arrays.asList(arraydata));
    }*/

   /* void fetchNewdata()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i =1 ; i<5 ;i++)
                {
                    listdatal.add(Math.floor(Math.random())+"");
                    paginationAdapter.notifyDataSetChanged();
                }

            }
        },5000);



    }*/


    void fetchNewdata()
    {
        paginationAdapter.notifyDataSetChanged();
    }
}
