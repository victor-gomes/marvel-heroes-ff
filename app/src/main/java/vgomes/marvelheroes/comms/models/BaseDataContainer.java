package vgomes.marvelheroes.comms.models;

import com.google.gson.annotations.Expose;

/**
 * Created by victorgomes on 27/06/17.
 */

public class BaseDataContainer<T> {

    @Expose
    Integer offset;
    @Expose
    Integer limit;
    @Expose
    Integer total;
    @Expose
    Integer count;
    @Expose
    T[] results;

}
