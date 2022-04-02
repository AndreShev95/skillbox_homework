package main;

public class DealDto {

    private int id; //Порядковый номер дела
    private volatile String text; //Содержание дела
    private volatile String date; //Необходимая дата завершения дела
    private volatile boolean readiness; //Статус готовности

    public DealDto() {
        this.text = "Дело не задано";
        this.date = "Дата завершения не задана";
        this.readiness = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isReadiness() {
        return readiness;
    }

    public void setReadiness(boolean readiness) {
        this.readiness = readiness;
    }

}
