package com.vng.zing.education.common;

import com.vng.zing.configer.ZConfig;
import com.vng.zing.logger.ZLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

/**
 *
 * @author anhlh
 */
public class MysqlClient {

    private static Logger _logger = ZLogger.getLogger(MysqlClient.class);
    private String _host;
    private int _port;
    private String _dbname;
    private String _user;
    private String _password;
    private BlockingQueue<Connection> pool;
    private String url;
    private static Map<String, MysqlClient> _MysqlClient = new HashMap<>();

    public MysqlClient(String name) {
        _host = ZConfig.Instance.getString(MysqlClient.class, name, "host", "localhost");
        _port = ZConfig.Instance.getInt(MysqlClient.class, name, "port", 3306);
        _dbname = ZConfig.Instance.getString(MysqlClient.class, name, "dbname", "zpublish");
        _user = ZConfig.Instance.getString(MysqlClient.class, name, "uname", "root");
        _password = ZConfig.Instance.getString(MysqlClient.class, name, "passwd", "root");
        this.init(ZConfig.Instance.getInt(MysqlClient.class, name, "poolsize", 10));
    }

    public static MysqlClient getMysqlClient(String _name) {
        if (_MysqlClient.get(_name) == null) {
            synchronized (MysqlClient.class) {
                if (_MysqlClient.get(_name) == null) {
                    _MysqlClient.put(_name, new MysqlClient(_name));
                }
            }
        }
        return _MysqlClient.get(_name);
    }

    private boolean init(int poolsize) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://" + _host + ":" + _port + "/" + _dbname + "?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&interactiveClient=true&" + "&user=" + _user
                    + "&password=" + _password;

            BlockingQueue<Connection> cnnPool = new ArrayBlockingQueue<Connection>(poolsize);
            while (cnnPool.size() < poolsize) {
                cnnPool.add(DriverManager.getConnection(url));
            }
            pool = cnnPool;
            _logger.info("MysqlClient init pool success");
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
            return false;
        }
        return true;
    }

    public Connection getDbConnection() {
        Connection conn = null;
        int retry = 0;
        do {
            try {
                conn = pool.poll(10000, TimeUnit.MILLISECONDS);
                if (conn == null || !conn.isValid(0)) {
                    conn = DriverManager.getConnection(url);
                }
            } catch (Exception ex) {
                _logger.warn(ex);
            }
            ++retry;
        } while (conn == null && retry < 3);
        return conn;
    }

    public void releaseDbConnection(java.sql.Connection conn) {
        if (conn != null) {
            try {
                pool.add(conn);
            } catch (Exception ex) {
                _logger.error(ex.getMessage(), ex);
            }
        }
    }
}
