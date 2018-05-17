package pract.rsys.com.paginationrecyclerview;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by akash.sharma on 5/16/2018.
 */

public interface RetrofitApiInterface {

    @GET("list_paging/")
    Call<MenuBean> getMenuItems(@Query("page") String pageId);

}
