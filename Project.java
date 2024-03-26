package ex;

import java.util.Scanner;
import java.util.HashMap;

public class Project {

  /**
   * Конвертер валют от 21.07.2023 Авторы: Александра Чхеидзе, Вадим Шаламов, Галина Борисевич
   * версия 2
   *
   * @param args
   */
  public static void main(String[] args) {
    HashMap<String, Double> exchangeRate = new HashMap<>();
    exchangeRate.put("EUR", 1.00);
    exchangeRate.put("USD", 1.11);
    exchangeRate.put("GBP", 0.87);

    CurrencyConverter converter = new User();
    converter.exchangeRates = exchangeRate;
    converter.monitoring(new Scanner(System.in));
  }
}
