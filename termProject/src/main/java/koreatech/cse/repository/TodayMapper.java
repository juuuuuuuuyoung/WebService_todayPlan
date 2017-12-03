package koreatech.cse.repository;

import koreatech.cse.domain.rest.Today;
import koreatech.cse.domain.Searchable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodayMapper {
    
    @Select("SELECT ID FROM TODAY WHERE APP_KEY = #{appKey}")
    Today findWeatherFKByAppKey(@Param("appKey") String appKey);

    @Select("SELECT ID FROM TODAY WHERE USER_NAME = #{userName}")
    Today findWeatherFKByUserName(@Param("userName") String userName);

    @Select("SELECT APP_KEY FROM TODAY WHERE USER_NAME = #{userName}")
    Today findAppKeyByUserName(@Param("userName") String userName);

    @Insert("INSERT INTO TODAY (APP_KEY, CREATE_TIME) VALUES (#{appKey}, #{createTime})")

    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "appKey", before = false, resultType = int.class)
    void insert(Today today);

    @Delete("DELETE FROM TODAY WHERE ID = #{appKey}")
    void delete(@Param("appKey") int appKey);
    

    //@formatter off
//    @Select("<script>"
//            + "SELECT * FROM TODAY"
//            + "<if test='orderParam != null'>ORDER BY ${orderParam} DESC</if>"
//            + "</script>")
    //@formatter on
    List<Today> findByScript(Searchable searchable);


}
