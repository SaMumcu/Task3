package mumcu.sabiha.task3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sabis on 1/30/2018.
 */

public class BaseModel {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("records")
    @Expose
    private List<RecordModel> records = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RecordModel> getRecords() {
        return records;
    }

    public void setRecords(List<RecordModel> records) {
        this.records = records;
    }

}
