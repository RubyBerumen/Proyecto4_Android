package controlador;

        import android.util.Log;
        import android.widget.ArrayAdapter;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedInputStream;
        import java.io.BufferedOutputStream;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.UnsupportedEncodingException;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLEncoder;
        import java.util.ArrayList;

public class AnalizadorJSON {

    private InputStream is = null;
    private OutputStream os = null;
    private JSONObject jsonObject = null;

    private HttpURLConnection conexion = null;
    private URL url = null;

    //String...datos
    public JSONObject peticionHTTP(String direccionURL, String metodo, ArrayList<String> datos){



        //Estructurar la peticion  con cadena JSON  {"nc":"02", "e":30} ----------------------------

        String variableNC = null;
        String variableN = null;
        try {
            variableNC = URLEncoder.encode(datos.get(0),"UTF-8");
            variableN = URLEncoder.encode(datos.get(1),"UTF-8");

            String cadenaJSON = "{\"nc\":\""+variableNC+", " +
                    "\"n\":\""+variableN+"\"}";

            url = new URL(direccionURL);
            conexion = (HttpURLConnection) url.openConnection();

            //activar el envio a traves de HTTP
            conexion.setDoOutput(true);

            //indicar metodo de peticion
            conexion.setRequestMethod(metodo);

            //tamaño prestabelcido o fijo de la cadena a enviar
            conexion.setFixedLengthStreamingMode(cadenaJSON.length());

            //formato de peticion
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            //perparar el envio de la peticion
            os = new BufferedOutputStream(conexion.getOutputStream());
            os.write(cadenaJSON.getBytes());
            os.flush();
            os.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //----------------- RECIBIR RESPUESTA ----------------------------

        try {
            is = new BufferedInputStream(conexion.getInputStream());
            BufferedReader br =  new BufferedReader(new InputStreamReader(is));
            StringBuilder cadena = new StringBuilder();

            String fila = null;
            while ((fila=br.readLine()) != null){
                cadena.append(fila+"\n");
            }

            is.close();
            jsonObject = new JSONObject(String.valueOf(cadena));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }//metodo
}//class