package vgomes.marvelheroes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vgomes.marvelheroes.MHApplication;
import vgomes.marvelheroes.R;
import vgomes.marvelheroes.comms.apis.MarvelApi;
import vgomes.marvelheroes.comms.models.BaseDataContainer;
import vgomes.marvelheroes.comms.models.BaseResponse;
import vgomes.marvelheroes.comms.models.CharacterItemModel;
import vgomes.marvelheroes.datastorage.realmmodels.RealmCharacter;

public class LandingPageActivity extends AppCompatActivity {

    private MarvelApi service;
    @BindView(R.id.button)
    TextView button;

    RealmResults<RealmCharacter> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        ButterKnife.bind(this);
        service = MHApplication.getCommsComponent().getAdapter().create(MarvelApi.class);

        results = MHApplication.getRealm().where(RealmCharacter.class).findAllAsync();
        Log.d("DEBUG", "results size = " + results.size());
        results.addChangeListener(new RealmChangeListener<RealmResults<RealmCharacter>>() {
            @Override
            public void onChange(RealmResults<RealmCharacter> element) {
                Log.d("DEBUG", "onChange size = " + element.size());
            }
        });
    }

    @OnClick(R.id.button)
    public void onClick(View v) {

        service.getCharactersList("spider").enqueue(new Callback<BaseResponse<CharacterItemModel>>() {


            @Override
            public void onResponse(Call<BaseResponse<CharacterItemModel>> call, Response<BaseResponse<CharacterItemModel>> response) {
                Log.d("DEBUG", "onResponse");
                BaseResponse<CharacterItemModel> result = response.body();
                BaseDataContainer<CharacterItemModel> container = result.getData();
                CharacterItemModel[] characterList = container.getResults();
                MHApplication.getDataComponent().addOrUpdateCharacter(MHApplication.getRealm(), characterList);

            }

            @Override
            public void onFailure(Call<BaseResponse<CharacterItemModel>> call, Throwable t) {
                Log.d("DEBUG", "onFailure");
            }
        });
    }
}
