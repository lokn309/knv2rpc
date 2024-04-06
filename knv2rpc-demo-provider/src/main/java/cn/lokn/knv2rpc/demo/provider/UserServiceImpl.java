package cn.lokn.knv2rpc.demo.provider;

import cn.lokn.knv2rpc.core.annotation.KNProvider;
import cn.lokn.knv2rpc.demo.User;
import cn.lokn.knv2rpc.demo.api.UserService;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: lokn
 * @date: 2024/03/31 20:20
 */
@Component
@KNProvider
public class UserServiceImpl implements UserService {
    @Override
    public User findById(int id) {
        return new User(100, "kn-v2-" + System.currentTimeMillis());
    }
}
