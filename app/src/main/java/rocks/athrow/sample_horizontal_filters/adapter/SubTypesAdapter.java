package rocks.athrow.sample_horizontal_filters.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import rocks.athrow.sample_horizontal_filters.R;

/**
 * Created by joselopez1 on 3/1/2017.
 */

public class SubTypesAdapter extends RecyclerView.Adapter<SubTypesAdapter.ViewHolder> {
    private String[] mItems;

    SubTypesAdapter(String[] items) {
        this.mItems = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Button rowView;

        ViewHolder(View view) {
            super(view);
            rowView = (Button) view.findViewById(R.id.sub_type);
        }
    }

    @Override
    public SubTypesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View locationItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sub_type, parent, false);
        return new SubTypesAdapter.ViewHolder(locationItem);
    }

    @Override
    public void onBindViewHolder(final SubTypesAdapter.ViewHolder viewHolder, int position) {
        final String subType = mItems[position];
        Button rowView = viewHolder.rowView;
        rowView.setText(subType);
    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.length;
        } else {
            return 0;
        }
    }
}