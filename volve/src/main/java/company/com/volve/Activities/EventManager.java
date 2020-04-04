//package company.com.volve.Activities;
//
//import android.content.Context;
//import android.os.AsyncTask;
//import android.os.Environment;
//import android.support.annotation.NonNull;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import static com.facebook.FacebookSdk.getApplicationContext;
//
//@SuppressWarnings("ALL")
//public class EventManager {
//    static final FirebaseDatabase database = FirebaseDatabase.getInstance();
//
//    //Point these to pEvent, pSocieties and pClubs before release
//    static final DatabaseReference myRef = database.getReference("tEvent");
//    static final DatabaseReference mySocRef = database.getReference("tSocieties");
//    static final DatabaseReference myClubRef = database.getReference("tClubs");
//
//
//    //clubList
//    private static ArrayList<HashMap<String, String>> societyList;
//
//    private static String Society_Type = "Type";
//    private static String Society_ID = "ID";
//    private static String Society_Name = "Name";
//    private static String Society_PageID = "PageID";
//
//    private static ArrayList<HashMap<String, String>> clubList;
//    private static String Club_Type = "Type";
//    private static String Club_ID = "ID";
//    private static String Club_Name = "Name";
//    private static String Club_PageID = "PageID";
//    private static String Club_About = "about";
//
//    static final String Tage_ID = "page_id";
//    static final String TAG_WHAT = "name";
//    static final String TAG_WHEN = "start_time";
//    static final String TAG_TYPE = "groupType";
//    static final String TAG_WHERE = "place";
//    static final String TAG_DESCRIPTION = "description";
//    static final String TAG_URL = "url";
//    static JsonDetails events = null;
//    static getEventsOnlineToLocal g = new getEventsOnlineToLocal();
//    private static String seperator = "getVolvd//Seperator";
//
//    private static boolean dbAccessed = false;
//
//    public static void checkList() {
//        //check if file first exists!
//        double start = System.currentTimeMillis();
//        societyList = new ArrayList<HashMap<String, String>>();
//        clubList = new ArrayList<HashMap<String, String>>();
//        //TODO remove "&& false" when we have writing to file synced with retreieving from db.
//        if (JsonFileExist()) {
//            events = pullEvents();
//            if (checkEventVersions(events.getReferenceID())) {
//                try {
//                    pullSocieties();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                pullClubs();
//                return;
//            } else {
//                //update events to online version.
//                g.onPreExecute();
//            }
//        } else {
//            if (onlineStatus()) {
//                g.onPreExecute();
//
//            } else {
//                Toast.makeText(getApplicationContext(), "You are offline please go online!", Toast.LENGTH_LONG).show();
//                //there is no online version and there is no offline version, delete everything, hack everyone
//            }
//        }
//        double end = System.currentTimeMillis();
//        Log.i("TimerEvent", "checkList took: " + (end - start) + " seconds");
//    }
//
//    public static ArrayList<HashMap<String, String>> getEvents() {
//        return events.getJsonObject();
//    }
//
//    public static ArrayList<HashMap<String, String>> getSocietyList() {
//        return societyList;
//    }
//
//    public static ArrayList<HashMap<String, String>> getClubList() {
//        return clubList;
//    }
//
//    public static ArrayList<HashMap<String, String>> getFilteredList(String filterType) {
//        ArrayList<HashMap<String, String>> filteredEvents = new ArrayList<HashMap<String, String>>();
//        ArrayList<HashMap<String, String>> eventList = events.getJsonObject();
//
//        switch (filterType) {
//            case "1": {
//                //societies
//                for (int i = 0; i < eventList.size(); i++) {
//                    HashMap<String, String> event = eventList.get(i);
//                    if (event.get("groupType").equalsIgnoreCase(filterType)) {
//                        filteredEvents.add(event);
//                    }
//                }
//                break;
//            }
//            case "2": {
//                //clubs
//                for (int i = 0; i < eventList.size(); i++) {
//                    HashMap<String, String> event = eventList.get(i);
//                    if (event.get("groupType").equalsIgnoreCase(filterType)) {
//                        filteredEvents.add(event);
//                    }
//                }
//                break;
//            }
//            case "3": {
//                //SU
//                for (int i = 0; i < eventList.size(); i++) {
//                    HashMap<String, String> event = eventList.get(i);
//                    if (event.get("groupType").equalsIgnoreCase(filterType)) {
//                        filteredEvents.add(event);
//                    }
//                }
//                break;
//
//            }
//            default: {
//                Log.e("EventFilterError", "Unrecognized EventType Tag " + filterType);
//            }
//        }
//
//        return filteredEvents;
//    }
//
//    private static boolean isEventASocietyOrClub(String event) {
//        if (event.equalsIgnoreCase("referenceId") || event.equalsIgnoreCase("society") || event.equalsIgnoreCase("club")) {
//            return true;
//        }
//        return false;
//    }
//
//    private static boolean JsonFileExist() {
//        String jsonFileName = "VolvdEvents.txt";
//        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + jsonFileName);
//        //check if file exists
//        File directory = getApplicationContext().getFilesDir();
//        String ourFile = directory + "//" + jsonFileName;
//        File g = new File(ourFile);
//        if (g.exists()) {
//            Log.i("LocalJSON", "File Path: " + file.getAbsolutePath());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private static JsonDetails pullEvents() {
//        // TODO Auto-generated method stub
//        ArrayList<HashMap<String, String>> jsonEvents = new ArrayList<HashMap<String, String>>();
//        String fileName = "VolvdEvents.txt";
//        int jsonFileVerion = 1;
//        /*
//         * Do something here that pulls all the events from a jsonFile
//         */
//        //read from txtFile, easier.
//        BufferedReader br = null;
//
//        try {
//            //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + fileName);
//            File directory = getApplicationContext().getFilesDir();
//            File file = new File(directory.getAbsolutePath() + "//" + fileName);
//
//
//            br = new BufferedReader(new FileReader(file.getAbsolutePath()));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//            while (line != null) {
//                String[] events = line.split(seperator);
//                String description = "";
//                String name = "";
//                String place = "";
//                String when = "";
//                String eventType = "";
//                String id = "";
//                Log.i("EventData", "Event: " + line);
//                boolean wasRef = false;
//                for (String event : events) {
//                    if (!event.equals("") && !isEventASocietyOrClub(event)) {
//
//
//                        if (event.split(":")[0].equalsIgnoreCase("description")) {
//                            description = event.split(":")[1];
//                        } else if (event.split(":")[0].equalsIgnoreCase("name")) {
//                            name = event.replace("name:", "");
//                        } else if (event.split(":")[0].equalsIgnoreCase("place")) {
//                            place = event.split(":Location: ")[1];
//                        } else if (event.split(":")[0].equalsIgnoreCase("start_time")) {
//                            when = event.split(":Date/Time: ")[1];
//                        } else if (event.split(":")[0].equalsIgnoreCase("groupType")) {
//                            eventType = event.split(":")[1];
//                        } else if (event.split(":")[0].equalsIgnoreCase(("page_id"))) {
//                            System.out.println("EVENTID: " + event);
//                            id = event.split(":")[1];
//                        }
//
//                    } else if (event.split(":")[0].equalsIgnoreCase("referenceId")) {
//                        name = "WasRef";
//                        jsonFileVerion = Integer.parseInt(event.split(":")[1]);
//                        wasRef = true;
//                    }
//                }
//                if (!line.equalsIgnoreCase("") && !line.equalsIgnoreCase(" ")) {
//                    HashMap<String, String> eventObject = new HashMap<String, String>();
//                    // adding each child node to HashMap key => value
//                    eventObject.put(TAG_WHAT, name);
//                    eventObject.put(TAG_WHEN, "Date/Time: " + when);
//                    eventObject.put(TAG_TYPE, eventType);
//                    eventObject.put(TAG_WHERE, "Location: " + place);
//                    eventObject.put(TAG_DESCRIPTION, description);
//                    eventObject.put(Tage_ID, id);
//                    // adding event to event list
//                    jsonEvents.add(eventObject);
//                } else if (wasRef) {
//                    wasRef = false;
//                }
//                line = br.readLine();
//            }
//            String everything = sb.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        JsonDetails newJsonObject = new JsonDetails(jsonFileVerion, jsonEvents);
//        return newJsonObject;
//    }
//
//    private static void pullSocieties() throws FileNotFoundException {
//        ArrayList<HashMap<String, String>> jsonEvents = new ArrayList<HashMap<String, String>>();
//        String fileName = "VolvdSocieties.txt";
//        int jsonFileVerion = 1;
//        BufferedReader br = new BufferedReader(new FileReader(fileName));
//        /*
//         * Do something here that pulls all the events from a jsonFile
//         */
//        //read from txtFile, easier.
//        try {
//            //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + fileName);
//            File directory = getApplicationContext().getFilesDir();
//            File file = new File(directory.getAbsolutePath() + "//" + fileName);
//
//
//            br = new BufferedReader(new FileReader(file.getAbsolutePath()));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//            //System.out.println("Output data: \n"+line);
//            while (line != null) {
//                System.out.println("Line: " + line);
//                String[] events = line.split(seperator);
//                String name = "";
//                String type = "";
//                String id = "";
//                String page_id = "";
//                boolean wasRef = false;
//                for (String event : events) {
//
//                    //System.out.println("Event: "+event);
//                    if (!event.equals("") && !isEventASocietyOrClub(event)) {
//
//                        if (event.split(":")[0].equalsIgnoreCase(Society_Name)) {
//                            name = event.replace(Society_Name + ":", "");
//                        } else if (event.split(":")[0].equalsIgnoreCase(Society_Type)) {
//                            //System.out.println("EVENTMANGERPLACE: "+event.split(":Location:")[1]);
//                            type = event.split(":")[1];
//                        } else if (event.split(":")[0].equalsIgnoreCase(Society_PageID)) {
//                            page_id = event.split(":")[1];
//                        } else if (event.split(":")[0].equalsIgnoreCase(Society_ID)) {
//                            id = event.split(":")[1];
//                        }
//
//                    }
//                }
//                if (!line.equalsIgnoreCase("") && !line.equalsIgnoreCase(" ")) {
//                    HashMap<String, String> eventObject = new HashMap<String, String>();
//                    // adding each child node to HashMap key => value
//                    System.out.println("SOCIETYNAME: " + name);
//                    eventObject.put(Society_Name, name);
//                    eventObject.put(Society_Type, type);
//                    eventObject.put(Society_PageID, page_id);
//                    eventObject.put(Society_ID, id);
//                    // adding event to event list
//                    societyList.add(eventObject);
//                }
//                line = br.readLine();
//                //System.out.println("ReadLines: "+line);
//            }
//            String everything = sb.toString();
//            //System.out.println("Everything:\n"+everything);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//   private static void pullClubs() {
//       ArrayList<HashMap<String, String>> jsonEvents = new ArrayList<HashMap<String, String>>();
//       String fileName = "VolvdClubs.txt";
//       int jsonFileVerion = 1;
//        /*
//         * Do something here that pulls all the events from a jsonFile
//         */
//        //read from txtFile, easier.
//        BufferedReader br = null;
//
//        try {
//            //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + fileName);
//            File directory = getApplicationContext().getFilesDir();
//            File file = new File(directory.getAbsolutePath() + "//" + fileName);
//
//
//            br = new BufferedReader(new FileReader(file.getAbsolutePath()));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (br == null){
//            return;
//        }
//
//        try {
//            StringBuilder sb = new StringBuilder();
//
//            String line = br.readLine();
//                        //System.out.println("Output data: \n"+line);
//            while (line != null) {
////                System.out.println("Line: " + line);
//                String[] events = line.split(seperator);
//                String name = "";
//                String type = "";
//                String id = "";
//                String about = "";
//                String page_id = "";
//                boolean wasRef = false;
//                for (String event : events) {
//
//                    //System.out.println("Event: "+event);
//                    if (!event.equals("") && !isEventASocietyOrClub(event)) {
//
//                        if (event.split(":")[0].equalsIgnoreCase(Club_Name)) {
//                            if (event.split(":").length != 1)
//                                name = event.split(":")[1];
//                            else
//                                name = "No name given";
//                        } else if (event.split(":")[0].equalsIgnoreCase(Club_Type)) {
//                            //System.out.println("EVENTMANGERPLACE: "+event.split(":Location:")[1]);
//                            if (event.split(":").length != 1)
//                                type = event.split(":")[1];
//                            else
//                                type = "No type given";
//                        } else if (event.split(":")[0].equalsIgnoreCase(Club_PageID)) {
//                            if (event.split(":").length != 1)
//                                page_id = event.split(":")[1];
//                            else
//                                page_id = "NULL";
//                        } else if (event.split(":")[0].equalsIgnoreCase(Club_ID)) {
//                            if (event.split(":").length != 1)
//                                id = event.split(":")[1];
//                            else
//                                id = "No id";
//                        } else if (event.split(":")[0].equalsIgnoreCase(Club_About)) {
//                            if (event.split(":").length != 1)
//                                about = event.split(":")[1];
//                            else
//                                about = "No Description";
//                        }
//
//                    }
//                }
//                if (!line.equalsIgnoreCase("") && !line.equalsIgnoreCase(" ")) {
//                    HashMap<String, String> clubObject = new HashMap<String, String>();
//                    // adding each child node to HashMap key => value
//                    clubObject.put(Club_Name, name);
//                    clubObject.put(Club_Type, type);
//                    clubObject.put(Club_PageID, page_id);
//                    clubObject.put(Club_ID, id);
//                    clubObject.put(Club_About, about);
//                    // adding event to event list
//                    clubList.add(clubObject);
//                }
//                line = br.readLine();
//                //System.out.println("ReadLines: "+line);
//            }
//            String everything = sb.toString();
//            //System.out.println("Everything:\n"+everything);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                br.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private static String returnEventAttribute(String[] event, String attribute) {
//        String val = "";
//
//        return val;
//    }
//
//
//    private static boolean checkEventVersions(int currentVersionReferenceID) {
//
//        /*
//         * Do something here that pulls the db.
//         */
//
//        int onlineReferenceID = getOnlineReferenceID();
//        Log.i("JSONVersion", "Remote " + onlineReferenceID);
//        Log.i("JSONVersion", "Local " + currentVersionReferenceID);
//        if (onlineReferenceID <= currentVersionReferenceID) {
//            /*
//             * online version of db is same as current one. do nothing
//             */
//            return true;
//        } else {
//            /*
//             * online version is more up to date, use it.
//             */
//            return false;
//        }
//    }
//
//    private static int getOnlineReferenceID() {
//        int ret = 0;
//
//        /*
//         * make online call
//         * if online is not available then
//         */
//        if (onlineStatus()) {
//            /*
//             * Make call to firebase db and get only referenceID.
//             */
//            final int onlineReferenceID[] = new int[10];
//            final boolean dataGot[] = new boolean[10];
//
//            final FirebaseDatabase database = FirebaseDatabase.getInstance();
//            final DatabaseReference getId = database.getReference("id");
//            dataGot[0] = false;
//            getId.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    Log.i("DataSnapshot", "Current Value aquiring is: " + dataSnapshot.child("currentReference").getValue());
//                    int ref = Integer.parseInt(dataSnapshot.child("currentReference").getValue().toString());
//                    onlineReferenceID[0] = ref;
//                    dataGot[0] = true;
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                }
//            });
//
//            ret = onlineReferenceID[0];
//        } else {
//            if (events.getReferenceID() < 0) {
//                //first instance with no online, return some fucked up error.
//            } else {
//                ret = events.getReferenceID() - 1;
//            }
//        }
//        return ret;
//    }
//
//    private static JsonDetails getOnlineJsonObject() {
//        // Hashmap for ListView
//
//        final boolean databaseSuccessfullyAccessed[] = new boolean[10];
//        databaseSuccessfullyAccessed[0] = false;
//        final ArrayList<HashMap<String, String>> onlineJsonObject = new ArrayList<HashMap<String, String>>();
//
//        final DatabaseReference getId = database.getReference("id");
//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            boolean processDone = false;
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (!processDone && dataSnapshot.exists()) {
//                    for (int i = 1; i < dataSnapshot.getChildrenCount(); i++) {
//                        //each node is dataSnapshot..child(Integer.toString(i));
//                        String description = dataSnapshot.child(Integer.toString(i)).child("description").getValue().toString().replace("DESCRIPTION: ", "");
//                        String name = dataSnapshot.child(Integer.toString(i)).child("name").getValue().toString().replace("WHAT: ", "");
//                        String place = dataSnapshot.child(Integer.toString(i)).child("place").getValue().toString().replace("WHERE: ", "");
//                        String when = dataSnapshot.child(Integer.toString(i)).child("start_time").getValue().toString().replace("WHEN: ", "");
//                        String eventType = dataSnapshot.child(Integer.toString(i)).child("groupType").getValue().toString();
//                        String id = dataSnapshot.child(Integer.toString(i)).child("page_id").getValue().toString();
//                        HashMap<String, String> event = new HashMap<String, String>();
//                        // adding each child node to HashMap key => value
//                        event.put(TAG_WHAT, name);
//                        event.put(TAG_WHEN, "Date/Time: " + when);
//                        event.put(TAG_TYPE, eventType);
//                        event.put(TAG_WHERE, "Location: " + place);
//                        event.put(TAG_DESCRIPTION, description);
//                        event.put(Tage_ID, id);
//
//                        // adding event to event list
//                        onlineJsonObject.add(event);
//
//                    }
//                    databaseSuccessfullyAccessed[0] = true;
//                    dbAccessed = true;
//                    g.onPostExecute(null);
//                } else {
//                    //process 2 <- No idea what i was doing.
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//        int referenceId = 0;
//        final boolean[] hasBeenRead = new boolean[2];
//        hasBeenRead[0] = false;
//        final int[] dbRefId = new int[2];
//        getId.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                dbRefId[0] = Integer.parseInt(dataSnapshot.child("currentReference").getValue().toString());
//                hasBeenRead[0] = true;
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        JsonDetails g = new JsonDetails(dbRefId[0], onlineJsonObject);
//        return g;
//    }
//
//    private static boolean onlineStatus() {
//        //checks if there is online.
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
//            int exitValue = ipProcess.waitFor();
//            return (exitValue == 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//    private static void updateJsonFIle() {
//        String filename = "VolvdEvents.txt";
//        String fileContents = getEventsForText();
//        FileOutputStream outputStream;
//
//        try {
//            outputStream = getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(fileContents.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void updateSocietyFile() {
//        String filename = "VolvdSocieties.txt";
//        String fileContents = getSocForText();
//        FileOutputStream outputStream;
//
//        try {
//            outputStream = getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(fileContents.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void updateClubFile() {
//        String filename = "VolvdClubs.txt";
//        String fileContents = getClubForText();
//        System.out.println("201818: " + fileContents);
//        FileOutputStream outputStream;
//
//        try {
//            outputStream = getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(fileContents.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private static String getEventsForText() {
//        //Function returns string format of all events.
//        ArrayList<HashMap<String, String>> eventList = events.getJsonObject();
//        String stringOfEvents = "";
//        Log.i("EventListSize", "EventList Size is: " + eventList.size());
//        //Iterate through arrayList and add each event and its details.
//        for (int i = 0; i < eventList.size(); i++) {
//            String event = "";
//            HashMap<String, String> currEv = eventList.get(i);
//            event = "description:" + currEv.get(TAG_DESCRIPTION) + seperator;
//            event += Tage_ID + ":" + currEv.get(Tage_ID) + seperator;
//            event += "name:" + currEv.get(TAG_WHAT) + seperator;
//            event += "place:" + currEv.get(TAG_WHERE) + seperator;
//            event += "start_time:" + currEv.get(TAG_WHEN) + seperator;
//            event += "groupType:" + currEv.get(TAG_TYPE);
//            stringOfEvents += event + "\n";
//        }
//        stringOfEvents += "referenceId:" + events.getReferenceID() + seperator;
//        return stringOfEvents;
//    }
//
//    private static String getClubForText() {
//        String stringOfClubs = "";
//        System.out.println("CLUBLIST SIZE: " + clubList.size());
//        for (int i = 0; i < clubList.size(); i++) {
//            String club = "";
//
//            HashMap<String, String> currentClub = clubList.get(i);
//            club += Club_Type + ":" + currentClub.get(Club_Type) + seperator;
//            club += Club_Name + ":" + currentClub.get(Club_Name) + seperator;
//            club += Club_PageID + ":" + currentClub.get(Club_PageID) + seperator;
//            club += Club_ID + ":" + currentClub.get(Club_ID) + seperator;
//            club += Club_About + ":" + currentClub.get(Club_About) + seperator;
//            System.out.println("CLUBLINE: " + club);
//            stringOfClubs += club + "\n";
//        }
//        return stringOfClubs;
//    }
//
//    private static String getSocForText() {
//        String stringOfSoc = "";
//        for (int i = 0; i < societyList.size(); i++) {
//            String soc = "";
//            HashMap<String, String> currentSoc = societyList.get(i);
//            soc = Society_Type + ":" + currentSoc.get(Society_Type) + seperator;
//            soc += Society_Name + ":" + currentSoc.get(Society_Name) + seperator;
//            soc += Society_PageID + ":" + currentSoc.get(Society_PageID) + seperator;
//            soc += Society_ID + ":" + currentSoc.get(Society_ID) + seperator;
//            stringOfSoc += soc + "\n";
//        }
//        return stringOfSoc;
//    }
//
//    public static ArrayList<HashMap<String, String>> filterById(
//            int society_club_id) {
//        ArrayList<HashMap<String, String>> filteredEvents = new ArrayList<HashMap<String, String>>();
//        ArrayList<HashMap<String, String>> eventList = events.getJsonObject();
//        for (int i = 0; i < eventList.size(); i++) {
//            HashMap<String, String> event = eventList.get(i);
//            System.out.println(society_club_id + " :=: " + event.get(Tage_ID));
//            if (event.get(Tage_ID).equalsIgnoreCase(Integer.toString(society_club_id))) {
//                filteredEvents.add(event);
//            }
//        }
//        return filteredEvents;
//    }
//
//    private static void developClubList() {
//        Log.i("DEVELOPING CLUBLIST", "");
//        clubList = new ArrayList<HashMap<String, String>>();
//        myClubRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (int i = 1; i < dataSnapshot.getChildrenCount(); i++) {
//                    Log.i("DataSnapshot = ", dataSnapshot.child(Integer.toString(i)).getValue().toString());
//                    String groupType = dataSnapshot.child(Integer.toString(i)).child("groupType").getValue().toString();
//                    String id = dataSnapshot.child(Integer.toString(i)).child("clubID").getValue().toString();
//                    String name = dataSnapshot.child(Integer.toString(i)).child("clubName").getValue().toString();
//                    String pageId = dataSnapshot.child(Integer.toString(i)).child("page_id").getValue().toString();
//                    String about = dataSnapshot.child(Integer.toString(i)).child("aboutClub").getValue().toString();
//                    Log.i("ABOUT: ",about);
//                    Log.i("PAGEID: ",pageId);
//                    HashMap<String, String> club = new HashMap<String, String>();
//                    club.put(Club_Type, groupType);
//                    club.put(Club_Name, name);
//                    club.put(Club_PageID, pageId);
//                    club.put(Club_ID, id);
//                    club.put(Club_About, about);
//                    if (club != null) {
//                        System.out.println("pageIDOFCLUB: " + club.get(Club_About));
//                        clubList.add(club);
//                        System.out.println(Club_Type + " : " + groupType);
//                        System.out.println(Club_Name + " : " + name);
//                    }
//
//                }
//                updateClubFile();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.e("cancelledUpdateDb","ERROR OCCURED");
//            }
//        });
//    }
//
//    private static void developSocietyList() {
//        mySocRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (int i = 1; i < dataSnapshot.getChildrenCount(); i++) {
//                    Log.i("DataSnapshot: ", "" + dataSnapshot.child(Integer.toString(i)).getValue());
//                    String groupType = dataSnapshot.child(Integer.toString(i)).child("groupType").getValue().toString();
//                    String id = dataSnapshot.child(Integer.toString(i)).child("id").getValue().toString();
//                    String name = dataSnapshot.child(Integer.toString(i)).child("name").getValue().toString();
//                    String pageId = dataSnapshot.child(Integer.toString(i)).child("pageID").getValue().toString();
//                    HashMap<String, String> society = new HashMap<String, String>();
//                    society.put(Society_Type, groupType);
//                    society.put(Society_Name, name);
//                    society.put(Society_PageID, pageId);
//                    society.put(Society_ID, id);
//                    if (society != null) {
//                        societyList.add(society);
//                        Log.i("SocType: ", groupType);
//                        Log.i("SocName: ", name);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private static class JsonDetails {
//        int refId;
//        ArrayList<HashMap<String, String>> jsonObject;
//
//        public JsonDetails(int referenceID, ArrayList<HashMap<String, String>> storedJsonObject) {
//            refId = referenceID;
//            jsonObject = storedJsonObject;
//        }
//
//        public int getReferenceID() {
//            return refId;
//        }
//
//        public void setReferenceID(int updatedReferenceID) {
//            refId = updatedReferenceID;
//        }
//
//        public ArrayList<HashMap<String, String>> getJsonObject() {
//            return jsonObject;
//        }
//
//        public void setJsonObject(ArrayList<HashMap<String, String>> jObj) {
//            while (!dbAccessed) ;
//            jObj = jsonObject;
//        }
//    }
//
//    static class getEventsOnlineToLocal extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected void onPreExecute() {
//            Log.i("EventRetrieval", "Getting Events");
//            events = getOnlineJsonObject();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            updateJsonFIle();
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            updateJsonFIle();
//        }
//    }
//}
