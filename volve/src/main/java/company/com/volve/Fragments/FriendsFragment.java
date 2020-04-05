package company.com.volve.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import company.com.volve.R;

public class FriendsFragment extends android.support.v4.app.Fragment {


    @SuppressWarnings("ALL")
    public class GreenTipsFragment extends android.support.v4.app.Fragment {

        //        Button Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9, Button10, Button11, Button12, Button13, Button14, Button15;
        private View view;
//
//        private void goToSocieties(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.societiesUCD_url));
//        }
//
//        private void goToUCDOfficial(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.ucd_url));
//        }
//
//        private void goToCSMoodle(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.csmoodle_url));
//        }
//
//        public void goToSisWeb(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.sisweb_url));
//        }
//
//        public void goToLibrary(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.library_url));
//        }
//
//        public void goToCampusIE(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.campusIE_url));
//        }
//
//        public void goToInfoViewUCD(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.infoview_url));
//        }
//
//        public void goToCareersConnectUCD(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.careersconnect_url));
//        }
//
//        public void goToITServicesUCD(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.IT_services_url));
//        }
//
//        public void goToSU_UCD(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.ucdsu_url));
//        }
//
//        public void goToConnectUCD(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.ucdconnect_url));
//        }
//
//        public void goToRegistryUCD(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.registry_url));
//        }
//
//        public void goToStudentSupportUCD(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.support_url));
//        }
//
//        public void goToUnicareUCD(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.unicare_url));
//        }
//
//        public void goToMapsUCD(View view) {
//            this.view = view;
//            goToUrl(getString(R.string.ucd_estates_url));
//        }
//
//        private void goToUrl(String url) {
//            if (!url.startsWith("http://") && !url.startsWith("https://"))
//                url = "http://" + url;
//            Uri uriUrl = Uri.parse(url);
//            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
//            startActivity(launchBrowser);
//        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = new View(this.getContext());

            TableLayout tl;
            tl = (TableLayout) view.findViewById(R.id.fragment1_tlayout);

            for (int i = 0; i < 30; i++) {

                TableRow tr = new TableRow(getActivity());

                tr.setId(i);
                tr.setBackgroundResource(Color.green(1));
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                //TEXTVIEWS********
                TextView tv1 = new TextView(getActivity());
                tv1.setText("TEST NUMBER");
                tv1.setId(i);
                tv1.setTextColor(Color.WHITE);
                tv1.setTextSize(20);
                tv1.setPadding(5, 5, 5, 5);
                tr.addView(tv1);

                TextView tv2 = new TextView(getActivity());

                tv2.setText("nº: " + i);
                tv2.setId(i + i);
                tv2.setTextColor(Color.WHITE);
                tv1.setTextSize(20);
                tv2.setPadding(5, 5, 5, 5);
                tr.addView(tv2);

                tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
            return view;

        }
    }
}