package vgomes.marvelheroes.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.RealmResults;
import vgomes.marvelheroes.R;
import vgomes.marvelheroes.datastorage.realmmodels.RealmCharacter;
import vgomes.marvelheroes.interfaces.ICharacterViewHolderClick;
import vgomes.marvelheroes.ui.viewholders.CharacterViewHolder;

/**
 * Created by victorgomes on 01/07/17.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private RealmResults<RealmCharacter> data;
    private ICharacterViewHolderClick listener;

    public CharacterAdapter(ICharacterViewHolderClick listener) {
        this.listener = listener;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        holder.setData(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    public void addData(RealmResults<RealmCharacter> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
