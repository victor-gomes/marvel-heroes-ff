package vgomes.marvelheroes.datastorage.helper;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;
import vgomes.marvelheroes.datastorage.consts.DataStorageConstants;

/**
 * Created by victorgomes on 28/06/17.
 */

public class Migration implements RealmMigration {

    int version;

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();
        version = DataStorageConstants.DATABASE_VERSION;


    }
}
