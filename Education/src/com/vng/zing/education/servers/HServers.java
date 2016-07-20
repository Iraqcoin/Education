/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.servers;

import com.vng.zing.education.handlers.AdminServiceHandler;
import com.vng.zing.education.handlers.FileUploadServlet;
import com.vng.zing.jettyserver.WebServers;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.MultipartConfigElement;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.IncludableGzipFilter;

/**
 *
 * @author namnq
 */
public class HServers {

    private static String HOSTNAME = "localhost";

    public boolean setupAndStart() {
        WebServers servers = new WebServers("education");
        ServletContextHandler h = new ServletContextHandler();

        FilterHolder holder = new FilterHolder(IncludableGzipFilter.class);
        holder.setInitParameter("deflateCompressionLevel", "9");
        holder.setInitParameter("minGzipSize", "0");
        holder.setInitParameter("mimeTypes", "application/json");

        h.addFilter(holder, "/*", EnumSet.of(DispatcherType.REQUEST));

        h.addServlet(AdminServiceHandler.class, "/api");

        ContextHandler resource_handler = new ContextHandler("/static");
        resource_handler.setResourceBase("./public/");
        resource_handler.setHandler(new ResourceHandler());

        h.setMaxFormContentSize(50000000);

        ServletHolder fileUploadServletHolder = new ServletHolder(new FileUploadServlet());
        fileUploadServletHolder.getRegistration().setMultipartConfig(new MultipartConfigElement("data/tmp"));
        h.addServlet(fileUploadServletHolder, "/fileUpload");

        /*
         * Set session cookie
         */
        SessionManager sm = new HashSessionManager();
        ((HashSessionManager) sm).setSessionCookie("ZPID" + HOSTNAME);
        h.setSessionHandler(new SessionHandler(sm));
        h.setMaxFormContentSize(5000000);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, h});

        servers.setup(handlers);
        return servers.start();
    }
}
