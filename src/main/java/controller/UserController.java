package controller;

import entity.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.*;

import java.sql.SQLException;
import java.util.List;

public class UserController {

    private static UserRepository userRepository;
    private static UserRepositoryImpl userRepositoryImpl;
    static UserService userService = new UserServiceImpl();



    public UserController() {
        this.userService = userService;

    }

    public static void createUser(String userLogin, String userPassword) throws ClassNotFoundException {
        userService.createUser(userLogin, userPassword);
    }

    public static void updateUser(int id, String newLogin, String newPassword, String newRole) throws ClassNotFoundException{
        userService.updateUser(id, newLogin, newPassword, newRole);
    }

    public static void findUser(int idUser) throws ClassNotFoundException {
        userService.findUser(idUser);
    }

    public static void readAllUser() throws ClassNotFoundException {
        userService.readAllUser();
    }

    public static void deleteUser(int idUser) throws ClassNotFoundException {
        userService.deleteUser(idUser);
    }

    public static void openUser(String userLogin, String userPassword) throws ClassNotFoundException {
        userService.openUser(userLogin, userPassword);
    }

    public static void openManager(String userLogin, String userPassword) throws ClassNotFoundException {
        userService.openManager(userLogin, userPassword);
    }

    public static void openAdmin(String userLogin, String userPassword) throws ClassNotFoundException {
        userService.openAdmin(userLogin, userPassword);
    }

}