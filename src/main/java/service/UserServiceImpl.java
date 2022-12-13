package service;

import entity.User;

import repository.TicketRepository;
import repository.TicketRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new UserRepositoryImpl();
    private UserRepositoryImpl userRepositoryImpl;

    public UserServiceImpl(UserRepository userRepository, UserRepositoryImpl userRepositoryImpl) {
        this.userRepository = userRepository;
        this.userRepositoryImpl = userRepositoryImpl;
    }


    public UserServiceImpl() {
    }


    @Override
    public void createUser(String userLogin, String userPassword) throws ClassNotFoundException {
        userRepository.createUser(userLogin, userPassword);
    }

    @Override
    public void openUser(String userLogin, String userPassword) throws ClassNotFoundException {
        userRepository.openUser(userLogin, userPassword);
    }

    @Override
    public void openManager(String userLogin, String userPassword) throws ClassNotFoundException {
        userRepository.openManager(userLogin, userPassword);
    }

    @Override
    public void openAdmin(String userLogin, String userPassword) throws ClassNotFoundException {
        userRepository.openAdmin(userLogin, userPassword);
    }

    @Override
    public void deleteUser(int idUser) throws ClassNotFoundException {
        userRepository.deleteUser(idUser);
    }

    @Override
    public List<User> readAllUser() throws ClassNotFoundException {
        userRepository.readAllUser();
        return null;
    }

    @Override
    public List<User> findUser(int idUser) throws ClassNotFoundException {
        userRepository.findUser(idUser);
        return null;
    }

    @Override
    public void updateUser(int id, String newLogin, String newPassword, String newRole) throws ClassNotFoundException {
        userRepository.updateUser(id, newLogin, newPassword, newRole);
    }


}