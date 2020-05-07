package example.devtips.senddatatoactivity.unused;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import example.devtips.senddatatoactivity.BikeStation;
import example.devtips.senddatatoactivity.R;

/**
 * Created by ankit on 27/10/17.
 */

public class BikeStationAdapter extends RecyclerView.Adapter<BikeStationAdapter.ViewHolder> {

    private Context context;
    private List<BikeStation> list;

    public BikeStationAdapter(Context context, List<BikeStation> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BikeStation movie = list.get(position);

//        holder.textTitle.setText(movie.getAddress());
//        holder.textRating.setText(String.valueOf(movie.getBikesAvailable()));
//        holder.textYear.setText(String.valueOf(movie.getParkingAvailable()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle, textRating, textYear;

        public ViewHolder(View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.main_title);
            textRating = itemView.findViewById(R.id.main_rating);
            textYear = itemView.findViewById(R.id.main_year);
        }
    }

}