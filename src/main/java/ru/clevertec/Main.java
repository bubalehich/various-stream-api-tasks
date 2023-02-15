package ru.clevertec;

import ru.clevertec.model.*;
import ru.clevertec.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
        task16();
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

        animals.stream()
                .filter(a -> a.getOrigin().equals("Japanese"))
                .map(a -> a.getGender().equals("Female")
                        ? a.getBread().toUpperCase()
                        : a.getBread())
                .forEach(System.out::println);
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

        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .map(Animal::getAge)
                .reduce(Integer::max)
                .ifPresent(System.out::println);
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();

        /* With char array */
        animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .mapToInt(a -> a.length)
                .min()
                .ifPresent(System.out::println);

        /* Without char array */
        animals.stream()
                .map(Animal::getBread)
                .mapToInt(String::length)
                .min()
                .ifPresent(System.out::println);
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();

        System.out.println(
                animals.stream()
                        .mapToInt(Animal::getAge)
                        .sum()
        );
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .filter(a -> a.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge)
                .average()
                .ifPresent(System.out::println);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();

        people.stream()
                .filter(p -> p.getGender().equals("Male"))
                .filter(p -> Math.abs(ChronoUnit.YEARS.between(LocalDate.now(), p.getDateOfBirth())) >= 18
                        && Math.abs(ChronoUnit.YEARS.between(LocalDate.now(), p.getDateOfBirth())) <= 27)
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();

        houses.stream()
                .collect(Collectors.partitioningBy(x -> x.getBuildingType().equals("Hospital")))
                .entrySet()
                .stream()
                .flatMap(entry -> entry.getKey()
                        ? entry.getValue().stream().map(House::getPersonList).flatMap(Collection::stream).map(person -> Map.entry(1, person))
                        : entry.getValue().stream().map(House::getPersonList).flatMap(Collection::stream)
                        .map(person -> Math.abs(ChronoUnit.YEARS.between(LocalDate.now(), person.getDateOfBirth())) < 18
                                || Math.abs(ChronoUnit.YEARS.between(LocalDate.now(), person.getDateOfBirth())) > 58
                                ? Map.entry(2, person)
                                : Map.entry(3, person)))
                .sorted((entry1, entry2) -> entry1.getKey() - entry2.getKey())
                .limit(500)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        //        Продолжить...
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();

        System.out.println(
                flowers.stream()
                        .sorted(Comparator
                                .comparing(Flower::getOrigin)
                                .reversed()
                                .thenComparing(Flower::getPrice)
                                .thenComparing(Flower::getWaterConsumptionPerDay)
                                .reversed())
                        .filter(f -> f.getCommonName().matches("[C-S].*"))
                        .filter(Flower::isShadePreferred)
                        .filter(f -> f.getFlowerVaseMaterial().contains("Glass")
                                || f.getFlowerVaseMaterial().contains("Aluminum")
                                || f.getFlowerVaseMaterial().contains("Steel"))
                        .map(f -> f.getPrice() + f.getWaterConsumptionPerDay() * 1.39 * 365 * 5)
                        .reduce(0.0, Double::sum)
        );
        //result 3629693.9850000036
    }

    private static void task16() throws IOException {
        List<Car> cars = Util.getCars();

        /* Finding Bumblbee (Chevrolet Camaro, 1977) */
        cars.stream()
                .filter(c -> c.getColor().equals("Yellow")
                        && c.getCarMake().equals("Chevrolet")
                        && c.getCarMake().equals("Camaro")
                        && c.getReleaseYear() == 1977)
                .findFirst()
                .ifPresent(c -> System.out.println("Bumblbee was found!"));

        /* Search for a car to buy (by year, price & mass)*/
        var carsToBuy = cars.stream()
                .sorted(
                        Comparator.comparing(Car::getReleaseYear)
                                .reversed()
                                .thenComparing(Car::getPrice)
                                .thenComparing(Car::getMass)
                )
                .filter(c -> c.getColor().equals("Indigo")
                        || c.getColor().equals("Aquamarine")
                        || c.getColor().equals("Blue"))
                .peek(System.out::println)
                .toList();
    }
}
