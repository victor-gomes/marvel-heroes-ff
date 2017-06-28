package vgomes.marvel_heroes_client;

import android.app.Application;

import vgomes.marvel_heroes_client.comms.components.IRetrofitComponent;
import vgomes.marvel_heroes_client.comms.components.RetrofitComponent;

/**
 * Created by victorgomes on 27/06/17.
 */

public class MHApplication extends Application {

    private static IRetrofitComponent commsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        commsComponent = createCommsComponent();
    }

    public static IRetrofitComponent getCommsComponent() {
        return commsComponent;
    }

    private RetrofitComponent createCommsComponent(){
        return new RetrofitComponent();
    }

}
