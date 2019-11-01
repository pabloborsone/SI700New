package Internet;

import android.os.AsyncTask;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class InternetGameStatus extends AsyncTask<String, Void, String> {

    private String name;
    private String aux;
    private int hits;
    private int errors;

    InternetGameStatus(String name, String aux) {
        this.name = name;
        this.aux = aux;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... args) {
        getValue(args);
        if (aux.equals("Acertos")) {
            hits++;
        } else {
            errors++;
        }
        return setValue();
    }

    private void getValue(String[] args) {
        HttpURLConnection httpURLConnection;
        try {
            /*
               Endereço que será acessado.
             */
            String HOST = "https://aula-10-1d28e.firebaseio.com/" + name + "/.json";
        /*
          Abrindo uma conexão com o servidor
        */
            URL url = new URL(HOST);
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
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            try {
                JSONObject obj = new JSONObject(sb.toString());
                hits = obj.getJSONObject(name).getInt("Acertos");
                errors = obj.getJSONObject(name).getInt("Erros");
            } catch (Exception e) {
                hits = 0;
                errors = 0;
            }
            sb.toString();
        } catch (IOException e) {
            Log.v("Erro", Objects.requireNonNull(e.getMessage()));
            e.getMessage();
        }
    }

    @NotNull
    private String setValue() {
        HttpURLConnection httpURLConnection;
        try {
        /*
          Abrindo uma conexão com o servidor
        */
            URL url = new URL("https://aula-10-1d28e.firebaseio.com/.json");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("PATCH");

            String json = "{\"" + name + "\": {\"Acertos\":\"" + hits + "\",\"Erros\" : \"" + errors + "\"}}";

            httpURLConnection.addRequestProperty("Content-Type", "application/json");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), StandardCharsets.UTF_8);
            outputStreamWriter.write(json);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            /*
          Lendo a resposta do servidor
        */
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            try {
                JSONObject obj = new JSONObject(sb.toString());
                hits = obj.getJSONObject(name).getInt("Acertos");
                errors = obj.getJSONObject(name).getInt("Erros");
            } catch (Exception e) {
                hits = 0;
                errors = 0;
            }
            return sb.toString();
        } catch (IOException e) {
            Log.v("Erro", Objects.requireNonNull(e.getMessage()));
            return "Exception\n" + e.getMessage();
        }
    }
}
