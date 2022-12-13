package entity;

public class Ticket {
    private Integer id;
    private Integer userId;
    private Integer filmId;
    private Integer seatNumber;
    private Double price;
    private String isSold;

    public Ticket(Integer id, Integer userId, Integer filmId, Integer seatNumber, Double price, String isSold) {
        this.id = id;
        this.userId = userId;
        this.filmId = filmId;
        this.seatNumber = seatNumber;
        this.price = price;
        this.isSold = isSold;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSold() {
        return isSold;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", filmId=" + filmId +
                ", seatNumber=" + seatNumber +
                ", price=" + price +
                ", isSold='" + isSold + '\'' +
                '}';
    }
}
