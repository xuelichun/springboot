package com.bonc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 10:38 2018/11/28.
 * @Modified By:
 */
    @Api(tags ="模版", description = "Freemarker")
    @Controller
    @RequestMapping("/home")
public class FreemarkerTest {


        @PostMapping("/freemarker")
        @ApiOperation("返回freemarker数据")
       // @ResponseBody
        public String Freemarker(Model model){
            List list=new ArrayList();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            list.add("f");
            Map map=new HashMap();
            model.addAttribute("list",list);

            return "freemarker";
        }
}
