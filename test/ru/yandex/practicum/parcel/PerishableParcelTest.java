package ru.yandex.practicum.parcel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.parcel.ParcelType;
import ru.yandex.practicum.delivery.parcel.PerishableParcel;

import static org.junit.jupiter.api.Assertions.*;

class PerishableParcelTest {
    PerishableParcel parcel1;
    PerishableParcel parcel2;
    PerishableParcel parcel3;
    int currentDay;

    @BeforeEach
    void setUp() {
        parcel1 = new PerishableParcel(ParcelType.PERISHABLE,"Сгущёнка", 20,"Moscow", 15, 90);
        parcel2 = new PerishableParcel(ParcelType.PERISHABLE,"Рыба", 5,"Moscow", 15, 5);
        parcel3 = new PerishableParcel(ParcelType.PERISHABLE,"Живое пиво", 10,"Moscow", 15,3);
        currentDay = 20;
    }

    @Test
    void isExpired() {
        assertFalse(parcel1.isExpired(currentDay));
        assertFalse(parcel2.isExpired(currentDay));
        assertTrue(parcel3.isExpired(currentDay));
    }
}