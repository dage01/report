package com.dage.suju.DAO;

import com.dage.suju.DTO.ListDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {


    ListDto mod_list(@Param("seq")String seq);

    ListDto mod_list_dt(@Param("seq")String seq);


    List<ListDto> file_list(@Param("seq")String seq);

    ListDto getFileInfo(@Param("seq")String seq);
}
