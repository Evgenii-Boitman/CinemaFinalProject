package service;

import entity.Film;
import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilmServiceImpl implements FilmService {
    private FilmRepository filmRepository = new FilmRepositoryImpl();
    private FilmRepositoryImpl filmRepositoryImpl;

    public FilmServiceImpl(FilmRepositoryImpl filmRepositoryImpl, FilmRepository filmRepository) {
        this.filmRepositoryImpl = this.filmRepositoryImpl;
        this.filmRepository = filmRepository;
    }

    public FilmServiceImpl() {
    }


    @Override
    public void addFilm(String nameFilm, String dateTime, int listTicketFilm) throws ClassNotFoundException {
        filmRepository.addFilm(nameFilm, dateTime, listTicketFilm);
    }

    @Override
    public List<Film> readAllFilm() throws ClassNotFoundException {
        filmRepository.readAllFilm();
        return null;
    }

    @Override
    public List<Film> findFilm(int id) {
        filmRepository.findFilm(id);
        return null;
    }

    @Override
    public void deleteFilm(int idFilm) throws ClassNotFoundException {
        filmRepository.deleteFilm(idFilm);
    }

    @Override
    public void updateFilm(int id, String newNameFilm, String newDateTime, int newListTicketFilm) throws ClassNotFoundException {
        filmRepository.updateFilm(id, newNameFilm, newDateTime, newListTicketFilm);
    }


}
