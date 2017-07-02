package vgomes.marvelheroes.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vgomes.marvelheroes.R;
import vgomes.marvelheroes.ui.viewholders.CharacterDetailsDataHolder;
import vgomes.marvelheroes.ui.viewholders.CharacterDetailsViewHolder;
import vgomes.marvelheroes.utils.AppDataTypes;

/**
 * Created by victorgomes on 02/07/17.
 */

public class CharacterDetailsAdapter extends RecyclerView.Adapter<CharacterDetailsViewHolder> {

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_ITEM = 1;

    List<CharacterDetailsDataHolder> data;

    @Override
    public CharacterDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_TITLE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_details_title, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_details_item, parent, false);
        }
        return new CharacterDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterDetailsViewHolder holder, int position) {
        holder.setData(data.get(position).getLabel());
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getType().equals(AppDataTypes.CHARACTER_DETAIL_TITLE)) {
            return TYPE_TITLE;
        } else {
            return TYPE_ITEM;
        }
    }

    public void addData(List<CharacterDetailsDataHolder> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
