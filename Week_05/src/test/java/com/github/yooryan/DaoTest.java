package com.github.yooryan;

import com.github.yooryan.spring.jdbc.dao.UserDao;
import com.github.yooryan.spring.jdbc.dao.impl.UserDaoImpl;
import com.github.yooryan.spring.jdbc.entity.User;
import com.github.yooryan.spring.jdbc.handler.impl.ListHandlerImpl;
import com.github.yooryan.spring.jdbc.handler.impl.ObjectHandlerImpl;
import com.github.yooryan.spring.jdbc.util.JdbcTemplate;
import org.junit.Test;

import java.util.List;

/**
 * @author linyunrui
 */
public class DaoTest {

    private UserDao userDao = new UserDaoImpl();

    @Test
    public void testSave(){
        User user = new User();
        user.setAge(12);
        user.setId(23L);
        user.setName("agent");
        userDao.save(user);
    }


    @Test
    public void testSelect(){
        System.out.println(userDao.selectOneById(23L));
    }


    @Test
    public void testUpdate(){
        userDao.update(null);
    }


    @Test
    public void testDelete(){
    userDao.deleteById(23L);
    }

    @Test
    public void testJdbcTemplate(){
        String sql = "select * from user where id = ?";
        String sql1 = "select * from user ";
        List<User> query = JdbcTemplate.query(sql1, new ListHandlerImpl<>(User.class),null);

        String ddlInsert = "insert into user (id,name,age) values (?,?,?)";
        String ddlUpdate = "update user set name = ? where id = ?";
        String ddlDelete = "delete from user where id = ?";
        JdbcTemplate.update(ddlInsert, 25, "deep", 20);
        JdbcTemplate.update(ddlUpdate, "deep01", 25);
        JdbcTemplate.update(ddlDelete,25);
        System.out.println(query.toString());
    }

}