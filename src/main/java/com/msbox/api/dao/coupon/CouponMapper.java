package com.msbox.api.dao.coupon;

import com.msbox.api.model.CouponDeliver;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Description: coupon 数据仓库
 * Author: guost
 * Date: 2015-04-01 11:12
 */

@Repository
@Transactional
public interface CouponMapper {
    public String getActiveIdByBuid(Map map);

    public void saveCouponDeliver(CouponDeliver map);
    public void updateBuCoupon(Map map);

    public void updateBuCouponTotal(Map map);

    /**
     * 用来测试是否更新数据used_num
     * @param map
     * @return
     */
    public int getTotalByBuIdAndActiveId(Map map);
}
