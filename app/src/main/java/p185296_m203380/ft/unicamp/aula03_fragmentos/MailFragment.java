package p185296_m203380.ft.unicamp.aula03_fragmentos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MailFragment extends Fragment implements FragmentController {

    private View view;

    public MailFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null || savedInstanceState == null) {
            view = inflater.inflate(R.layout.fragment_mail, container, false);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button enviar = view.findViewById(R.id.send_button);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText subject = MailFragment.this.view.findViewById(R.id.edit_message);
                String subjectText = subject.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("message", subjectText);
                AuthorsFragment authorsFragment = new AuthorsFragment();
                authorsFragment.setArguments(bundle);
                replaceFragment(authorsFragment, MainActivity.AUTHORS_KEY);
            }
        });
    }

    public void replaceFragment(Fragment fragment, String key) {
        if (getFragmentManager() == null) {
            setTargetFragment(fragment, 0);
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.authors_frame, fragment, key);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
