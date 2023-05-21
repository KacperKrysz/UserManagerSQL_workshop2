package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        UserDao userDao = new UserDao();

//        User user1 = new User("Jan","jan@gmail.com","janspassword");
//        User user2 = new User("Kasia","kasia@gmail.com","kasiaspassword");
//        User user3 = new User("Michał","michal@gmail.com","michalspassword");
//        userDao.createUser(user1);
//        userDao.createUser(user2);
//        userDao.createUser(user3);
//        User user2edited = userDao.readUser(5);
//        user2edited.setUserName("katarzyna");
//        user2edited.setEmail("katarzyna@gmail.com");
//        userDao.updateUser(user2edited);

//        userDao.deleteUser(2);

        ArrayList<User> arrayList = userDao.findAll();
        System.out.println(arrayList.size());
//        UserDao userDao = new UserDao();
//        User user = userDao.readUser(1);
//        System.out.println(user);
//        user.setEmail("agnieszka@interia.pl");
//        user.setPassword("hasłoagnieszki123");
//        userDao.updateUser(user);
//        userDao.deleteUser(1);

    }

}
