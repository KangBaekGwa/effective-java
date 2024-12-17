package baekgwa.chapter2.item3;

import baekgwa.chapter2.item3.elvis.Elvis;
import baekgwa.chapter2.item3.elvis.ElvisEnum;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        // 싱글턴 객체 직렬화
//        Elvis instance1 = Elvis.getInstance();
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("elvis.ser"));
//        out.writeObject(instance1);
//        out.close();
//
//        // 싱글턴 객체 역직렬화
//        ObjectInputStream in = new ObjectInputStream(new FileInputStream("elvis.ser"));
//        Elvis instance2 = (Elvis) in.readObject();
//        in.close();
//
//        // 싱글턴인지 비교
//        System.out.println(instance1 == instance2);  // true


        //case 3)
        ElvisEnum elvisEnum1 = ElvisEnum.INSTANCE;
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("elvisEnum.ser"));
        out.writeObject(elvisEnum1);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("elvisEnum.ser"));
        ElvisEnum elvisEnum2 = (ElvisEnum) in.readObject();
        in.close();
        System.out.println(elvisEnum1 == elvisEnum2);  // true
    }
}
