package org.util.demo.nio;

import java.nio.channels.SocketChannel;

/**
 * Created by lk on 2016/12/26.
 */
public interface Handler {

    public void handle(SocketChannel channel);
}
