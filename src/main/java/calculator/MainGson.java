package calculator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by VeraL on 10.12.2015.
 */
public class MainGson {

    public double getCurrency() {
        double rateUSD = 0;

        String request = "http://query.yahooapis.com/v1/public/yql?format=json&q=select%20*%20from%20" +
                "yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";

        String result = performRequest(request);


//    System.out.println(result);

        Gson gson = new GsonBuilder().create();
        JSON json = (JSON) gson.fromJson(result, JSON.class);

        for (Rate rate : json.query.results.rate) {
            //  System.out.println(rate.id + "=" + rate.Rate);
            if (rate.id.endsWith("UAH")) {
                rateUSD = rate.Rate;
            }
        }return rateUSD;
    }

   public static String performRequest(String urlStr)  {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();

        HttpURLConnection http = null;
        try {
            http = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(http.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            char[] buf = new char[1000000];

            int r = 0;
            do {
                try {
                    if ((r = br.read(buf)) > 0)
                        sb.append(new String(buf, 0, r));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (r > 0);
        } finally {
            http.disconnect();
        }

        return sb.toString();
    }

    public static InputStream performRequestXML(String urlStr)  {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();

        HttpURLConnection http = null;
        try {
            http = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            return http.getInputStream();
        }catch(IOException e){
            return null;
        }
    }


}