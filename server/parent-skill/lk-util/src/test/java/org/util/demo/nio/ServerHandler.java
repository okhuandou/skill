package org.util.demo.nio;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.zip.Adler32;
import java.util.zip.Checksum;
/**
 * Created by lk on 2016/12/26.
 */
public class ServerHandler  implements  Handler
{

    private static Semaphore semaphore = new Semaphore(Runtime.getRuntime().availableProcessors() + 1);

    private static Map<SocketChannel,Thread> holder = new HashMap<SocketChannel,Thread>(32);

    @Override
    public void handle(SocketChannel channel) {
        synchronized (holder) {
            if(holder.containsKey(channel)){
                return;
            }
            Thread t = new ReadThread(channel);
            holder.put(channel, t);
            t.start();
        }
    }


    static class ReadThread extends Thread{
        SocketChannel channel;
        ReadThread(SocketChannel channel){
            this.channel = channel;
        }
        @Override
        public void run(){
            try{
                semaphore.acquire();
                boolean eof = false;
                while(channel.isOpen()){
                    //ByteBuffer byteBuffer = new ByteBuffer(1024);
                    ByteBuffer head = ByteBuffer.allocate(4);//int for data-size
                    while(true){
                        int cb = channel.read(head);
                        if(cb == -1){
                            throw new RuntimeException("EOF error,data lost!");
                        }
                        if(isFull(head)){
                            break;
                        }
                    }
                    head.flip();
                    int dataSize = head.getInt();
                    if(dataSize <= 0){
                        throw new RuntimeException("Data format error,something lost???");
                    }
                    ByteBuffer body = ByteBuffer.allocate(dataSize);
                    while(true){
                        int cb = channel.read(body);
                        if(cb == -1){
                            throw new RuntimeException("EOF error,data lost!");
                        }else if(cb == 0 && this.isFull(body)){
                            break;
                        }
                    }
                    ByteBuffer tail = ByteBuffer.allocate(8);//int for data-size
                    while(true){
                        int cb = channel.read(tail);
                        if(cb == -1){
                            eof = true;
                        }
                        if(isFull(tail)){
                            break;
                        }
                    }
                    tail.flip();
                    long sck = tail.getLong();
                    Checksum checksum = new Adler32();
                    checksum.update(body.array(), 0, dataSize);
                    long cck = checksum.getValue();
                    if(sck != cck){
                        throw new RuntimeException("Sorry,some data lost or be modified,please check!");
                    }
                    body.flip();
                    Packet packet = Packet.wrap(body);
                    System.out.println(packet.getDataAsString());
                    if(eof){
                        break;
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
                holder.remove(channel);
                semaphore.release();
            }
        }

        private boolean isFull(ByteBuffer byteBuffer){
            return byteBuffer.position() == byteBuffer.capacity() ? true : false;
        }
    }

}
