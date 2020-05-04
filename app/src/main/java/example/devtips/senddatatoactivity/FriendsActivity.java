package example.devtips.senddatatoactivity;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class FriendsActivity extends AppCompatActivity {
    String[] friendsArray = {"Bob Marley", "Louis Armstrong", "George Benson"};
    ListView friendsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_friends_listview, friendsArray);

        ListView listView = (ListView)findViewById(R.id.friends_list);

        listView.setAdapter(adapter);
    }
}
