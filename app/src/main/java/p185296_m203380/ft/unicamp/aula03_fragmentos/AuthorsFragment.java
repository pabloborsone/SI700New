package p185296_m203380.ft.unicamp.aula03_fragmentos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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
        return view;
    }

    public void setText(String text) {
        TextView textView = view.findViewById(R.id.random_message);
        textView.setText(mensagem.getText().toString());
    }

}
