package calculator;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by VeraL on 13.12.2015.
 */
public class Date {
    public String getDate() {
    Calendar calendar = new GregorianCalendar();
    Calendar cal = Calendar.getInstance();
    int day = cal.get(Calendar.DATE);
    int month = cal.get(Calendar.MONTH) + 1;
    int year = cal.get(Calendar.YEAR);
        String date = Integer.toString(day) + " " + Integer.toString(month) + " " + Integer.toString(year);
        return date;
}
}
