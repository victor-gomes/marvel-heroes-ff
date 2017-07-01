package vgomes.marvelheroes.interfaces;

import java.util.List;

/**
 * Created by victorgomes on 01/07/17.
 */

public interface IDataListener<T> {

    void onDataChanged(List<T> data);
}
