package vgomes.marvelheroes.ui.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vgomes.marvelheroes.MHApplication;
import vgomes.marvelheroes.R;
import vgomes.marvelheroes.comms.apis.MarvelApi;
import vgomes.marvelheroes.comms.models.BaseDataContainer;
import vgomes.marvelheroes.comms.models.BaseResponseWrapper;
import vgomes.marvelheroes.comms.models.CharacterItemModel;
import vgomes.marvelheroes.datastorage.realmmodels.RealmCharacter;
import vgomes.marvelheroes.interfaces.ICharacterViewHolderClick;
import vgomes.marvelheroes.ui.adapters.CharacterAdapter;

public class LandingPageActivity extends AppCompatActivity {

    private static final String TAG = LandingPageActivity.class.getSimpleName();

    @BindView(R.id.recycler_rl)
    RecyclerView recycler;

    private MarvelApi service;
    private RealmResults<RealmCharacter> results;
    private CharacterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        ButterKnife.bind(this);
        adapter = new CharacterAdapter(new ICharacterViewHolderClick() {
            @Override
            public void onItemClick(Object item) {
                Toast.makeText(getApplicationContext(), "onItemClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFavoriteClick(Object item) {
                Toast.makeText(getApplicationContext(), "onFavoriteClick", Toast.LENGTH_SHORT).show();
            }
        });
        recycler.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(adapter);
        service = MHApplication.getCommsComponent().getAdapter().create(MarvelApi.class);
        results = MHApplication.getRealm().where(RealmCharacter.class).findAllAsync();
        results.addChangeListener(new RealmChangeListener<RealmResults<RealmCharacter>>() {
            @Override
            public void onChange(RealmResults<RealmCharacter> elements) {
                Log.d(TAG, "onChange size = " + elements.size());
                adapter.addData(elements);
            }
        });
        fetchData("spider");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        results = null;
        service = null;
        adapter = null;
    }


    private void fetchData(final String characterName) {

        service.getCharactersList(characterName).enqueue(new Callback<BaseResponseWrapper<CharacterItemModel>>() {


            @Override
            public void onResponse(Call<BaseResponseWrapper<CharacterItemModel>> call, Response<BaseResponseWrapper<CharacterItemModel>> response) {
                Log.d(TAG, "onResponse");
                BaseResponseWrapper<CharacterItemModel> result = response.body();
                BaseDataContainer<CharacterItemModel> container = result.getData();
                CharacterItemModel[] characterList = container.getResults();
                MHApplication.getDataComponent().addOrUpdateCharacter(MHApplication.getRealm(), characterList);

            }

            @Override
            public void onFailure(Call<BaseResponseWrapper<CharacterItemModel>> call, Throwable t) {
                Log.d(TAG, "onFailure");
                //we can have a fine grained error validation here.
                //In this example we will only display a general error message
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.activity_root_cl),
                        R.string.server_error_generic_message, Snackbar.LENGTH_SHORT);
                mySnackbar.setAction(R.string.snack_bar_retry_label, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fetchData(characterName);
                    }
                });
                mySnackbar.show();
            }
        });
    }
}
