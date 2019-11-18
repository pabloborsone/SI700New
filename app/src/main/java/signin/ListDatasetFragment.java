package signin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import p185296_m203380.ft.unicamp.aula03_fragmentos.R;

public class ListDatasetFragment extends Fragment {

    public static class RespostaViewHolder extends RecyclerView.ViewHolder {
        TextView answer;
        TextView choosen;

        public RespostaViewHolder(View v) {
            super(v);
            answer = itemView.findViewById(R.id.txtAnswer);
            choosen = itemView.findViewById(R.id.txtChosen);

        }
    }

    // Firebase instance variables
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<Resposta, RespostaViewHolder>
            mFirebaseAdapter;

    public ListDatasetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lview = inflater.inflate(R.layout.fragment_list_dataset, container, false);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        SnapshotParser<Resposta> parser = new SnapshotParser<Resposta>() {
            @Override
            public Resposta parseSnapshot(DataSnapshot dataSnapshot) {
                Resposta resposta = dataSnapshot.getValue(Resposta.class);
                return resposta;
            }
        };

        DatabaseReference messagesRef = mFirebaseDatabaseReference.child("respostas");
        FirebaseRecyclerOptions<Resposta> options =
                new FirebaseRecyclerOptions.Builder<Resposta>()
                        .setQuery(messagesRef, parser)
                        .build();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Resposta, RespostaViewHolder>(options) {
            @Override
            public RespostaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new RespostaViewHolder(inflater.inflate(R.layout.item_message, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(final RespostaViewHolder viewHolder,
                                            int position,
                                            Resposta resposta) {
                if (resposta.getAnswer() != null) {
                    viewHolder.answer.setText(resposta.getAnswer());
                    viewHolder.choosen.setText(resposta.getChosen());
                }
            }
        };
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecycler = lview.findViewById(R.id.respostaRecyclerView);
        mRecycler.setLayoutManager(llm);
        mRecycler.setAdapter(mFirebaseAdapter);


        return lview;
    }

    @Override
    public void onPause() {
        mFirebaseAdapter.stopListening();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAdapter.startListening();
    }
}
