package vgomes.marvelheroes.datastorage.components;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import vgomes.marvelheroes.MHApplication;
import vgomes.marvelheroes.comms.models.CharacterParticipationsModel;
import vgomes.marvelheroes.comms.models.ParticipationsSummaryModel;
import vgomes.marvelheroes.comms.models.CharacterItemModel;
import vgomes.marvelheroes.datastorage.realmmodels.RealmCharacter;
import vgomes.marvelheroes.datastorage.realmmodels.RealmSummary;
import vgomes.marvelheroes.interfaces.IDataListener;

/**
 * Created by victorgomes on 28/06/17.
 */

public class RealmComponent implements IRealmComponent {

    @Override
    public RealmAsyncTask addOrUpdateCharacter(Realm realm, final CharacterItemModel[] list) {

        return realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (list != null && list.length > 0) {
                    Log.d("DEBUG", "Total elements = " + list.length);
                    for (CharacterItemModel character : list) {
                        createRealmCharacter(realm, character);
                    }
                    Log.d("DEBUG", "added: ");
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("DEBUG", "onSuccess");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("DEBUG", "onError", error);
            }
        });
    }

    private CharacterItemModel getCharacterFromRealm(Realm realm, RealmCharacter realmCharacter) {
        CharacterItemModel character = new CharacterItemModel();

        character.setName(realmCharacter.getName());
        character.setDescription(realmCharacter.getDescription());
        character.setModified(realmCharacter.getModified());
        character.setResourceURI(realmCharacter.getResourceURI());

        character.setComics(getSummaryFromRealm(realmCharacter.getComics()));
        character.setEvents(getSummaryFromRealm(realmCharacter.getEvents()));
        character.setSeries(getSummaryFromRealm(realmCharacter.getSeries()));
        character.setStories(getSummaryFromRealm(realmCharacter.getStories()));

        return character;
    }

    private CharacterParticipationsModel getSummaryFromRealm(RealmList<RealmSummary> realmList) {
        CharacterParticipationsModel result = new CharacterParticipationsModel();
        ParticipationsSummaryModel[] resultsList = new ParticipationsSummaryModel[realmList.size()];
        for (int i = 0; i < realmList.size(); i++) {
            ParticipationsSummaryModel bsm = new ParticipationsSummaryModel();
            bsm.setName(realmList.get(i).getName());
            bsm.setResourceURI(realmList.get(i).getResourceURI());
            resultsList[i] = bsm;
        }
        result.setItems(resultsList);
        return result;
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
        realmCharacter.setComics(getRealSummary(realm, character.getComics()));
        realmCharacter.setEvents(getRealSummary(realm, character.getEvents()));
        realmCharacter.setSeries(getRealSummary(realm, character.getSeries()));
        realmCharacter.setStories(getRealSummary(realm, character.getStories()));
    }

    private RealmList<RealmSummary> getRealSummary(Realm realm, CharacterParticipationsModel summary) {
        RealmList<RealmSummary> realmList = new RealmList<>();
        if (summary != null && summary.getItems() != null && summary.getItems().length > 0) {
            for (ParticipationsSummaryModel bsm : summary.getItems()) {
                RealmSummary realmComicSummary = realm.createObject(RealmSummary.class);
                realmComicSummary.setName(bsm.getName());
                realmComicSummary.setResourceURI(bsm.getResourceURI());
                realmList.add(realmComicSummary);
            }
        }
        return realmList;
    }
}
