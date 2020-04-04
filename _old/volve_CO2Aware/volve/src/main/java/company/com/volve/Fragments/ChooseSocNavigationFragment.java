package company.com.volve.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import company.com.volve.R;


public class ChooseSocNavigationFragment extends android.support.v4.app.Fragment {
    ListView mListView;
    SimpleAdapter simpleAdapter;
    EditText inputSearch;
    ArrayList<HashMap<String, String>> arrayList;

    //TODO reimplement these
    String names[] = {"NetSoc", "Buddy Coders", "Islamic SOC"};
    int ids[] = {R.drawable.netsoc_logo, R.drawable.buddy, R.drawable.isoc};


    public ChooseSocNavigationFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_soc_navigation, container, false);

        mListView = view.findViewById(R.id.listView_contacts);
        inputSearch = view.findViewById(R.id.inputSearch);

        arrayList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("name", names[i]);
            hashMap.put("image", ids[i] + "");

            arrayList.add(hashMap);

            String keys[] = {"name", "image"};
            int id[] = {R.id.textView_contact, R.id.imageView_contact};

            //Stored in SimpleAdapter
            //template_contact is an activty created for the list
            simpleAdapter = new SimpleAdapter(getContext(), arrayList, R.layout.template_contact, keys, id);

            mListView.setAdapter(simpleAdapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Fragment newFragment = new InsideSocFragment();
                    assert getFragmentManager() != null;
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.screen_area, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            inputSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    simpleAdapter.getFilter().filter(charSequence);

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    simpleAdapter.getFilter().filter(charSequence);

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
        return view;
    }

}
