package ru.yandex.practicum.parcel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.parcel.FragileParcel;
import ru.yandex.practicum.delivery.parcel.ParcelType;
import ru.yandex.practicum.delivery.parcel.PerishableParcel;
import ru.yandex.practicum.delivery.parcel.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;
class DeliveryCostTest {
    protected static final int COST_ONE_UNIT_SHIPMENT_STANDARD_PARCEL = 2;
    protected static final int COST_ONE_UNIT_SHIPMENT_PERISHABLE_PARCEL = 3;
    protected static final int COST_ONE_UNIT_SHIPMENT_FRAGILE_PARCEL = 4;
    StandardParcel parcel1;
    PerishableParcel parcel2;
    FragileParcel parcel3;

    @BeforeEach
    void setUp() {
        parcel1 = new StandardParcel(ParcelType.STANDARD,"Светильники", 20,"Moscow", 15);
        parcel2 = new PerishableParcel(ParcelType.PERISHABLE,"Рыба", 5,"Moscow", 15, 5);
        parcel3 = new FragileParcel(ParcelType.FRAGILE,"Посуда", 10,"Moscow", 15);
    }
    @Test
    void calculateDeliveryCost() {
        assertEquals(20 * 2, parcel1.calculateDeliveryCost());
        assertEquals(5 * 3, parcel2.calculateDeliveryCost());
        assertEquals(10 * 4, parcel3.calculateDeliveryCost());
    }
}