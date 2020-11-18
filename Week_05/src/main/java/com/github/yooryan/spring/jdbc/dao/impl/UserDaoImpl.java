package com.github.yooryan.spring.jdbc.dao.impl;

import com.github.yooryan.spring.jdbc.dao.UserDao;
import com.github.yooryan.spring.jdbc.entity.User;
import com.github.yooryan.spring.jdbc.util.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author linyunrui
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void save(User user) {
        String sql = "insert into user (id,age,name) value ("+user.getId()+","+user.getAge()+",'"+user.getName()+"')";
        Statement statement = null;
        try {
            statement = ConnectionUtil.getStatement();
            statement.execute(sql);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

    @Override
    public void update(User user) {
        String sql = "update user set name = '111' where id =1";
        Statement statement = null;
        try {
            statement = ConnectionUtil.getStatement();
            statement.execute(sql);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    @Override
    public User selectOneById(Long id) {
        String sql = "select  * from user  where id =1";
        Statement statement = null;
        try {
            statement = ConnectionUtil.getStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                User user = new User();
                user.setId( resultSet.getLong("id"));
                user.setName( resultSet.getString("name"));
                user.setAge( resultSet.getInt("age"));
                return user;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from user  where id =1";
        Statement statement = null;
        try {
            statement = ConnectionUtil.getStatement();
            statement.execute(sql);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
}
