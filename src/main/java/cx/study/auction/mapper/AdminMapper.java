package cx.study.auction.mapper;

import cx.study.auction.pojo.Admin;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface AdminMapper {
    Admin login(Admin admin);

    void updateLoginTime(Admin admin);
}