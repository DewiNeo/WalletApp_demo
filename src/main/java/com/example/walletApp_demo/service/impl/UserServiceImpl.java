package com.example.walletApp_demo.service.impl;

import com.example.walletApp_demo.io.entity.UserEntity;
import com.example.walletApp_demo.io.irepository.UserRepository;
import com.example.walletApp_demo.service.iservice.IUserInterface;
import com.example.walletApp_demo.shared.dto.UserDTO;
import com.example.walletApp_demo.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Service       //gbleh lupa
public class UserServiceImpl implements IUserInterface {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GenerateRandomPublicId generateRandomPublicId;


    @Override
//    public List<UserDTO> getListUser() {
//        return null;
//    }
    //bgian mau get user semuaa
    public List<UserDTO> getListUser() {
        List<UserDTO> value = new ArrayList<>();
        ModelMapper mapper = new ModelMapper(); //1object bsa di pake bbrp x
        //mau mendapatkan semua data dari database ktia (usernya saiapa saja gtuu)
        //set all users from database
        List<UserEntity> users = userRepository.findAll();

        //kita perlu Mapping untuk
        //List<UserEntity> --> List<UserDTO>
        //karena dia multi value maka kita harus looping
        for (UserEntity userEntity : users){    //mksdnya setiap data users setiap data akan mapping ke single object single entity
            value.add(mapper.map(userEntity, UserDTO.class));
        }

        return value;
    }

    @Override
    public UserDTO getUserByUserName(String username) {
        UserEntity getUser = userRepository.findByUsername(username);//service akanmengrequest di dalam repository sesuai
                                        // dengan nama yang kita mnta apakah dia ada atau enga namanay didalam database
        if (getUser == null) {  //jika user tidak dikembalikan maka return null
            return null;
        }
        //jika usernya ditemukan maka
        return new ModelMapper().map(getUser, UserDTO.class); //map -> sourcenya dari mana (GetUser -> UserDTO.class)
    }

    @Override
    public UserDTO addNewData(UserDTO user) {
        user.setUserId(generateRandomPublicId.generateUserId(30));  //generate userID

        ModelMapper mapper = new ModelMapper(); //digunakan untuk mengtransfer data dari service ke repository

        //UserDTO -> user Entity
        UserEntity entity = mapper.map(user, UserEntity.class);
        //buat store data
        UserEntity storedData = userRepository.save(entity);

        //Mapping dari UserEntity kembali ke UserDTO (UserEntity -> UserDTO) //untuk return value
        UserDTO value = mapper.map(storedData, UserDTO.class);

        return value;
    }
}
