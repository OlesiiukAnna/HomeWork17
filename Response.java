package home.Task_17;

import java.util.Arrays;

public class Response {

    private String date;
    private Currency[] exchangeRate;

    public Response() {
    }

    public Response(String date, Currency[] exchangeRate) {
        this.date = date;
        this.exchangeRate = exchangeRate;
    }

    public String getDate() {
        return date;
    }

    public Currency[] getExchangeRate() {
        return exchangeRate;
    }

    public Currency getUSD() throws Exception {
        Currency usd = new Currency();

        for (Currency currency : exchangeRate) {
            if (currency.getCurrency() != null &&
                    currency.getCurrency().equals("USD")) {
                usd = currency;
                return usd;
            }
        }

        if (usd.getCurrency() == null) {
                throw new Exception();
        }
        return usd;
    }

    @Override
    public String toString() {
        return "Response{" +
                "date='" + date + '\'' +
                ", exchangeRate=" + Arrays.toString(exchangeRate) +
                '}';
    }
}
