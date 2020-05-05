package example.devtips.senddatatoactivity;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FriendsActivity extends Activity {
    ArrayList<String> friendsArray = new ArrayList<>();

    private Button save;
    Button cancel;
    private EditText user;
    LayoutInflater layoutInflater;
    View popupInputDialogView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Friends using the App");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        save = new Button(this);
        cancel = new Button(this);
        user = new EditText(this);
        layoutInflater = LayoutInflater.from(this);
        listView = findViewById(R.id.friends_list);
        popupInputDialogView = layoutInflater.inflate(R.layout.popup_input_dialog, null);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_friends_listview, friendsArray);


        listView.setAdapter(adapter);
        setTheme(R.style.Theme_AppCompat);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a AlertDialog Builder.
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FriendsActivity.this);
                // Set title, icon, can not cancel properties.
                alertDialogBuilder.setTitle("Add a friend");
                alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
                alertDialogBuilder.setCancelable(false);

                // Init popup dialog view and it's ui controls.
                initPopupViewControls();

                // Set the inflated layout view object to the AlertDialog builder.
                alertDialogBuilder.setView(popupInputDialogView);

                // Create AlertDialog and show.
                final AlertDialog alertDialog = alertDialogBuilder.create();


                // When user click the save user data button in the popup dialog.
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Get data from popup dialog editeext.
                        String userName = user.getText().toString();
                        // Create data for the listview.
                        String[] titleArr = {"User Name"};
                        String[] dataArr = {userName};

//                        ArrayList<String> itemDataList = new ArrayList<>();

                        int titleLen = titleArr.length;
                        List<String> listItemMap = null;
                        for (String s : titleArr) {
                            listItemMap = new ArrayList<String>();
                            listItemMap.add(s);
                            friendsArray.add(userName);
                        }

//                        itemDataList.add(String.valueOf(listItemMap));
                        listView.setItemsCanFocus(true);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_friends_listview, friendsArray);
                        listView.setAdapter(adapter);
                        alertDialog.cancel();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });
    }


    /* Initialize popup dialog view and ui controls in the popup dialog. */
    private void initPopupViewControls() {
        // Get layout inflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        // Inflate the popup dialog from a layout xml file.
        popupInputDialogView = layoutInflater.inflate(R.layout.popup_input_dialog, null);

        // Get user input edittext and button ui controls in the popup dialog.
        user = popupInputDialogView.findViewById(R.id.userName);
        save = popupInputDialogView.findViewById(R.id.save);
        cancel = popupInputDialogView.findViewById(R.id.cancel);

        // Display values from the main activity list view in user input edittext.
//        initEditTextUserDataInPopupDialog();
    }


    /* Get current user data from listview and set them in the popup dialog edittext controls. */
    private void initEditTextUserDataInPopupDialog() {
        List<String> userDataList = getExistUserDataInListView(listView);

        if (userDataList.size() == 3) {
            String userName = userDataList.get(0);


            if (user != null) {
                user.setText(userName);
            }
        }
    }

    /* If user data exist in the listview then retrieve them to a string list. */
    private List<String> getExistUserDataInListView(ListView listView) {
        List<String> ret = new ArrayList<String>();

        if (listView != null) {
            ListAdapter listAdapter = listView.getAdapter();

            if (listAdapter != null) {

                int itemCount = listAdapter.getCount();

                for (int i = 0; i < itemCount; i++) {
                    Object itemObject = listAdapter.getItem(i);
                    HashMap<String, String> itemMap = (HashMap<String, String>) itemObject;

                    Set<String> keySet = itemMap.keySet();

                    Iterator<String> iterator = keySet.iterator();

                    String key = iterator.next();

                    String value = itemMap.get(key);

                    ret.add(value);
                }
            }
        }

        return ret;
    }
}
