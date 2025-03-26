package com.project.demo.controller;

import com.project.demo.entity.InformationReporting;
import com.project.demo.service.InformationReportingService;
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
 *上报信息情况：(InformationReporting)表控制层
 *
 */
@RestController
@RequestMapping("/information_reporting")
public class InformationReportingController extends BaseController<InformationReporting,InformationReportingService> {

    /**
     *上报信息情况对象
     */
    @Autowired
    public InformationReportingController(InformationReportingService service) {
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
