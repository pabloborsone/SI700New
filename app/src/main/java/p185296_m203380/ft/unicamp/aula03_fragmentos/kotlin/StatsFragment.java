package p185296_m203380.ft.unicamp.aula03_fragmentos.kotlin;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
        onInserir();
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


    public void onInserir() {
        try {
            int id = Integer.parseInt("1");
            String name = "Testando";

            ContentValues contentValues = new ContentValues();
            contentValues.put("_id", id);
            contentValues.put("Nome", name);
            contentValues.put("Acertos", 0);
            contentValues.put("Erros", 0);


            sqLiteDatabase.insert("tabela", null, contentValues);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Ops... esse não é um ID válido. Utilize somente números", Toast.LENGTH_LONG).show();
        }

    }

    public void onSelecionar() {
        String sql = "Select * from tabela";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            StringBuilder str = new StringBuilder();
            do {
                int id = cursor.getInt(0);
                String nome = cursor.getString(1);
                int acertos = cursor.getInt(1);
                int erros = cursor.getInt(1);

                str.append(id).append(" ").append(nome).append(" ").append(acertos).append(" ").append(erros).append("\n");

            } while (cursor.moveToNext());
            firstValue.append(str.toString());
        }
        cursor.close();

    }
}
