package p185296_m203380.ft.unicamp.aula03_fragmentos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorsFragment extends Fragment {

    private View view;

    public AuthorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.authors_fragment, container, false);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() == null) {
            TextView randomText = view.findViewById(R.id.random_message);
            randomText.setText(getText(R.string.random_text_not_found));
        } else {
            String text = getArguments().getString("message");
            TextView randomText = view.findViewById(R.id.random_message);
            randomText.setText(text);
        }
    }

}
