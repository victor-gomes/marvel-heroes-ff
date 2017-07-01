package vgomes.marvelheroes.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import vgomes.marvelheroes.R;
import vgomes.marvelheroes.datastorage.realmmodels.RealmCharacter;
import vgomes.marvelheroes.interfaces.ICharacterViewHolderClick;

/**
 * Created by victorgomes on 01/07/17.
 */

public class CharacterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.background_iv)
    ImageView backgroundIv;
    @BindView(R.id.character_name_tv)
    TextView nameTv;
    @BindView(R.id.favorite_iv)
    ImageView favoriteIv;
    View itemView;

    public CharacterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
    }

    public void setData(final RealmCharacter data, final ICharacterViewHolderClick<RealmCharacter> listener) {
        nameTv.setText(data.getName());
        Glide.with(itemView.getContext()).load(String.format(Locale.getDefault(), "%s%s%s", data.getThumbnail().getPath(),".", data.getThumbnail().getExtension())).into(backgroundIv);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(data);
                }
            }
        });

        favoriteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onFavoriteClick(data);
                }
            }
        });
    }
}
