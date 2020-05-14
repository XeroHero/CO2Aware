package example.devtips.senddatatoactivity.network;

import java.util.List;

import example.devtips.senddatatoactivity.models.BikeStation;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("v1/stations?contract=dublin&apiKey=17566ea530bc36033158dda33346ffbc2313d405")
    Call<List<BikeStation>> getAllBikeStations();
}
