package cn.cheng.flux.common.controller;

import cn.cheng.flux.common.pojo.User;
import cn.cheng.flux.common.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author Cheng Mingwei
 * @create 2020-08-31 16:21
 **/
@RestController
@RequestMapping("/user")
public class UserFluxController {

    @Resource
    private UserService userService;

    @GetMapping("/getAll")
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get")
    public Mono<User> getUser(@RequestParam("id") Integer id) {
        return userService.getOneUser(id);
    }

    @PostMapping("/add")
    public Mono<Boolean> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/update")
    public Mono<Boolean> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete")
    public Mono<Boolean> deleteUser(@RequestParam("id") Integer id) {
        return userService.deleteUser(id);

    }


}
