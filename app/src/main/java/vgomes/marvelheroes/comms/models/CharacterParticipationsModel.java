package vgomes.marvelheroes.comms.models;

import com.google.gson.annotations.Expose;

/**
 * Created by victorgomes on 27/06/17.
 */

public class CharacterParticipationsModel {

    @Expose
    Integer available;
    @Expose
    Integer returned;
    @Expose
    String collectionURI;
    @Expose
    ParticipationsSummaryModel[] items;

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getReturned() {
        return returned;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public ParticipationsSummaryModel[] getItems() {
        return items;
    }

    public void setItems(ParticipationsSummaryModel[] items) {
        this.items = items;
    }
}
