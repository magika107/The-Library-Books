package mft.tools;

import lombok.Data;
import mft.model.entities.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data

public class EntityMapper {
    public static Person personMapper(ResultSet resultSet) throws SQLException {
        return Person

                .builder()
                .id(resultSet.getInt("ID"))
                .name(resultSet.getString("NAME"))
                .family(resultSet.getString("FAMILY"))
                .nationalId(resultSet.getString("NATIONAL_ID"))
                .birthDate(resultSet.getDate("BIRT_DATE").toLocalDate())
                .phonenumber(resultSet.getString("PHONE_NUMBER"))
                .email(resultSet.getString("EMAIL"))
                .city(resultSet.getString("CITY"))
                .University(resultSet.getString("URNIVECITY"))
                .address(resultSet.getString("ADDSRESS"))
                .build();

    }
}
