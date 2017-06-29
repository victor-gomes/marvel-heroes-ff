package vgomes.marvelheroes.datastorage.components;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import vgomes.marvelheroes.comms.models.CharacterItemModel;

/**
 * Created by victorgomes on 28/06/17.
 */

public interface IRealmComponent {

    RealmAsyncTask addOrUpdateCharacter(Realm realm, final CharacterItemModel[] list);
}
