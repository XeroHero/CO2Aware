package company.com.volve.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

//import company.com.volve.Activities.EventManager;
import company.com.volve.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsideSocFragment extends Fragment {
    ArrayList<HashMap<String, String>> eventList;
    ListView lv;

    //Tags
    // event
    private static final String TAG_WHAT = "name";
    private static final String TAG_WHEN = "start_time";
    private static final String TAG_WHERE = "place";
    private static final String TAG_DESCRIPTION = "description";
    private static int society_page_id = 0;

    public InsideSocFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //filter by society with this id

        View view = inflater.inflate(R.layout.fragment_inside_soc, container, false);
        eventList = new ArrayList<>();
        //TODO show events that have the society_page_id, call in EventManager.getSocID(society_page_id);
//        eventList = EventManager.filterById(society_page_id);
        System.out.println("Not Cancelled"+ eventList.size());
        lv = view.findViewById(R.id.list);

        // ListView on item click listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String description = ((TextView) view.findViewById(R.id.description)).getText().toString();
                String when = ((TextView) view.findViewById(R.id.when)).getText().toString().replaceAll(getString(R.string.date_time), "");
                final String where = ((TextView) view.findViewById(R.id.where)).getText().toString().replaceAll(getString(R.string.location), "");
//                Toast.makeText(getContext(), where, Toast.LENGTH_LONG).show();

                new AlertDialog.Builder(view.getContext()).setTitle(description);
                new AlertDialog.Builder(view.getContext()).setMessage("Date/Time: " + when +
                        "\nVenue:" + where);
                new AlertDialog.Builder(view.getContext()).setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
                new AlertDialog.Builder(view.getContext()).setNegativeButton("Navigate to Venue", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Uri uriUrl = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + where.replaceAll(getString(R.string.location_colon), ""));
                        startActivity(new Intent(Intent.ACTION_VIEW, uriUrl));

                    }
                });
                new AlertDialog.Builder(view.getContext()).show();
            }
        });
        // update parsed data into ListView
        try {
            ListAdapter adapter = new SimpleAdapter(
                    getContext(),
                    eventList,
                    R.layout.list_item,
                    new String[]{
                            TAG_WHAT,
                            TAG_WHEN,
                            TAG_WHERE,
                            TAG_DESCRIPTION
                    },
                    new int[]{
                            R.id.what,
                            R.id.when,
                            R.id.where,
                            R.id.description
                    }
            );

            lv.setAdapter(adapter);
        } catch (Exception e) {
            Log.e("rotationEvent", "Crash avoided with catch block");
        }
        return view;
    }
    public void getMessage(String message){
        society_page_id = Integer.parseInt(message);
        System.out.println("NEw Society page Id: "+society_page_id);
    }

}
