package company.com.volve.Fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
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
import android.widget.Toast;

import org.json.JSONArray;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import company.com.volve.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("ALL")
public class BikeFragment extends Fragment {
    // event
    private static final String TAG_EVENTS = "data";
    private static final String TAG_WHAT = "name";
    private static final String TAG_WHEN = "start_time";
    private static final String TAG_TIMEZONE = "timezone";
    // place
    private static final String TAG_WHERE = "place";
    private static final String TAG_WHERE_NAME = "name";
    private static final String TAG_WHERE_LOCATION = "location";
    private static final String TAG_WHERE_LOCATION_CITY = "city";
    private static final String TAG_WHERE_LOCATION_COUNTRY = "country";
    private static final String TAG_WHERE_LOCATION_STATE = "state";
    private static final String TAG_WHERE_LOCATION_STREET = "street";
    private static final String TAG_WHERE_LOCATION_ZIP = "zip";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_TYPE = "type";
    // url to get json data
    private static String url = "http://blank.org";
    final String TAG = "EventsTabFragment.java";
    ListView lv;
    int year_range = 2; // get events for the next x years
    // events JSONArray
    JSONArray events = null;
    // Hashmap for ListView
    ArrayList<HashMap<String, String>> eventList;
    private ProgressDialog progressDialog;

    public BikeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events_tab, container, false);

        // get since date
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String since_date = sdfDateTime.format(new Date(System.currentTimeMillis()));

        String since_unix_timestamp = getTimeStamp(since_date);

        // get until date
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdfDateTime.parse(since_date));
        } catch (ParseException e) {
            e.getStackTrace();
        }
        c.add(Calendar.YEAR, year_range);

        Date result_date = new Date(c.getTimeInMillis());
        String until_date = sdfDateTime.format(result_date);

        String until_unix_timestamp = getTimeStamp(until_date);

        eventList = new ArrayList<HashMap<String, String>>();
//        eventList = EventManager.getFilteredList("3"); //3 == SU events
        /*
            Event description is returned when a user clicks on a specific event.
            lv when clicked gets the description of that event.
        */
        lv = view.findViewById(R.id.list);

        // Listview on item click listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final String description = ((TextView) view.findViewById(R.id.description)).getText().toString();
                final String what = ((TextView) view.findViewById(R.id.what)).getText().toString();
                final String when = ((TextView) view.findViewById(R.id.when)).getText().toString().replaceAll("Date/Time:", "");
                final String where = ((TextView) view.findViewById(R.id.where)).getText().toString().replaceAll("Location:", "");;
//                Toast.makeText(getContext(), where, Toast.LENGTH_LONG).show();
                System.out.println("WHEN IS: "+when);
                int index =0;
                final String date_split[] = when.split(", ");
                for(String i: date_split){
                    System.out.println(index+" - "+i);
                    index++;
                }
                final String month[]=date_split[1].split(" ");

                //Background conversion to parse date information in the conventional Android format.
                final int monthNumber = monthNumberConversion(month);
                final int dayNumber = Integer.parseInt(month[1]);
                final String[] time=date_split[2].split(" ");
                final String year = time[0];
                final String timePMAM = time[2];
                final String[] hourMin=time[1].split(":");
                String hour = hourMin[0] ;
                final String min = hourMin[1];
                int hourInt = Integer.parseInt(hour);
                if (timePMAM.equals("PM"))
                    hourInt = hourInt+ 12;
                final int finalHourInt = hourInt;

                new AlertDialog.Builder(view.getContext())
                        .setTitle(description)
                        .setMessage("Date/Time: " + when +
                                "\nVenue:" + where)
                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        })
                        .setNegativeButton("Navigate to Venue", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Uri uriUrl = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + where.replaceAll("Location:", ""));
                                startActivity(new Intent(Intent.ACTION_VIEW, uriUrl));
                            }
                        })
                        .setNeutralButton("Add to my Calendar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_CALENDAR}, 123);

                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CALENDAR}, 1);

                                int calendarID=1;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                    calendarID= 3;
                                }

                                Calendar beginTime = Calendar.getInstance();
                                beginTime.set(Integer.parseInt(year), monthNumber-1, dayNumber, finalHourInt, Integer.parseInt(min));
                                ContentValues values = new ContentValues();
                                values.put("calendar_id", calendarID);
                                values.put(CalendarContract.Events.TITLE, what);
                                values.put(CalendarContract.Events.DESCRIPTION, description);
                                values.put(CalendarContract.Events.EVENT_LOCATION, where);
                                values.put(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
                                values.put("dtstart", beginTime.getTimeInMillis());
                                values.put("allDay", 0);   // 0 for false, 1 for true
                                values.put("eventStatus", 1);
                                values.put("hasAlarm", 1); // 0 for false, 1 for true
                                values.put("duration","PT1H");
                                ContentResolver cr=getActivity().getContentResolver();
                                values.put("eventTimezone", TimeZone.getDefault().getID());
                                Uri url = getContext().getContentResolver().insert(CalendarContract.Events.CONTENT_URI, values);

                                long eventID = Long.parseLong(url.getLastPathSegment());
                                ContentValues reminderValues = new ContentValues();

                                String reminderUriString = "content://com.android.calendar/reminders";
                                reminderValues.put("event_id", eventID);
                                reminderValues.put("minutes", 60);

                                Uri reminderUri = getActivity().getApplicationContext().getContentResolver().
                                        insert(Uri.parse(reminderUriString), reminderValues);
                                Cursor c = CalendarContract.Reminders.query(cr, eventID,
                                        new String[]{CalendarContract.Reminders.MINUTES});
                                c.close();
                                Toast.makeText(getContext(), "THis event is added to your calendar", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });


        // Calling async task to get json
//        new GetEvents().execute();

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
            Log.e("lvAdapter", "Crash avoided with catch block");
        }
        return view;
    }




    // get formatted 'when' field
    public String getWhen(String dateInput, String timeZone) {

        String dateOutput = "";

        try {

            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");

            DateFormat df2 = new SimpleDateFormat("cccc, MMMM d, yyyy hh:mm a");
            df2.setTimeZone(TimeZone.getTimeZone(timeZone));

            dateOutput = df2.format(df1.parse(dateInput));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateOutput;
    }

    // get unix timestamp
    public String getTimeStamp(String ymd) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        try {
            date = (Date) formatter.parse(ymd);
        } catch (ParseException e) {
            e.getStackTrace();
        }

        long output = date.getTime() / 1000L;
        String str = Long.toString(output);
        long timestamp_result = Long.parseLong(str);

        return Long.toString(timestamp_result);
    }

    int monthNumberConversion(String[] month) {
        final int monthNumber;
        switch (month[0]){
            case "January":
                monthNumber = 1;
                break;

            case "February":
                monthNumber = 2;
                break;

            case "March":
                monthNumber = 3;
                break;

            case "April":
                monthNumber = 4;
                break;

            case "May":
                monthNumber = 5;
                break;

            case "June":
                monthNumber = 6;
                break;

            case "July":
                monthNumber = 7;
                break;

            case "August":
                monthNumber = 8;
                break;

            case "September":
                monthNumber = 9;
                break;

            case "October":
                monthNumber = 10;
                break;

            case "November":
                monthNumber = 11;
                break;

            case "December":
                monthNumber = 12;
                break;

            default:
                monthNumber = 0;
                break;
        }
        return monthNumber;
    }

}
