package baekgwa.chapter2.item4.printer;

public class PrinterPrivate {

    private PrinterPrivate() {
        throw new AssertionError("can't instantiate me");
    }

    public void printer() {
        System.out.println("public 생성자는 인스턴스화가 가능");
    }
}
