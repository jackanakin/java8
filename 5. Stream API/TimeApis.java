import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class TimeApis {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate : " + localDate);
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime : " + localTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime : " + localDateTime);

        LocalDate localDate1 = LocalDate.of(2018, 03,03);
        System.out.println(localDate1);

        System.out.println("Year Day Date " + LocalDate.ofYearDay(2018,365));

        /**
         * Getting Values from Local Date
         */
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getYear());
        System.out.println("Day of Month using get : " + localDate.get(ChronoField.DAY_OF_MONTH));

        /**
         * Modifying Values in Local Date
         */
        System.out.println("Plus Weeks  : " + localDate.plusWeeks(1));
        System.out.println("Plus Years : " + localDate.plusYears(1));
        System.out.println("Plus Days " + localDate.plusDays(1));
        System.out.println("Plus Months : " + localDate.plusMonths(1));
        System.out.println("Minus Months : "+localDate.minusMonths(1));
        System.out.println("Chrono Unit : " + localDate.minus(2, ChronoUnit.YEARS));
        System.out.println("With Year : " + localDate.withYear(2019));
        System.out.println("With Year Chrono Field : " + localDate.with(ChronoField.YEAR , 2019));
        System.out.println("With Temporal Adjusters : " + localDate.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("With Temporal Adjusters : " + localDate.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.FRIDAY))); // maps to the first friday of the week

        /**
         * Unsupported Scenarios
         */
        //  System.out.println("Chrono Unit : " + localDate.minus(2, ChronoUnit.SECONDS));
        System.out.println("is Supported : " + localDate.isSupported(ChronoUnit.DAYS));

        /**
         * Additional Support Methods
         */
        System.out.println("Not a Leap Year : " + localDate.isLeapYear());
        LocalDate  localDate2 = LocalDate.of(2020,01,31);
        System.out.println("Leap Year : " +  localDate2.isLeapYear());

        System.out.println("isBefore : " + localDate1.isBefore(localDate));
        System.out.println("isAfter : " + localDate1.isAfter(localDate));
        System.out.println("isEqual : " + localDate1.isEqual(localDate));


        /**
         * Getting the values from LocalTime
         */
        System.out.println("Hours " + localTime.getHour());
        System.out.println("Second " + localTime.getSecond());
        System.out.println("Using Get " + localTime.get(ChronoField.CLOCK_HOUR_OF_DAY));
        System.out.println("toSecondOfDay : "+ localTime.toSecondOfDay());

        /**
         * Modifying the values in Local Time
         */
        System.out.println("modify hours : " + localTime.minusHours(2));
        System.out.println("modify hours using chronoUnit: " + localTime.minus(2,ChronoUnit.HOURS));
        System.out.println("modify using with : " + localTime.with(LocalTime.MIDNIGHT));
        System.out.println("modify using with chrono field: " + localTime.with(ChronoField.HOUR_OF_DAY,22));
        System.out.println("modify hours : " + localTime.plus(2, ChronoUnit.MINUTES));
        System.out.println("modify withHour : " + localTime.withHour(2));

                /**
         * Getting the Values
         */
        System.out.println("getMinute : " + localDateTime.getMinute());
        System.out.println("getHour : " + localDateTime.getHour());
        System.out.println("DAY_OF_MONTH : " + localDateTime.get(ChronoField.DAY_OF_MONTH));

        /**
         *  Modifying the values in Local Time
         */
        System.out.println("plusDays : " + localDateTime.plusDays(2));
        System.out.println("minusDays : " + localDateTime.minusDays(2));
        System.out.println("withMonth : " + localDateTime.withMonth(2));
        System.out.println("with : " + localDateTime.with(ChronoField.MONTH_OF_YEAR,3));
        System.out.println("firstDayOfNextMonth : " + localDateTime.with(TemporalAdjusters.firstDayOfNextMonth()));



        /**
         * Converting LocalDate,LocalTime to LocalDateTime and vice versa
         */

        System.out.println("atTime : "+ localDate.atTime(23,30));

        System.out.println("atTime : "+ localTime.atDate(localDate));
        System.out.println("atStartOfDay : " + localDate.atStartOfDay());

        System.out.println("toLocalDate : " + localDateTime.toLocalDate());;
        System.out.println("toLocalTime : " + localDateTime.toLocalTime());;
    }
}