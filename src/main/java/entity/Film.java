package entity;

public class Film {
    private Integer id;
    private String nameFilm;
    private String dateTime;
    private int listTicketFilm;

    public Film(Integer id, String nameFilm, String dateTime, int listTicketFilm) {
        this.id = id;
        this.nameFilm = nameFilm;
        this.dateTime = dateTime;
        this.listTicketFilm = listTicketFilm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getListTicketFilm() {
        return listTicketFilm;
    }

    public void setListTicketFilm(int listTicketFilm) {
        this.listTicketFilm = listTicketFilm;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", nameFilm=" + nameFilm +
                ", dateTime=" + dateTime +
                ", listTicketFilm=" + listTicketFilm +
                '}';
    }
}
