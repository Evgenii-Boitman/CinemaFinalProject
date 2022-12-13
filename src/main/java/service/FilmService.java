package service;

import entity.Film;

import java.util.Date;
import java.util.List;

public interface FilmService {

    void deleteFilm(int idFilm) throws ClassNotFoundException;

    void updateFilm(int id, String newNameFilm, String newDateTime, int newListTicketFilm) throws ClassNotFoundException;

    void addFilm(String nameFilm, String dateTime, int listTicketFilm) throws ClassNotFoundException;

    List<Film> readAllFilm() throws ClassNotFoundException;

    List<Film> findFilm(int id);

}
