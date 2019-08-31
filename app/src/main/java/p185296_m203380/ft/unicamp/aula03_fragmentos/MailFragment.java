package p185296_m203380.ft.unicamp.aula03_fragmentos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MailFragment extends Fragment {

    private View view;
    private EditText mensagem;

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
        mensagem = view.findViewById(R.id.edit_message);

        Button enviar = view.findViewById(R.id.send_button);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = MailFragment.this.mensagem.getText().toString();
                ((MainActivity)getActivity()).doSomething(msg);
            }
        });
        return view;
    }

    public void setText() {
        TextView textView = view.findViewById(R.id.random_message);
        textView.setText(mensagem.getText().toString());
    }

}
