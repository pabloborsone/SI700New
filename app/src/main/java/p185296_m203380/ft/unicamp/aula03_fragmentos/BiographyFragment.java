package p185296_m203380.ft.unicamp.aula03_fragmentos;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

import alunos.Aluno;
import alunos.Alunos;

public class BiographyFragment extends Fragment {

    private View view;
    private int position = 0;

    public BiographyFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.biography_fragment, container, false);
        }


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getArguments() != null) {
            position = getArguments().getInt("position");
        }

        final ArrayList<Aluno> aluno = new ArrayList<>(Arrays.asList(Alunos.alunos));

        Button prevButton = view.findViewById(R.id.previous_biography_button);
        Button nextButton = view.findViewById(R.id.next_biography_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position >= aluno.size() - 1) {
                    position = aluno.size() - 1;
                } else {
                    position += 1;
                }
                BiographyFragment.this.setElements(aluno.get(position));
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position - 1 < 0) {
                    position = 0;
                } else {
                    position -= 1;
                }
                BiographyFragment.this.setElements(aluno.get(position));
            }
        });

        setElements(aluno.get(position));
    }

    private void setElements(Aluno alunos) {
        TextView nameTv = view.findViewById(R.id.biography_title);
        ImageView pictureIv = view.findViewById(R.id.biography_image_view);
        TextView descriptionTv = view.findViewById(R.id.biography_description);

        nameTv.setText(alunos.getNome());
        pictureIv.setImageResource(alunos.getFoto());
        descriptionTv.setText(Html.fromHtml(alunos.getDescricao()));
    }
}
