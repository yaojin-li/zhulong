package com.demo.zhulong.service;

import com.demo.zhulong.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: --------------------------------------
 * @ClassName: UserRepository.java
 * @Date: 2019/9/18 20:32
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);
}