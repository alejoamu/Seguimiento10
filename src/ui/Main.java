package ui;

import model.CountriesList;
import model.Country;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);


    static CountriesList countriesList = new CountriesList();

    public static void main(String[] args) throws IOException {
        try {
            countriesList.load();
        } catch (NullPointerException ex) {

        }
        int option = 0;
        while (option != 5) {
            System.out.println("Welcome to Olympic games Paris 2024");
            System.out.println("\n1. Enter a country\n2. Show medals\n3. Show total medals\n4. Show countries\n5. Exit");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Please enter a country with the format: ");
                    System.out.println("Country::MedalType::Quantity");
                    String country = sc.nextLine();
                    String[] data = country.split("::");
                    addCountry(data);
                    countriesList.save();
                    break;
                case 2:
                    System.out.println("Please select an option");
                    System.out.println("\n1. Conventional show\n2. show by total medals\n3. show by alphabetic order");
                    int op = sc.nextInt();
                    switch (op) {
                        case 1:
                            conventionalShow();
                            break;
                        case 2:
                            showByTotalMedals();
                            break;
                        case 3:
                            showByAlphabet();
                            break;
                    }
                    break;
                case 3:
                    showByTotalMedals();
                    break;
                case 4:
                    countriesList.show();
                case 5:
                    break;
            }
        }

    }


    public static void addCountry(String[] countryData) throws IOException {
        String countryName = countryData[0];
        String[] medals = Arrays.copyOfRange(countryData, 1, countryData.length);

        for (int i = 0; i < countriesList.getCountries().size(); i++) {

            if (countriesList.getCountries().get(i).getName().equals(countryName)) {
                countriesList.getCountries().get(i).addMedals(medals);
                return;
            }
        }
        countriesList.getCountries().add(new Country(countryName));
        countriesList.getCountries().get(countriesList.getCountries().size() - 1).addMedals(medals);
    }

    public static void conventionalShow() {
        ArrayList<Country> countries = countriesList.getCountries();
        Collections.sort(countries, (a, b) -> {
            int criteria1 = b.getMedals()[0] - a.getMedals()[0];
            if (criteria1 == 0) {
                int criteria2 = b.getMedals()[1] - a.getMedals()[1];
                if (criteria2 == 0) {
                    int criteria3 = b.getMedals()[2] - a.getMedals()[2];
                    if (criteria3 == 0) {
                        showByAlphabet();
                    }
                    return criteria3;
                }
                return criteria2;
            } else {
                return criteria1;
            }
        });

        countries.forEach(country -> {
            System.out.println(country.getName() + " " + "::Gold::" + country.getMedals()[0] + "::Silver::" +
                    country.getMedals()[1] + "::Bronze::" + country.getMedals()[2] + "::Total::" + country.getTotalMedals());
        });
    }

    public static void showByTotalMedals() {
        ArrayList<Country> countries = countriesList.getCountries();
        Collections.sort(countries, (a, b) -> {
            return b.getTotalMedals() - a.getTotalMedals();
        });

        countries.forEach(country -> {
            System.out.println(country.getName() + " " + country.getTotalMedals());
        });
    }

    public static void showByAlphabet() {
        ArrayList<Country> country = countriesList.getCountries();
        burbleSort(country);
        for (int i = 0; i < country.size(); i++) {
            System.out.println(country.get(i).getName() + "::Gold::" + country.get(i).getMedals()[0] + "::Silver::" + country.get(i).getMedals()[1] +
                    "::Bronze::" + country.get(i).getMedals()[2] + "::Total::" + country.get(i).getTotalMedals());
        }
    }


    public static void burbleSort(ArrayList<Country> countries) {
        for (int i = 0; i < countries.size() - 1; i++) {
            for (int n = 1; n < countries.size() - i; n++) {
                if (countries.get(n - 1).getName().compareTo(countries.get(n).getName()) > 0) {
                    Country menor = countries.get(n);
                    Country mayor = countries.get(n - 1);
                    countries.set(n - 1, menor);
                    countries.set(n, mayor);
                }
            }
        }
    }

}