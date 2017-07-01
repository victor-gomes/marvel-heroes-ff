package vgomes.marvelheroes.comms.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vgomes.marvelheroes.comms.consts.ApiConstants;
import vgomes.marvelheroes.comms.models.BaseResponseWrapper;
import vgomes.marvelheroes.comms.models.CharacterItemModel;

/**
 * Created by victorgomes on 26/06/17.
 */

public interface MarvelApi {

    @GET(ApiConstants.CHARACTERS_ENDPOINT)
    Call<BaseResponseWrapper<CharacterItemModel>> getCharactersList(@Query("nameStartsWith") String name);

}
