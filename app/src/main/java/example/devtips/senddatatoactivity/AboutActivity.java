package example.devtips.senddatatoactivity;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    TextView aboutTitle;
    TextView aboutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutTitle = findViewById(R.id.about_title_large);
        aboutTitle.setText("About the App");
        aboutText = findViewById(R.id.about_subtitle_medium);
        aboutText.setText("This is my Final Year Project submission. It uses many open-source libraries, " +
                "such as the Gson library to hadnle the JSON retrieved from the JCDecaux API dynamically each time.\n" +
                "All graphics used in this application are royalty-free open graphics from flaticon.com and freeiconspng.com.\n\n" +
                        "All the real-time data is gathered from the JCDecaux API for public city bikes." +
                        "The \"40 ways to go Green\" article is taken from conserve-energy-future.com"
                );
    }
}
