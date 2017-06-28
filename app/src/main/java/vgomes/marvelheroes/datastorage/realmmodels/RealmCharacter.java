package vgomes.marvelheroes.datastorage.realmmodels;

import com.google.gson.annotations.Expose;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import vgomes.marvelheroes.comms.models.BaseListModel;

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

    RealmList<RealmComicSummary> comics;

    RealmList<RealmEventsSummary> events;

    RealmList<RealmStorySummary> stories;

    RealmList<RealmSeriesSummary> series;

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

    public RealmList<RealmComicSummary> getComics() {
        return comics;
    }

    public void setComics(RealmList<RealmComicSummary> comics) {
        this.comics = comics;
    }

    public RealmList<RealmEventsSummary> getEvents() {
        return events;
    }

    public void setEvents(RealmList<RealmEventsSummary> events) {
        this.events = events;
    }

    public RealmList<RealmStorySummary> getStories() {
        return stories;
    }

    public void setStories(RealmList<RealmStorySummary> stories) {
        this.stories = stories;
    }

    public RealmList<RealmSeriesSummary> getSeries() {
        return series;
    }

    public void setSeries(RealmList<RealmSeriesSummary> series) {
        this.series = series;
    }
}
