package springboot_dubbo.chenrui.service.impl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import common.pojo.UserAddress;
import common.service.OrderService;
import common.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	@Reference
	UserService userService;
	@Override
	public List<UserAddress> initOrder(String userId) {
		// TODO Auto-generated method stub
		System.out.println("用户id："+userId);
		//1、查询用户的收货地址
		List<UserAddress> addressList = userService.getUserAddressList(userId);
		return addressList;
	}
}
