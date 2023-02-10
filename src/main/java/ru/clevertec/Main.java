package ru.clevertec;

import ru.clevertec.model.*;
import ru.clevertec.util.Util;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();

        AtomicInteger index = new AtomicInteger(0);
        animals.stream()
                .filter(a -> a.getAge() > 10 && a.getAge() < 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .collect(Collectors.groupingBy(x -> index.getAndIncrement() / 7))
                .get(2)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        //        animals.stream() Продолжить ...

    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .filter(a -> a.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(s -> s.matches("A.*"))
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println(
                animals.stream()
                        .filter(a -> a.getGender().equals("Female"))
                        .count()
        );
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println(
                animals.stream()
                        .filter(a -> a.getAge() > 20 && a.getAge() < 30)
                        .anyMatch(a -> a.getOrigin().equals("Hungarian"))
                        ? "Animal from Hungarian exists."
                        : "Animal from Hungarian not exists."
        );
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println(
                animals.stream()
                        .anyMatch(a -> !a.getGender().equals("Male") && !a.getGender().equals("Female"))
                        ? "Not all animals have only `Male` or `Female` gender."
                        : "All animals have only `Male` or `Female` gender."
        );
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println(
                animals.stream()
                        .anyMatch(a -> a.getOrigin().equals("Oceania"))
                        ? "Animals from Oceania exist."
                        : "Animal from Oceania not exist."
        );
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        //        animals.stream() Продолжить ...
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        //        animals.stream() Продолжить ...
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        //        animals.stream() Продолжить ...
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        //        animals.stream() Продолжить ...
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
//        Продолжить...
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        //        Продолжить...
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        //        Продолжить...
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        //        Продолжить...
    }
}