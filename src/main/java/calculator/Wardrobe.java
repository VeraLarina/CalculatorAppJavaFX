package calculator;

import javax.persistence.*;

/**
 * Created by VeraL on 09.12.2015.
 */@Entity
   @Table(name = "Wardrobe")


public class Wardrobe {
    public static final int SIDE_RACK_NUMBER = 2;
    public static final int FLAKEBOARD_WIDTH = 18;
    public static final int MIN_LENGTH = 1000;
    public static final int MAX_LENGTH = 7000;
    public static final int MIN_HEIHT = 2400;
    public static final int MAX_HEIGHT = 2950;
    public static final int MIN_WIDTH = 300;
    public static final int MAX_WIDTH = 800;
    public static final int MIN_SHELF_AMOUNT = 4;
    public static final int MAX_SHELF_AMOUNT = 30;
    public static final int MIN_BOX_AMOUNT = 0;
    public static final int MAX_BOX_AMOUNT = 20;
    public static final int RADIUS_SH_NUMBER = 6;
    public static final int RADIUS_EL_WIDTH = 300;

    private long id;
    private String date;
    private int height;
    private int length;
    private int width;
    private int doors;
    private String flakeboard;
    private String facade;
    private String  currency;
    private int margin;
    private String price;
    public Wardrobe(){
    }

    public Wardrobe(String date, int height, int length, int width, int doors, String flakeboard,
                     String facade, int margin, String  currency, String price) {
        this.date = date;
        this.height = height;
        this.length = length;
        this.width = width;
        this.doors = doors;
        this.flakeboard = flakeboard;
        this. facade = facade;
        this.currency = currency;
        this.margin = margin;
        this.price = price;
        }

    @Id
    @GeneratedValue
    @Column(name = "Id")
    public long getId() {
            return id;
    }
        @Column(name = "Date")
        public String getDate() {
            return date;
        }
    @Column(name = "Height")
    public int getHeight() {
        return height;
    }
    @Column(name = "Length")
    public int getLength() {
        return length;
    }
    @Column(name = "Width")
    public int getWidth() {
        return width;
    }
    @Column(name = "Doors")
    public int getDoors(){
            return doors;
        }
    @Column(name = "Facade")
    public String getFacade() {
            return facade;
        }
    @Column(name = "Flakeboard")
    public String getFlakeboard() {
            return flakeboard;
        }
    @Column(name = "USD")
    public String  getCurrency() {
            return currency;
        }
    @Column(name = "Margin")
    public int getMargin() {
            return margin;
        }
    @Column(name = "Price")
    public String getPrice() {
            return price;
        }


    public void setDate(String date) {
            this.date = date;
        }
    public void setId(long id) {
            this.id = id;
        }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public void setDoors(int doors) {
            this.doors = doors;
        }

    public void setFlakeboard(String flakeboard) {
            this.flakeboard = flakeboard;
        }

    public void setCurrency(String  currency) {
        this.currency = currency ;
          }

    public void setMargin(int margin) {
        this.margin = margin;
            }
    public void setFacade(String facade) {
            this.facade = facade;
        }

    public void setPrice(String price) {
        this.price = price;
    }
    }
