package cn.cheng.flux.common.service;

import cn.cheng.flux.common.pojo.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Cheng Mingwei
 * @create 2020-08-31 16:52
 **/
@Service
public class UserServiceImpl implements UserService {
    List<User> allUser = new ArrayList<>(Arrays.asList(
            new User("jack", 1),
            new User("rose", 2),
            new User("marry", 3),
            new User("tom", 4),
            new User("jerry", 5)
    ));

    @Override
    public Flux<User> getAllUsers() {
        return Flux.fromIterable(allUser);
    }

    @Override
    public Mono<User> getOneUser(Integer id) {
        User user = getUser(id);
        return Mono.just(user == null ? new User() : user);
    }

    @Override
    public Mono<Boolean> addUser(User user) {
        User userFromList = getUser(user.getId());
        if (userFromList != null) {
            return Mono.just(true);
        }
        allUser.add(user);
        return Mono.just(false);
    }

    @Override
    public Mono<Boolean> updateUser(User user) {
        User userFromList = getUser(user.getId());
        if (userFromList == null) {
            return Mono.just(false);
        } else {
            for (User userCache : allUser) {
                if (userCache.getId() == user.getId()) {
                    allUser.remove(userCache);
                    allUser.add(user);
                }
            }
            return Mono.just(true);
        }
    }

    @Override
    public Mono<Boolean> deleteUser(Integer id) {
        boolean delete = allUser.removeIf(userCache -> userCache.getId() == id);
        return Mono.just(delete);
    }

    public User getUser(Integer id) {
        for (User user : allUser) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

}
