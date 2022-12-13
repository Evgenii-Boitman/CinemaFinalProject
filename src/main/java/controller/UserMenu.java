package controller;

import repository.UserRepository;
import repository.UserRepositoryImpl;

import java.util.Scanner;

public class UserMenu {
    private UserRepositoryImpl userRepositoryImpl;
    private UserRepository userRepository;

    public UserMenu(UserRepositoryImpl userRepositoryImpl, UserRepository userRepository) {
        this.userRepositoryImpl = userRepositoryImpl;
        this.userRepository = userRepository;
    }

    public static void userMenu() throws ClassNotFoundException {
//        UserController userController = new UserController();
//        FilmController filmController = new FilmController();
//        TicketController ticketController = new TicketController();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в кинотеатр \"Речица\"!");
        System.out.println("-----------------");
        System.out.println("1. Зарегистрируйтесь как новый Пользователь");
        System.out.println("2. Войдите как зарегистрированный Пользователь");
        System.out.println("3. Менеджер");
        System.out.println("4. Администратор");
        System.out.println("0. Выход из меню");
        System.out.println("-----------------");
        System.out.println("Сделайте свой выбор: ");
        int menu = scanner.nextInt();
        switch (menu) {
            case 1 -> menuNewUser();
            case 2 -> menuUser();
            case 3 -> menuManager();
            case 4 -> menuAdmin();
            case 0 -> {
                System.out.println("До встречи!");
                scanner.close();
            }
            default -> System.out.println("Ничего не выбрано!");
        }
    }

    public static void menuNewUser() throws ClassNotFoundException {
        Scanner userScannerMenu = new Scanner(System.in);
        System.out.println("Введите Ваш логин: ");
        System.out.println("-----------------");
        String userLogin = userScannerMenu.nextLine();
        System.out.println("Введите Ваш пароль: ");
        System.out.println("-----------------");
        String userPassword = userScannerMenu.nextLine();
        if (checkNewUser(userLogin, userPassword)) {
            System.out.println("------------------------");
        } else {
            System.out.println("Не верное имя Пользователя или Пароль");
        }
    }

    public static boolean checkNewUser(String userLogin, String userPassword) throws ClassNotFoundException {
        UserController.createUser(userLogin, userPassword);
        Scanner scanner = new Scanner(System.in);
        System.out.println(userLogin + ", добро пожаловать!");
        System.out.println("-----------------");
        System.out.println("1. Просмотреть список фильмов");
        System.out.println("2. Купить билет");
        System.out.println("3. Вернуть билет");
        System.out.println("4. Просмотреть приобретенные билеты");
        System.out.println("5. Просмотреть все билеты");
        System.out.println("0. Выход из меню");
        System.out.println("-----------------");
        System.out.println("Сделайте свой выбор: ");
        int menu = scanner.nextInt();
        switch (menu) {
            case 1 -> {
                FilmController.readAllFilm();
                checkUser(userLogin, userPassword);
            }
            case 2 -> {
                Scanner buyTicket = new Scanner(System.in);
                System.out.println("Выберите место: ");
                System.out.println("-----------------");
                int seatNumber = buyTicket.nextInt();
                System.out.println("Введите Ваш Login при регистрации: ");
                System.out.println("-----------------");
                String user = buyTicket.next();
                System.out.println("Для покупки билета введите ID  фильма: ");
                System.out.println("-----------------");
                int idFilm = buyTicket.nextInt();
                TicketController.buyTicket(seatNumber, user, idFilm);
                checkUser(userLogin, userPassword);
            }
            case 3 -> {
                Scanner returnTicket = new Scanner(System.in);
                System.out.println("Выберите место билета, который хотите вернуть: ");
                System.out.println("-----------------");
                int seatNumber = returnTicket.nextInt();
                System.out.println("Введите Ваш Login: ");
                System.out.println("-----------------");
                String user = returnTicket.next();
                System.out.println("Для возврата билета введите ID  фильма: ");
                System.out.println("-----------------");
                int idFilm = returnTicket.nextInt();
                TicketController.returnTicket(user, seatNumber, idFilm);
                checkUser(userLogin, userPassword);
            }
            case 4 -> {
                Scanner allTicketUser = new Scanner(System.in);
                System.out.println("Для просмотра приобретенных билетов введите ID пользователя: ");
                System.out.println("-----------------");
                String user = allTicketUser.nextLine();
                TicketController.readAllTicketFilmUser(user);
                checkUser(userLogin, userPassword);
            }
            case 5 -> {
                TicketController.readAllTicketFilm();
                checkUser(userLogin, userPassword);
            }
            case 0 -> {
                System.out.println("До встречи!");
                scanner.close();
            }
            default -> System.out.println("Ничего не выбрано!");
        }
        return true;
    }

