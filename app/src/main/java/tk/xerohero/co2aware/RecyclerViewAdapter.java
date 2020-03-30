package tk.xerohero.co2aware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * This class populates the RecyclerView with data, as well as converts pure Java objects into list
 * item Views to be inserted and displayed to the user in the Android UI.
 *
 * The variable mData (List<String>) will contain the data elements that are in the list. //TODO
 * Possibly change this as required
 *
 * The layout inflater mInflater instantiates the XML Layout object to a Java object so it can be
 * used.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<BikeStation> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    RecyclerViewAdapter(Context context, List<BikeStation> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BikeStation bikeStation = mData.get(position);
        holder.bikeStationAddress.setText(bikeStation.getAddress());
//        Picasso.get().load(bikeStation.getImageLink()).into(holder.recipeImage);
        
//        holder.parkingAvailable
//                .setText("Ingredients match: (" + bikeStation.getUsedIngredients().size() + "/" + (
//                        bikeStation.getMissedIngredients().size() + bikeStation.getUsedIngredients().size()) + ")");
    }

    //get size of list (number of items)
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {

        void onItemClick(View view, int position);
    }

    // dynamically loads only a subset of the mData container to keep resource usage low.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView bikeStationAddress;
        TextView bikesAvailable;
        TextView parkingAvailable;
        TextView distance;

        ViewHolder(View itemView) {
            super(itemView);
            bikeStationAddress = itemView.findViewById(R.id.bike_station_address);
            parkingAvailable = itemView.findViewById(R.id.parking_available);
            bikesAvailable = itemView.findViewById(R.id.bikes_available);
            distance = itemView.findViewById(R.id.distance);
//            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}