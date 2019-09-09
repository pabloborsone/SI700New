package alunos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import p185296_m203380.ft.unicamp.aula03_fragmentos.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlunosFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MyFirstAdapter mAdapter;
    private View view;

    public AlunosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_alunos, container, false);
        }

        mRecyclerView = view.findViewById(R.id.alunos_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mAdapter = new MyFirstAdapter(new ArrayList<>(Arrays.asList(Alunos.alunos)));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setMyOnItemClickListener(new MyFirstAdapter.MyOnItemClickListener() {
            @Override
            public void myOnItemClick(String nome) {
                Toast.makeText(view.getContext(), "oi", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
