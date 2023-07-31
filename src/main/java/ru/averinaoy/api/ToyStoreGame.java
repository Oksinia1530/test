
// Необходимо написать программу – розыгрыша игрушек в магазине детских товаров.

package ru.averinaoy.api.lesson1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Toy {
    private int id;
    private String name;
    private int quantity;
    private int dropFrequency;

    public Toy(int id, String name, int quantity, int dropFrequency) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.dropFrequency = dropFrequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDropFrequency() {
        return dropFrequency;
    }

    public void setDropFrequency(int dropFrequency) {
        this.dropFrequency = dropFrequency;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class ToyStore {
    private List<Toy> toys;

    public ToyStore() {
        toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void updateDropFrequency(int toyId, int dropFrequency) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setDropFrequency(dropFrequency);
                break;
            }
        }
    }

    public void drawToy() {
        int totalFrequency = 0;

        for (Toy toy : toys) {
            totalFrequency += toy.getDropFrequency();
        }

        Random random = new Random();
        int randomNum = random.nextInt(totalFrequency) + 1;
        int accumulatedFrequency = 0;

        for (Toy toy : toys) {
            accumulatedFrequency += toy.getDropFrequency();
            if (randomNum <= accumulatedFrequency) {
                if (toy.getQuantity() > 0) {
                    System.out.println("Поздравляем! Вы выиграли -  " + toy.getName() + ".");
                    toy.setQuantity(toy.getQuantity() - 1);
                } else {
                    System.out.println("Извините, больше игрушек нет! " + toy.getName() + " available.");
                }
                break;
            }
        }
    }
}



public class ToyStoreGame {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        toyStore.addToy(new Toy(1, "Машинка", 5, 20));
        toyStore.addToy(new Toy(2, "Lego", 3, 10));
        toyStore.addToy(new Toy(3, "Кукла", 7, 15));
        toyStore.addToy(new Toy(4, "Зайчик", 7, 5));
        toyStore.addToy(new Toy(5, "Пирамидка", 7, 10));
        toyStore.addToy(new Toy(6, "Пазл", 7, 15));

        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;

        while (shouldContinue) {
            System.out.println("1. Получить приз!");
            System.out.println("2. Добавить игрушку.");
            System.out.println("3. Обновить частоту выпадения игрушкек.");
            System.out.println("4. Выйти.");
            System.out.print("Выбери действие: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    toyStore.drawToy();
                    break;

                case 2:
                    System.out.print("Введите ID игрушки: ");
                    int id = scanner.nextInt();
                    System.out.print("Введите название игрушки: ");
                    String name = scanner.next();
                    System.out.print("Введите колличество игрушек: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Введите вес игрушки в розыгрыше: ");
                    int dropFrequency = scanner.nextInt();
                    toyStore.addToy(new Toy(id, name, quantity, dropFrequency));
                    break;
                case 3:

                    System.out.print("Введите ID игрушки, чтобы обновить частоту выпадения: ");
                    int toyId = scanner.nextInt();
                    System.out.print("Введите новую частоту выпадения: ");
                    int newDropFrequency = scanner.nextInt();
                    toyStore.updateDropFrequency(toyId, newDropFrequency);
                    break;

                case 4:
                    shouldContinue = false;
                    break;
                default:
                    System.out.println("Ошибка. Введите верное значение.");
                    break;
            }
        }

        System.out.println("Спасибо за игру!");
    }
}