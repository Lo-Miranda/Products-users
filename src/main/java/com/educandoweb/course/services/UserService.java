package com.educandoweb.course.services;


import com.educandoweb.course.controllers.exceptions.DatabaseException;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRespository userRespository;

    public List<User> findAll(){
        return userRespository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = userRespository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj){
        return userRespository.save(obj);
    }

    public void delete(Long id){
        try {
            userRespository.deleteById(id);
        } catch (EmptyResultDataAccessException e ){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj){
        try {
            User entity = userRespository.getReferenceById(id);
            updateData(entity, obj);
            return userRespository.save(entity);
        } catch (EntityNotFoundException e ){
            e.printStackTrace();
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(User entity, User obj){
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }


}
