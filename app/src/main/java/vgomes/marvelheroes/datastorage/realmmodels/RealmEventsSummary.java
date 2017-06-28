package vgomes.marvelheroes.datastorage.realmmodels;

import io.realm.RealmObject;

/**
 * Created by victorgomes on 28/06/17.
 */

public class RealmEventsSummary extends RealmObject{

    String resourceURI;
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
