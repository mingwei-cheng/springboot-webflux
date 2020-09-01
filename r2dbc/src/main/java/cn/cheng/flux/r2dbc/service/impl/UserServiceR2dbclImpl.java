package cn.cheng.flux.r2dbc.service.impl;

import cn.cheng.flux.common.pojo.User;
import cn.cheng.flux.common.service.UserService;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * @author Cheng Mingwei
 * @create 2020-08-31 16:52
 **/
@Service
public interface UserServiceR2dbclImpl extends ReactiveCrudRepository<User, Integer>, UserService {

    /**
     * 获取所有用户
     *
     * @return 用户列表
     */
    @Override
    @Query("select id,name from users")
    Flux<User> getAllUsers();

    /**
     * 通过id获取用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Override
    @Query("select id,name from users where id = :id")
    Mono<User> getOneUser(Integer id);

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 是否新增成功
     */
    @Override
    @Modifying
    @Query("insert into users (id,name) values (:#{#user.id},:#{#user.name})")
    Mono<Boolean> addUser(User user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 是否修改成功
     */
    @Override
    @Modifying
    @Query("update users set name=:#{#user.name} where id=:#{#user.id}")
    Mono<Boolean> updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 需要删除的用户id
     * @return 是否删除成功
     */
    @Override
    @Modifying
    @Query("delete from users where id=:id")
    Mono<Boolean> deleteUser(Integer id);


}