    public static void menuUser() throws ClassNotFoundException {
        Scanner userScannerMenu = new Scanner(System.in);
        System.out.println("Введите Ваш логин: ");
        System.out.println("-----------------");
        String userLogin = userScannerMenu.nextLine();
        System.out.println("Введите Ваш пароль: ");
        System.out.println("-----------------");
        String userPassword = userScannerMenu.nextLine();
        if (checkUser(userLogin, userPassword)) {
            System.out.println("------------------------");
        } else {
            System.out.println("Не верное имя Пользователя или Пароль");
        }
    }


    public static boolean checkUser(String userLogin, String userPassword) throws ClassNotFoundException {
        UserController.openUser(userLogin, userPassword);
        Scanner scanner = new Scanner(System.in);
        System.out.println(userLogin + ", добро пожаловать!");
        System.out.println("-----------------");
        System.out.println("1. Просмотреть список фильмов");
        System.out.println("2. Купить билет");
        System.out.println("3. Вернуть билет");
        System.out.println("4. Просмотреть приобретенные билеты");
        System.out.println("5. Просмотреть все билеты");
        System.out.println("0. Выход из меню");
        System.out.println("-----------------");
        System.out.println("Сделайте свой выбор: ");
        int menu = scanner.nextInt();
        switch (menu) {
            case 1 -> {
                FilmController.readAllFilm();
                checkUser(userLogin, userPassword);
            }
            case 2 -> {
                Scanner buyTicket = new Scanner(System.in);
                System.out.println("Выберите место: ");
                System.out.println("-----------------");
                int seatNumber = buyTicket.nextInt();
                System.out.println("Введите Ваш Login при регистрации: ");
                System.out.println("-----------------");
                String user = buyTicket.next();
                System.out.println("Для покупки билета введите ID  фильма: ");
                System.out.println("-----------------");
                int idFilm = buyTicket.nextInt();
                TicketController.buyTicket(seatNumber, user, idFilm);
                checkUser(userLogin, userPassword);
            }
            case 3 -> {
                Scanner returnTicket = new Scanner(System.in);
                System.out.println("Выберите место билета, который хотите вернуть: ");
                System.out.println("-----------------");
                int seatNumber = returnTicket.nextInt();
                System.out.println("Введите Ваш Login: ");
                System.out.println("-----------------");
                String user = returnTicket.next();
                System.out.println("Для возврата билета введите ID  фильма: ");
                System.out.println("-----------------");
                int idFilm = returnTicket.nextInt();
                TicketController.returnTicket(user, seatNumber, idFilm);
                checkUser(userLogin, userPassword);
            }
            case 4 -> {
                Scanner allTicketUser = new Scanner(System.in);
                System.out.println("Для просмотра приобретенных билетов введите Login пользователя: ");
                System.out.println("-----------------");
                String user = allTicketUser.nextLine();
                TicketController.readAllTicketFilmUser(user);
                checkUser(userLogin, userPassword);
            }
            case 5 -> {
                TicketController.readAllTicketFilm();
                checkUser(userLogin, userPassword);
            }
            case 0 -> {
                System.out.println("До встречи!");
                scanner.close();
            }
            default -> System.out.println("Ничего не выбрано!");
        }
        return true;
    }

