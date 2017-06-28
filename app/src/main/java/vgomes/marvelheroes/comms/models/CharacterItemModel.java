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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public BaseListModel getComics() {
        return comics;
    }

    public void setComics(BaseListModel comics) {
        this.comics = comics;
    }

    public BaseListModel getEvents() {
        return events;
    }

    public void setEvents(BaseListModel events) {
        this.events = events;
    }

    public BaseListModel getStories() {
        return stories;
    }

    public void setStories(BaseListModel stories) {
        this.stories = stories;
    }

    public BaseListModel getSeries() {
        return series;
    }

    public void setSeries(BaseListModel series) {
        this.series = series;
    }
}

