package alunos;

import alunos.Alunos;
import alunos.MyFirstAdapter;
import p185296_m203380.ft.unicamp.aula03_fragmentos.R;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


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
            return inflater.inflate(R.layout.fragment_alunos, container, false);
        }

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new MyFirstAdapter(new ArrayList(Arrays.asList(Alunos.alunos)));


        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setMyOnItemClickListener(new MyFirstAdapter.MyOnItemCLickListener());

        //EXISTENTIAL DREAD
    }

}
