package mft.model.repository;

import mft.model.entities.Book;
import mft.tools.ConnectionProvider;
import mft.tools.EntityMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;


    public BookRepository() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    public void save(Book book) throws SQLException {
        preparedStatement = connection.prepareStatement("select book_seq.nextval from dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        book.setId(resultSet.getInt("nextval"));
        preparedStatement = connection.prepareStatement("insert into book values(?,?,?,?,?)");
        preparedStatement.setInt(1, book.getId());
        preparedStatement.setString(2, book.getTitle());
        preparedStatement.setString(3, book.getAuthor());
        preparedStatement.setString(4, book.getIsbn());
        preparedStatement.execute();
    }

    public void edit(Book book) throws SQLException {
        preparedStatement = connection.prepareStatement("update book set title=?, author=?, isbn=? where id=?");
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setString(3, book.getIsbn());
        preparedStatement.setInt(4, book.getId());
        preparedStatement.execute();
    }

    public void delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("delete from book where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Book> findAll() throws SQLException {
        List<Book> booksList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from book");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            booksList.add(EntityMapper.bookMapper((resultSet)));
        }
        return booksList;
    }

    public Book findById(int id) throws SQLException {

        Book book = null;
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from book where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            book = EntityMapper.bookMapper((resultSet));
        }
        return book;
    }

    public List<Book> findByTitleAndAuthor(String title, String author) throws SQLException {
        List<Book> booksList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from book where title like ? and author like ?");
        preparedStatement.setString(1, "%" + title + "%");
        preparedStatement.setString(2, "%" + author + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            booksList.add(EntityMapper.bookMapper((resultSet)));
        }
        return booksList;
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }
}

