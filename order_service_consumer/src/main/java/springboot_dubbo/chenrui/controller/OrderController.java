package springboot_dubbo.chenrui.controller;

import common.pojo.UserAddress;
import common.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 陈睿
 * @date 2020-03-18 17:18
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/initOrder/{userId}")
    public List<UserAddress> initOrder(@PathVariable String userId){
        return orderService.initOrder(userId);
    }
}
