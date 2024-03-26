package ex;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public abstract class CurrencyConverter {

  protected HashMap<String, Double> exchangeRates;
  protected Scanner scanner;

  public CurrencyConverter() {
    this.exchangeRates = new HashMap<>();
  }

  public void printCurrency(HashMap<String, Double> exchangeRates) {
    System.out.printf("\t\t\t\t\tКурс по отношению к евро по состоянию на %s%n", getCurrentDate());
    for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
      System.out.printf("\t\t%-4s: %.2f%n", entry.getKey(), entry.getValue());
    }
    System.out.println();
  }

  public void monitoring(Scanner scanner) {
    this.scanner = scanner;
    System.out.println("\n\t\t\t\t\tДобро пожаловать!");
    System.out.println("\t\t\tДля входа выберите уровень Вашего доступа");
    System.out.println("\t\t\t1. Пользователь");
    System.out.println("\t\t\t2. Администратор");

    int accessLevel;
    while (true) {
      while (!scanner.hasNextInt()) {
        String error = scanner.nextLine();
        System.out.println("Некорректный ввод: " + error
            + " \nВведите 1 - для пользователя или 2 - для администратора");
        System.out.print("Для входа выберите уровень Вашего доступа: ");
      }
      accessLevel = scanner.nextInt();
      scanner.nextLine();

      if (accessLevel == 1 || accessLevel == 2) {
        break;
      } else {
        System.out.println("Некорректный выбор. Повторите попытку.");
      }
    }

    if (accessLevel == 1) {
      User user = new User();
      user.exchangeRates = this.exchangeRates;
      user.runCurrencyConverter(scanner);
    } else if (accessLevel == 2) {
      while (true) {
        System.out.print("Введите пароль для администратора: ");
        String adminPassword = scanner.nextLine();
        if (isAdminPasswordCorrect(adminPassword)) {
          Admin admin = new Admin();
          admin.exchangeRates = this.exchangeRates;
          admin.runCurrencyConverter(scanner);
          break;
        } else {
          System.out.println("Неверный пароль для администратора. Повторите попытку.");
        }
      }
    } else {
      System.out.println("До встречи!");
    }
  }

  public boolean isAdminPasswordCorrect(String inputPassword) {
    String correctPassword = "123";
    return inputPassword.equals(correctPassword);
  }

  public void addCurrency(Scanner scanner, HashMap<String, Double> exchangeRates) {
    System.out.print("Введите название добавляемой валюты: ");
    String newName = checkAddCurrency(scanner);

    if (!exchangeRates.containsKey(newName)) {
      System.out.println("Введите курс валюты: ");

      double newValue = checkNumber(scanner);
      scanner.nextLine();
      exchangeRates.put(newName, newValue);
      System.out.println("Новая валюта: " + newName + " успешно добавлена");
    } else {
      System.out.println("Эта валюта есть в наличии: " + newName);
    }
  }

  public double convertCurrency(Scanner scanner, HashMap<String, Double> exchangeRates) {
    System.out.print("Введите исходную валюту: ");
    String sourceCurrency = checkCurrency(scanner, exchangeRates);
    System.out.print("Введите конечную валюту: ");
    String targetCurrency = checkCurrency(scanner, exchangeRates);
    System.out.print("Количество исходной валюты для конвертации: ");
    double amount = checkNumber(scanner);
    double result = amount * exchangeRates.get(targetCurrency) / exchangeRates.get(sourceCurrency);
    System.out.println("\n\t\tВаш запрос: " + amount + " " + sourceCurrency + " конвертировать в "
        + targetCurrency);
    System.out.println(
        "\t\tРезультат по этой операции: " + String.format("%.2f", result) + " " + targetCurrency);
    return amount;
  }

  public void removeCurrency(Scanner scanner, HashMap<String, Double> exchangeRates) {
    System.out.print("\tВведите название валюты: ");
    String removeName = checkCurrency(scanner, exchangeRates);

    if (exchangeRates.containsKey(removeName)) {
      exchangeRates.remove(removeName);
      System.out.println("\tВалюта " + removeName + " удалена из списка");
    } else {
      System.out.println("\tВалюты " + removeName + " в списке нет");
    }
  }

  public void newValueCurrency(Scanner scanner, HashMap<String, Double> exchangeRates) {
    System.out.print("\tОбновить курс: ");
    String currencyName = checkCurrency(scanner, exchangeRates);

    if (exchangeRates.containsKey(currencyName)) {
      System.out.print("\tВведите новый курс для " + currencyName + ": ");
      double newRate = checkNumber(scanner);
      scanner.nextLine();
      exchangeRates.put(currencyName, newRate);
      System.out.println("\tКурс валюты " + currencyName + " успешно обновлен на " + newRate);
    } else {
      System.out.println("\tВалюты " + currencyName + " нет в списке, чтобы обновить её курс.");
    }
  }
  protected abstract void printMenu();

  public String getCurrentDate() {
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd. MM. yyyy");
    return currentDate.format(formatter);
  }

  public String checkCurrency(Scanner scanner, HashMap<String, Double> exchangeRates) {
    String name = scanner.nextLine().toUpperCase();
    while (name.isEmpty() || !exchangeRates.containsKey(name)) {
      System.out.println("Некорректное название валюты: " + name);
      System.out.print("\tВведите название валюты: ");
      name = scanner.nextLine().toUpperCase();
    }
    return name;
  }

  public String checkAddCurrency(Scanner scanner) {
    String name = scanner.nextLine().toUpperCase();
    while (name.isEmpty()) {
      System.out.println("Некорректное название валюты: " + name);
      System.out.print("\tВведите название валюты: ");
      name = scanner.nextLine().toUpperCase();
    }
    return name;
  }

  public double checkNumber(Scanner scanner) {
    double number;
    while (true) {
      if (scanner.hasNextDouble()) {
        number = scanner.nextDouble();
        if (number > 0) {
          break;
        } else {
          System.out.println(
              "Обратите внимание на корректность ввода - число не может быть отрицательным");
          System.out.print("Введите корректное число:");
        }
      } else {
        String error = scanner.nextLine();
        System.out.println(
            "Некорректный ввод: " + error + "\n Введите корректное значение валюты");
      }
    }
    return number;
  }
}
