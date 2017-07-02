package vgomes.marvelheroes.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;

import java.util.Date;

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
import vgomes.marvelheroes.interfaces.ISearchListener;
import vgomes.marvelheroes.ui.adapters.CharacterAdapter;
import vgomes.marvelheroes.ui.customviews.CustomToolBar;

public class LandingPageActivity extends BaseActivity {

    private static final String TAG = LandingPageActivity.class.getSimpleName();

    @BindView(R.id.recycler_rl)
    RecyclerView recycler;
    @BindView(R.id.tool_bar_ctb)
    CustomToolBar toolBarCtb;

    private MarvelApi service;
    private RealmResults<RealmCharacter> results;
    private CharacterAdapter adapter;
    private GridLayoutManager layoutManager;
    private int limit = 20;
    private int offset = 0;
    private int total = 0;
    private String characterName = null;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Slide());
        setContentView(R.layout.activity_landing_page);
        ButterKnife.bind(this);
        setSupportActionBar(toolBarCtb);
        //Create recycler view adapter
        adapter = new CharacterAdapter(new ICharacterViewHolderClick<RealmCharacter>() {
            @Override
            public void onItemClick(RealmCharacter item, View sharedView) {
                //Launch details activity
                Intent i = new Intent(LandingPageActivity.this, CharacterDetailsActivity.class);
                i.putExtra(CharacterDetailsActivity.EXTRA_CHARACTER_ID, item.getId());
                //Add transition info
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        LandingPageActivity.this,
                        sharedView,
                        ViewCompat.getTransitionName(sharedView));
                // start the new activity
                startActivity(i, options.toBundle());
            }

            @Override
            public void onFavoriteClick(RealmCharacter item, boolean isFavorite) {
                Log.d(TAG, "onFavoriteClick = " + item.getName() + " -> isFavorite = " + !isFavorite);
                //Save new favorite status in DB
                MHApplication.getRealm().beginTransaction();
                item.setFavorite(!isFavorite);
                MHApplication.getRealm().commitTransaction();

            }
        });
        //Create and add layout manager for recyclerView
        layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        //Check if there are records stored in DB and add change listener
        setQueryToDB(null);
        //Get our retrofit API
        service = MHApplication.getCommsComponent().getAdapter().create(MarvelApi.class);
        //Fetch data from Marvel web API
        fetchData();

        toolBarCtb.setSearchChangedListener(new ISearchListener() {
            @Override
            public void onSearchChanged(String s) {
                Log.d(TAG, "onSearchChanged = " + s);
                offset = 0;
                if (isResumed) {
                    if (!TextUtils.isEmpty(s)) {
                        characterName = s;
                        fetchData();
                    } else {
                        characterName = null;
                        fetchData();
                    }
                }
            }
        });
    }

    /**
     * Calculate if user is in last position of the list
     *
     * @param layoutManager reference to current LayoutManager added to our adapter
     */
    private void onScrollCall(LinearLayoutManager layoutManager) {
        int totalItemCount = layoutManager.getItemCount();
        int lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition();

        if (lastVisiblePosition == totalItemCount - 1) {
            Log.d(TAG, "onLoadMore limit = " + limit + " offset = " + offset);
            if (adapter.getItemCount() < total) {
                if (!isLoading) {
                    fetchData();
                }
            } else {
                Log.d(TAG, "onLoadMore no more data total= " + total);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        results = null;
        service = null;
        adapter = null;
        scrollListener = null;
    }

    /**
     * Fetch data from Marvel API.
     */
    private void fetchData() {
        isLoading = true;
        service.getCharactersList(characterName, limit, offset).enqueue(new Callback<BaseResponseWrapper<CharacterItemModel>>() {


            @Override
            public void onResponse(Call<BaseResponseWrapper<CharacterItemModel>> call, Response<BaseResponseWrapper<CharacterItemModel>> response) {
                Log.d(TAG, "onResponse");
                BaseResponseWrapper<CharacterItemModel> result = response.body();
                if (result != null) {
                    BaseDataContainer<CharacterItemModel> container = result.getData();
                    if (container != null) {
                        total = container.getTotal();
                        CharacterItemModel[] characterList = container.getResults();
                        Date date = java.util.Calendar.getInstance().getTime();
                        if (!TextUtils.isEmpty(characterName)) {
                            setQueryToDB(date);
                            MHApplication.getDataComponent().addOrUpdateCharacter(MHApplication.getRealm(), characterList, date, false);
                        } else {
                            MHApplication.getDataComponent().addOrUpdateCharacter(MHApplication.getRealm(), characterList, date, false);
                            setQueryToDB(null);
                        }
                    }
                }
                isLoading = false;
            }

            @Override
            public void onFailure(Call<BaseResponseWrapper<CharacterItemModel>> call, Throwable t) {
                Log.d(TAG, "onFailure");
                //we can have a fine grained error validation here.
                //In this example we will only display a general error message
                if (isResumed) {
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.activity_root_cl),
                            R.string.server_error_generic_message, Snackbar.LENGTH_LONG);
                    mySnackbar.setAction(R.string.snack_bar_retry_label, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fetchData();
                        }
                    });
                    mySnackbar.show();
                }
                isLoading = false;
            }
        });
    }

    /**
     * @param date Date of date to be queried. If null query all records from DB.
     */
    private void setQueryToDB(Date date) {
        if (results != null) {
            results.removeAllChangeListeners();
            results = null;
        }
        if (date == null) {
            results = MHApplication.getRealm().where(RealmCharacter.class).findAllAsync();
        } else {
            results = MHApplication.getRealm().where(RealmCharacter.class).equalTo("addedDate", date).findAllAsync();
        }
        results.addChangeListener(new RealmChangeListener<RealmResults<RealmCharacter>>() {
            @Override
            public void onChange(RealmResults<RealmCharacter> elements) {
                Log.d(TAG, "onChange size = " + elements.size());
                if (isResumed) {
                    recycler.removeOnScrollListener(scrollListener);
                    adapter.addData(elements);
                    offset = elements.size();
                    if (elements.size() > 0) {
                        recycler.addOnScrollListener(scrollListener);
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (toolBarCtb.isSearching()) {
            toolBarCtb.reset();
        } else {
            super.onBackPressed();
        }
    }

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            onScrollCall(layoutManager);
        }
    };
}
