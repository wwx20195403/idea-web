import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Hello {

    public static void main(String[] args) {


        System.out.println("你");
        System.out.println("好");
        System.out.println("啊");
        System.out.println("啊");

        System.out.println("啊");
        System.out.println("啊");
        showtime();
        showtime();
        showtime();

    }

    public static void showtime() {
        System.out.println("saw");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));
    }
}
