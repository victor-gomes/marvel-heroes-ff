package vgomes.marvelheroes.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import vgomes.marvelheroes.MHApplication;
import vgomes.marvelheroes.R;
import vgomes.marvelheroes.datastorage.realmmodels.RealmCharacter;
import vgomes.marvelheroes.datastorage.realmmodels.RealmSummary;
import vgomes.marvelheroes.ui.adapters.CharacterDetailsAdapter;
import vgomes.marvelheroes.ui.customviews.CustomToolBar;
import vgomes.marvelheroes.ui.viewholders.CharacterDetailsDataHolder;
import vgomes.marvelheroes.utils.AppDataTypes;

/**
 * Created by victorgomes on 02/07/17.
 */

public class CharacterDetailsActivity extends BaseActivity {

    public static final String TAG = CharacterDetailsActivity.class.getSimpleName();
    public static final String EXTRA_CHARACTER_ID = "EXTRA_CHARACTER_ID";
    public static final String SAVED_CHARACTER_ID = "SAVED_CHARACTER_ID";
    @BindView(R.id.background_iv)
    ImageView backgroundIv;
    @BindView(R.id.character_name_tv)
    TextView nameTv;
    @BindView(R.id.favorite_iv)
    ImageView favoriteIv;
    @BindView(R.id.tool_bar_ctb)
    CustomToolBar toolBarCtb;
    @BindView(R.id.recycler_rl)
    RecyclerView recycleRl;
    private RealmCharacter result;
    private int characterId;
    private CharacterDetailsAdapter adapter;
    private final int maxElements = 3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolBarCtb);
        if (savedInstanceState == null) {
            characterId = getIntent().getExtras().getInt(EXTRA_CHARACTER_ID, -1);
        } else {
            characterId = savedInstanceState.getInt(SAVED_CHARACTER_ID, -1);
        }

        adapter = new CharacterDetailsAdapter();
        recycleRl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycleRl.setAdapter(adapter);

        result = MHApplication.getRealm().where(RealmCharacter.class).equalTo("id", characterId).findFirstAsync();
        result.addChangeListener(new RealmChangeListener<RealmCharacter>() {
            @Override
            public void onChange(RealmCharacter element) {
                Log.d(TAG, "onChange element name = " + element.getName());
                if (isResumed) {
                    setupToolBar(element);
                    setHeaderData(element);
                    updateData(element);
                }
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SAVED_CHARACTER_ID, characterId);
        super.onSaveInstanceState(outState);
    }

    private void updateData(RealmCharacter element) {
        List<CharacterDetailsDataHolder> list = new ArrayList<>();
        if (element.getComics() != null && !element.getComics().isEmpty()) {
            addElementsToList(list, element.getComics(), getString(R.string.character_details_comic_title));
        }
        if (element.getStories() != null && !element.getStories().isEmpty()) {
            addElementsToList(list, element.getStories(), getString(R.string.character_details_stories_title));
        }
        if (element.getSeries() != null && !element.getSeries().isEmpty()) {
            addElementsToList(list, element.getSeries(), getString(R.string.character_details_series_title));
        }
        if (element.getEvents() != null && !element.getEvents().isEmpty()) {
            addElementsToList(list, element.getEvents(), getString(R.string.character_details_events_title));
        }
        adapter.addData(list);
    }

    private void addElementsToList(List<CharacterDetailsDataHolder> list, RealmList<RealmSummary> addingElements, String title) {
        list.add(new CharacterDetailsDataHolder(title, AppDataTypes.CHARACTER_DETAIL_TITLE));
        for (int i = 0; i < addingElements.size(); i++) {
            list.add(new CharacterDetailsDataHolder(addingElements.get(i).getName(), AppDataTypes.CHARACTER_DETAIL_ITEM));
            if (i == maxElements - 1) {
                break;
            }
        }
    }

    private void setupToolBar(RealmCharacter item) {
        toolBarCtb.setTitle(item.getName());
        toolBarCtb.setSearchBar(false);
    }

    private void setHeaderData(final RealmCharacter data) {
        nameTv.setText(data.getName());
        Glide.with(this).load(String.format(Locale.getDefault(), "%s%s%s", data.getThumbnail().getPath(), ".", data.getThumbnail().getExtension())).into(backgroundIv);
        final boolean isFavorite = data.isFavorite();
        favoriteIv.setBackground(ContextCompat.getDrawable(this, isFavorite ? R.drawable.ic_favorite_white : R.drawable.ic_favorite_border_white));
        favoriteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MHApplication.getRealm().beginTransaction();
                data.setFavorite(!isFavorite);
                MHApplication.getRealm().commitTransaction();
            }
        });
    }

    @Override
    protected void onDestroy() {
        result = null;
        adapter = null;
        super.onDestroy();
    }
}
