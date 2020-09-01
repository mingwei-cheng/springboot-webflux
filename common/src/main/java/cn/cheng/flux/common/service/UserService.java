package cn.cheng.flux.common.service;

import cn.cheng.flux.common.pojo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 用户信息操作接口
 *
 * @author Chengmw
 */
public interface UserService {

    /**
     * 获取所有用户
     *
     * @return 用户列表
     */
    Flux<User> getAllUsers();

    /**
     * 通过id获取用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    Mono<User> getOneUser(Integer id);

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 是否新增成功
     */
    Mono<Boolean> addUser(User user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 是否修改成功
     */
    Mono<Boolean> updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 需要删除的用户id
     * @return 是否删除成功
     */
    Mono<Boolean> deleteUser(Integer id);
}
