package com.mycompany.demoproject.api;

import com.mycompany.demoproject.Business.UserBusiness;
import com.mycompany.demoproject.Exception.BaseException;
import com.mycompany.demoproject.Model.LoginModel;
import com.mycompany.demoproject.Model.RegisterModel;
import com.mycompany.demoproject.Model.RegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserApi {

    //method2
    private final UserBusiness business;
    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginModel request)throws BaseException{
        String response = business.login(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterModel request) throws BaseException {
        RegisterResponse response = business.register(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException {
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }

}
