package mumcu.sabiha.task3.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mumcu.sabiha.task3.R;
import mumcu.sabiha.task3.model.RecordModel;

/**
 * Created by sabis on 1/30/2018.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private List<RecordModel> recordList;
    private Context context;

    public RecordAdapter(List<RecordModel> recordList, Context context) {
        this.recordList = recordList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvRow.setText(recordList.get(position).getId().getId() + "\n" + recordList.get(position).getId().getKey() + "\n" +
                recordList.get(position).getId().getValue() + "\n" + recordList.get(position).getId().getCreatedAt());

    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvRow;

        public ViewHolder(View itemView) {
            super(itemView);

            tvRow = (TextView) itemView.findViewById(R.id.tv_row);

        }
    }

}
