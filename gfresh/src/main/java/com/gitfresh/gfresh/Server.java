package com.gitfresh.gfresh;


import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

import com.gitfresh.thrift.gfresh.GFresh;


/**
 * Created by zma on 5/2/17.
 */
public class Server implements Runnable {
    private static final int PORT = 7911;

    public void run() {
        try {
            TServerSocket serverTransport = new TServerSocket(PORT);
            GFresh.Processor processor = new GFresh.Processor(new SearchQueryImpl());
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting server on port " + PORT);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new Server()).run();
    }
}
