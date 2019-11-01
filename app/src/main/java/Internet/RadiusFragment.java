package Internet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Collections;

import p185296_m203380.ft.unicamp.aula03_fragmentos.R;

public class RadiusFragment extends Fragment {

    private View view;
    private InternetGame internetGame;
    private TextView biography;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;
    private String answer;

    public RadiusFragment() {
        // Required empty public constructor
    }

    private void updateGame() {
        new InternetGameTask(this).execute();
        radioGroup.clearCheck();
    }

    private void validator() {
        int selectId = radioGroup.getCheckedRadioButtonId();

        if (selectId != -1) {
            RadioButton selectedRadioButton = view.findViewById(selectId);

            if (selectedRadioButton.getText().toString().equals(internetGame.getName())) {
                Toast toast = Toast.makeText(view.getContext(), "Correto", Toast.LENGTH_SHORT);
                toast.show();
                answer = "Acertos";
            } else {
                Toast toast = Toast.makeText(view.getContext(), "Errou", Toast.LENGTH_SHORT);
                toast.show();
                answer = "Erros";
            }
        }
        updateGame();
        new InternetGameStatus(internetGame.getName(), answer).execute();
        radioGroup.clearCheck();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_radius, container, false);
        }

        biography = view.findViewById(R.id.biography);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.radio1);
        radioButton2 = view.findViewById(R.id.radio2);
        radioButton3 = view.findViewById(R.id.radio3);
        radioButton4 = view.findViewById(R.id.radio4);
        radioButton5 = view.findViewById(R.id.radio5);
        Button send = view.findViewById(R.id.internetGameEnviar);
        Button update = view.findViewById(R.id.internetGameAtualizar);

        new InternetGameTask(this).execute();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateGame();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    void resetGame(InternetGame internetGame) {
        this.internetGame = internetGame;
        Collections.shuffle(internetGame.getwrongNames());
        biography.setText(internetGame.getBiography());
        radioButton1.setText(internetGame.getwrongNames().get(0));
        radioButton2.setText(internetGame.getwrongNames().get(1));
        radioButton3.setText(internetGame.getwrongNames().get(2));
        radioButton4.setText(internetGame.getwrongNames().get(3));
        radioButton5.setText(internetGame.getwrongNames().get(4));
    }
}
