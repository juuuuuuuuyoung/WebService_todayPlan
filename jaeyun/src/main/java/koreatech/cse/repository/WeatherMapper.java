package koreatech.cse.repository;

import koreatech.cse.domain.rest.Weather;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherMapper {

    @Insert("INSERT INTO WEATHERS (SKY, TMAX, TMIN, USERID) VALUES (#{title}, #{tmax}, #{tmin}")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void insert(Weather weather);

    @Update("UPDATE WEATHERS SET TITLE = #{title}, TMAX = #{tmax}, TMIN = #{tmin} WHERE ID = #{id}")
    void update(Weather weather);

    @Select("SELECT * FROM WEATHERS WHERE ID = #{id}")
    Weather findOne(@Param("id") int id);

    @Delete("DELETE FROM WEATHERS WHERE ID = #{id}")
    void delete(@Param("id") int id);

    }
