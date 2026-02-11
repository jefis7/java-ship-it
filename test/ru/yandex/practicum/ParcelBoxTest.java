package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.box.ParcelBox;
import ru.yandex.practicum.delivery.parcel.ParcelType;
import ru.yandex.practicum.delivery.parcel.StandardParcel;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {
    ParcelBox<StandardParcel> box;
    StandardParcel parcel1;

    @BeforeEach
    void setUp() {
        box = new ParcelBox<>(20);
        parcel1 = new StandardParcel(ParcelType.STANDARD,"Светильники", 10,"Moscow", 15);
        box.addParcel(parcel1);
    }

    @Test
    void getAllParcels() {
        int expectedSize = 1;
        List<StandardParcel> actual = box.getAllParcels();

        assertEquals(expectedSize, actual.size());
        assertTrue(box.getAllParcels().contains(parcel1));
    }

    @Test
    void addParcelUnderLimit() {
        int expectedSize = 2;
        StandardParcel parcel2 = new StandardParcel(ParcelType.STANDARD,"Саундбар",5,"Moscow", 20);

        box.addParcel(parcel2);
        List<StandardParcel> actual = box.getAllParcels();

        assertEquals(expectedSize, actual.size());
        assertTrue(box.getAllParcels().contains(parcel1));
        assertTrue(box.getAllParcels().contains(parcel2));
    }

    @Test
    void addParcelAboveLimit() {
        int expectedSize = 1;
        StandardParcel parcel2 = new StandardParcel(ParcelType.STANDARD,"ТВ",15,"Moscow", 17);

        box.addParcel(parcel2);
        List<StandardParcel> actual = box.getAllParcels();

        assertEquals(expectedSize, actual.size());
        assertTrue(box.getAllParcels().contains(parcel1));
        assertFalse(box.getAllParcels().contains(parcel2));
    }
}