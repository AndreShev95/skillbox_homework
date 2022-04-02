import java.text.SimpleDateFormat;
import java.util.Date;

public class RedisTest {

    // И всего на сайте зарегистрировано 20 различных пользователей
    private static final int USERS = 20;

    // Пауза между пользователями
    private static final int PAUSE = 500; // 0.5 секунды

    // Пауза между регистрацией пользователей
    private static final int PAUSE_REG = 100; // 0.1 секунды

    // Задержка между циклами
    private static final int SLEEP = 1000; // 1 секунда

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");


    private static void log(String user_id) {
        String log = String.format("[%s] На главной странице показываем пользователя: %s",
                DF.format(new Date()), user_id);
        System.out.println(log);
    }

    private static void logPayment(String user_id) {
        String log = String.format("[%s] > Пользователь %s оплатил платную услугу",
                DF.format(new Date()), user_id);
        System.out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {
        RedisStorage redis = new RedisStorage();
        redis.init();
        for (int i = 1; i <= USERS; i++){
            redis.logRegistration(String.valueOf(i));
            Thread.sleep(PAUSE_REG);
        }

        boolean cycle = true;
        while(cycle) {
            int i = 8;
            for (int j = 1; j <= USERS; j++) {
                String user = redis.getUser();
                i++;
                if (i == 10) {
                    String donater = redis.getRandomUser(USERS);
                    logPayment(donater);
                    log(donater);
                    redis.logRegistration(user);
                    Thread.sleep(PAUSE);
                    i = 0;
                }
                log(user);
                redis.logRegistration(user);
                Thread.sleep(PAUSE);
            }
            Thread.sleep(SLEEP);
        }
    }
}
