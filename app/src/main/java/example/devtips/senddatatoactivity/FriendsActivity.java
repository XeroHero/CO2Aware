package example.devtips.senddatatoactivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class FriendsActivity extends AppCompatActivity {
    String[] friendsArray = {"Bob Marley", "Louis Armstrong", "George Benson"};
    ListView friendsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Friends using the App");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_friends_listview, friendsArray);

        ListView listView = (ListView)findViewById(R.id.friends_list);

        listView.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
