package p185296_m203380.ft.unicamp.aula03_fragmentos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


/**
 * A simple {@link Fragment} subclass.
 */
public class MailFragment extends Fragment {

    private View view;

    public MailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mail, container, false);
        }

        Button enviar = view.findViewById(R.id.send_button);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MailFragment mailFragment = (MailFragment) getFragmentManager().findFragmentById(R.id.authors_frame);
                View frag = mailFragment.view;
                EditText subject = frag.findViewById(R.id.relative_about_text);
                String subjectText = subject.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("message", subjectText);
                AuthorsFragment authorsFragment = new AuthorsFragment();
                authorsFragment.setArguments(bundle);
                replaceFragment(authorsFragment, "authors");
            }
        });
        return view;
    }

    private void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.authors_frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
