package controller;

import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import service.*;

public class FilmController {
//    private static FilmRepositoryImpl filmRepositoryImpl;
//    static FilmService filmService = new FilmServiceImpl(filmRepositoryImpl);

    public FilmController() {
        this.filmService = filmService;
    }

    static FilmService filmService = new FilmServiceImpl();
    public static FilmRepository filmRepository;
    public static FilmRepositoryImpl filmRepositoryImpl;;


    public static void readAllFilm() throws ClassNotFoundException {
        filmService.readAllFilm();
    }

    public static void addFilm(String nameFilm, String dateTime, int listTicketFilm) throws ClassNotFoundException {
        filmService.addFilm(nameFilm, dateTime, listTicketFilm);
    }


    public static void deleteFilm(int idFilm) throws ClassNotFoundException {
        filmService.deleteFilm(idFilm);
    }

    public static void updateFilm(int id, String newNameFilm, String newDateTime, int newListTicketFilm) throws ClassNotFoundException {
        filmService.updateFilm(id, newNameFilm, newDateTime, newListTicketFilm);
    }

    public static void findFilm(int id) throws ClassNotFoundException {
        filmService.findFilm(id);
    }
}