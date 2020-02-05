package info.semicolen.resturentapplicationtask.Models;

import java.util.ArrayList;

import info.semicolen.resturentapplicationtask.FirebaseModel.fMenuItem;

public class Orders {

    int orderId;
    String tableName;
    String orderTime;
    String OrderProgress;
    int orderTotalItems;
    ArrayList<fMenuItem> al_menuItem;

    public Orders(int orderId, String tableName, String orderTime, String orderProgress, int orderTotalItems, ArrayList<fMenuItem> al_menuItem) {
        this.orderId = orderId;
        this.tableName = tableName;
        this.orderTime = orderTime;
        OrderProgress = orderProgress;
        this.orderTotalItems = orderTotalItems;
        this.al_menuItem = al_menuItem;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderProgress() {
        return OrderProgress;
    }

    public void setOrderProgress(String orderProgress) {
        OrderProgress = orderProgress;
    }

    public int getOrderTotalItems() {
        return orderTotalItems;
    }

    public void setOrderTotalItems(int orderTotalItems) {
        this.orderTotalItems = orderTotalItems;
    }

    public ArrayList<fMenuItem> getAl_menuItem() {
        return al_menuItem;
    }

    public void setAl_menuItem(ArrayList<fMenuItem> al_menuItem) {
        this.al_menuItem = al_menuItem;
    }
}
