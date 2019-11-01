package p185296_m203380.ft.unicamp.aula03_fragmentos;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import alunos.Aluno;
import alunos.Alunos;
import p185296_m203380.ft.unicamp.aula03_fragmentos.database.DatabaseHelper;


public class NameFragment extends Fragment implements FragmentController {

    private View lview;

    private Random random = new Random();
    private String nomeCorreto;
    private int positionAluno;
    private int numTentativas;

    private ImageView imageView;
    private TextView txtTentativas;
    private TextView txtFeedback;
    private ArrayList<Button> arrayListButton;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_name, container, false);
        }

        imageView = lview.findViewById(R.id.imageFoto);
        txtTentativas = lview.findViewById(R.id.txtTentativas);
        txtFeedback = lview.findViewById(R.id.txtFeedback);

        buttonInit();
        startGame();

        final View.OnClickListener guessButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeEscolhido = ((Button) v).getText().toString();
                if (nomeEscolhido.equals(nomeCorreto)) {
                    txtFeedback.setText("Correto!!");

                    Cursor cursor = sqLiteDatabase.rawQuery("Select * from tabela", null);
                    cursor.move(positionAluno);
                    int hitsAtuais = Integer.valueOf(cursor.getString(2));
                    ContentValues cv = new ContentValues();
                    cv.put("Acertos", ++hitsAtuais);
                    sqLiteDatabase.update("tabela", cv, "_id=" + (positionAluno + 1), null);
                    new Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    startGame();
                                }
                            }, 2000);
                    cursor.close();
                } else {
                    txtFeedback.setText("Incorreto!!");
                    if (numTentativas > 0) {
                        numTentativas--;
                        Cursor cursor = sqLiteDatabase.rawQuery("Select * from tabela", null);
                        cursor.move(positionAluno);
                        int errosAtuais = Integer.valueOf(cursor.getString(3));
                        ContentValues cv = new ContentValues();
                        cv.put("Erros", ++errosAtuais);
                        sqLiteDatabase.update("tabela", cv, "_id=" + (positionAluno + 1), null);
                        cursor.close();

                        Cursor cursor1 = sqLiteDatabase.rawQuery("Select * from tabela", null);
                        if (cursor1.moveToFirst()) {
                            do {
                                String[] firstName = cursor1.getString(1).split(" ", 2);
                                if (firstName[0].toLowerCase().contains(nomeEscolhido)) {
                                    int popularidadeAtual = cursor1.getInt(4);
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("Popularidade", popularidadeAtual + 1);
                                    sqLiteDatabase.update("tabela", contentValues, "_id=" + cursor1.getPosition(), null);
                                }
                            } while (cursor1.moveToNext());
                        }
                        cursor1.close();
                    }
                    txtTentativas.setText("Tentativas: " + numTentativas);

                    if (numTentativas == 0) {
                        txtFeedback.setText("Você Perdeu!!");

                        new Handler().postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("position", positionAluno);
                                        BiographyFragment biography = new BiographyFragment();
                                        biography.setArguments(bundle);
                                        replaceFragment(biography, MainActivity.BIOGRAPHY_KEY);
                                    }
                                }, 2000);


                    }
                }
            }
        };

        for (int i = 0; i < 9; i++) {
            arrayListButton.get(i).setOnClickListener(guessButtonListener);
        }

        return lview;
    }

    @Override
    public void onStart() {
        super.onStart();

        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        addStudentsToDatabase();
    }

    @Override
    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

    private void buttonInit() {
        arrayListButton = new ArrayList<>();
        arrayListButton.add((Button) lview.findViewById(R.id.button1));
        arrayListButton.add((Button) lview.findViewById(R.id.button2));
        arrayListButton.add((Button) lview.findViewById(R.id.button3));
        arrayListButton.add((Button) lview.findViewById(R.id.button4));
        arrayListButton.add((Button) lview.findViewById(R.id.button5));
        arrayListButton.add((Button) lview.findViewById(R.id.button6));
        arrayListButton.add((Button) lview.findViewById(R.id.button7));
        arrayListButton.add((Button) lview.findViewById(R.id.button8));
        arrayListButton.add((Button) lview.findViewById(R.id.button9));
    }

    private void startGame() {
        int guess = random.nextInt(Alunos.alunos.length);
        positionAluno = guess;
        Aluno aluno = Alunos.alunos[guess];
        nomeCorreto = aluno.getNome().split(" ")[0].toLowerCase();
        imageView.setImageResource(aluno.getFoto());
        numTentativas = 3;
        txtTentativas.setText("Tentativas: " + numTentativas);
        txtFeedback.setText("");

        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            Aluno candidate = Alunos.alunos[(guess + i) % Alunos.alunos.length];
            arrayList.add(candidate.getNome().split(" ")[0].toLowerCase());
        }
        Collections.shuffle(arrayList);
        for (int i = 0; i < 9; i++) {
            arrayListButton.get(i).setText(arrayList.get(i));
        }
    }

    private void addStudentsToDatabase() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT count(*) FROM tabela", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count == 0) {
            for (Aluno alunos : Alunos.alunos) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("Nome", alunos.getNome());
                contentValues.put("Acertos", 0);
                contentValues.put("Erros", 0);
                contentValues.put("Popularidade", 0);

                sqLiteDatabase.insert("tabela", null, contentValues);
            }
        }
        cursor.close();
    }

    public void replaceFragment(Fragment fragment, String key) {
        if (getFragmentManager() == null) {
            setTargetFragment(fragment, 0);
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.authors_frame, fragment, key);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}