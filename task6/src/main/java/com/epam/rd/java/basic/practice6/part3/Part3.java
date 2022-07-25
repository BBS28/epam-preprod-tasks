package com.epam.rd.java.basic.practice6.part3;

public class Part3 {

    public static void main(String[] args) {
        Parking parking = new Parking(4);
        parking.arrive(2);  // 0010, true
        parking.print();
        parking.arrive(3);  // 0011, true
        parking.print();
        parking.arrive(2);  // 1011, true
        parking.print();
        parking.arrive(2);  // 1111, true
        parking.print();
        parking.arrive(2);  // 1111, false
        parking.print();
        parking.depart(1);  // 1011, true
        parking.print();
        parking.depart(1);  // 1011, false
        parking.print();
    }
}
