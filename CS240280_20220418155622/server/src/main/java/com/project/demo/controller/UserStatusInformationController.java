package com.project.demo.controller;

import com.project.demo.entity.UserStatusInformation;
import com.project.demo.service.UserStatusInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *用户状态信息：(UserStatusInformation)表控制层
 *
 */
@RestController
@RequestMapping("/user_status_information")
public class UserStatusInformationController extends BaseController<UserStatusInformation,UserStatusInformationService> {

    /**
     *用户状态信息对象
     */
    @Autowired
    public UserStatusInformationController(UserStatusInformationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
