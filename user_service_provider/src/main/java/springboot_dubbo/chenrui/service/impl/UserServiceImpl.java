package springboot_dubbo.chenrui.service.impl;


import common.pojo.UserAddress;
import common.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@com.alibaba.dubbo.config.annotation.Service  //暴露服务
@Service("userService")
public class UserServiceImpl implements UserService {

	public List<UserAddress> getUserAddressList(String userId) {
		UserAddress address1 = new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y");
		UserAddress address2 = new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N");
		return Arrays.asList(address1,address2);
	}
}
