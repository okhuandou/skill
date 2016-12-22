package org.lk.skill;

/**
 * Created by lk on 2016/12/21.
 */
public class ClassLoadTest {

    public static void main(String[] args) {
        ClassLoader loader = ClassLoadTest.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }
}
