package az.transactional.db;

import az.transactional.entity.Student;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Setter
@Getter
@Slf4j
@Scope("request")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyConnection {
    Connection connection;
    final String url = "jdbc:mysql://localhost:3306/student";
    final String username = "root";
    final String password = "root";

    @SneakyThrows
    public void openConnection(){
        connection=null;
        connection = DriverManager.getConnection(url,username,password);
    }

    @SneakyThrows
    public void closeConnection(){
        connection.close();
    }


}
