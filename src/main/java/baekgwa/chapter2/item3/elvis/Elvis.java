package baekgwa.chapter2.item3.elvis;

import java.io.Serializable;

public class Elvis implements Serializable {
    private static final Elvis INSTANCE = new Elvis();

    // 정적 팩터리 메서드
    public static Elvis getInstance() {
        return INSTANCE;
    }

    private Elvis() { }  // private 생성자 (싱글턴 보장)

    public void print() {
        System.out.println("Hello, I'm Elvis Singleton!");
    }

    // 직렬화 후 역직렬화 과정에서 항상 동일한 인스턴스를 반환하기 위해 readResolve 메서드 추가
    private Object readResolve() {
        return INSTANCE;  // 싱글턴 인스턴스를 반환
    }
}
