package vgomes.marvelheroes.datastorage.components;

import android.util.Log;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmList;
import io.realm.RealmResults;
import vgomes.marvelheroes.comms.models.CharacterItemModel;
import vgomes.marvelheroes.comms.models.CharacterParticipationsModel;
import vgomes.marvelheroes.comms.models.CharacterThumbnailModel;
import vgomes.marvelheroes.comms.models.ParticipationsSummaryModel;
import vgomes.marvelheroes.datastorage.realmmodels.RealmCharacter;
import vgomes.marvelheroes.datastorage.realmmodels.RealmCharacterThumbnail;
import vgomes.marvelheroes.datastorage.realmmodels.RealmSummary;

/**
 * Created by victorgomes on 28/06/17.
 */

public class RealmComponent implements IRealmComponent {

    @Override
    public RealmAsyncTask addOrUpdateCharacter(Realm realm, final CharacterItemModel[] list, final Date addedDate) {

        return realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (list != null && list.length > 0) {
                    Log.d("DEBUG", "Total elements = " + list.length);
                    for (CharacterItemModel character : list) {
                        createRealmCharacter(realm, character, addedDate);
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

    private void deleteRealmCharacters(Realm realm) {
        RealmResults<RealmCharacter> realmCharacterList = realm.where(RealmCharacter.class).findAll();
        if (realmCharacterList != null && !realmCharacterList.isEmpty()) {
            for (RealmCharacter realmCharacter : realmCharacterList) {
                if (realmCharacter.getComics() != null) {
                    realmCharacter.getComics().deleteAllFromRealm();
                }
                if (realmCharacter.getSeries() != null) {
                    realmCharacter.getSeries().deleteAllFromRealm();
                }
                if (realmCharacter.getEvents() != null) {
                    realmCharacter.getEvents().deleteAllFromRealm();
                }
                if (realmCharacter.getStories() != null) {
                    realmCharacter.getStories().deleteAllFromRealm();
                }
                if (realmCharacter.getThumbnail() != null) {
                    realmCharacter.getThumbnail().deleteFromRealm();
                }
            }
            realmCharacterList.deleteAllFromRealm();
        }
    }

    private CharacterItemModel getCharacterFromRealm(RealmCharacter realmCharacter) {
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

    private void createRealmCharacter(Realm realm, CharacterItemModel character, final Date addedDate) {
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
        realmCharacter.setThumbnail(getRealmCharacterThumbnail(realm, character.getThumbnail()));
        realmCharacter.setAddedDate(addedDate);
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

    private RealmCharacterThumbnail getRealmCharacterThumbnail(Realm realm, CharacterThumbnailModel thumbnailModel) {
        RealmCharacterThumbnail realmCharacterThumbnail = realm.createObject(RealmCharacterThumbnail.class);
        realmCharacterThumbnail.setExtension(thumbnailModel.getExtension());
        realmCharacterThumbnail.setPath(thumbnailModel.getPath());
        return realmCharacterThumbnail;
    }
}
