package org.project.services;

import org.project.dao.UserDao;
import org.project.entity.User;
import org.project.exceptions.DaoException;

public class LoginService {
    private static final UserDao dao = new UserDao();

    public static boolean doesMatch(String enteredLogin, String enteredPassword) throws DaoException {
        User user = dao.findByLogin(enteredLogin).orElse(new User());//
        String userLogin = user.getLogin();
        String userPassword = user.getPassword();

        if (userLogin == null) return false;
        return userPassword.equals(enteredPassword);
    }

    public static boolean doesUserExist(String login, String email) throws DaoException{
        User user = dao.findByLogin(login).orElse(new User());//
        if (user.getLogin() == null) {
            return false;
        }
        return (user.getLogin().equals(login) || user.getEmail().equals(email));
    }
}