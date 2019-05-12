package util;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++)
            new Thread(() -> {
                for (int j = 0; j < 100; j++)
                    test.increase();
            }).start();

        while(Thread.activeCount()>2)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}
