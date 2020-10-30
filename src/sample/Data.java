package sample;

import java.sql.Date;
import java.time.LocalDate;

public class Data {
    private int id_region;
    private int number;
    private int id;
    private double quantity;
    private String city;
    private String area;
    private String type;
    private String name;
    private String name_inventory;
    private String sort;
    private LocalDate date;

    public Data(double quantity, LocalDate date, String name_inventory) {
        this.date=date;
        this.name_inventory=name_inventory;
        this.quantity=quantity;
    }

    public Data(double quantity, int id) {
        this.id=id;
        this.quantity=quantity;
    }

    public Data(String name, double quantity) {
        this.name=name;
        this.quantity=quantity;
    }

    public double getquantity() {
        return quantity;
    }

    public void setquantity(double quantity) {
        this.quantity = quantity;
    }

    public String getName_inventory() {
        return name_inventory;
    }

    public void setName_inventory(String name_inventory) {
        this.name_inventory = name_inventory;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Data(int id, String name, String name_inventory, double quantity, LocalDate date,
                String name_employee,String position_employee) {
        this.id=id;
        this.name=name;
        this.name_inventory=name_inventory;
        this.quantity=quantity;
        this.date=date;
        this.name_employee=name_employee;
        this.position_employee=position_employee;
    }

    public Data(String name, LocalDate date, Double quantity) {
        this.quantity=quantity;
        this.name=name;
        this.date=date;
    }

    public Data(double quantity, String name, String name_inventory, LocalDate date) {
        this.quantity=quantity;
        this.name=name;
        this.name_inventory=name_inventory;
        this.date=date;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    private String group;

    public Data(int id, String name, String type) {
         this.id=id;
        this.name=name;
        this.type=type;
    }                                           

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public int getnumber() {
        return number;
    }

    public void setnumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName_employee() {
        return name_employee;
    }

    public void setName_employee(String name_employee) {
        this.name_employee = name_employee;
    }

    public String getPosition_employee() {
        return position_employee;
    }

    public void setPosition_employee(String position_employee) {
        this.position_employee = position_employee;
    }

    private String address;
    private String telephone;
    private String name_employee;     
    private String position_employee;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Data(int id, int number, String name, String telephone) {
        this.id=id;
        this.number=number;
        this.name=name;
        this.telephone=telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
