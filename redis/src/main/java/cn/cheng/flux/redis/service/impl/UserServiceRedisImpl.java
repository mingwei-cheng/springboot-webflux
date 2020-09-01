package cn.cheng.flux.redis.service.impl;

import cn.cheng.flux.common.pojo.User;
import cn.cheng.flux.common.service.UserService;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author Cheng Mingwei
 * @create 2020-08-31 16:52
 **/
@Service
public class UserServiceRedisImpl implements UserService {
    @Resource
    private ReactiveRedisTemplate<String, User> userRedisTemplate;

    @Override
    public Flux<User> getAllUsers() {
        Flux<String> keys = userRedisTemplate.keys("user:*");
        return keys.flatMap(key -> userRedisTemplate.opsForValue().get(key));
    }

    @Override
    public Mono<User> getOneUser(Integer id) {
        String s = genKey(id);
        return userRedisTemplate.opsForValue().get(s);
        //若ReactiveRedisTemplate为<String,Object>，需要在获取后增加转换
        //return userRedisTemplate.opsForValue().get(s).map(o->(User)o);
    }

    @Override
    public Mono<Boolean> addUser(User user) {
        //如果键不存在则新增,并返回true
        //存在则不进行操作，返回false
        return userRedisTemplate.opsForValue().setIfAbsent(genKey(user.getId()), user);
    }

    @Override
    public Mono<Boolean> updateUser(User user) {
        //如果键的值是传入的，则不做操作，并返回false
        //如果键的值不是传入的，则更新并替换，并返回true
        return userRedisTemplate.opsForValue().setIfPresent(genKey(user.getId()), user);
    }

    @Override
    public Mono<Boolean> deleteUser(Integer id) {
        return userRedisTemplate.opsForValue().delete(genKey(id));
    }

    private static String genKey(Integer id) {
        return "user:" + id;
    }
}
