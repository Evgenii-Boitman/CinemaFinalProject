package repository;

import controller.UserMenu;
import entity.Film;
import entity.Role;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmRepositoryImpl implements FilmRepository{
    private FilmRepository filmRepository;
    private FilmRepositoryImpl filmRepositoryImpl;
    private Role role;

    public FilmRepositoryImpl(FilmRepository filmRepository, FilmRepositoryImpl filmRepositoryImpl, Role role) {
        this.filmRepository = filmRepository;
        this.filmRepositoryImpl = filmRepositoryImpl;
        this.role = role;
    }

    public FilmRepositoryImpl() {

    }

    @Override
    public void addFilm(String nameFilm, String dateTime, int listTicketFilm) throws ClassNotFoundException {
        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO film (name_film, date_time, list_ticket_film) VALUES (?,?,?)");
            stmt.setString(1, nameFilm);
            stmt.setString(2, dateTime);
            stmt.setInt(3, listTicketFilm);
            stmt.execute();
            System.out.println("Фильм под названием " + nameFilm + " , на дату " + dateTime + " и в количестве " + listTicketFilm + " билетов, " + " успешно добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close();
        }
    }

    @Override
    public void deleteFilm(int idFilm) throws ClassNotFoundException {
        if (idFilm == 0 || idFilm < 0 || !checkFilmById(idFilm)) {
            System.out.println("Введен не верный ID!");
            System.out.println("------------------------");
        } else {
            try (Connection conn = ConnectionManager.open()) {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM film WHERE id = ?");
                ResultSet resultSet = stmt.executeQuery();
                String nameFilm = null;
                int id = 0;
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    nameFilm = resultSet.getString("name_film");
                }
                if (id != idFilm) {
                    System.out.println("Фильм с указанным ID не найден в базе данных!");
                } else {
                    try (Connection conn1 = ConnectionManager.open()) {
                        PreparedStatement stmt1 = conn1.prepareStatement("DELETE from film WHERE id = ?");
                        System.out.println("Удален Фильм под ID: " + idFilm + " ," + nameFilm);
                        stmt1.execute();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectionManager.close();
            }
        }

    }

    @Override
    public List<Film> readAllFilm(){
        List<Film> films = new ArrayList<>();
        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM film");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameFilm = resultSet.getString("name_film");
                String dateTime = resultSet.getString("date_time");
                int listTicketFilm = resultSet.getInt("list_ticket_film");
                System.out.println("id: " + id);
                System.out.println("Название фильма: " + nameFilm);
                System.out.println("Дата выхода: " + dateTime);
                System.out.println("Количество билетов: " + listTicketFilm);
                System.out.println("-----------------\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close();
        }
        return films;
    }

    @Override
    public void updateFilm(int id, String newNameFilm, String newDateTime, int newListTicketFilm) throws ClassNotFoundException {
        if (id <= 0  || !checkFilmById(id)) {
            System.out.println("Не верный ID фильма!");
            System.out.println("Испытайте удачу еще раз!");
            System.out.println("------------------------");
            UserMenu.userMenu();
        } else {
            try (Connection conn = ConnectionManager.open()) {
                PreparedStatement stmt = conn.prepareStatement("UPDATE film SET name_film = ?, date_time = ?, list_ticket_film = ? WHERE id = ?");
                stmt.setString(1, newNameFilm);
                stmt.setString(2, newDateTime);
                stmt.setInt(3, newListTicketFilm);
                stmt.setInt(4, id);
                stmt.executeUpdate();
                System.out.println("Обновлен Фильм под ID: " + id);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionManager.close();
            }
        }
    }

    @Override
    public List<Film> findFilm(int id) {
        return null;
    }

    public boolean checkFilmById(int idFilm) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM film WHERE id = ?");
            stmt.setInt(1, idFilm);
            return stmt.executeQuery().first();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
