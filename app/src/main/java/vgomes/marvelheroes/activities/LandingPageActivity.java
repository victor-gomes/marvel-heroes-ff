package vgomes.marvelheroes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vgomes.marvelheroes.MHApplication;
import vgomes.marvelheroes.R;
import vgomes.marvelheroes.comms.apis.MarvelApi;
import vgomes.marvelheroes.comms.models.BaseResponse;
import vgomes.marvelheroes.comms.models.CharacterItemModel;

public class LandingPageActivity extends AppCompatActivity {

    private MarvelApi service;
    @BindView(R.id.button)
    TextView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        ButterKnife.bind(this);
        service = MHApplication.getCommsComponent().getAdapter().create(MarvelApi.class);
    }

    @OnClick(R.id.button)
    public void onClick(View v) {
        service.getCharactersList("spider").enqueue(new Callback<BaseResponse<CharacterItemModel>>() {


            @Override
            public void onResponse(Call<BaseResponse<CharacterItemModel>> call, Response<BaseResponse<CharacterItemModel>> response) {
                Log.d("DEBUG", "onResponse");
                BaseResponse<CharacterItemModel> result = response.body();
            }

            @Override
            public void onFailure(Call<BaseResponse<CharacterItemModel>> call, Throwable t) {
                Log.d("DEBUG", "onFailure");
            }
        });
    }
}
