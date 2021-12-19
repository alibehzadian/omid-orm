package info.behzadian.repository.base;

import info.behzadian.repository.annotation.Column;
import info.behzadian.repository.annotation.Table;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleRepository<T, ID> {

    private final Class<T> tClass;
    Field[] fields;
    Method[] methods;
    Connection connection;

    public SimpleRepository(Class<T> tClass) {
        this.tClass = tClass;
        fields = tClass.getDeclaredFields();
        methods = tClass.getDeclaredMethods();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/omid", "root", "pass");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<T> findAll() {
        //
        // To avoid null pointer exception
        var result = new ArrayList<T>();

        //
        // Determine whether userClass uses the Table annotation
        if (tClass.isAnnotationPresent(Table.class)) {
            Table table = tClass.getAnnotation(Table.class);

            String tableName = table.tableName();

            try {
                var statement = connection.createStatement();
                var resultSet = statement.executeQuery("SELECT * FROM " + tableName);

                while (resultSet.next()) {
                    result.add(fillObject(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public T findById(ID id) {
        if (tClass.isAnnotationPresent(Table.class)) {
            Table table = tClass.getAnnotation(Table.class);

            String tableName = table.tableName();

            try {
                var statement = connection.createStatement();
                var resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE ID=" + id);

                if (resultSet.next()) {
                    return fillObject(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void deleteById(ID id) {
        if (tClass.isAnnotationPresent(Table.class)) {
            Table table = tClass.getAnnotation(Table.class);

            String tableName = table.tableName();

            try {
                var statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM " + tableName + " WHERE ID=" + id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private T fillObject(ResultSet resultSet) {
        try {
            T object = tClass.getConstructor().newInstance();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    String columnName = column.columnName();

                    String fieldType = field.getType().getSimpleName();

                    String setMethodName = "set" + field.getName().substring(0, 1).toUpperCase()
                            + field.getName().substring(1);

                    for (Method method : methods) {
                        if (method.getName().equals(setMethodName)) {
                            switch (fieldType) {
                                case "String":
                                    method.invoke(object, resultSet.getString(columnName));
                                    break;
                                case "Long":
                                case "long":
                                    method.invoke(object, resultSet.getLong(columnName));
                                    break;
                                case "Boolean":
                                case "boolean":
                                    method.invoke(object, resultSet.getBoolean(columnName));
                                    break;
                            }

                            break;
                        }
                    }
                }
            }

            return object;
        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException
                | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
