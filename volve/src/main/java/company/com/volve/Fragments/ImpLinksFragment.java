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

import company.com.volve.R;


@SuppressWarnings("ALL")
public class ImpLinksFragment extends android.support.v4.app.Fragment {

    Button Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9, Button10, Button11, Button12, Button13, Button14, Button15;
    private View view;

    private void goToSocieties(View view) {
        this.view = view;
        goToUrl(getString(R.string.societiesUCD_url));
    }

    private void goToUCDOfficial(View view) {
        this.view = view;
        goToUrl(getString(R.string.ucd_url));
    }

    private void goToCSMoodle(View view) {
        this.view = view;
        goToUrl(getString(R.string.csmoodle_url));
    }

    public void goToSisWeb(View view) {
        this.view = view;
        goToUrl(getString(R.string.sisweb_url));
    }

    public void goToLibrary(View view) {
        this.view = view;
        goToUrl(getString(R.string.library_url));
    }

    public void goToCampusIE(View view) {
        this.view = view;
        goToUrl(getString(R.string.campusIE_url));
    }

    public void goToInfoViewUCD(View view) {
        this.view = view;
        goToUrl(getString(R.string.infoview_url));
    }

    public void goToCareersConnectUCD(View view) {
        this.view = view;
        goToUrl(getString(R.string.careersconnect_url));
    }

    public void goToITServicesUCD(View view) {
        this.view = view;
        goToUrl(getString(R.string.IT_services_url));
    }

    public void goToSU_UCD(View view) {
        this.view = view;
        goToUrl(getString(R.string.ucdsu_url));
    }

    public void goToConnectUCD(View view) {
        this.view = view;
        goToUrl(getString(R.string.ucdconnect_url));
    }

    public void goToRegistryUCD(View view) {
        this.view = view;
        goToUrl(getString(R.string.registry_url));
    }

    public void goToStudentSupportUCD(View view) {
        this.view = view;
        goToUrl(getString(R.string.support_url));
    }

    public void goToUnicareUCD(View view) {
        this.view = view;
        goToUrl(getString(R.string.unicare_url));
    }

    public void goToMapsUCD(View view) {
        this.view = view;
        goToUrl(getString(R.string.ucd_estates_url));
    }

    private void goToUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_imp_links, container, false);
        LinearLayout activityLayout = new LinearLayout(getActivity());

        Button1 = view.findViewById(R.id.goToUCDSocieties);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSocieties(v);
            }
        });

        Button2 = view.findViewById(R.id.goToUCDOfficial);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUCDOfficial(v);
            }
        });

        Button3 = view.findViewById(R.id.goToCSMoodle);
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCSMoodle(v);
            }
        });

        Button4 = view.findViewById(R.id.goToCampusIE);
        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCampusIE(v);
            }
        });

        Button5 = view.findViewById(R.id.goToCareersConnect);
        Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCareersConnectUCD(v);
            }
        });

        Button6 = view.findViewById(R.id.goToConnectUCD);
        Button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToConnectUCD(v);
            }
        });

        Button7 = view.findViewById(R.id.goToInfoViewUCD);
        Button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInfoViewUCD(v);
            }
        });

        Button8 = view.findViewById(R.id.goToITServicesUCD);
        Button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToITServicesUCD(v);
            }
        });

        Button9 = view.findViewById(R.id.goToLibrary);
        Button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLibrary(v);
            }
        });

        Button10 = view.findViewById(R.id.goToMapsUCD);
        Button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMapsUCD(v);
            }
        });

        Button11 = view.findViewById(R.id.goToRegistryUCD);
        Button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegistryUCD(v);
            }
        });

        Button12 = view.findViewById(R.id.goToStudentSupportUCD);
        Button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStudentSupportUCD(v);
            }
        });

        Button13 = view.findViewById(R.id.goToSisWeb);
        Button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSisWeb(v);
            }
        });

        Button14 = view.findViewById(R.id.goToSU_UCD);
        Button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSU_UCD(v);
            }
        });

        Button15 = view.findViewById(R.id.goToUnicareUCD);
        Button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUnicareUCD(v);
            }
        });

        activityLayout.getRootView().setBackgroundColor(Color.CYAN);
        return view;
    }


}