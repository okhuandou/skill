package org.util.demo.nio;

/**
 * Created by lk on 2016/12/26.
 */
public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        int port = 30008;
        ServerControllor sc = new ServerControllor(port);
        sc.start();
        Thread.sleep(2000);
        ClientControllor cc = new ClientControllor("127.0.0.1", port);
        cc.start();
        Packet p1 = Packet.wrap("Hello,I am first!");
        cc.put(p1);
        Packet p2 = Packet.wrap("Hello,I am second!");
        cc.put(p2);
        Packet p3 = Packet.wrap("Hello,I am thread!");
        cc.put(p3);

    }
}
