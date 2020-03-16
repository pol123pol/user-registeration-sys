package com.accn.service;

import com.accn.business.bean.User;
import com.accn.dao.UserDAO;
import com.accn.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl {

    @Autowired
    private UserDAO userDAO;

    public long addUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        UserEntity newUSer = userDAO.save(userEntity);
        return newUSer.getUserId();

    }

    public User getUserDetailsbyId(long userId) {
        User user = null;
        UserEntity userEntity = userDAO.findOne(userId);
        if (userEntity != null) {
            user = new User();
            BeanUtils.copyProperties(userEntity, user);
        }
        return user;
    }

    public User deleteUser(long userId) {
        User user = null;
        UserEntity userEntity = userDAO.findOne(userId);
        if (userEntity != null) {
            userDAO.delete(userEntity);
            user = new User();
            BeanUtils.copyProperties(userEntity, user);
        }
        return user;
    }


    public User updateUser(User user) {
        User updatedUser = new User();
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        BeanUtils.copyProperties(userDAO.save(userEntity), updatedUser);
        return updatedUser;
    }

    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userDAO.findAll();
        List<User> userList = new ArrayList<User>();
        //userEntityList.forEach(userList::add);
        for (UserEntity userEntity : userEntityList) {
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            userList.add(user);
        }
        return userList;
    }
}
