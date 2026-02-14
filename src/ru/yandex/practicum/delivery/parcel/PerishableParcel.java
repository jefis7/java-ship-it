package ru.yandex.practicum.delivery.parcel;

public class PerishableParcel extends Parcel {
    private int timeToLive;
    protected static final int COST_ONE_UNIT_SHIPMENT_PERISHABLE_PARCEL = 3;

    public PerishableParcel(ParcelType type, String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(type, description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public String toString() {
        return "PerishableParcel{" +
                "type=" + type +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", sendDay=" + sendDay +
                ", timeToLive=" + timeToLive +
                '}';
    }

    @Override
    public int getUnitCost() {
        return COST_ONE_UNIT_SHIPMENT_PERISHABLE_PARCEL;
    }

    public boolean isExpired(int currentDay) {
        return sendDay + timeToLive < currentDay;
    }
}
