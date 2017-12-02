package koreatech.cse.repository;

import koreatech.cse.domain.Book;
import koreatech.cse.domain.Searchable;
import koreatech.cse.repository.provider.BookSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    @Insert("INSERT INTO BOOKS (TITLE, AUTHOR, PAGE, USERID) VALUES (#{title}, #{author}, #{page}, #{userId})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void insert(Book book);

    @Update("UPDATE BOOKS SET TITLE = #{title}, AUTHOR = #{author}, PAGE = #{page}, USERID = #{userId} WHERE ID = #{id}")
    void update(Book book);

    @Select("SELECT * FROM BOOKS WHERE ID = #{id}")
    Book findOne(@Param("id") int id);

    @Delete("DELETE FROM BOOKS WHERE ID = #{id}")
    void delete(@Param("id") int id);

    @SelectProvider(type = BookSqlProvider.class, method = "findAllByProvider")
    List<Book> findByProvider(Searchable searchable);

    //@formatter off
    @Select("<script>"
            + "SELECT * FROM BOOKS"
            + "<if test='orderParam != null'>ORDER BY ${orderParam} DESC</if>"
            + "</script>")
    //@formatter on
    List<Book> findByScript(Searchable searchable);
}