    public static void menuManager() throws ClassNotFoundException {
        Scanner managerScannerMenu = new Scanner(System.in);
        System.out.println("Введите Ваш логин: ");
        System.out.println("-----------------");
        String userLogin = managerScannerMenu.nextLine();
        System.out.println("Введите Ваш пароль: ");
        System.out.println("-----------------");
        String userPassword = managerScannerMenu.nextLine();
        if (checkManager(userLogin, userPassword)) {
            System.out.println("------------------------");
        } else {
            System.out.println("Не верное имя Пользователя или Пароль");
        }
    }

    public static boolean checkManager(String userLogin, String userPassword) throws
            ClassNotFoundException {
        UserController.openManager(userLogin, userPassword);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manager, добро пожаловать!");
        System.out.println("-----------------");
        System.out.println("1. Добавить фильм");
        System.out.println("2. Удалить фильм");
        System.out.println("3. Купить билет");
        System.out.println("4. Вернуть билет");
        System.out.println("5. Изменить Login и Password пользователя");
        System.out.println("6. Найти пользователя");
        System.out.println("7. Показать всех пользователей");
        System.out.println("8. Просмотреть все билеты");
        System.out.println("0. Выход из меню");
        System.out.println("-----------------");
        System.out.println("Сделайте свой выбор: ");
        int menu = scanner.nextInt();
        switch (menu) {
            case 1 -> {
                Scanner newFilm = new Scanner(System.in);
                System.out.println("Введите название фильма: ");
                System.out.println("-----------------");
                String nameFilm = newFilm.nextLine();
                System.out.println("Введите дату сеанса в формате YYYY-MM-DD HH:MM ");
                System.out.println("-----------------");
                String dateTime = newFilm.next();
                System.out.println("Введите количество билетов на фильм: ");
                System.out.println("-----------------");
                int listTicketFilm = newFilm.nextInt();
                FilmController.addFilm(nameFilm, dateTime, listTicketFilm);
                checkManager(userLogin, userPassword);
            }
            case 2 -> {
                Scanner deleteFilm = new Scanner(System.in);
                System.out.println("Для удаления Фильма введите ID фильма: ");
                System.out.println("-----------------");
                int idFilm = deleteFilm.nextInt();
                FilmController.deleteFilm(idFilm);
                checkManager(userLogin, userPassword);
            }
            case 3 -> {
                Scanner buyTicket = new Scanner(System.in);
                System.out.println("Выберите место: ");
                System.out.println("-----------------");
                int seatNumber = buyTicket.nextInt();
                System.out.println("Введите Ваш Login: ");
                System.out.println("-----------------");
                String user = buyTicket.next();
                System.out.println("Для покупки билета введите ID  фильма: ");
                System.out.println("-----------------");
                int idFilm = buyTicket.nextInt();
                TicketController.buyTicket(seatNumber, user, idFilm);
                checkManager(userLogin, userPassword);
            }
            case 4 -> {
                Scanner returnTicket = new Scanner(System.in);
                System.out.println("Выберите место билета, который хотите вернуть: ");
                System.out.println("-----------------");
                int seatNumber = returnTicket.nextInt();
                System.out.println("Введите Ваш Login: ");
                System.out.println("-----------------");
                String user = returnTicket.next();
                System.out.println("Для возврата билета введите ID  фильма: ");
                System.out.println("-----------------");
                int idFilm = returnTicket.nextInt();
                TicketController.returnTicket(user, seatNumber, idFilm);
                checkManager(userLogin, userPassword);
            }
            case 5 -> {
                Scanner updateUser = new Scanner(System.in);
                System.out.println("Для обновления Пользователя введите ID пользователя: ");
                System.out.println("-----------------");
                int id = updateUser.nextInt();
                System.out.println("Введите новый логин пользователя: ");
                System.out.println("-----------------");
                String newLogin = updateUser.next();
                System.out.println("Введите новый пароль Пользователя: ");
                System.out.println("-----------------");
                String newPassword = updateUser.next();
                System.out.println("Введите новую роль Пользователя: ");
                System.out.println("-----------------");
                String newRole = updateUser.next();
                UserController.updateUser(id, newLogin, newPassword, newRole);
                checkManager(userLogin, userPassword);
            }
            case 6 -> {
                Scanner findUser = new Scanner(System.in);
                System.out.println("Для поиска Пользователя введите ID пользователя: ");
                System.out.println("-----------------");
                int idUser = findUser.nextInt();
                UserController.findUser(idUser);
                checkManager(userLogin, userPassword);
            }
            case 7 -> {
                UserController.readAllUser();
                checkManager(userLogin, userPassword);
            }
            case 8 -> {
                TicketController.readAllTicketFilmForAdminManager();
                checkManager(userLogin, userPassword);
            }
            case 0 -> {
                System.out.println("До встречи!");
                scanner.close();
            }
            default -> System.out.println("Ничего не выбрано!");
        }
        return true;
    }


