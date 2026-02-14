package ru.yandex.practicum.delivery.parcel;

import ru.yandex.practicum.delivery.tracking.Trackable;

public class FragileParcel extends Parcel implements Trackable {
    protected static final int COST_ONE_UNIT_SHIPMENT_FRAGILE_PARCEL = 4;

    public FragileParcel(ParcelType type, String description, int weight, String deliveryAddress, int sendDay) {
        super(type, description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.printf("Посылка <<%s>> обёрнута в защитную плёнку\n", description);
        super.packageItem();
    }

    @Override
    public int getUnitCost() {
        return COST_ONE_UNIT_SHIPMENT_FRAGILE_PARCEL;
    }

    @Override
    public void reportStatus(String newLocation){
        System.out.printf("Хрупкая посылка <<%s>> изменила местоположение на %s\n", description, newLocation);
    }
}
