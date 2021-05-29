package com.trainer.courserunner.Application.geo;

import android.database.Cursor;

import com.trainer.courserunner.Application.AppFunctionLoader;
import com.trainer.courserunner.component.maker.scopetype.ScopeMapInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoadAddressDao {
    public static List<RoadAddressDto> getScopeAddress(ScopeMapInfo scopeMapInfo) {
        String sql = "SELECT longitude, latitude FROM addresstable " +
                "WHERE longitude > ? AND latitude > ? AND longitude < ? AND latitude< ?";
        String[] whereArgs = new String[]{
                String.valueOf(scopeMapInfo.getStartX()),
                String.valueOf(scopeMapInfo.getStartY()),
                String.valueOf(scopeMapInfo.getEndX()),
                String.valueOf(scopeMapInfo.getEndY())
        };

        Connection connection = RoadAddressConnector.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql,whereArgs);
            ResultSet resultSet = statement.getResultSet();
            ArrayList<RoadAddressDto> addresseses = new ArrayList<>();
            while (resultSet.next()) {
                double longitude = resultSet.getDouble("longitude");
                double latitude=resultSet.getDouble("latitude");
                addresseses.add(new RoadAddressDto(longitude,latitude));
            }
            return addresseses;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
