package ru.yandex.practicum.delivery.parcel;

public abstract class Parcel {
    //добавьте реализацию и другие необходимые классы
    ParcelType type;
    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;
    protected static final int COST_ONE_UNIT_SHIPMENT_STANDARD_PARCEL = 2;
    protected static final int COST_ONE_UNIT_SHIPMENT_PERISHABLE_PARCEL = 3;
    protected static final int COST_ONE_UNIT_SHIPMENT_FRAGILE_PARCEL = 4;

    public Parcel(ParcelType type, String description, int weight, String deliveryAddress, int sendDay) {
        this.type = type;
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "type=" + type +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", sendDay=" + sendDay +
                '}';
    }

    public int getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

    public void packageItem() {
        System.out.printf("Посылка <<%s>> упакована%n", description);
    }

    public void deliver() {
        System.out.printf("Посылка <<%s>> доставлена по адресу %s%n", description, deliveryAddress);
    }

    protected abstract String getType();
    protected abstract int getUnitCost();

    public int calculateDeliveryCost() {
        int cost = weight * getUnitCost();
        return cost;
    }
}
