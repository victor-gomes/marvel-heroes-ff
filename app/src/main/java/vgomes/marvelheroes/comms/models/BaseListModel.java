package vgomes.marvelheroes.comms.models;

import com.google.gson.annotations.Expose;

/**
 * Created by victorgomes on 27/06/17.
 */

public class BaseListModel {

    @Expose
    Integer available;
    @Expose
    Integer returned;
    @Expose
    String collectionURI;
    @Expose
    BaseSummaryModel[] items;

}
