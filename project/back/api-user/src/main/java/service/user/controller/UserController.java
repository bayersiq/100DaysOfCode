package service.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.user.model.UserModel;
import service.user.repository.UserRepository;
import service.user.user.Status;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/cadastrar")
    public Status cadastraUsuario(@Valid @RequestBody UserModel param){
        try{
            List<UserModel> listUser = userRepository.findAll();
            System.out.println("novo usuario" + param.toString());
                for(UserModel user : listUser){
                    if(user.equals(param)) {
                        return Status.USER_ALREADY_EXISTS;
                    }
                }
            userRepository.save(param);
                return Status.SUCCESS;
        }catch (NoSuchElementException e) {
            return Status.FAILURE;
        }
    }
    @GetMapping
    public String teste(){
        return "ok";
    }

}
