package Internet;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class MyViaCepAsyncTask extends AsyncTask<String, Void, String> {

    private TextView textView;
    MyViaCepAsyncTask(TextView textView) {
        this.textView = textView;
    }


    @Override
    protected void onPreExecute() {
        textView.append("####################### \n ");
        textView.append("Iniciando ViaCep \n ");
    }

    @Override
    protected String doInBackground(String... args) {
        if (args.length == 0) {
            return "No Parameter";
        }

        HttpURLConnection httpURLConnection;
        try {
            /*
               Endereço que será acessado.
             */
            String HOST = "https://viacep.com.br/ws/"+args[0]+"/json/";

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
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));


            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            Log.v("Erro", Objects.requireNonNull(e.getMessage()));
            return "Exception\n" + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String args) {
        // Via Cep

        try {
            JSONObject jsonObject = new JSONObject(args);
            textView.append("   Rua:    "+  jsonObject.getString("logradouro"));
            textView.append("   Bairro: "+  jsonObject.getString("bairro"));
        } catch(JSONException e) {
            textView.append("ERRO: Não foi possível converter em JSONObject: " + args+"\n");
        }


    }
}
