package com.example.demo.service;

import com.example.demo.bean.LoginRequestBean;
import com.example.demo.bean.RegisterRequestBean;
import com.example.demo.bean.ResponseBean;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepo;
import com.example.demo.util.MessageVarList;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created By Heshan
 * Created Date 6/6/2020
 */
@Service
public class UserService {

    static final long ONE_MINUTE_IN_MILLIS=60000;

    @Autowired
    UserRepo userRepo;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.salt}")
    private String salt;

    public ResponseBean registerUser(RegisterRequestBean registerRequestBean) throws Exception{

        ResponseBean responseBean = new ResponseBean();
        UserEntity alreadyUser = userRepo.findByUsername(registerRequestBean.getUsername());
        if(alreadyUser==null) {
            String passwordWithSalt = registerRequestBean.getPassword() + salt;
            String bytesOfPassword = String.valueOf(Math.abs(passwordWithSalt.hashCode()));

            UserEntity userEntity = new UserEntity(registerRequestBean.getUsername(),
                    bytesOfPassword,
                    registerRequestBean.getFirstname(),
                    registerRequestBean.getMiddlename(),
                    registerRequestBean.getLastname(),
                    registerRequestBean.getCountry());

            UserEntity responseEntity = userRepo.save(userEntity);

            responseBean.setResponseCode(MessageVarList.RES_SUCCESS);
            responseBean.setResponseMsg(MessageVarList.RES_SUCCESS_Msg);
            responseBean.setContent(responseEntity);
        }
        else{
            responseBean.setResponseCode(MessageVarList.RES_ERROR);
            responseBean.setResponseMsg(MessageVarList.RES_ALREADY_Msg);
            responseBean.setContent(null);
        }

        return responseBean;
    }

    public ResponseBean loginUser(LoginRequestBean loginRequestBean) throws Exception{

        ResponseBean responseBean = new ResponseBean();
        UserEntity userEntity = userRepo.findByUsername(loginRequestBean.getUsername());

        if(userEntity!=null){

            String savePassword = userEntity.getPassword();

            String passwordWithSalt = loginRequestBean.getPassword() + salt;
            String bytesOfPassword =  String.valueOf(Math.abs(passwordWithSalt.hashCode()));


            if(bytesOfPassword.equals(savePassword)){
                String jwt = this.getJWT(userEntity.getUsername());
                HashMap<String, String> content = new HashMap<>();
                content.put("token", jwt);

                responseBean.setResponseCode(MessageVarList.RES_SUCCESS);
                responseBean.setResponseMsg(jwt);
                responseBean.setContent(content);
            }
            else{
               responseBean.setResponseCode(MessageVarList.RES_ERROR);
               responseBean.setResponseMsg(MessageVarList.BAD_CREDENTIAL);
            }
        }
        else {
            responseBean.setResponseCode(MessageVarList.RES_ERROR);
            responseBean.setResponseMsg(MessageVarList.BAD_CREDENTIAL);
        }
        return responseBean;
    }

    private String getJWT(String username) throws Exception{

        Calendar date = Calendar.getInstance();
        long t= date.getTimeInMillis();
        Date afterAddingTousandMins = new Date(t + ONE_MINUTE_IN_MILLIS);

        String token = Jwts.builder()
                .setSubject("VALIDATION_TOKEN")
                .setExpiration(afterAddingTousandMins)
                .claim("username", username)
                .signWith(
                        SignatureAlgorithm.HS256,
                        secret.getBytes("UTF-8")
                )
                .compact();

        return token;
    }
}
