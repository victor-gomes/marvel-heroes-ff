package vgomes.marvelheroes.datastorage.realmmodels;

import io.realm.RealmObject;

/**
 * Created by victorgomes on 01/07/17.
 */

public class RealmCharacterThumbnail extends RealmObject {

    String path;

    String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
