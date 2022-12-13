package repository;

import entity.Ticket;

import java.util.List;

public interface TicketRepository {

    void buyTicket(int seatNumber, String user, int idFilm) throws ClassNotFoundException;

    void returnTicket(String user, int seatNumber, int idFilm) throws ClassNotFoundException;

    List<Ticket> readAllTicketFilm() throws ClassNotFoundException;

    List<Ticket> readAllTicketFilmForAdminManager() throws ClassNotFoundException;

    List<Ticket> readAllTicketFilmUser(String user) throws ClassNotFoundException;

    List<Ticket> readAllFreeTicket(int idFilm);

    void addTicket(int filmId, int seatNumber, float price, String isSold);



}
