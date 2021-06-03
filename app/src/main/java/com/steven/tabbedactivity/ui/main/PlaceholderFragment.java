package com.steven.tabbedactivity.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.steven.tabbedactivity.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        final ImageView imageView = root.findViewById(R.id.imageView);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

                switch(s)
                {
                    case "1":
                        textView.setText(R.string.tab_text_1);
                        imageView.setImageResource(R.drawable.group_1);
                        break;


                    case "2":
                        textView.setText(R.string.tab_text_2);
                        imageView.setImageResource(R.drawable.xenoblade_shulk);
                        break;


                    case "3":
                        textView.setText(R.string.tab_text_3);
                        imageView.setImageResource(R.drawable.xenoblade_reyn);
                        break;


                    case "4":
                        textView.setText(R.string.tab_text_4);
                        imageView.setImageResource(R.drawable.xenoblade_dunban);
                        break;

                    case "5":
                        textView.setText(R.string.tab_text_5);
                        imageView.setImageResource(R.drawable.xenoblade_sharla);
                        break;

                    case "6":
                        textView.setText(R.string.tab_text_6);
                        imageView.setImageResource(R.drawable.xenoblade_fiora);
                        break;

                    case "7":
                        textView.setText(R.string.tab_text_7);
                        imageView.setImageResource(R.drawable.xenoblade_melia);
                        break;

                    case "8":
                        textView.setText(R.string.tab_text_8);
                        imageView.setImageResource(R.drawable.xenoblade_riki);
                        break;
                }

            }
        });
        return root;
    }
}