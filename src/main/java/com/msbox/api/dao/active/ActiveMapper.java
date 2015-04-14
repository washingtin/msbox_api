package com.msbox.api.dao.active;

import com.msbox.api.model.Active;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guost on 15-3-31.
 */
@Repository
public interface ActiveMapper {
    @Select("select c_bu_id from ms_business_unit_extra_info where business_unit_id = #{bu_id}")
    public String getCbuidByBuId(String bu_id);
    @Select("select bu_key from ms_business_unit_extra_info where business_unit_id = #{bu_id}")
    public String getSignByBuId(String bu_id);
    @Select("select id from ms_bussiness_unit mb where mb.parent_id=#{bu_id} and mb.code=#{bu_code}")
    public List<Integer> getActiveByBuIdAndBucode(String bu_id,String bu_code);
    @Select("select mobile from ms_user where user_id=#{uid}")
    public String getMobileByUid(int uid);
}
