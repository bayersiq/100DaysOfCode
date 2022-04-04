package service.user.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import service.user.model.UserModel;
import service.user.repository.UserRepository;
import service.user.user.Status;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("user")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/cadastrar")
    public Object cadastraUsuario(@Valid @RequestBody UserModel param){
        try{
            List<UserModel> listUser = userRepository.findAll();
            System.out.println("novo usuario" + param.toString());
                for(UserModel user : listUser){
                    System.out.println("usuario regristado" + param.toString());
                    if(user.equals(param)) {
                        return Status.USER_ALREADY_EXISTS;
                    }
                }
            userRepository.save(param);
                return Status.SUCCESS;
        }catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
