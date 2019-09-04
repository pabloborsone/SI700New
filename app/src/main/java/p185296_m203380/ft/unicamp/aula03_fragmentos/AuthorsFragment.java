package p185296_m203380.ft.unicamp.aula03_fragmentos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.authors_fragment, container, false);
        }

        String text = getArguments().getString("message");
        AuthorsFragment authorsFragment = (AuthorsFragment) getFragmentManager().findFragmentById(R.id.authors_frame);
        View frag =  authorsFragment.view;
        TextView randomText = frag.findViewById(R.id.random_message);
        randomText.setText(text);

        return view;
    }

}
