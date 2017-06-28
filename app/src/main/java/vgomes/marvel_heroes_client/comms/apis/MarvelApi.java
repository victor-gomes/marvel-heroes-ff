package vgomes.marvel_heroes_client.comms.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vgomes.marvel_heroes_client.comms.consts.ApiConstants;
import vgomes.marvel_heroes_client.comms.models.BaseResponse;
import vgomes.marvel_heroes_client.comms.models.CharacterItemModel;

/**
 * Created by victorgomes on 26/06/17.
 */

public interface MarvelApi {

    @GET(ApiConstants.CHARACTERS_ENDPOINT)
    Call<BaseResponse<CharacterItemModel>> getCharactersList(@Query("nameStartsWith") String name);

}
