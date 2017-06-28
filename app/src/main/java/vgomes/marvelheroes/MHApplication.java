package vgomes.marvelheroes;

import android.app.Application;

import vgomes.marvelheroes.comms.components.IRetrofitComponent;
import vgomes.marvelheroes.comms.components.RetrofitComponent;

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
