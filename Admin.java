package ex;

import java.util.Scanner;

public class Admin extends CurrencyConverter {

  public void runCurrencyConverter(Scanner scanner) {
    System.out.println("\n\t\t\tДобро пожаловать с правами администратора");
    boolean isRun = true;
    while (isRun) {
      printAdminMenu();
      System.out.print("Выберите действие: ");
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
          addCurrency(scanner, exchangeRates);
          System.out.println("Обновленный список:");
          printCurrency(exchangeRates);
          break;

        case 4:
          removeCurrency(scanner, exchangeRates);
          System.out.println("Обновленный список:");
          printCurrency(exchangeRates);
          break;

        case 5:
          printCurrency(exchangeRates);
          newValueCurrency(scanner, exchangeRates);
          System.out.println("Обновленный список:");
          printCurrency(exchangeRates);
          break;

        case 6:
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
  }
  @Override
  protected void printMenu() {
  }

  private static void printAdminMenu() {
    System.out.println("\n\t\t\t\t\tМеню администратора:\n");
    System.out.println("\t\t\t1." + " Список валют и курс");
    System.out.println("\t\t\t2." + " Конвертировать валюту");
    System.out.println("\t\t\t3." + " Добавить валюту");
    System.out.println("\t\t\t4." + " Удалить валюту");
    System.out.println("\t\t\t5." + " Изменить курс");
    System.out.println("\t\t\t6." + " Вернуться в предыдущее меню");
    System.out.println("\t\t\t0." + " Выход из системы");
    System.out.println();
  }
}
