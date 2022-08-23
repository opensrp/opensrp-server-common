package org.opensrp.common.util;

import javax.net.ssl.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ServerNameIndicationSSLContextSpi extends SSLContextSpi {

    private final String hostname;

    private final int port;

    public ServerNameIndicationSSLContextSpi(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    protected void engineInit(KeyManager[] keyManagers, TrustManager[] trustManagers, SecureRandom secureRandom) {

    }

    @Override
    protected SSLSocketFactory engineGetSocketFactory() {
        try {
            return SSLContext.getDefault().getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected SSLServerSocketFactory engineGetServerSocketFactory() {
        try {
            return SSLContext.getDefault().getServerSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected SSLEngine engineCreateSSLEngine() {
        try {
            return SSLContext.getDefault().createSSLEngine(hostname, port);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected SSLEngine engineCreateSSLEngine(String host, int port) {
        try {
            return SSLContext.getDefault().createSSLEngine(host, port);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected SSLSessionContext engineGetServerSessionContext() {
        return null;
    }

    @Override
    protected SSLSessionContext engineGetClientSessionContext() {
        return null;
    }
}
