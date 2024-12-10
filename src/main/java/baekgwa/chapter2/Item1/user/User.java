package baekgwa.chapter2.Item1.user;

import java.util.HashMap;
import java.util.Map;

public class User {

    private final String name;
    private final Long age;
    private final String role; //회원의 권한 default : "

    public User(String name, Long age) {
        this.name = name;
        this.age = age;
        this.role = "NONE";
    }

    public static User of(String name, long age){
        return new User(name, age);
    }

    public static User ofNoneRole(String name, long age){
        return new User(name, age);
    }

    //캐싱처리
    private static final Map<String, User> cache = new HashMap<>();
    public static User getInstance(String name, long age) {
        String key = name + ":" + age;
        if(!cache.containsKey(key)) {
            cache.put(key, new User(name, age));
        }
        return cache.get(key);
    }
}
