package p185296_m203380.ft.unicamp.aula03_fragmentos.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import p185296_m203380.ft.unicamp.aula03_fragmentos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatabaseFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private EditText edtId;
    private EditText edtTexto;
    private TextView txtOutput;

    private View view;

    public DatabaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_database, container, false);
        }

        edtId = view.findViewById(R.id.edtId);
        edtTexto = view.findViewById(R.id.edtTexto);
        txtOutput = view.findViewById(R.id.txtOutput);

        /*
        Configurando os Listeners
         */
        view.findViewById(R.id.btnInserir).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onInserir();
                    }
                }
        );

        view.findViewById(R.id.btnRemover).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onRemover();
                    }
                }
        );

        view.findViewById(R.id.btnSelecionar).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onSelecionar();
                    }
                }
        );

        view.findViewById(R.id.btnAtualizar).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onAtualizar();
                    }
                }
        );


        return view;
    }

    public void onStart() {
        super.onStart();
        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
    }

    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

    public void onInserir() {
        try {
            int id = Integer.parseInt(edtId.getText().toString());
            String texto = edtTexto.getText().toString();

            ContentValues contentValues = new ContentValues();
            contentValues.put("_id", id);
            contentValues.put("texto", texto);


            sqLiteDatabase.insert("tabela", null, contentValues);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Ops... esse não é um ID válido. Utilize somente números", Toast.LENGTH_LONG).show();
        }

    }

    public void onAtualizar() {
        try {
            int id = Integer.parseInt(edtId.getText().toString());
            String texto = edtTexto.getText().toString();

            ContentValues contentValues = new ContentValues();
            contentValues.put("texto", texto);

            String whereClause = "_id = ?";
            String[] whereArgs = new String[]{Integer.toString(id)};

            sqLiteDatabase.update("tabela", contentValues, whereClause, whereArgs);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Ops... Não há o que atualizar", Toast.LENGTH_LONG).show();
        }

    }

    public void onRemover() {
        try {
            int id = Integer.parseInt(edtId.getText().toString());

            String whereClause = "_id = ?";
            String[] whereArgs = new String[]{Integer.toString(id)};

            sqLiteDatabase.delete("tabela", whereClause, whereArgs);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Ops... Não há o que remover", Toast.LENGTH_LONG).show();
        }
    }

    public void onSelecionar() {
        String sql = "Select * from tabela";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            StringBuilder str = new StringBuilder();
            do {
                int id = cursor.getInt(0);
                String texto = cursor.getString(1);

                str.append(id).append(", ").append(texto).append("\n");

            } while (cursor.moveToNext());
            txtOutput.append(str.toString());
        }
        cursor.close();

    }
}
