# Currency Converter API - Java Project

💱 **Live Currency Converter in Java**

This Java project allows users to convert currencies in real-time using the free [open.er-api.com](https://www.exchangerate-api.com/) API. Users can input a **base currency**, **target currency**, and **amount**, and get the converted value.

---

## **Features**

- Live currency conversion with up-to-date rates.
- User-friendly input of base & target currency.
- Handles invalid currency codes and API errors gracefully.
- Portfolio-ready: clean structure and working Maven setup.

---

## **How to Run**

1. Clone the project:
   git clone https://github.com/yourusername/CurrencyConverterAPI.git
2. Navigate to the project folder:
   cd CurrencyConverterAPI
3.Compile the project:
  mvn clean compile
4.Run the project:
  mvn exec:java



## **Dependencies**

org.json:json:20230618 (for parsing API responses)

## **Output Example**
Welcome to the Live Currency Converter!
Enter base currency (e.g., INR, USD, EUR): INR
Enter target currency (e.g., USD, EUR, GBP, JPY): USD
Enter amount in INR: 10000
10000.00 INR = 121.50 USD



