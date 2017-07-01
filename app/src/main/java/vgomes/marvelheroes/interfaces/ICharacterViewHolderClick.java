package vgomes.marvelheroes.interfaces;

/**
 * Created by victorgomes on 01/07/17.
 */

public interface ICharacterViewHolderClick<T> {

    void onItemClick(T item);
    void onFavoriteClick(T item, boolean isFavorite);
}
