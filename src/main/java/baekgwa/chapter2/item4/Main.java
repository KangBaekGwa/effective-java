package baekgwa.chapter2.item4;

import baekgwa.chapter2.item4.printer.PrinterPrivate;
import baekgwa.chapter2.item4.printer.PrinterPublic;

public class Main {

    public static void main(String[] args) {
        PrinterPublic printerPublic = new PrinterPublic(); //public 생성자는 인스턴스화 가능
//        PrinterPrivate printerPrivate = new PrinterPrivate(); // private 생성자는 인스턴스화 불가능. 접근이 안됨
    }
}
