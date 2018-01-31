package mumcu.sabiha.task3.api;

import mumcu.sabiha.task3.model.BaseModel;
import mumcu.sabiha.task3.model.BodyModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by sabis on 1/30/2018.
 */

public interface ApiInterface {

    @POST("searchRecords")
    Call<BaseModel> getData(@Body BodyModel bodyModel);

}
