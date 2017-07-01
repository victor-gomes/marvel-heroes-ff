package vgomes.marvelheroes.comms.models;

import com.google.gson.annotations.Expose;

/**
 * Created by victorgomes on 27/06/17.
 */

public class ParticipationsSummaryModel {

    @Expose
    String resourceURI;
    @Expose
    String name;

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
