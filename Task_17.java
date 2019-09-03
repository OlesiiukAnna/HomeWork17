package home.Task_17;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task_17 {
    final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String inputDate = null;

        try {
            inputDate = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = "https://api.privatbank.ua/p24api/exchange_rates?json&date=" +
                verifiedDate(inputDate);

        String json = HttpUtil.sendRequest(url, null, null);
        Gson gson = new Gson();
        Response response = gson.fromJson(json, Response.class);
        try {
            System.out.println(response.getUSD());
        } catch (Exception e) {
            System.out.println("No exchange rate for this day");
        }

    }

    public static String verifiedDate(String inputDate) {
        String result = null;
        try {
            LocalDate localDate = LocalDate.parse(inputDate, DATE_FORMAT);

            if (localDate.isAfter(LocalDate.now())) {
                throw new DateTimeException("Future date");
            }
            if (localDate.isBefore(LocalDate.of(2009, 8, 10))) {
                throw new DateTimeException("No exchange rates for this date");
            }

            result = localDate.format(DATE_FORMAT);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        return result;
    }

}
