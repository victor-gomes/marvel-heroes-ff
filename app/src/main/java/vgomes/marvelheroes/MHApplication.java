package vgomes.marvelheroes;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import vgomes.marvelheroes.comms.components.IRetrofitComponent;
import vgomes.marvelheroes.comms.components.RetrofitComponent;
import vgomes.marvelheroes.datastorage.components.IRealmComponent;
import vgomes.marvelheroes.datastorage.components.RealmComponent;
import vgomes.marvelheroes.datastorage.consts.DataStorageConstants;
import vgomes.marvelheroes.datastorage.helper.Migration;

/**
 * Created by victorgomes on 27/06/17.
 */

public class MHApplication extends Application {

    private static IRetrofitComponent commsComponent;
    private static IRealmComponent dataComponent;
    private static RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
        commsComponent = createCommsComponent();
        dataComponent = createDataComponent();

        Realm.init(this);
        realmConfiguration = new RealmConfiguration.Builder().name(DataStorageConstants.DATABASE_NAME)
                .schemaVersion(DataStorageConstants.DATABASE_VERSION)
                .migration(new Migration())
                .build();
    }

    public static IRetrofitComponent getCommsComponent() {
        return commsComponent;
    }

    private RetrofitComponent createCommsComponent(){
        return new RetrofitComponent();
    }

    public static IRealmComponent getDataComponent() {
        return dataComponent;
    }

    private IRealmComponent createDataComponent(){
        return new RealmComponent();
    }

    public static Realm getRealm() {
        return Realm.getInstance(realmConfiguration);
    }
}
