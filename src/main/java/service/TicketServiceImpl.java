package service;

import entity.Ticket;
import repository.TicketRepository;
import repository.TicketRepositoryImpl;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository = new TicketRepositoryImpl();
    private TicketRepositoryImpl ticketRepositoryImpl;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketRepositoryImpl ticketRepositoryImpl) {
        this.ticketRepository = ticketRepository;
        this.ticketRepositoryImpl = ticketRepositoryImpl;
    }

    public TicketServiceImpl() {

    }

    @Override
    public void buyTicket(int seatNumber, String user, int idFilm) throws ClassNotFoundException {
        ticketRepository.buyTicket(seatNumber, user, idFilm);
    }


    @Override
    public void returnTicket(String user, int seatNumber, int idFilm) throws ClassNotFoundException {
        ticketRepository.returnTicket(user, seatNumber, idFilm);

    }

    @Override
    public List<Ticket> readAllTicketFilm() throws ClassNotFoundException {
        ticketRepository.readAllTicketFilm();
        return null;
    }

    @Override
    public List<Ticket> readAllTicketFilmForAdminManager() throws ClassNotFoundException {
        ticketRepository.readAllTicketFilmForAdminManager();
        return null;
    }

    @Override
    public List<Ticket> readAllFreeTicket(int idFilm) {
        ticketRepository.readAllFreeTicket(idFilm);
        return null;
    }


    @Override
    public void addTicket(int filmId, int seatNumber, float price, String isSold) {
        ticketRepository.addTicket(filmId, seatNumber, price, isSold);

    }

    @Override
    public List<Ticket> readAllTicketFilmUser(String user) throws ClassNotFoundException {
        ticketRepository.readAllTicketFilmUser(user);
        return null;
    }
}
