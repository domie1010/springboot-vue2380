package com.project.demo.controller;

import com.project.demo.entity.EpidemicData;
import com.project.demo.service.EpidemicDataService;
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
 *疫情数据：(EpidemicData)表控制层
 *
 */
@RestController
@RequestMapping("/epidemic_data")
public class EpidemicDataController extends BaseController<EpidemicData,EpidemicDataService> {

    /**
     *疫情数据对象
     */
    @Autowired
    public EpidemicDataController(EpidemicDataService service) {
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
