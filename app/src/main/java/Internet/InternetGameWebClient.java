package Internet;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;

public class InternetGameWebClient extends AsyncTask<String, Void, String> {

    private InternetGameFragment txt;
    private StringBuilder bd;

    InternetGameWebClient(InternetGameFragment txt) {
        this.txt = txt;
    }

    @Override protected void onPreExecute() {}

    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected String doInBackground(String... args) {
        return getValue(args);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @NotNull
    private String getValue(String[] args) {
        HttpURLConnection httpURLConnection;
        try {
            /*
               Endereço que será acessado.
             */
        /*
          Abrindo uma conexão com o servidor
        */
            URL url = new URL("https://aula-10-1d28e.firebaseio.com/.json");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");

            if (args.length == 3) {
                httpURLConnection.addRequestProperty("Content-Type", "application/json");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), StandardCharsets.UTF_8);
                outputStreamWriter.write(args[2]);
                outputStreamWriter.flush();
                outputStreamWriter.close();
            }
            /*
          Lendo a resposta do servidor
        */
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            try {
                String name;
                String total;
                JSONObject jsn = new JSONObject(sb.toString());
                Iterator<String> database = jsn.keys();
                bd = new StringBuilder();
                for (int i = 0; database.hasNext(); i++) {
                    name = database.next();
                    total = name +
                            "\n" +
                            " - Porcentagem de Acertos : " + (jsn.getJSONObject(name).getDouble("Acertos") / ((jsn.getJSONObject(name).getDouble("Acertos") + jsn.getJSONObject(name).getDouble("Erros")))) * 100+"%" + "\n"+
                            " - Porcentagem de Erros : "   + (jsn.getJSONObject(name).getDouble("Erros") / (jsn.getJSONObject(name).getDouble("Acertos") + jsn.getJSONObject(name).getDouble("Erros"))) * 100 +"%" + "\n";
                    bd.append(total);
                }
                return bd.toString();
            } catch (Exception ignored) {}
            return sb.toString();
        } catch (IOException e) {
            Log.v("Erro", Objects.requireNonNull(e.getMessage()));
            return "Exception\n" + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String args) {
        txt.put(bd.toString());
    }
}
