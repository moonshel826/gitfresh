package com.gitfresh.gfresh;


import com.gitfresh.thrift.gfresh.GFresh;
import com.gitfresh.thrift.gfresh.SearchQueryResponse;


import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by zma on 5/2/17.
 */
public class Client {
    private static final int PORT = 7911;

    public static void main(String[] args) {
        try {
            TTransport transport = new TSocket("localhost", PORT);
            GFresh.Client client = new GFresh.Client(new TBinaryProtocol(transport));
            transport.open();
            SearchQueryResponse response = client.searchQuery("moonshel");
            transport.close();
            System.out.println("From Client: ");
            for (com.gitfresh.thrift.gfresh.GFreshUser user : response.getUsers()) {
                System.out.println("User ID: " + user.getId() + " Name: " + user.getName() + " Followers: " + user.getFollowersCount() + " Location: " + user.getLocation());
            }

        } catch (TException e) {
            e.printStackTrace();
        }
    }
}

