package koreatech.cse.repository;

import koreatech.cse.domain.rest.Area;
import koreatech.cse.domain.Searchable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaMapper {
    
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void insert(Area area);
    
    @Select("SELECT FULL_CD FROM AREAS WHERE KOR_NM = #{korNm}")
    String findOneFullCD(@Param("korNm") String korNm);


    //@formatter off
    @Select("<script>"
            + "SELECT * FROM AREAS"
            + "<if test='orderParam != null'>ORDER BY ${orderParam} DESC</if>"
            + "</script>")
    //@formatter on
    List<Area> findByScript(Searchable searchable);
}
