package com.trainer.courserunner.Application.geo;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RoadAddressConnector {
    private static String address="trainer0528.database.windows.net";
    private static String port = "1443";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "DrawRunner";
    private static String username = "appguest"; //서버접속용 ID PW
    private static String password = "xD6oaEUn4AXa";
    //
    private static Connection connection = null;
    private static String url ="jdbc:jtds:sqlserver://"+address+":"+port+"/"+database;
    public static Connection getConnection(){
        if(connection==null) {
            try {
                connection = DriverManager.getConnection(url, username, password);
                Log.v("success","asdbasb");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }
}
