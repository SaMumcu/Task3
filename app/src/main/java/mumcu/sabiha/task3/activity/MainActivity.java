package mumcu.sabiha.task3.activity;

import android.app.ActionBar;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import mumcu.sabiha.task3.R;
import mumcu.sabiha.task3.adapter.RecordAdapter;
import mumcu.sabiha.task3.api.ApiInterface;
import mumcu.sabiha.task3.api.ApiRequest;
import mumcu.sabiha.task3.model.BaseModel;
import mumcu.sabiha.task3.model.BodyModel;
import mumcu.sabiha.task3.model.RecordModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.applandeo.materialcalendarview.CalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private RelativeLayout relativeLayout;
    private PopupWindow popupWindow;
    private CalendarView calendarView;
    private Button setButton;
    private TextView tvStart, tvEnd;
    private EditText etVal1, etVal2;
    private ImageView ivGetir;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ImageView next, prev;
    private LinearLayout ll;
    List<RecordModel> recordList;
    int range = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        tvStart = (TextView) findViewById(R.id.tv_startDate);
        tvEnd = (TextView) findViewById(R.id.tv_endDate);
        etVal1 = (EditText) findViewById(R.id.et_val1);
        etVal2 = (EditText) findViewById(R.id.et_val2);
        ivGetir = (ImageView) findViewById(R.id.iv_getir);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        next = (ImageView) findViewById(R.id.iv_nextPage);
        prev = (ImageView) findViewById(R.id.iv_prevPage);


        ivGetir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                recyclerView.setVisibility(View.VISIBLE);

                if (!etVal1.getText().toString().equals("") && !etVal2.getText().toString().equals("") && !tvStart.getText().equals("start date") && !tvEnd.getText().equals("end date")) {

                    String[] start = tvStart.getText().toString().split("/");
                    String day = start[0];
                    String month = start[1];
                    String year = start[2];
                    String startDate = year + "-" + month + "-" + day;

                    String[] end = tvEnd.getText().toString().split("/");
                    String day2 = end[0];
                    String month2 = end[1];
                    String year2 = end[2];
                    String endDate = year2 + "-" + month2 + "-" + day2;

                    Toast.makeText(getApplicationContext(), "getiriyoruz", Toast.LENGTH_SHORT).show();

                    BodyModel bodyModel = new BodyModel();
                    bodyModel.setStartDate(startDate);
                    bodyModel.setEndDate(endDate);
                    bodyModel.setMinCount(Integer.parseInt(etVal1.getText().toString()));
                    bodyModel.setMaxCount(Integer.parseInt(etVal2.getText().toString()));

                    ApiInterface apiInterface = ApiRequest.getApiService();

                    Call<BaseModel> baseModelCall = apiInterface.getData(bodyModel);
                    baseModelCall.enqueue(new Callback<BaseModel>() {
                        @Override
                        public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                            if (response.isSuccessful()) {

                                recordList = response.body().getRecords();
                                if (recordList.size() > 10) {
                                    next.setVisibility(View.VISIBLE);
                                }
                                setData();
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseModel> call, Throwable t) {

                        }
                    });


                } else {
                    Toast.makeText(getApplicationContext(), "getiremiyoruz!!paremetrelerde sorun var.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void setRange(View view) {

        /*more than one search-hiding showing*/
        recyclerView.setVisibility(View.INVISIBLE);

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        View popup = inflater.inflate(R.layout.popup_layout, null);

        popupWindow = new PopupWindow(popup, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

        calendarView = (CalendarView) popup.findViewById(R.id.calendarView);
        setButton = (Button) popup.findViewById(R.id.btn_set);


        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Calendar calendar=calendarView.getFirstSelectedDate();
                String firstDate=calendar.getTime().toString();*/

                Calendar calendar = calendarView.getSelectedDates().get(0);
                Calendar calendar1 = calendarView.getSelectedDates().get(calendarView.getSelectedDates().size() - 1);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                String firstDate = sdf.format(new Date(String.valueOf(calendar.getTime())));
                tvStart.setText(firstDate);

                String lastDate = sdf.format(new Date(String.valueOf(calendar1.getTime())));
                tvEnd.setText(lastDate);

                //String firstDate=calendarView.getSelectedDates().get(0).getTime().toString();
                //String lastDate=calendarView.getSelectedDates().get(calendarView.getSelectedDates().size()-1).getTime().toString();
                Toast.makeText(getApplicationContext(), "" + firstDate + " " + lastDate, Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });

    }

    private void setData() {
        List<RecordModel> subList = null;
        if (recordList.size() < 10) {
            subList = new ArrayList<RecordModel>(recordList);
        } else if(recordList.size()>10 && range<recordList.size()-10){
            subList = new ArrayList<RecordModel>(recordList.subList(range, range + 10));
        }
        else if(recordList.size()>10 && range>recordList.size()-10){
            int diff=recordList.size()-range;
            subList = new ArrayList<RecordModel>(recordList.subList(range, range + diff));
        }


        RecordAdapter recordAdapter = new RecordAdapter(subList, getApplicationContext());
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recordAdapter);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                range += 10;
                if (range < recordList.size() - 10) {
                    next.setVisibility(View.VISIBLE);
                    prev.setVisibility(View.VISIBLE);
                } else if (range == recordList.size() - 10) {
                    next.setVisibility(View.INVISIBLE);
                }else if(range>recordList.size()-10){
                    next.setVisibility(View.INVISIBLE);
                }
                setData();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                range -= 10;
                if (range >=10) {
                    prev.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                } else if (range < 10) {
                    prev.setVisibility(View.INVISIBLE);
                }
                setData();
            }
        });

    }
}
