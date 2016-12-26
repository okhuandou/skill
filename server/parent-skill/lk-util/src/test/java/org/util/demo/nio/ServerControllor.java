package org.util.demo.nio;

/**
 * Created by lk on 2016/12/26.
 */
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerControllor {
    private int port;
    private Thread thread = new ServerThread();;
    private Object lock = new Object();
    public ServerControllor(){
        this(0);
    }
    public ServerControllor(int port){
        this.port = port;
    }

    public void start(){
        if(thread.isAlive()){
            return;
        }
        synchronized (lock) {
            thread.start();
            System.out.println("Server starting....");
        }
    }


    class ServerThread extends Thread {
        private static final int TIMEOUT = 3000;
        private ServerHandler handler = new ServerHandler();
        @Override
        public void run(){
            try{
                ServerSocketChannel channel = null;
                try{
                    channel = ServerSocketChannel.open();
                    channel.configureBlocking(false);
                    channel.socket().setReuseAddress(true);
                    channel.socket().bind(new InetSocketAddress(port));
                    Selector selector = Selector.open();
                    channel.register(selector, SelectionKey.OP_ACCEPT);
                    while(selector.isOpen()){
                        System.out.println("Server is running,port:" + channel.socket().getLocalPort());
                        if(selector.select(TIMEOUT) == 0){
                            continue;
                        }
                        Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                        while(it.hasNext()){
                            SelectionKey key = it.next();
                            it.remove();
                            if(!key.isValid()){
                                continue;
                            }
                            if(key.isAcceptable()){
                                accept(key);
                            }else if(key.isReadable()){
                                read(key);
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    if(channel != null){
                        try{
                            channel.close();
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        private void accept(SelectionKey key) throws Exception{
            SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
            socketChannel.configureBlocking(true);
            //socketChannel.register(key.selector(), SelectionKey.OP_READ);
            handler.handle(socketChannel);
        }

        private void read(SelectionKey key) throws Exception{
            SocketChannel channel = (SocketChannel)key.channel();
            //handler.handle(channel);
        }
    }
}
