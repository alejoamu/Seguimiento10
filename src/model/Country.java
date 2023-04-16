package model;

public class Country implements Comparable<Country> {
    private String name;
    private int[] medals;
    private int totalMedals;


    public Country(String name) {
        this.name = name;
        medals = new int[3];
        totalMedals = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getMedals() {
        return medals;
    }

    public void setMedals(int[] medals) {
        this.medals = medals;
    }

    public int getTotalMedals() {
        return totalMedals;
    }

    public void setTotalMedals(int totalMedals) {
        this.totalMedals = totalMedals;
    }

    public void addMedals(String[] medals) {
        int count = 0;
        while (count < medals.length) {
            addTypeMedal(medals, count);
            count += 2;
        }
    }

    public void addTypeMedal(String[] medalsArr, int count) {
        Medals medalType = Medals.valueOf(medalsArr[count].toUpperCase());
        int quantity = Integer.parseInt(medalsArr[count + 1]);

        for (int i = 0; i < Medals.values().length; i++) {
            if (Medals.values()[i] == medalType) {
                medals[i] += quantity;
                totalMedals += quantity;
                break;
            }
        }
    }

    @Override
    public int compareTo(Country o) {
        return 0;
    }
}
