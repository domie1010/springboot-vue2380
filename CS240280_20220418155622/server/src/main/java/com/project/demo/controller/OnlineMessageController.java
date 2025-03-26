package com.project.demo.controller;

import com.project.demo.entity.OnlineMessage;
import com.project.demo.service.OnlineMessageService;
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
 *在线留言：(OnlineMessage)表控制层
 *
 */
@RestController
@RequestMapping("/online_message")
public class OnlineMessageController extends BaseController<OnlineMessage,OnlineMessageService> {

    /**
     *在线留言对象
     */
    @Autowired
    public OnlineMessageController(OnlineMessageService service) {
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
