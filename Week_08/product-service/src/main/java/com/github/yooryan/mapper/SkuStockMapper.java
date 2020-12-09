package com.github.yooryan.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yooryan.entity.SkuStock;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-12-08
 */
public interface SkuStockMapper extends BaseMapper<SkuStock> {

    @Update("update sku_stock set stock= stock -1 where stock > 0 and product_id = #{productId}")
    int lockStock(Long productId);

}
