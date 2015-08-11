package iVIS;

//import com.imcode.entities.User;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

//import  static java.lang.Math.*;

/**
 * Hello world!
 */
//class Discounter {
//    static double percent; //1
//    static double coupon; //1
//    int offset = 10, base = 50; //2
//
//    public static double calc(double value) {
//        int coupon, offset, base; //3
//        if (percent < 10) { //4
//            coupon = 15;
//            offset = 20;
//            base = 10;
//        }
//        return coupon * offset * base * value / 100; //5
//    }
//
//    public static void main(String[] args) {
//        System.out.println(calc(100));
//    }
//}
interface House {
    public default String getAddress() {
        return "House";
    }
}

interface Bungalow extends House{
    public default String getAddress() {
        return "Bungalow";
    }
}

class MyHouse implements House, Bungalow {

}

class A {
    public int h = 4;

    public int getH() {
        System.out.println("A " + h);
        return h;
    }
    double m1(int a) {
        return a * 10 / 4 - 30;
    }
}

class B extends A {
    public int h = 44;
    public int getH() {
        System.out.println("B " + h);
        return h;
    }
    double m1(int a) {
        return a * 10 / 4.0;
    }
}

class App {
    static int x = 10;

    //    @SuppressWarnings("fallthrough")
    public void m1(String... args) {

    }
    public static void main(String[] args) {
        boolean b = false,x;
        boolean[] ab = new boolean[1];
        x = (ab[0] = b);
//        LocalDateTime ldt = LocalDateTime.of(2015, 10, 1, 10, 10);
//        System.out.println(ldt);
//        System.out.println((char)10);
//        System.out.println(new MyHouse().getAddress());
//        A a = new B();

//        System.out.println((a).h + " " + a.getH());
//        B b = (B) a;
//        System.out.println(b.h + " " + b.getH());
//int[] array1[] = {{123, 124}};
//
////        printMonth(args);
////        int[][] a = new int[0][3];
////        String s = "Java";
////        s.concat(" SE 6");
////        System.out.println(s.replace('6', '7'));
//       jil: System.out.println(Boolean.parseBoolean("tRue"));
//        return 10L;
////        for (int x = 0; x < 5; x++) {
////            System.out.print(x);
//        }
//        System.out.println(x);
//        User user = new User();
//        System.out.println( "Hello World!" );
//        for (BigDecimal i = new BigDecimal(0), border = new BigDecimal(10), step = BigDecimal.valueOf(.1); i.compareTo(border) < 0; i = i.add(step)) {
//            System.out.println(i);
//        }
//        Scanner in = new Scanner(System.in);
////        System.out.print("input number: ");
////        int value = in.nextInt();
//
//        SizeUnits value = SizeUnits.GRAD;
////        String value = "234";
//
////        switch (value) {
////            case "234":
////                System.out.println("1");
////            case "123222":
////                System.out.println(2);
////            default:
////                System.out.println("default");
////
////        }
//        switch (value) {
//            case CM:
//                System.out.println(;
//            case DEG:
//                System.out.println(2);
//            case GRAD:
//                System.out.println(3);
//            default:
//                System.out.println("default");

//        }
    }

//    public static void printMonth(String... args) {
//        Locale locale = Locale.CANADA;
//        Locale.setDefault(locale);
//        GregorianCalendar calendar = new GregorianCalendar();
//        int today = calendar.get(Calendar.DAY_OF_MONTH);
//        int month = calendar.get(Calendar.MONTH);
//        int firstDayOfWeek = calendar.getFirstDayOfWeek();
//
//        String[] weakdays = new DateFormatSymbols().getShortWeekdays();
//
////        int dayOfWeek = firstDayOfWeek;
////
////        do {
////            System.out.printf("%4s", weakdays[dayOfWeek]);
////            dayOfWeek++;
////            dayOfWeek %= 8;
////        } while (dayOfWeek != firstDayOfWeek);
//
//        for (int i = firstDayOfWeek; i < weakdays.length; i++) {
//            System.out.printf("%4s", weakdays[i]);
//        }
//
//        System.out.println();
//
//    }
}
