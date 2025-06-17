package mft.tools;

import lombok.Data;
import mft.model.entities.Book;
import mft.model.entities.Loans;
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

    public static Book bookMapper(ResultSet resultSet) throws SQLException {
        return Book
                .builder()
                .id(resultSet.getInt("ID"))
                .title(resultSet.getString("TITLE"))
                .author(resultSet.getString("AUTHOR"))
                .isbn(resultSet.getString("ISBN"))
                .build();
    }
//    public static Loans loansMapper(ResultSet resultSet) throws SQLException {
//
//    }
}
