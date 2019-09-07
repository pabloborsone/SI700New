package alunos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import p185296_m203380.ft.unicamp.aula03_fragmentos.R;

public class MyFirstAdapter extends RecyclerView.Adapter {

    private ArrayList<Aluno> alunos;
    private myFirstViewHolder myOnItemClickListener;

    class myFirstViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtNome;
        private TextView txtDescription;

        public myFirstViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            txtNome = itemView.findViewById(R.id.nome_aluno);
            txtDescription = itemView.findViewById(R.id.descricao_aluno);
        }

        public void bind(final Aluno aluno) {
            txtNome.setText(aluno.getNome());
            txtDescription.setText(aluno.getDescrição());
            imageView.setImageResource(aluno.getFoto());
        }

        view.setOnClickListener(new View.OnClickListener) {
            @Override
            public void onClick(View view) {
                if(myOnItemClickListener != null) {
                    TextView txt = view.findViewById(R.id.nome_aluno);
                    myOnItemClickListener.myOnItemClick(txt.get);
                }
            }

        }


        public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
            this.myOnItemClickListener = myOnItemClickListener;
        }
    }


    public myFirstViewHolder onCreateViewHolder(ViewGroup parent, int viewtType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout, parent, false);
        myFirstViewHolder myFirstViewHolder = new myFirstViewHolder(view);
        return myFirstViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Aluno aluno = alunos.alunos.get(position);
        ((MyFirstViewHolder)holder).onBind(aluno);
    }

    @Override
    public int getItemCount() {
        return alunos.alunos.size();
    }



}