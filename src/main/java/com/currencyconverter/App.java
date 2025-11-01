package com.currencyconverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONObject;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("💱 Welcome to the Live Currency Converter!");

        // Fetch available currency codes from API
        JSONObject rates = null;
        try {
            String urlStr = "https://open.er-api.com/v6/latest/USD"; // Use USD as default to get all currencies
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());
            if (!json.getString("result").equals("success")) {
                System.out.println(" Error fetching currency codes: " + json.getString("error-type"));
                sc.close();
                return;
            }

            rates = json.getJSONObject("rates");

            System.out.println("\nAvailable currency codes:");
            Iterator<String> keys = rates.keys();
            int count = 0;
            while (keys.hasNext()) {
                System.out.print(keys.next() + "  ");
                count++;
                if (count % 10 == 0) System.out.println();
            }
            System.out.println("\n");

        } catch (Exception e) {
            System.out.println(" Error fetching currency codes: " + e.getMessage());
            sc.close();
            return;
        }

        // User inputs
        System.out.print("Enter base currency (e.g., INR, USD, EUR): ");
        String base = sc.next().toUpperCase();

        System.out.print("Enter target currency (e.g., USD, EUR, GBP, JPY): ");
        String target = sc.next().toUpperCase();

        System.out.print("Enter amount in " + base + ": ");
        double amount = sc.nextDouble();

        // Fetch conversion rates
        try {
            String urlStr = "https://open.er-api.com/v6/latest/" + base;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());
            if (!json.getString("result").equals("success")) {
                System.out.println(" Error: " + json.getString("error-type"));
                sc.close();
                return;
            }

            rates = json.getJSONObject("rates");

            if (!rates.has(target)) {
                System.out.println(" Invalid target currency: " + target);
                sc.close();
                return;
            }

            double rate = rates.getDouble(target);
            double converted = amount * rate;
            System.out.printf(" %.2f %s = %.2f %s%n", amount, base, converted, target);

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }

        sc.close();
    }
}
