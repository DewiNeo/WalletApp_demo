package com.example.walletApp_demo.service.iservice;

import com.example.walletApp_demo.shared.dto.UserDTO;

import java.util.List;

public interface IUserInterface {
    List<UserDTO> getListUser();

    //get single value by username
    UserDTO getUserByUserName(String username);

    UserDTO addNewData(UserDTO user);

}
