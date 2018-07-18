package com.ivrom.fragmentlesson;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class WithButtonFragment extends Fragment implements Fragment1.OnSelectedButtonListener {
    int mCounter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_with_button, container, false);
        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener((View.OnClickListener) this);
        return rootView;
    }

    @Override
    public void onButtonSelected(int buttonIndex) {
        mCounter++;
    }
}
