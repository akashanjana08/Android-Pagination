package pract.rsys.com.paginationrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by akash.sharma on 5/17/2018.
 */

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.PageAdapterView> {

    List<MenuBean.Item> data;
    Context mContext;


    public PaginationAdapter(Context mContext , List<MenuBean.Item> data)
    {
        this.mContext = mContext;
        this.data     = data;
    }

    @Override
    public PageAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(this.mContext).inflate(R.layout.pagination_view, parent, false);
        return new PageAdapterView(view);
    }

    @Override
    public void onBindViewHolder(PageAdapterView holder, int position) {

        holder.id.setText(data.get(position).getId());
        holder.name.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PageAdapterView extends RecyclerView.ViewHolder
    {
        TextView id;
        TextView name;
        public PageAdapterView(View view)
        {
            super(view);
            id   = (TextView)view.findViewById(R.id.textView);
            name = (TextView)view.findViewById(R.id.textView2);
        }
    }

}
