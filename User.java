package ex;

import java.util.Scanner;

public class User extends CurrencyConverter {

  public void runCurrencyConverter(Scanner scanner) {
    this.scanner = scanner;
    System.out.printf("%nДобро пожаловать в конвертер валют для пользователя!%n");
    boolean isRun = true;
    while (isRun) {
    printMenu();
      int command = scanner.nextInt();
      scanner.nextLine();

      switch (command) {
        case 1:
          printCurrency(exchangeRates);
          break;
        case 2:
          printCurrency(exchangeRates);
          convertCurrency(scanner, exchangeRates);
          break;
        case 3:
          monitoring(scanner);
          break;
        case 0:
          isRun = false;
          break;
        default:
          System.out.println("Некорректный выбор. Повторите попытку.");
          break;
      }
    }
    System.out.println("Программа завершена. До свидания!");
  }
  @Override
  protected void printMenu() {
    System.out.println("\n\t\t\t\t\t------Currency Converter ComersBank------");
    System.out.println("\t\t\t Добро пожаловать, выберите необходимую операцию из списка ниже:");
    System.out.println("\t\t1." + " Посмотреть список валют и их курс для обмена");
    System.out.println("\t\t2." + " Обмен валюты");
    System.err.println("\t\t3." + " Права Администратора");
    System.out.println("\t\t0." + " Выход из системы\n");
  }
}
