package p185296_m203380.ft.unicamp.aula03_fragmentos.kotlin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import p185296_m203380.ft.unicamp.aula03_fragmentos.FragmentController;
import p185296_m203380.ft.unicamp.aula03_fragmentos.R;
import p185296_m203380.ft.unicamp.aula03_fragmentos.database.DatabaseHelper;

public class StatsFragment extends Fragment implements FragmentController {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private View view;

    private TextView firstValue;
    private TextView secondValue;
    private TextView thirdValue;
    private int comparadorErros = 0;
    private int qtdErros = 0;
    private int qtdAcertos = 0;
    private int popularidadeAtual = 0;

    public StatsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_stats, container, false);
        }


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        firstValue = view.findViewById(R.id.stats_first_value);
        secondValue = view.findViewById(R.id.stats_second_value);
        thirdValue = view.findViewById(R.id.stats_third_value);

        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        onSelecionar();
    }

    @Override
    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag) {
        if (getFragmentManager() == null) {
            setTargetFragment(fragment, 0);
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.authors_frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void onSelecionar() {
        String sql = "Select * from tabela";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            String str = "Nothing yet";
            String worstName = "Nothing yet";
            do {
                String nome = cursor.getString(1);
                int hits = cursor.getInt(2);
                int erros = cursor.getInt(3);
                int popularidade = cursor.getInt(4);

                if (comparadorErros < erros) {
                    comparadorErros = erros;
                    str = nome;
                }
                if (popularidadeAtual < popularidade) {
                    popularidadeAtual = popularidade;
                    worstName = nome;
                }
                qtdAcertos = qtdAcertos + hits;
                qtdErros = qtdErros + erros;
            } while (cursor.moveToNext());

            firstValue.setText(str);
            secondValue.setText(worstName);
            double porcentagem = (((double) qtdErros / (qtdAcertos + qtdErros)) * 100);
            thirdValue.setText(porcentagem + "%");
        }
        cursor.close();

    }
}
