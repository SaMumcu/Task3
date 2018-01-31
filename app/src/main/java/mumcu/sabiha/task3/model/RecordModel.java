package mumcu.sabiha.task3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sabis on 1/30/2018.
 */

public class RecordModel {

    @SerializedName("_id")
    @Expose
    private IdModel id;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;

    public IdModel getId() {
        return id;
    }

    public void setId(IdModel id) {
        this.id = id;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
