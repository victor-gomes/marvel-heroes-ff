package vgomes.marvelheroes.datastorage.components;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;
import vgomes.marvelheroes.comms.models.CharacterItemModel;
import vgomes.marvelheroes.datastorage.realmmodels.RealmCharacter;

/**
 * Created by victorgomes on 28/06/17.
 */

public class RealmComponent<T> implements IRealmComponent {

    public static RealmAsyncTask addOrUpdateCharacter(Realm realm, final CharacterItemModel[] list) {

        return realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (list != null && list.length > 0) {
                    for (CharacterItemModel character : list) {

                    }
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

            }
        });
    }

    private void createRealmCharacter(Realm realm, CharacterItemModel character) {
        RealmCharacter realmCharacter = realm.where(RealmCharacter.class).equalTo("id", character.getId()).findFirst();
        if (realmCharacter == null) {
            realmCharacter = realm.createObject(RealmCharacter.class, character.getId());
        }

        realmCharacter.setName(character.getName());
        realmCharacter.setDescription(character.getDescription());
        realmCharacter.setModified(character.getModified());
        realmCharacter.setResourceURI(character.getResourceURI());
        createRealCommicSummary(realm, realmCharacter, character);


    }

    private void createRealCommicSummary(Realm realm, RealmCharacter realmCharacter, CharacterItemModel characterItemModel) {

    }

    private void createRealSeriesSummary(Realm realm, RealmCharacter realmCharacter, CharacterItemModel characterItemModel) {

    }

    private void createRealEventsSummary(Realm realm, RealmCharacter realmCharacter, CharacterItemModel characterItemModel) {

    }

    private void createRealStoriesSummary(Realm realm, RealmCharacter realmCharacter, CharacterItemModel characterItemModel) {

    }
}
