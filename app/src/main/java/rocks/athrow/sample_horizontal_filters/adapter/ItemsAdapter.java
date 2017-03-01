package rocks.athrow.sample_horizontal_filters.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rocks.athrow.sample_horizontal_filters.R;
import rocks.athrow.sample_horizontal_filters.data.Item;
import rocks.athrow.sample_horizontal_filters.realmadapter.RealmRecyclerViewAdapter;

/**
 * ItemsAdapter
 * Created by joselopez1 on 3/1/2017.
 */

public class ItemsAdapter extends RealmRecyclerViewAdapter<Item> {
    public ItemsAdapter() {
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        final TextView itemView;

        ViewHolder(View view) {
            super(view);
            itemView = (TextView) view.findViewById(R.id.item_card);
        }
    }

    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View locationItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ItemsAdapter.ViewHolder(locationItem);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        ItemsAdapter.ViewHolder vh = (ItemsAdapter.ViewHolder) viewHolder;
        Item realmItem = getItem(position);
        final String item = realmItem.getItem();
        vh.itemView.setText(item);
    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }
}
