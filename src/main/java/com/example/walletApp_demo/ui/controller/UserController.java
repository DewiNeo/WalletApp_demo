package com.example.walletApp_demo.ui.controller;

import com.example.walletApp_demo.service.iservice.IUserInterface;
import com.example.walletApp_demo.shared.dto.UserDTO;
import com.example.walletApp_demo.ui.model.request.UserRequest;
import com.example.walletApp_demo.ui.model.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    IUserInterface userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserResponse> getUsers(){
        List<UserDTO> users = userService.getListUser(); //semua list yg dpat akan di masukkin kedalam List<UserDTO>

        //setlah itu kita perlu mapping ke UserResponse
        List<UserResponse> value = new ArrayList<>();

        //kemudia kita bkin model mapper
        ModelMapper mapper = new ModelMapper();

        //setelah itu bru looping
        for (UserDTO userDTO: users){  //setiap data users saya mau mapping ke UserDTO(?)
            value.add(mapper.map(userDTO, UserResponse.class)); //didlam sini yg mlakukan mapping

        }
        return value;
    }

    @GetMapping(path = "/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})    //ini untuk get by username
    public UserResponse getUserByUserName(@PathVariable String username){  //di usernamenya hrus sama dengan path di getMapping
        UserDTO getUser = userService.getUserByUserName(username);
        if (getUser==null)
            return null;
        return new ModelMapper().map(getUser, UserResponse.class);
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse addNewUser(@RequestBody UserRequest user){
        ModelMapper mapper = new ModelMapper();

        //UseRequest -> UserDTO
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        //panggil service kita
        UserDTO createdUser = userService.addNewData(userDTO);

        //karena dia mnta user response jadi kita hrus mapping dari userDTO -> UserResponse
        UserResponse response = mapper.map(createdUser, UserResponse.class);
        //udah dapat value dari response
        // trkhir kita tunggl return response saja
        return response;
    }

}
