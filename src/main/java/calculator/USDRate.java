package calculator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by VeraL on 21.12.2015.
 */
public class USDRate {
    private  MainGson mg;
    public static final double USD_COEFFICIENT = 1.059;

    public double getRate() {
        double rateUSD = 0;

        String request = "http://query.yahooapis.com/v1/public/yql?format=json&q=select%20*%20from%20" +
                "yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";

        String result = mg.performRequest(request);


        Gson gson = new GsonBuilder().create();
        JSON json = (JSON) gson.fromJson(result, JSON.class);

        for (Rate rate : json.query.results.rate) {
            if (rate.id.endsWith("UAH")) {
                rateUSD = rate.Rate;
            }
        }
        double currencyRate = (double) Math.round(rateUSD * 100) / 100;
        System.out.println(currencyRate);
        return currencyRate;

    }

    public String rateToString() {
        double rate = getRate() * USD_COEFFICIENT;
        rate = (double) Math.round(rate * 100) / 100;
        String rateString = Double.toString(rate);
        return rateString;
    }
}