    public static void menuAdmin() throws ClassNotFoundException {
        Scanner adminScannerMenu = new Scanner(System.in);
        System.out.println("Введите Ваш логин: ");
        System.out.println("-----------------");
        String userLogin = adminScannerMenu.nextLine();
        System.out.println("Введите Ваш пароль: ");
        System.out.println("-----------------");
        String userPassword = adminScannerMenu.nextLine();
        if (checkAdmin(userLogin, userPassword)) {
            System.out.println("------------------------");
        } else {
            System.out.println("Не верное имя Пользователя или Пароль");
        }
    }

    public static boolean checkAdmin(String userLogin, String userPassword) throws
            ClassNotFoundException {
        UserController.openAdmin(userLogin, userPassword);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Administrator, добро пожаловать!");
        System.out.println("-----------------");
        System.out.println("1. Добавить фильм");
        System.out.println("2. Удалить фильм");
        System.out.println("3. Купить билет");
        System.out.println("4. Вернуть билет");
        System.out.println("5. Удалить пользователя");
        System.out.println("6. Добавить пользователя");
        System.out.println("7. Изменить фильм");
        System.out.println("8. Изменить Login и Password пользователя");
        System.out.println("9. Найти пользователя");
        System.out.println("10. Показать всех пользователей");
        System.out.println("11. Добавить билеты");
        System.out.println("12. Просмотреть все билеты");
        System.out.println("0. Выход из меню");
        System.out.println("-----------------");
        System.out.println("Сделайте свой выбор: ");
        int menu = scanner.nextInt();
        switch (menu) {
            case 1 -> {
                Scanner newFilm = new Scanner(System.in);
                System.out.println("Введите название фильма: ");
                System.out.println("-----------------");
                String nameFilm = newFilm.nextLine();
                System.out.println("Введите дату сеанса в формате YYYY-MM-DD HH:MM ");
                System.out.println("-----------------");
                String dateTime = newFilm.nextLine();
                System.out.println("Введите количество билетов на фильм: ");
                System.out.println("-----------------");
                int listTicketFilm = newFilm.nextInt();
                FilmController.addFilm(nameFilm, dateTime, listTicketFilm);
                checkAdmin(userLogin, userPassword);
            }
            case 2 -> {
                Scanner deleteFilm = new Scanner(System.in);
                System.out.println("Для удаления Фильма введите ID фильма: ");
                System.out.println("-----------------");
                int idFilm = deleteFilm.nextInt();
                FilmController.deleteFilm(idFilm);
                checkAdmin(userLogin, userPassword);
            }
            case 3 -> {
                Scanner buyTicket = new Scanner(System.in);
                System.out.println("Выберите место: ");
                System.out.println("-----------------");
                int seatNumber = buyTicket.nextInt();
                System.out.println("Введите Ваш Login: ");
                System.out.println("-----------------");
                String user = buyTicket.next();
                System.out.println("Для покупки билета введите ID  фильма: ");
                System.out.println("-----------------");
                int idFilm = buyTicket.nextInt();
                TicketController.buyTicket(seatNumber, user, idFilm);
                checkAdmin(userLogin, userPassword);
            }
            case 4 -> {
                Scanner returnTicket = new Scanner(System.in);
                System.out.println("Выберите место билета, который хотите вернуть: ");
                System.out.println("-----------------");
                int seatNumber = returnTicket.nextInt();
                System.out.println("Введите Ваш Login: ");
                System.out.println("-----------------");
                String user = returnTicket.next();
                System.out.println("Для возврата билета введите ID  фильма: ");
                System.out.println("-----------------");
                int idFilm = returnTicket.nextInt();
                TicketController.returnTicket(user, seatNumber, idFilm);
                checkAdmin(userLogin, userPassword);
            }
            case 5 -> {
                Scanner deleteUser = new Scanner(System.in);
                System.out.println("Для удаления Пользователя введите ID пользователя: ");
                System.out.println("-----------------");
                int idUser = deleteUser.nextInt();
                UserController.deleteUser(idUser);
                checkAdmin(userLogin, userPassword);
            }
            case 6 -> {
                Scanner newUser = new Scanner(System.in);
                System.out.println("Введите логин нового Пользователя: ");
                System.out.println("-----------------");
                String userLogin1 = newUser.nextLine();
                System.out.println("Введите пароль нового Пользователя: ");
                System.out.println("-----------------");
                String userPassword1 = newUser.nextLine();
                UserController.createUser(userLogin1, userPassword1);
                checkAdmin(userLogin, userPassword);
            }
            case 7 -> {
                Scanner updateFilm = new Scanner(System.in);
                System.out.println("Для обновления Фильма введите ID фильма: ");
                System.out.println("-----------------");
                int id = updateFilm.nextInt();
                System.out.println("Введите новое название фильма: ");
                System.out.println("-----------------");
                String newNameFilm = updateFilm.next();
                System.out.println("Введите новую дату и время сеанса в формате YYYY-MM-DD HH:MM: ");
                System.out.println("-----------------");
                String newDateTime = updateFilm.next();
                System.out.println("Введите новый список билетов: ");
                System.out.println("-----------------");
                int newListTicketFilm = updateFilm.nextInt();
                FilmController.updateFilm(id, newNameFilm, newDateTime, newListTicketFilm);
                checkAdmin(userLogin, userPassword);
            }
            case 8 -> {
                Scanner updateUser = new Scanner(System.in);
                System.out.println("Для обновления Пользователя введите ID пользователя: ");
                System.out.println("-----------------");
                int id = updateUser.nextInt();
                System.out.println("Введите новый логин пользователя: ");
                System.out.println("-----------------");
                String newLogin = updateUser.next();
                System.out.println("Введите новый пароль Пользователя: ");
                System.out.println("-----------------");
                String newPassword = updateUser.next();
                System.out.println("Введите новую роль Пользователя: ");
                System.out.println("-----------------");
                String newRole = updateUser.next();
                UserController.updateUser(id, newLogin, newPassword, newRole);
                checkAdmin(userLogin, userPassword);
            }
            case 9 -> {
                Scanner findUser = new Scanner(System.in);
                System.out.println("Для поиска Пользователя введите ID пользователя: ");
                System.out.println("-----------------");
                int idUser = findUser.nextInt();
                UserController.findUser(idUser);
                checkAdmin(userLogin, userPassword);
            }
            case 10 -> {
                UserController.readAllUser();
                checkAdmin(userLogin, userPassword);
            }
            case 11 -> {
                Scanner addTicket = new Scanner(System.in);
                System.out.println("Для добавления билета введите ID фильма: ");
                System.out.println("-----------------");
                int idFilm = addTicket.nextInt();
                System.out.println("Введите номер места: ");
                System.out.println("-----------------");
                int seatNumber = addTicket.nextInt();
                System.out.println("Введите цену билета: ");
                System.out.println("-----------------");
                float price = addTicket.nextFloat();
                System.out.println("Введите статус билета: ");
                System.out.println("-----------------");
                String isSold = addTicket.next();
                TicketController.addTicket(idFilm, seatNumber, price, isSold);
                checkAdmin(userLogin, userPassword);
            }
            case 12 -> {
                TicketController.readAllTicketFilmForAdminManager();
                checkAdmin(userLogin, userPassword);
            }
            case 0 -> {
                System.out.println("До встречи!");
                scanner.close();
            }
            default -> System.out.println("Ничего не выбрано!");
        }
        return true;
    }
//
//    public static void checkUser() {
//
//    }
}

