/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author somor
 */
public class Pool {

    private static Pool dataSource;
    private static BasicDataSource ds = null;
    private static final String URL= ResourceBundle.getBundle("model.config").getString("url");
    private static final String USER= ResourceBundle.getBundle("model.config").getString("user");
    private static final String PASS= ResourceBundle.getBundle("model.config").getString("pass");
    private static final String NAME= ResourceBundle.getBundle("model.config").getString("driver");

    private Pool() {
        ds = new BasicDataSource();
        ds.setDriverClassName(NAME);
        ds.setUsername(USER);
        ds.setPassword(PASS);
        ds.setUrl(URL);
        ds.setMinIdle(1);
        ds.setMaxIdle(10);

    }

    public static Pool getInstance() {
        if (dataSource == null) {
            dataSource = new Pool();
            return dataSource;
        } else {
            return dataSource;
        }

    }

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();

    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
