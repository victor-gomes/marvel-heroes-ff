package vgomes.marvelheroes.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vgomes.marvelheroes.R;

/**
 * Created by victorgomes on 02/07/17.
 */

public class CharacterDetailsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.label_tv)
    TextView labelTv;

    public CharacterDetailsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(String label) {
        labelTv.setText(label);
    }

}
