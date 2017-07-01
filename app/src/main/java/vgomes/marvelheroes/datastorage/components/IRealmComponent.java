package vgomes.marvelheroes.datastorage.components;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;
import vgomes.marvelheroes.comms.models.CharacterItemModel;
import vgomes.marvelheroes.datastorage.realmmodels.RealmCharacter;
import vgomes.marvelheroes.interfaces.IDataListener;

/**
 * Created by victorgomes on 28/06/17.
 */

public interface IRealmComponent {

    RealmAsyncTask addOrUpdateCharacter(Realm realm, final CharacterItemModel[] list);
}
