/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.app;

import com.vng.zing.education.servers.HServers;
import com.vng.zing.education.servers.TServers;

/**
 *
 * @author baonh2
 */
public class MainApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ///
        ///http servers
        ///
        HServers hServers = new HServers();
        if (!hServers.setupAndStart()) {
            System.err.println("Could not start http servers! Exit now.");
            System.exit(1);
        }

        ///
        ///thrift servers
        ///
//        TServers tServers = new TServers();
//        if (!tServers.setupAndStart()) {
//            System.err.println("Could not start thrift servers! Exit now.");
//            System.exit(1);
//        }
    }
}
