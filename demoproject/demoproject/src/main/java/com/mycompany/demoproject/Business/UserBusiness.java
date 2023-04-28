package com.mycompany.demoproject.Business;

import com.mycompany.demoproject.Entity.User;
import com.mycompany.demoproject.Exception.BaseException;
import com.mycompany.demoproject.Exception.FileException;
import com.mycompany.demoproject.Exception.UserException;
import com.mycompany.demoproject.Mapper.UserMapper;
import com.mycompany.demoproject.Model.LoginModel;
import com.mycompany.demoproject.Model.RegisterModel;
import com.mycompany.demoproject.Model.RegisterResponse;
import com.mycompany.demoproject.Service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    
    public String login(LoginModel request)throws BaseException{
        //validate request
        
        //verify database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if(opt.isEmpty()){
            //throw login fail, email not found
            throw UserException.loginFailEmailNotFound();
        }
        User user = opt.get();
        if(!userService.matchPassword(request.getPassword(),user.getPassword())){
            //throw login fail pass incorrect
            throw UserException.loginFailPasswordIncorrect();

        }
        //TODO generate JWT
        String token = "JWT TO DO";
        return token;
    }
    public RegisterResponse register(RegisterModel request)throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        //TODO: mapper
        RegisterResponse model = userMapper.toRegisterResponse(user);

        return model;
    }


    public String uploadProfilePicture(MultipartFile file) throws BaseException {
        //validate file
        if(file == null){
            //throw error
           throw FileException.fileNull();
        }
        //validate size
        if(file.getSize() > 10048576 * 2){
            //throw error
            throw FileException.fileMaxSize();
        }
        String contentType = file.getContentType();
        if(contentType == null){
            //throw error
            throw FileException.unsupported();
        }
        List<String> supportedType = Arrays.asList("image/jpeg","image/png");
        if(supportedType.contains(contentType)){
            //throw error (unsupport)
            throw FileException.unsupported();
        }
        //TODO: upload file FIle Storage(AWS S3,etc...)
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return"";
    }
}
