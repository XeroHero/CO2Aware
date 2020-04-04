package company.com.volve.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import company.com.volve.R;

public class LoadingScreen extends Activity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startHeavyProcessing();

        mTextView = findViewById(R.id.text_loadingScreen);

    }

    private void startHeavyProcessing() {
        new LongOperation().execute("");
    }
    @SuppressLint("StaticFieldLeak")
    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Intent mainIntent = new Intent(LoadingScreen.this, MainOpeningScreen.class);
            startActivity(mainIntent);
            finish();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}