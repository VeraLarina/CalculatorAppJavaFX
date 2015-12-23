import calculator.DBData;
import calculator.Date;
import calculator.Model;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by VeraL on 10.12.2015.
 */
public class ModelTest {

    Model model = new Model();
    DBData db = new DBData();

    @Test
     public void testFlakeBoardPrice(){
        DBData db = new DBData();
        assertEquals(7.46, db.checkFlakeboardPrice("Egger ST22"),0 );
        System.out.println("Flakeboard OK");
    }


    @Test
      public void testRodPrice() {
        DBData db = new DBData();
        assertEquals(1.7, db.checkRodPrice(), 0);
        System.out.println("Rod OK");
    }

    @Test
     public void testPantographPrice() {
        DBData db = new DBData();
        assertEquals(54.16, db.checkPantographPrice(), 0);
        System.out.println("Pantograph OK");
    }

    @Test
    public void testHangerPrice() {
        DBData db = new DBData();
        assertEquals(37.5, db.checkHangerPrice(), 0);
        System.out.println("Hanger OK");
    }

    @Test
    public void testBoxPrice() {
        DBData db = new DBData();
        assertEquals(8.33, db.checkBoxPrice(), 0);
        System.out.println("Box OK");
    }

    @Test
      public void testMarginRate() {
        DBData db = new DBData();
        assertEquals(1.75, db.checkMarginRate(4), 0);
        System.out.println("Margin OK");
    }

    @Test
    public void testFacadePrice() {
        DBData db = new DBData();
        assertEquals(18.58, db.checkFacadePrice("Зеркало серебро"), 0);
        System.out.println("Facade Price OK");
    }


    }
