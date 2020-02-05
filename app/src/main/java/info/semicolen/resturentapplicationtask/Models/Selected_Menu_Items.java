package info.semicolen.resturentapplicationtask.Models;

public class Selected_Menu_Items {

    int id;
    String name;
    int quantity;
    int itemprice;

    public Selected_Menu_Items(int id, String name, int quantity, int itemprice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.itemprice = itemprice;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemprice() {
        return itemprice;
    }

    public void setItemprice(int itemprice) {
        this.itemprice = itemprice;
    }
}
