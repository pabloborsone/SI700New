package Internet;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import p185296_m203380.ft.unicamp.aula03_fragmentos.R;

public class InternetFragment extends Fragment {
    private View lview;

    public InternetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_internet, container, false);
        }

        final TextView textView = lview.findViewById(R.id.textView);
        final EditText editText = lview.findViewById(R.id.editText);
        final Spinner spinner = lview.findViewById(R.id.restSpinner);

        lview.findViewById(R.id.btnViaCep).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = "https://aula-10-1d28e.firebaseio.com/" +
                                editText.getText().toString() + "/.json";

                        String comando = spinner.getSelectedItem().toString();

                        Log.i("InternetFragment", url);
                        Log.i("InternetFragment", comando);

                        String json = "{\"-Ls49Q18fzRLjBl1WjfC\":{\"bairro\":\"Sé\",\"cep\":\"01001-000\"," +
                                "\"complemento\":\"lado ímpar\",\"gia\":\"1004\",\"ibge\":\"3550308\"," +
                                "\"localidade\":\"São Paulo\",\"logradouro\":\"Praça da Sé\"," +
                                "\"uf\":\"SP\",\"unidade\":\"\"}}";

                        new MyFirstWebClient(textView).execute(url, comando, json);
                    }
                }
        );
        return lview;
    }
}