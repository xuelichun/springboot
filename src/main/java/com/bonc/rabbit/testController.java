package com.bonc.rabbit;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 15:07 2019/1/9.
 * @Modified By:
 */
@RestController
public class testController {


    @Autowired
    private MsgProducer msgProducer;

    @RequestMapping("/send")
    public String send(){

        for (int i = 0; i <10 ; i++) {
            msgProducer.sendMsg("rabbit"+i);
        }

        return "ok";
    }

}
