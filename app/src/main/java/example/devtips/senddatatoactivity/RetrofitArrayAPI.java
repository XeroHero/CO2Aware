package example.devtips.senddatatoactivity;

import example.devtips.senddatatoactivity.models.BikeStation;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitArrayAPI {

    @GET("vls/v1/stations?contract=dublin&bonus=true&apiKey=17566ea530bc36033158dda33346ffbc2313d405")
    Call<BikeStation> getJsonArrayData(); //create a list of models to be fetched from JSON
}
