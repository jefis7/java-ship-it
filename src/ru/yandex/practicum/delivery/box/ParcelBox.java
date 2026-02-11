package ru.yandex.practicum.delivery.box;

import ru.yandex.practicum.delivery.parcel.Parcel;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private List<T> parcels = new ArrayList<>();
    private int totalWeight = 0;
    private int limitWeight;

    public ParcelBox(int limitWeight) {
        this.limitWeight = limitWeight;
    }

    public List<T> getAllParcels() {
        return parcels;
    }

    public void printAllParcels() {
        for (T parcel: parcels) {
            System.out.println(parcel);
        }
    }

    public void addParcel(T parcel) {
        if (totalWeight + parcel.getWeight() > limitWeight) {
            System.out.printf("Вес превышен, добавление посылки %s невозможно", parcel.getDescription());
            return;
        }
        parcels.add(parcel);
        totalWeight += parcel.getWeight();
    }

    public boolean isEmpty() {
        return parcels.isEmpty();
    }
}
