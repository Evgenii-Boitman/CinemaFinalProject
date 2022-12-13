package service;

import entity.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import java.util.List;

public interface UserService {

    void deleteUser(int idUser) throws ClassNotFoundException;

    List<User> readAllUser() throws ClassNotFoundException;

    List<User> findUser(int idUser) throws ClassNotFoundException;

    void updateUser(int id, String newLogin, String newPassword, String newRole) throws ClassNotFoundException;

    void createUser(String userLogin, String userPassword) throws ClassNotFoundException;

    void openUser(String userLogin, String userPassword) throws ClassNotFoundException;

    void openManager (String userLogin, String userPassword) throws ClassNotFoundException;

    void openAdmin (String userLogin, String userPassword) throws ClassNotFoundException;
}

