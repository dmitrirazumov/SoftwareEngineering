package rule;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

@RestController
public class Controller {

    @GetMapping("/")
    public Data greeting(@RequestParam(required = false) Integer year,
                         @RequestParam(required = false) String currentDate) {
        if (year != null && currentDate == null) {
            return new Data(HttpStatus.OK.value(), getDate(year));
        }
        if (currentDate != null && year == null) {
            if (currentDate.length() != 8) {
                return new Data(HttpStatus.BAD_REQUEST.value(), getDate(0));
            }
            try {
                Integer temp = Integer.parseInt(currentDate);
            } catch (NumberFormatException e) {
                return new Data(HttpStatus.BAD_REQUEST.value(), getDate(0));
            }
            if (getNewDate(currentDate).compareTo("INCORRECT INPUT") == 0){
                return new Data(HttpStatus.BAD_REQUEST.value(), getNewDate(currentDate));
            }
            return new Data(HttpStatus.OK.value(), getNewDate(currentDate));
        }
        return new Data(HttpStatus.BAD_REQUEST.value(), getDate(0));
    }

    private String getNewDate(String currentDate) {
        int days = Integer.parseInt(currentDate.substring(0, 2));
        int month = Integer.parseInt(currentDate.substring(2, 4));
        int year = Integer.parseInt(currentDate.substring(4, 8));
        try {
            LocalDate current = LocalDate.of(year, month, days);
            int date = ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) ? 12 : 13;
            if ((month == 9 && days <= date) || month < 9) {
                LocalDate prog_day = LocalDate.of(year, 9, date);
                long res = ChronoUnit.DAYS.between(current, prog_day);
                return String.valueOf(res);
            } else {
                LocalDate prog_day = LocalDate.of(year + 1, 9, (((year + 1) % 400 == 0) || (((year + 1) % 4 == 0) && ((year + 1) % 100 != 0))) ? 12 : 13);
                long res = ChronoUnit.DAYS.between(current, prog_day);
                return String.valueOf(res);
            }
        } catch (DateTimeException e) {
            return "INCORRECT INPUT";
        }
    }


    private String getDate(int year) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String message;
        if (year > 0) {
            Calendar calendar;
            if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
                calendar = new GregorianCalendar(year, Calendar.SEPTEMBER, 12);
            else
                calendar = new GregorianCalendar(year, Calendar.SEPTEMBER, 13);
            message = dateFormat.format(calendar.getTime());
        } else {
            message = "INCORRECT INPUT";
        }
        return message;
    }
}