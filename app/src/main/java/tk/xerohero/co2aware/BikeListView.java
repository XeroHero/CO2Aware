package tk.xerohero.co2aware;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * View that shows a list of recipes that is sent to it -> Primary purpose is to display search
 * results.
 */
public class BikeListView extends AppCompatActivity {

    RecyclerViewAdapter rvaAdapter;
    ArrayList<BikeStation> recipeHeaders;

    // Setup everything as required so that search aresults can be shown.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_list);
        Intent intent = getIntent();

        Bundle message = intent.getExtras();

        recipeHeaders = (ArrayList<BikeStation>) intent.getSerializableExtra("recipeHeaders");

        String recipeListHeading = getIntent().getStringExtra("RecipeTypes");
        TextView header = findViewById(R.id.textView);

        //Set the heading text of the current recipe list (favourites, search results)
        header.setText(recipeListHeading);
        /**
         * @author: Lorenzo Battilocchi
         */
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rvaAdapter = new RecyclerViewAdapter(this, recipeHeaders);
        recyclerView.setAdapter(rvaAdapter);

//        rvaAdapter.setClickListener(this);


    }


}
