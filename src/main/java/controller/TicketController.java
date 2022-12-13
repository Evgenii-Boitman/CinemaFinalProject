package controller;

import repository.TicketRepository;
import repository.TicketRepositoryImpl;
import service.TicketService;
import service.TicketServiceImpl;

public class TicketController {

    static TicketService ticketService = new TicketServiceImpl();
    public static TicketRepository ticketRepository;
    public static TicketRepositoryImpl ticketRepositoryImpl;


    public TicketController() {
        this.ticketService = ticketService;
    }


    public static void readAllFreeTicket(int idFilm) {
        ticketService.readAllFreeTicket(idFilm);
    }

    public static void buyTicket(int seatNumber, String user, int idFilm) throws ClassNotFoundException {
        ticketService.buyTicket(seatNumber, user, idFilm);
    }

    public static void returnTicket(String user, int seatNumber, int idFilm) throws ClassNotFoundException {
        ticketService.returnTicket(user, seatNumber, idFilm);
    }

    public static void readAllTicketFilm() throws ClassNotFoundException {
        ticketService.readAllTicketFilm();
    }

    public static void addTicket(int filmId, int seatNumber, float price, String isSold) throws ClassNotFoundException {
        ticketService.addTicket(filmId, seatNumber, price, isSold);
    }

    public static void readAllTicketFilmUser(String user) throws ClassNotFoundException {
        ticketService.readAllTicketFilmUser(user);
    }

    public static void readAllTicketFilmForAdminManager() {

    }
}

