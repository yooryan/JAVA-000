package com.github.yooryan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.yooryan.entity.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-12-06
 */
public interface IOrderService extends IService<Order> {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,6};//定义一个数组存放指定元素

        int sum = arr[0];//假设第一个元素是最大值
        Map<Integer, Integer> map = new HashMap<>(64);
        //for循环遍历数组中元素，每次循环跟数组索引为0的元素比较大小
        for (int i = 0; i < arr.length; i++){
            if (sum < arr[i]){//数组中的元素跟sum比较，比sum大就把它赋值给sum作为新的比较值
                sum = arr[i];
            }
            final int i1 = arr[i];
            map.merge(i1, 1, Integer::sum);
        }
        System.out.println(sum);//输出数组中的最大值
    }
}
