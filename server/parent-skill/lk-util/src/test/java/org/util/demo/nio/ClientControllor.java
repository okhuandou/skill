package org.util.demo.nio;

/**
 * Created by lk on 2016/12/26.
 */

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

public class ClientControllor {

    private BlockingQueue<Packet> inner = new LinkedBlockingQueue<Packet>(100);//no any more
    private Object lock = new Object();
    private InetSocketAddress remote;
    private Thread thread = new ClientThread(remote);
    public ClientControllor(String host,int port){
        remote = new InetSocketAddress(host, port);
    }

    public void start(){
        if(thread.isAlive() || remote == null){
            return;
        }
        synchronized (lock) {
            thread.start();
        }


    }
    public boolean put(Packet packet){
        return inner.offer(packet);
    }

    public void clear(){
        inner.clear();
    }

    class ClientThread extends Thread {
        SocketAddress remote;
        SocketChannel channel;
        ClientThread(SocketAddress remote){
            this.remote = remote;
        }
        @Override
        public void run(){
            try{
                try{
                    channel = SocketChannel.open();
                    channel.configureBlocking(true);
                    boolean isSuccess = channel.connect(new InetSocketAddress(30008));
                    if(!isSuccess){
                        while(!channel.finishConnect()){
                            System.out.println("Client is connecting...");
                        }
                    }
                    System.out.println("Client is connected.");
//                  Selector selector = Selector.open();
//                  channel.register(selector, SelectionKey.OP_WRITE);
//                  while(selector.isOpen()){
//                      selector.select();
//                      Iterator<SelectionKey> it = selector.selectedKeys().iterator();
//                      while(it.hasNext()){
//                          SelectionKey key = it.next();
//                          it.remove();
//                          if(!key.isValid()){
//                              continue;
//                          }
//                          if(key.isWritable()){
//                              write();
//                          }
//                      }
//                  }
                    while(channel.isOpen()){
                        write();
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
                inner.clear();
            }
        }

        private void write() throws Exception{
            Packet packet = inner.take();
            synchronized (lock) {
                ByteBuffer body = packet.getBuffer();//
                ByteBuffer head = ByteBuffer.allocate(4);
                head.putInt(body.limit());
                head.flip();
                while(head.hasRemaining()){
                    channel.write(head);
                }
                Checksum checksum = new Adler32();
                while(body.hasRemaining()){
                    checksum.update(body.get());
                }
                body.rewind();
                while(body.hasRemaining()){
                    channel.write(body);
                }
                long cks = checksum.getValue();
                ByteBuffer tail = ByteBuffer.allocate(8);
                tail.putLong(cks);
                tail.flip();
                while(tail.hasRemaining()){
                    channel.write(tail);
                }
            }

        }
}
}
