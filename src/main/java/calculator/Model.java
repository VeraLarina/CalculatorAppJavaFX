package calculator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by VeraL on 08.12.2015.
 */
public class Model {




   // private static final String  HANGER_QUERY = "SELECT * FROM mebdom_calc.pricelist WHERE id=?";
    private static final String WORKBENCH_EXE = "\"C:\\Program Files\\MySQL\\MySQL Workbench 6.3 CE\\MySQLWorkbench.exe\"";
    private static final String CALC_EXE = "calc.exe";
    //  Wardrobe wardrobe;
 // private  MainGson mg;
 //   Date currDate;
 //   Customer customer;
   private CustomerDAO cd = new CustomerDAO();
  private DBData dbData = new DBData();
private USDRate usdRate = new USDRate();
    private  static Calculator calculator;




    public static double getBoardPrice(int height, int length, int width, int doors, int shelf,
                                 int rod, int pantograph, int hanger,
                                 int box, int roof, int bottom, int rearPanel, int radius,
                                 String flakeboard, int margin) {

      double price = calculator.calculateBoard(height, length, width, doors,shelf,
              rod, pantograph, hanger, box, roof, bottom,
              rearPanel, radius, flakeboard, margin);

        return price;
    }


    public double getFacadePrice(int height, int length, int doors, int margin, String facade) {
double facadePrice = calculator.calculateFacade(height, length, doors, margin, facade);
        return facadePrice;
    }

    public String setRate() {
       String currencyRate=usdRate.rateToString();
        return currencyRate;

    }

    public double getProfilePrice() {
        double price = dbData.checkProfilePrice();
        return price;
    }
/*
    public double getFlakeboardPrice(String flakeboard) {
        double price = dbData.checkFlakeboardPrice(flakeboard);
        return price;
    }
*/
    public double getFacadePrice(String facade) {
        double facadePrice  = dbData.checkFacadePrice(facade);
        return facadePrice;
    }


    public double getRodPrice() {
        double price = dbData.checkRodPrice();
        return price;
    }

    public double getPantographPrice() {
        double price = dbData.checkPantographPrice();
        return price;
    }

    public double getHangerPrice() {
        double price = dbData.checkHangerPrice();
        return price;
    }

    public double getBoxPrice() {
        double price = dbData.  checkBoxPrice();
        return price;
    }

    public double getFiberboardPrice() {
        double price = dbData.checkFiberboardPrice();
        return price;
    }


    public double getMarginRate(int margin) {
        double price = dbData.checkMarginRate(margin);
        return price;
    }


    public void openCalc() {
        try {
            Process process = Runtime.getRuntime()
                    .exec(CALC_EXE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void openMySQLWorkbench() {
        try {
            Process process = Runtime.getRuntime()

                    .exec(WORKBENCH_EXE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String[] showFlakeboard() {
        String [] flakeboardDetails;
                flakeboardDetails = dbData.getFlakeboard(dbData.getConnection());
        return flakeboardDetails;
    }

    public String[] showFacade() {
        String [] facadeDetails = dbData.getFacade();
        return facadeDetails;
    }

    public Integer[] showMargin() {
        Integer[] marginDetails = dbData.getMargin();
        return marginDetails;
    }

public Connection newConnection(){
    return dbData.getConnection();
}

    public void createCustomer(String name, String phone, String adress, Wardrobe wardrobe) {
        Customer customer3 = new Customer(name, phone, adress, wardrobe);
        cd.addCustomer(customer3, cd.openSession());
    }


}