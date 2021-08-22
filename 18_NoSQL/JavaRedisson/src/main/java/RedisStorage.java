import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.client.codec.IntegerCodec;
import org.redisson.config.Config;

import java.util.List;

import static java.lang.System.out;

public class RedisStorage {

    private RedissonClient redisson;

    private RList<Integer> usersRList;

    private final static String KEY = "users";

    void init() {
        Config config = new Config();
        config.setCodec(IntegerCodec.INSTANCE);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
            usersRList = redisson.getList(KEY);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
    }

    void addUser(Integer user) {
        usersRList.add(user);
    }

    void deleteUser(Integer user) {
        out.println(">> Пользователь " + user + " оплатил платную услугу");
        usersRList.remove(user);
    }

    void replaceUser(Integer user) {
        usersRList.addBefore(usersRList.get(0), user);
    }

    void printList() {
        List<Integer> print = redisson.getList(KEY, IntegerCodec.INSTANCE);
        for (Integer element : print) {
            System.out.println("- На главной странице показываем пользователя " + element);
        }
    }

    void delAllList() {
        usersRList.clear();
    }

    void shutdown() {
        redisson.shutdown();
    }
}





