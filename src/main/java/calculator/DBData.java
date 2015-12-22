package calculator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by VeraL on 21.12.2015.
 */
public class DBData {
    private Connection con = getConnection();

    private static final String FLAKEBOARD_18MM_QUERY = "SELECT * FROM mebdom_calc.flakeboard18mm";
    private static final String FACADE_DETAILS_QUERY=  "SELECT * FROM mebdom_calc.facadeprice";
    private static final String PRICE_COLUMN = "price";
    private static final String MARGIN_LEVEL = "id";
    private static final String MATERIALS_NAME = "name";
    private static final String MARGIN_DETAILS_QUERY = "SELECT * FROM mebdom_calc.margin";
    private static final String ELEMENTS_PRICE_QUERY = "SELECT * FROM mebdom_calc.pricelist";



    public Connection getConnection() {

        if (con == null) {
            Properties connectionProps = new Properties();

            try (FileInputStream fis = new FileInputStream("resr/config.properties")) {
                connectionProps.load(fis);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            final String DB_CONNECTION = connectionProps.getProperty("DB_CONNECTION");
            final String DB_USER = connectionProps.getProperty("DB_USER");
            final String DB_PASSWORD = connectionProps.getProperty("DB_PASSWORD");


            try {
                con = DriverManager.getConnection(
                        DB_CONNECTION, DB_USER, DB_PASSWORD);
                if (con != null) {
                    System.out.println("Connected to database");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    public String[] getFlakeboard() {

        ArrayList<String> flakeboard = null;

        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(FLAKEBOARD_18MM_QUERY);
            flakeboard = new ArrayList<String>();
            while (rs.next()) {
                flakeboard.add(rs.getString(MATERIALS_NAME ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] names = new String[flakeboard.size()];
        flakeboard.toArray(names);
        return names;
    }

    public String[] getFacade() {

        ArrayList<String> facade = null;

        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(FACADE_DETAILS_QUERY);
            facade = new ArrayList<String>();
            while (rs.next()) {
                facade.add(rs.getString(MATERIALS_NAME ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] names = new String[facade.size()];
        facade.toArray(names);
        return names;
    }


    public Integer[] getMargin() {

        ArrayList<Integer> margin = null;

        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(MARGIN_DETAILS_QUERY);
            margin = new ArrayList<Integer>();
            while (rs.next()) {
                margin.add(rs.getInt(MARGIN_LEVEL ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Integer[] names = new Integer[margin.size()];
        margin.toArray(names);
        return names;
    }

    public double checkProfilePrice() {
        //String query = ELEMENTS_PRICE_QUERY  + "WHERE id=?";
        double price = 0;
        try (PreparedStatement st = con.prepareStatement(ELEMENTS_PRICE_QUERY  + "WHERE id=?")) {
            st.setInt(1, 6);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                price = rs.getDouble(PRICE_COLUMN);
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
        return price;
    }

    public double checkFlakeboardPrice(String flakeboard) {
        double flakeboardPrice = 0;
        try (PreparedStatement st = con.prepareStatement(FLAKEBOARD_18MM_QUERY + " WHERE name=?")) {
            st.setString(1, flakeboard);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                flakeboardPrice = rs.getDouble(PRICE_COLUMN);
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
        return flakeboardPrice;
    }

    public double checkFacadePrice(String facade) {

        double facadePrice = 0;
        try (PreparedStatement st = con.prepareStatement(FACADE_DETAILS_QUERY + "WHERE name=?")) {
            st.setString(1, facade);
            // st.executeQuery();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                facadePrice = rs.getDouble(PRICE_COLUMN);
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
            System.out.println("");// окошко в форме, что призошла ошибка+текст, в модель - метод эррор:принимает стинг в виде сообщения, что показать юзеру
            // в контроллере - чтобы выскочило окошко, что он не так делает
        }
        return facadePrice;
    }


    public double checkRodPrice() {
        double price = 0;
        try (PreparedStatement st = con.prepareStatement(ELEMENTS_PRICE_QUERY + "WHERE id=?")) {
            st.setInt(1, 1);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                price = rs.getDouble(PRICE_COLUMN);
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
        return price;
    }

    public double checkPantographPrice() {
        double price = 0;
        try (PreparedStatement st = con.prepareStatement(ELEMENTS_PRICE_QUERY+ "WHERE id=?")) {
            st.setInt(1, 3);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                price = rs.getDouble(PRICE_COLUMN);
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
        return price;
    }

    public double checkHangerPrice() {

        double price = 0;
        try (PreparedStatement st = con.prepareStatement(ELEMENTS_PRICE_QUERY + "WHERE id=?")) {
            st.setInt(1, 2);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                price = rs.getDouble(PRICE_COLUMN);
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
        return price;
    }

    public double checkBoxPrice() {
        double price = 0;
        try (PreparedStatement st = con.prepareStatement(ELEMENTS_PRICE_QUERY + "WHERE id=?")) {
            st.setInt(1, 4);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                price = rs.getDouble(PRICE_COLUMN);
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
        return price;
    }

    public double checkFiberboardPrice() {

        double flakeboardPrice = 0;
        try (PreparedStatement st = con.prepareStatement(ELEMENTS_PRICE_QUERY+ "WHERE id=?")) {
            st.setInt(1, 5);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                flakeboardPrice = rs.getDouble(PRICE_COLUMN);
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
        return flakeboardPrice;
    }


    public double checkMarginRate(int margin) {
        double marginRate = 0;
        try (PreparedStatement st = con.prepareStatement(MARGIN_DETAILS_QUERY + "WHERE id=?")) {
            st.setInt(1, margin);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                marginRate = rs.getDouble(2);
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
        return marginRate;
    }

}
