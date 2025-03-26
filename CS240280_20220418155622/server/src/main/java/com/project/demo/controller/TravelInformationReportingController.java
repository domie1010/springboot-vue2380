package com.project.demo.controller;

import com.project.demo.entity.TravelInformationReporting;
import com.project.demo.service.TravelInformationReportingService;
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
 *行程信息上报：(TravelInformationReporting)表控制层
 *
 */
@RestController
@RequestMapping("/travel_information_reporting")
public class TravelInformationReportingController extends BaseController<TravelInformationReporting,TravelInformationReportingService> {

    /**
     *行程信息上报对象
     */
    @Autowired
    public TravelInformationReportingController(TravelInformationReportingService service) {
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
