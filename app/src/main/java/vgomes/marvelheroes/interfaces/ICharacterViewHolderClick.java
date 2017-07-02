package vgomes.marvelheroes.interfaces;

import android.view.View;

/**
 * Created by victorgomes on 01/07/17.
 */

public interface ICharacterViewHolderClick<T> {

    void onItemClick(T item, View view);
    void onFavoriteClick(T item, boolean isFavorite);
}
