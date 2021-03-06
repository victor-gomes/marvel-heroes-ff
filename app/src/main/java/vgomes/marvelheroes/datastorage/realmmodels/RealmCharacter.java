package vgomes.marvelheroes.datastorage.realmmodels;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by victorgomes on 28/06/17.
 */

public class RealmCharacter extends RealmObject {

    @PrimaryKey
    Integer id;

    String name;

    String description;

    Date modified;

    String resourceURI;

    RealmList<RealmSummary> comics;

    RealmList<RealmSummary> events;

    RealmList<RealmSummary> stories;

    RealmList<RealmSummary> series;

    RealmCharacterThumbnail thumbnail;

    boolean isFavorite = false;

    Date addedDate;

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

    public RealmList<RealmSummary> getComics() {
        return comics;
    }

    public void setComics(RealmList<RealmSummary> comics) {
        this.comics = comics;
    }

    public RealmList<RealmSummary> getEvents() {
        return events;
    }

    public void setEvents(RealmList<RealmSummary> events) {
        this.events = events;
    }

    public RealmList<RealmSummary> getStories() {
        return stories;
    }

    public void setStories(RealmList<RealmSummary> stories) {
        this.stories = stories;
    }

    public RealmList<RealmSummary> getSeries() {
        return series;
    }

    public void setSeries(RealmList<RealmSummary> series) {
        this.series = series;
    }

    public RealmCharacterThumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(RealmCharacterThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}
