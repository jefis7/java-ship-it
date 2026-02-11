import ru.yandex.practicum.delivery.box.ParcelBox;
import ru.yandex.practicum.delivery.parcel.*;
import ru.yandex.practicum.delivery.tracking.Trackable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableShipments = new ArrayList<>();
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(20);
    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(20);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(20);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    getAllTrackableShipment();
                    break;
                case 5:
                    reportStatusAllTrackableShipment();
                    break;
                case 6:
                    showBoxContents();
                    break;
                case 7:
                    checkExpirationDate();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Список всех посылок, поддерживающих трекинг: ");
        System.out.println("5 — Информация о текущем местоположении и статусе отправления всех посылок, поддерживающих трекинг");
        System.out.println("6 - Показать содержимое коробки");
        System.out.println("7 - Проверка срока годности скоропортящейся посылки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже
    private static boolean isPositiveNumber(String input) {
        if (input.isEmpty()) {
            return false;
        }

        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        ParcelType[] types = ParcelType.values();
        ParcelType type;

        // выбор типа посылки
        while (true) {
            System.out.println("Введите тип посылки:");

            for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + ". " + types[i]);
            }

            String input = scanner.nextLine();

            if (!isPositiveNumber(input)) {
                System.out.println("Ошибка: нужно ввести число!");
                continue;
            }

            int choice = Integer.parseInt(input);

            if (choice < 1 || choice > types.length) {
                System.out.println("Неверный номер. Попробуйте ещё раз.");
                continue;
            }

            type = types[choice - 1];
            break;
        }

        // ввод остальных полей
        System.out.println("Введите описание посылки:");
        String description = scanner.nextLine();

        int weight;
        while (true) {
            System.out.println("Введите вес посылки:");
            String input = scanner.nextLine();

            if (!isPositiveNumber(input)) {
                System.out.println("Вес должен быть положительным числом!");
                continue;
            }

            weight = Integer.parseInt(input);

            if (weight == 0) {
                System.out.println("Вес должен быть положительным числом больше 0");
                continue;
            }

            break;
        }

        System.out.println("Введите адрес доставки:");
        String deliveryAddress = scanner.nextLine();

        int sendDay;
        while (true) {
            System.out.println("Введите день отправки:");
            String input = scanner.nextLine();

            if (!isPositiveNumber(input)) {
                System.out.println("День должен быть положительным числом!");
                continue;
            }

            sendDay = Integer.parseInt(input);

            if (sendDay > 31) {
                System.out.println("День должен быть положительным числом от 1 до 31!");
                continue;
            }
            break;
        }
        // создание посылки
        Parcel parcel;
        switch (type) {
            case PERISHABLE: {
                int timeToLive;
                while (true) {
                    System.out.println("Введите срок годности:");
                    String input = scanner.nextLine();

                    if (!isPositiveNumber(input)) {
                        System.out.println("Срок годности должен быть положительным числом!");
                        continue;
                    }

                    timeToLive = Integer.parseInt(input);
                    break;
                }
                parcel = new PerishableParcel(type, description, weight, deliveryAddress, sendDay, timeToLive);
                perishableBox.addParcel((PerishableParcel) parcel);
                break;
            }

            case FRAGILE: {
                parcel = new FragileParcel(type, description, weight, deliveryAddress, sendDay);
                fragileBox.addParcel((FragileParcel) parcel);
                break;
            }

            case STANDARD: {
                parcel = new StandardParcel(type, description, weight, deliveryAddress, sendDay);
                standardBox.addParcel((StandardParcel) parcel);
                break;
            }
            default:
                System.out.println("Неизвестный тип посылки!");
                return;
        }
        // добавление посылки в списки
        allParcels.add(parcel);
        if (parcel instanceof Trackable) {
            trackableShipments.add((Trackable) parcel);
        }
        System.out.println("Посылка успешно добавлена ✅");
    }


    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int allCosts = 0;
        for (Parcel parcel : allParcels) {
            allCosts += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок: " + allCosts);
    }

    private static void getAllTrackableShipment() {
        if (trackableShipments.isEmpty()) {
            System.out.println("Нет посылок, поддерживающих трекинг.");
            return;
        }
        System.out.println("Список всех посылок, поддерживающих трекинг: ");
        for (Trackable shipment : trackableShipments) {
            System.out.println(shipment);
        }
    }

    private static void reportStatusAllTrackableShipment() {
        for (Trackable shipment : trackableShipments) {
            System.out.println(shipment);
            System.out.println("Введите новое местоположение отправления: ");
            String input = scanner.nextLine();
            shipment.reportStatus(input);
        }
    }

    private static void showBoxContents() {
        ParcelType[] types = ParcelType.values();
        ParcelType type;
        // выбор типа посылки
        while (true) {
            System.out.println("Введите тип посылки:");

            for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + ". " + types[i]);
            }

            String input = scanner.nextLine();

            if (!isPositiveNumber(input)) {
                System.out.println("Ошибка: нужно ввести число!");
                continue;
            }

            int choice = Integer.parseInt(input);

            if (choice < 1 || choice > types.length) {
                System.out.println("Неверный номер. Попробуйте ещё раз.");
                continue;
            }

            type = types[choice - 1];
            break;
        }
        switch (type) {
            case PERISHABLE: {
                if (perishableBox.isEmpty()) {
                    System.out.println("Нет скоропортящихся посылок.");
                    break;
                }
                System.out.println("Список скоропортящихся посылок: ");
                perishableBox.printAllParcels();
                break;
            }

            case FRAGILE: {
                if (fragileBox.isEmpty()) {
                    System.out.println("Нет хрупких посылок.");
                    break;
                }
                System.out.println("Список хрупких посылок: ");
                fragileBox.printAllParcels();
                break;
            }

            case STANDARD: {
                if (standardBox.isEmpty()) {
                    System.out.println("Нет стандартных посылок.");
                    break;
                }
                System.out.println("Список стандартных посылок: ");
                standardBox.printAllParcels();
                break;
            }
        }
    }

private static void checkExpirationDate() {
    if (perishableBox.isEmpty()) {
        System.out.println("Нет скоропортящихся посылок.");
        return;
    }

    // ввод текущего дня
    int currentDay;

    while (true) {
        System.out.println("Введите текущий день месяца:");

        String input = scanner.nextLine();

        if (!isPositiveNumber(input)) {
            System.out.println("Введите число!");
            continue;
        }

        currentDay = Integer.parseInt(input);
        if (currentDay > 31) {
            System.out.println("День должен быть положительным числом от 1 до 31!");
            continue;
        }
        break;
    }

    // вывод списка
    List<PerishableParcel> list = perishableBox.getAllParcels();

    System.out.println("Выберите посылку:");
    for (int i = 0; i < list.size(); i++) {
        System.out.println((i + 1) + ". " + list.get(i));
    }

    int choice;

    while (true) {
        String input = scanner.nextLine();

        if (!isPositiveNumber(input)) {
            System.out.println("Введите число!");
            continue;
        }

        choice = Integer.parseInt(input);

        if (choice < 1 || choice > list.size()) {
            System.out.println("Неверный номер!");
            continue;
        }

        break;
    }

    PerishableParcel selected = list.get(choice - 1);


    // проверка
    if (selected.isExpired(currentDay)) {
        System.out.println("Посылка испортилась ❌");
    } else {
        System.out.println("Посылка ещё свежая ✅");
    }
}
}
