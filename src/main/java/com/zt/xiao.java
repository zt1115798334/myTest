package com.zt;

import com.zt.entity.User;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhangtong on 2017/7/4.
 */
public class xiao {


    public static void main(String[] args) throws ParseException {
        List<User> users = Arrays.asList(
                new User("小米", 20),
                new User("魅族", 30),
                new User("苹果", 20),
                new User("三星", 40),
                new User("诺基亚", 30),
                new User("vivo", 20),
                new User("oppo", 10)
        );

        Map<Integer, List<User>> map = users.stream().collect(Collectors.groupingBy(User::getAge));

        for (Map.Entry<Integer, List<User>> entry : map.entrySet()) {
            Integer key = entry.getKey();
            List<User> users1 = entry.getValue();
            System.out.println("年龄为:" + key);
            users1.stream().forEach(System.out::println);
        }
    }
}
