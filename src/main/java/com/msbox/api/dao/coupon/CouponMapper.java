package com.msbox.api.dao.coupon;

import com.msbox.api.model.CouponDeliver;
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
//    @Select("select active_id from bu_coupons bc where bc.bu_id=#{buid} and bc")
//    public List<String> getActiveIdByBuidAndBuco(String buid,String buco);
//    @Select("select bu_key from ms_business_unit_extra_info where business_unit_id = #{bu_id}")
//    public String getSignByBuId(String bu_id);

    public void saveCouponDeliver(CouponDeliver map);
    public void updateBuCoupon(Map map);

    public String getCouponIdByBuIdAndActiveId(java.util.Map map);
}
