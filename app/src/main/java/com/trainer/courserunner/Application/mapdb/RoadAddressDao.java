package com.trainer.courserunner.Application.mapdb;

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
                "WHERE longitude > STARTX AND latitude > STARTY AND longitude < ENDX AND latitude< ENDY";

        String STARTX=String.valueOf(scopeMapInfo.getStartX());
        String STARTY=String.valueOf(scopeMapInfo.getStartY());
        String ENDX=String.valueOf(scopeMapInfo.getEndX());
        String ENDY=String.valueOf(scopeMapInfo.getEndY());

        sql=sql.replace("STARTX",STARTX);
        sql=sql.replace("STARTY",STARTY);
        sql=sql.replace("ENDX",ENDX);
        sql=sql.replace("ENDY",ENDY);

        List<RoadAddressDto> addressDtoList = new ArrayList<>();

        RoadAddressConnector.startConnection();
        Connection connection=RoadAddressConnector.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                double longitude=resultSet.getDouble("longitude");
                double latitude = resultSet.getDouble("latitude");
                addressDtoList.add(new RoadAddressDto(longitude,latitude));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            RoadAddressConnector.closeConnection();
        }
        return addressDtoList;
    }
}
