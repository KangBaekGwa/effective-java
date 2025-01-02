package baekgwa.chapter2.item7;

import baekgwa.chapter2.item7.stack.MyStack;
import baekgwa.chapter2.item7.stack.MyStackSolve;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //메모리 누수가 발생하는 stack
        MyStack myStack = new MyStack();

        for(int i=1; i<=10; i++) {
            myStack.push(i + "데이터");
        }

        for(int i=1; i<=5; i++) {
            myStack.pop();
        }

        System.gc(); //gc 실행

        //10개를 넣고, 5개를 빼서 사용했다.
        //남아있는건 5개여야 하고, 6 ~ 9 까지는 모두 값이 없어야 한다.
        //즉, "5데이터" 라는건 이 시점에서 없어야 하는 데이터 이다.
        System.out.println("myStack = " + Arrays.toString(myStack.getElements()));
        //출력해보면, "6데이터" 는 존재하는 데이터다.


        //따라서, 다음과 같이 MyStackSolve 에서는 pop 메서드에서
        //사용한 참조는 null로 지워준다.
        MyStackSolve myStackSolve = new MyStackSolve();

        for(int i=1; i<=10; i++) {
            myStackSolve.push(i + "데이터");
        }

        for(int i=1; i<=5; i++) {
            myStackSolve.pop();
        }
        System.gc(); //gc 실행
        System.out.println("myStackSolve = " + Arrays.toString(myStackSolve.getElements()));
    }
}
