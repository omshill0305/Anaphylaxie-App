package com.example.olga.aa_app;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReactionFragment extends Fragment implements View.OnClickListener {
    private Button chooseBt;

    public ReactionFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_reaction, container, false);
        Button chooseBt = (Button) rootView.findViewById(R.id.button2);
        chooseBt.setOnClickListener(this);
            
        return rootView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), SymptomsActivity.class);
        getActivity().startActivity(intent);
    }
}


