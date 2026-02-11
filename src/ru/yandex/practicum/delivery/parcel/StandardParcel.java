package ru.yandex.practicum.delivery.parcel;

public class StandardParcel extends Parcel {
    public StandardParcel(ParcelType type, String description, int weight, String deliveryAddress, int sendDay) {
        super(type, description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int getUnitCost() {
        return COST_ONE_UNIT_SHIPMENT_STANDARD_PARCEL;
    }

    @Override
    protected String getType() {
        return ParcelType.STANDARD.name();
    }
}
