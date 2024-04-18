package az.transactional.db;


import az.transactional.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DBAccess {
    private static final Logger LOG = LoggerFactory.getLogger(DBAccess.class);

    MyConnection myConnection;
    private final WebApplicationContext context;


    public void initCon(){
        myConnection = context.getBean(MyConnection.class);
        myConnection.openConnection();
    }

    public void closeCon(){
        myConnection.closeConnection();
    }
    @SneakyThrows
    public void beginTransaction(){
        myConnection.getConnection().setAutoCommit(false);
    }
    @SneakyThrows
    public void commitTransaction(){
     myConnection.getConnection().commit();
    }
    @SneakyThrows
    public void rollBackTransaction(){
        myConnection.getConnection().rollback();
    }
    public synchronized List<Student> getResultList(String sql){
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement statement = myConnection.getConnection().prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return students;
    }

    public void executeUpdate(String sql, List<String> args){
        try {
            PreparedStatement statement = myConnection.getConnection().prepareStatement(sql);
            for (int i=0;i<args.size();i++){
                statement.setString(i +1, args.get(i));
            }
            statement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException("SQLException:" + ex.getMessage());
        }
    }
}
