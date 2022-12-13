package repository;

import controller.UserMenu;
import entity.Role;
import entity.User;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private UserRepository userRepository;
    private UserRepositoryImpl userRepositoryImpl;
    private Role role;


    public UserRepositoryImpl(UserRepository userRepository, UserRepositoryImpl userRepositoryImpl, Role role) {
        this.userRepository = userRepository;
        this.userRepositoryImpl = userRepositoryImpl;
        this.role = role;
    }

    public UserRepositoryImpl() {

    }


    @Override
    public void deleteUser(int idUser) throws ClassNotFoundException {
        if (idUser == 1 || idUser == 2 || idUser < 0) {
            System.out.println("Введен не верный ID!");
            System.out.println("Испытайте удачу еще раз!");
            System.out.println("------------------------");
            UserMenu.userMenu();
        } else {
            if (!checkUserById(idUser)) {
                System.out.println("Пользователь с ID: " + idUser + " не найден в базе данных!");
                System.out.println("Испытайте удачу еще раз!");
                System.out.println("------------------------");
                UserMenu.userMenu();
            } else {
                try (Connection conn = ConnectionManager.open()) {
                    PreparedStatement stmt = conn.prepareStatement("DELETE FROM user WHERE id = ? ");
                    stmt.setInt(1, idUser);
                    stmt.execute();
                    System.out.println("Удален Пользователь под ID: " + idUser);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    ConnectionManager.close();
                }
            }
        }
    }


    @Override
    public List<User> readAllUser() {
        List<User> users = new ArrayList<>();
        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String passwordUser = resultSet.getString("password");
                String roleUser = resultSet.getString("role_user");
                System.out.println("id: " + id);
                System.out.println("Логин: " + login);
                System.out.println("Пароль: " + passwordUser);
                System.out.println("Роль: " + roleUser);
                System.out.println("-----------------\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close();
        }
        return users;
    }

    @Override
    public List<User> findUser(int idUser) throws ClassNotFoundException {
        List<User> users = new ArrayList<>();
        if (idUser < 0 || !checkUserById(idUser)) {
            System.out.println("Введен не верный ID пользователя!");
            System.out.println("------------------------");
            UserMenu.userMenu();
        } else {
            try (Connection conn = ConnectionManager.open()) {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
                stmt.setInt(1, idUser);
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String login = resultSet.getString("login");
                    String passwordUser = resultSet.getString("password");
                    String roleUser = resultSet.getString("role_user");
                    System.out.println("id: " + id);
                    System.out.println("login: " + login);
                    System.out.println("password: " + passwordUser);
                    System.out.println("role_user: " + roleUser);
                    System.out.println("-----------------\n");
                    System.out.println("Пользователь с указанным ID найден в базе данных!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectionManager.close();
            }
        }
        return users;
    }

    @Override
    public void updateUser(int id, String newLogin, String newPassword, String newRole) throws ClassNotFoundException {
        if (id == 1 || id == 2 || id <= 0 || !validationRole(newRole) || newLogin.equals("admin") || newRole.equals("ADMIN") || !validationLoginPassword(newLogin, newPassword) || !validationMinMax(newLogin, newPassword)) {
            System.out.println("Не верное имя пользователя, пароль или роль!");
            System.out.println("Испытайте удачу еще раз!");
            System.out.println("------------------------");
            UserMenu.userMenu();
        } else {
            try (Connection conn = ConnectionManager.open()) {
                PreparedStatement stmt = conn.prepareStatement("UPDATE user SET login = ?, password = ?, role_user = ? WHERE id = ?");
                stmt.setString(1, newLogin);
                stmt.setString(2, newPassword);
                stmt.setString(3, newRole);
                stmt.setInt(4, id);
                stmt.executeUpdate();
                System.out.println("Обновлен Пользователь под ID: " + id);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionManager.close();
            }
        }
    }

    @Override
    public void createUser(String userLogin, String userPassword) throws ClassNotFoundException {
        if (!validationLoginPassword(userLogin, userPassword) || !validationMinMax(userLogin, userPassword)) {
            System.out.println("Не верное имя пользователя или пароль!");
            System.out.println("Минимальное количество символов для Login и Password - 3 символа!");
            System.out.println("Максимальное количество символов для Login и Password - 8 символов!");
            System.out.println("Испытайте удачу еще раз!");
            System.out.println("------------------------");
            UserMenu.userMenu();
        } else {
            if (checkUserByLogin(userLogin)) {
                System.out.println("Пользователь с Login: " + userLogin + " уже существует!");
                System.out.println("Испытайте удачу еще раз!");
                System.out.println("------------------------");
                UserMenu.userMenu();
            } else {
                try (Connection conn = ConnectionManager.open()) {
                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (login, password, role_user) VALUES (?,?,?)");
                    stmt.setString(1, userLogin);
                    stmt.setString(2, userPassword);
                    stmt.setString(3, String.valueOf(Role.USER));
                    stmt.execute();
                    System.out.println("Пользователь под Login: " + userLogin + " и c Password: " + userPassword + " успешно добавлен!");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    ConnectionManager.close();
                }
            }
        }
    }

    @Override
    public void openUser(String userLogin, String userPassword) throws ClassNotFoundException {
        if (!validationLoginPassword(userLogin, userPassword) || !validationMinMax(userLogin, userPassword)) {
            System.out.println("Не верное имя пользователя или пароль!");
            System.out.println("Минимальное количество символов для Login и Password - 3 символа!");
            System.out.println("Максимальное количество символов для Login и Password - 8 символов!");
            System.out.println("Испытайте удачу еще раз!");
            System.out.println("------------------------");
            UserMenu.userMenu();
        } else {
            if (!checkOpenUserByLoginPassword(userLogin, userPassword)) {
                System.out.println("Пользователь с Login: " + userLogin + "c Password: " + userPassword + " не существует!");
                System.out.println("Испытайте удачу еще раз!");
                System.out.println("------------------------");
                UserMenu.userMenu();
            }
        }
    }

    @Override
    public void openManager(String userLogin, String userPassword) throws ClassNotFoundException {
        if (!validationLoginPassword(userLogin, userPassword) || !validationMinMax(userLogin, userPassword)) {
            System.out.println("Не верное имя пользователя или пароль!");
            System.out.println("Минимальное количество символов для Login и Password - 3 символа!");
            System.out.println("Максимальное количество символов для Login и Password - 8 символов!");
            System.out.println("Испытайте удачу еще раз!");
            System.out.println("------------------------");
            UserMenu.userMenu();
        } else {
            if (!checkOpenManagerByLoginPassword(userLogin, userPassword)) {
                System.out.println("Пользователь с Login: " + userLogin + "c Password: " + userPassword + " не существует!");
                System.out.println("Испытайте удачу еще раз!");
                System.out.println("------------------------");
                UserMenu.userMenu();
            }
        }
    }

    @Override
    public void openAdmin(String userLogin, String userPassword) throws ClassNotFoundException {
        if (!validationLoginPassword(userLogin, userPassword) || !validationMinMax(userLogin, userPassword)) {
            System.out.println("Не верное имя пользователя или пароль!");
            System.out.println("Минимальное количество символов для Login и Password - 3 символа!");
            System.out.println("Максимальное количество символов для Login и Password - 8 символов!");
            System.out.println("Испытайте удачу еще раз!");
            System.out.println("------------------------");
            UserMenu.userMenu();
        } else {
            if (!checkOpenAdminByLoginPassword(userLogin, userPassword)) {
                System.out.println("Пользователь с Login: " + userLogin + "c Password: " + userPassword + " не существует!");
                System.out.println("Испытайте удачу еще раз!");
                System.out.println("------------------------");
                UserMenu.userMenu();
            }
        }
    }

    public boolean checkUserByLogin(String userLogin) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE login = ?");
            stmt.setString(1, userLogin);
            return stmt.executeQuery().first();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkUserById(int idUser) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            stmt.setInt(1, idUser);
            return stmt.executeQuery().first();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validationLoginPassword(String userLogin, String userPassword) {
        if (userLogin.length() == 0 || userPassword.length() == 0) {
            System.out.println("------------------------");
            return false;
        } else
            return true;
    }

    public static boolean validationRole(String role) {
        if (role.length() == 0 || role.length() == 0) {
            System.out.println("------------------------");
            return false;
        } else
            return true;
    }

    public static boolean validationMinMax(String userLogin, String userPassword) {
        int minInt = 3;
        int maxInt = 8;
        if (userLogin.length() < minInt || userPassword.length() < minInt || userLogin.length() > maxInt || userPassword.length() > maxInt) {
            System.out.println("------------------------");
            return false;
        } else
            return true;
    }

    public static boolean checkOpenUserByLoginPassword(String userLogin, String userPassword) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE login = ? AND password = ?");
            stmt.setString(1, userLogin);
            stmt.setString(2, userPassword);
            return stmt.executeQuery().first();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkOpenManagerByLoginPassword(String userLogin, String userPassword) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE login = ? AND password = ?");
            stmt.setString(1, userLogin);
            stmt.setString(2, userPassword);
            return stmt.executeQuery().first();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkOpenAdminByLoginPassword(String userLogin, String userPassword) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE login = ? AND password = ?");
            stmt.setString(1, userLogin);
            stmt.setString(2, userPassword);
            return stmt.executeQuery().first();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
