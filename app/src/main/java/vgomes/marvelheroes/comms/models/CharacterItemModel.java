package vgomes.marvelheroes.comms.models;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created by victorgomes on 27/06/17.
 */

public class CharacterItemModel{

    @Expose
    Integer id;
    @Expose
    String name;
    @Expose
    String description;
    @Expose
    Date modified;
    @Expose
    String resourceURI;
    @Expose
    BaseListModel comics;
    @Expose
    BaseListModel events;
    @Expose
    BaseListModel stories;
    @Expose
    BaseListModel series;

}

