package com.github.yooryan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yooryan.entity.Order;
import com.github.yooryan.mapper.OrderMapper;
import com.github.yooryan.service.IOrderService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-12-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    public static void main(String[] args) {
        String dateStr = "Tue Oct 15 00:00:00 CST 2020";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        try {
            final LocalDate localDate = simpleDateFormat.parse(dateStr).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            System.out.println(localDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
