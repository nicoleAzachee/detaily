package com.example.detaily.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.detaily.R;
import com.example.detaily.data.Songs;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final ItemListActivity mParentActivity;
    private final List<Songs> mValues;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                Songs item = (Songs) view.getTag();
                Context context = view.getContext();
//                Intent intent = new Intent(context, ItemDetailActivity.class);
//                intent.putExtra(EnvironmentVariables.SONGID, );
//                context.startActivity(intent);

        }
    };

    RecyclerViewAdapter(ItemListActivity parent,
                        List<Songs> items) {
        mValues = items;
        mParentActivity = parent;;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).getTrackName());
        holder.mContentView.setText(mValues.get(position).getTrackPrice());

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView mIdView;
        final TextView mContentView;

        ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id_text);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }
}