import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random(1);
        RedisStorage redisStorage = new RedisStorage();
        redisStorage.init();

        for (int i = 1; i <= 30; i++) {
            redisStorage.addUser(i);
            if (i % 10 == 0) {
                int rnd = random.nextInt(i);
                redisStorage.deleteUser(rnd);
                redisStorage.replaceUser(rnd);
                Thread.sleep(1000);
                redisStorage.printList();
            }

        }
        redisStorage.delAllList();
        redisStorage.shutdown();
    }
}
