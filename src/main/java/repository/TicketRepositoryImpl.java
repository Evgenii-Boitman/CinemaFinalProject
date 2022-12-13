package repository;

import controller.UserMenu;
import entity.Ticket;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository {
    public TicketRepository ticketRepository;
    public TicketRepositoryImpl ticketRepositoryImpl;

    public TicketRepositoryImpl(TicketRepository ticketRepository, TicketRepositoryImpl ticketRepositoryImpl) {
        this.ticketRepository = ticketRepository;
        this.ticketRepositoryImpl = ticketRepositoryImpl;
    }

    public TicketRepositoryImpl() {
    }

    @Override
    public void buyTicket(int seatNumber, String user, int idFilm) throws ClassNotFoundException {
        String isSold = "";
        if (!validationMinMaxSeatNumber(seatNumber) || !checkFilmId(idFilm)) {
            System.out.println("Минимальный номер места - 1!");
            System.out.println("Максимальное номер места - 10!");
            System.out.println("Введен не верный ID фильма!");
            System.out.println("Испытайте удачу еще раз!");
            System.out.println("------------------------");
            UserMenu.userMenu();
        } else {
            if (checkFreeSeatIsSold(isSold)) {
                try (Connection conn = ConnectionManager.open()) {
                    PreparedStatement stmt = conn.prepareStatement("UPDATE ticket SET user = ?, is_sold = ? WHERE seat_number = ? AND film_id = ?");
                    stmt.setString(1, user);
                    stmt.setString(2, "Продан");
                    stmt.setInt(3, seatNumber);
                    stmt.setInt(4, idFilm);
                    stmt.executeUpdate();
                    System.out.println("Вы приобрели билет под Login: " + user + " на фильм c ID: " + idFilm + " , номер места " + seatNumber + " !");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    ConnectionManager.close();
                }
            } else {
                System.out.println("Место уже занято!");
            }
        }
    }


    @Override
    public void returnTicket(String user, int seatNumber, int idFilm) throws ClassNotFoundException {
        if (!validationMinMaxSeatNumber(seatNumber) || !checkFilmId(idFilm)) {
            System.out.println("Введен не верный номер места или место еще свободно!");
            System.out.println("Минимальный номер места - 1!");
            System.out.println("Максимальное номер места - 10!");
            System.out.println("Введен не верный ID фильма!");
            System.out.println("Испытайте удачу еще раз!");
            System.out.println("------------------------");
            UserMenu.userMenu();
        } else {
            try (Connection conn = ConnectionManager.open()) {
                PreparedStatement stmt = conn.prepareStatement("UPDATE ticket SET user = ?, is_sold = ? WHERE seat_number = ?");
                stmt.setString(1, "");
                stmt.setString(2, "Свободен");
                stmt.setInt(3, seatNumber);
                stmt.executeUpdate();
                System.out.println("Вы вернули билет на фильм c ID: " + idFilm + " , номер места " + seatNumber + " !");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectionManager.close();
            }
        }
    }

    @Override
    public List<Ticket> readAllTicketFilm() throws ClassNotFoundException {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ticket");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int filmId = resultSet.getInt("film_id");
                int seatNumber = resultSet.getInt("seat_number");
                float price = resultSet.getFloat("price");
                String isSold = resultSet.getString("is_sold");
                System.out.println("id: " + id);
                System.out.println("ID фильма: " + filmId);
                System.out.println("Номер места: " + seatNumber);
                System.out.println("Цена билета: " + price);
                System.out.println("Статус места: " + isSold);
                System.out.println("-----------------\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close();
        }
        return tickets;
    }

    @Override
    public List<Ticket> readAllTicketFilmForAdminManager() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ticket");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String user = resultSet.getString("user");
                int filmId = resultSet.getInt("film_id");
                int seatNumber = resultSet.getInt("seat_number");
                float price = resultSet.getFloat("price");
                String isSold = resultSet.getString("is_sold");
                System.out.println("id: " + id);
                System.out.println("Логин пользователя: " + user);
                System.out.println("ID фильма: " + filmId);
                System.out.println("Номер места: " + seatNumber);
                System.out.println("Цена билета: " + price);
                System.out.println("Статус места: " + isSold);
                System.out.println("-----------------\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close();
        }
        return tickets;
    }

    @Override
    public List<Ticket> readAllTicketFilmUser(String user) throws ClassNotFoundException {
        List<Ticket> tickets = new ArrayList<>();
        if (!checkUserByLogin(user) || !validationMinMax(user)) {
            System.out.println("Введен не верный Login пользователя!");
            System.out.println("------------------------");
            UserMenu.userMenu();
        } else {
            try (Connection conn = ConnectionManager.open()) {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ticket WHERE user = ?");
                stmt.setString(1, user);
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String user1 = resultSet.getString("user");
                    int filmId = resultSet.getInt("film_id");
                    int seatNumber = resultSet.getInt("seat_number");
                    float price = resultSet.getFloat("price");
                    String isSold = resultSet.getString("is_sold");
                    System.out.println("id: " + id);
                    System.out.println("Логин пользователя: " + user1);
                    System.out.println("ID фильма: " + filmId);
                    System.out.println("Номер места: " + seatNumber);
                    System.out.println("Цена билета: " + price);
                    System.out.println("Статус места: " + isSold);
                    System.out.println("-----------------\n");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectionManager.close();
            }
        }
        return tickets;
    }


    @Override
    public List<Ticket> readAllFreeTicket(int idFilm) {
        return null;
    }

    @Override
    public void addTicket(int filmId, int seatNumber, float price, String isSold) {
        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO ticket (film_id, seat_number, price, is_sold) VALUES (?,?,?,?)");
            stmt.setInt(1, filmId);
            stmt.setInt(2, seatNumber);
            stmt.setFloat(3, price);
            stmt.setString(4, isSold);
            stmt.execute();
            System.out.println("Билет успешно добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close();
        }

    }

    public static boolean checkFreeSeatIsSold(String isSold) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ticket WHERE is_sold = ?");
            stmt.setString(1, isSold);
            return stmt.executeQuery().first();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static String checkUserFree(int seatNumber) throws SQLException {
        ResultSet resultSet;
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ticket WHERE seat_number = ?");
            stmt.setInt(1, seatNumber);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet.getString("is_sold");
    }

        public static boolean checkSeat () {
            try (Connection connection = ConnectionManager.open()) {
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ticket WHERE user = ?");
                stmt.setString(1, "");
                return stmt.executeQuery().first();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static boolean validationMinMaxSeatNumber ( int seatNumber){
            int minInt = 1;
            int maxInt = 10;
            if (seatNumber < minInt || seatNumber > maxInt) {
                System.out.println("------------------------");
                return false;
            } else
                return true;
        }

        public static boolean checkFilmId ( int filmId){
            try (Connection connection = ConnectionManager.open()) {
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ticket WHERE film_id = ?");
                stmt.setInt(1, filmId);
                return stmt.executeQuery().first();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean checkUserByLogin (String userLogin){
            try (Connection connection = ConnectionManager.open()) {
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE login = ?");
                stmt.setString(1, userLogin);
                return stmt.executeQuery().first();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static boolean validationMinMax (String userLogin){
            int minInt = 3;
            int maxInt = 8;
            if (userLogin.length() < minInt || userLogin.length() > maxInt) {
                System.out.println("------------------------");
                return false;
            } else
                return true;
        }
    }
