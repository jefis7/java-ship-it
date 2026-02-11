package ru.yandex.practicum.delivery.parcel;

public class PerishableParcel extends Parcel {
    private int timeToLive;

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

    @Override
    protected String getType() {
        return ParcelType.PERISHABLE.name();
    }

    public boolean isExpired(int currentDay) {
        if (sendDay + timeToLive >= currentDay) {
            return false;
        } else {
            return true;
        }
    }
}
