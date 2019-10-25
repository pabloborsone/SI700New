package Internet;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class InternetGameTask extends AsyncTask<String, Void, InternetGame> {

    private InternetGame InternetGame = new InternetGame();
    private RadiusFragment RadiusFragment;

    InternetGameTask(RadiusFragment RadiusFragment) {
        this.RadiusFragment = RadiusFragment;

    }

    @Override
    protected InternetGame doInBackground(String... strings) {
        HttpURLConnection httpURLConnection;
        try {
            /*
               Endereço que será acessado.
             */
            String HOST = "https://sa4a4dtiv4.execute-api.eu-west-1.amazonaws.com/default/PythonHTTP1?kind=alunos&num_outros=4";

        /*
          Abrindo uma conexão com o servidor
        */

            URL url = new URL(HOST);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
        /*
          Lendo a resposta do servidor
        */
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.append((String) null).toString());
            InternetGame.setBiography(jsonObject.getString("frase"));
            InternetGame.setName(jsonObject.getString("nome"));
            ArrayList<String> alunos = new ArrayList<>();
            alunos.add(jsonObject.getJSONArray("outros").getString(0));
            alunos.add(jsonObject.getJSONArray("outros").getString(1));
            alunos.add(jsonObject.getJSONArray("outros").getString(2));
            alunos.add(jsonObject.getJSONArray("outros").getString(3));
            alunos.add(jsonObject.getString("nome"));
            InternetGame.setwrongNames(alunos);
            return InternetGame;

        } catch (IOException e) {
            Log.v("Erro", Objects.requireNonNull(e.getMessage()));
            return InternetGame = null;
        } catch (JSONException e) {
            e.printStackTrace();
            return InternetGame = null;
        }
    }

    @Override
    protected void onPostExecute(InternetGame InternetGame) {
        RadiusFragment.resetGame(InternetGame);
    }
}
