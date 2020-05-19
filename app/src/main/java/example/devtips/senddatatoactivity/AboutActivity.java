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
        aboutText.setText("This is my UCD Final Year Project submission for the Completion of my BSc Computer Science degree.\n\n" +
                        " It uses many open-source libraries, such as the Gson library to hadnle the JSON retrieved from the " +
                        "JCDecaux API dynamically each time.\n\n" +
                "All graphics used in this application are royalty-free open graphics from flaticon.com and freeiconspng.com.\n\n" +
                        "All the real-time data is gathered from the JCDecaux API for public city bikes." +
                        "The \"40 ways to go Green\" article is taken from conserve-energy-future.com\n\n" +
                "Layout design is using build-in Android Buttons (customised) and Google MaterialUI \n\n" +
                        "Toast Messages hae been created with Toasty (by @GrenderG): The usual Toast, but with steroids \uD83D\uDCAA\n"
                );
    }
}
