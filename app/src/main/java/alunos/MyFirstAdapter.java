package alunos;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import p185296_m203380.ft.unicamp.aula03_fragmentos.MainActivity;
import p185296_m203380.ft.unicamp.aula03_fragmentos.R;

public class MyFirstAdapter extends RecyclerView.Adapter {

    private ArrayList<Aluno> alunos;
    private MyOnItemClickListener myOnItemClickListener;
    private MyOnItemLongClickListener myOnItemLongClickListener;

    public MyFirstAdapter(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public class MyFirstViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtNome;
        private TextView txtDescription;

        public MyFirstViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagem_aluno);
            txtNome = itemView.findViewById(R.id.nome_aluno);
            txtDescription = itemView.findViewById(R.id.descricao_aluno);
        }

        public void bind(final Aluno aluno) {
            txtNome.setText(aluno.getNome());
            txtDescription.setText(Html.fromHtml(aluno.getDescricao()));
            imageView.setImageResource(aluno.getFoto());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOnItemClickListener != null) {
                    TextView txt = view.findViewById(R.id.nome_aluno);
                    myOnItemClickListener.myOnItemClick(txt.getText().toString());
                }
            }
        });
        return new MyFirstViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyFirstViewHolder) holder).bind(alunos.get(position));
        View view = holder.itemView;

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (myOnItemLongClickListener != null)
                    myOnItemLongClickListener.myOnItemLongClick(position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public interface MyOnItemClickListener {
        void myOnItemClick(String nome);
    }

    public interface MyOnItemLongClickListener {
        void myOnItemLongClick(int position);
    }

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public void setMyOnItemLongClickListener(MyOnItemLongClickListener myOnItemLongClickListener) {
        this.myOnItemLongClickListener = myOnItemLongClickListener;
    }
}