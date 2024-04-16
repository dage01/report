package com.dage.suju.Service;

import com.dage.suju.DAO.UserDao;
import com.dage.suju.DTO.ListDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao Userdao;


    public ListDto mod_list(@Param("seq")String seq){
        return Userdao.mod_list(seq);
    }

    public ListDto mod_list_dt(@Param("seq")String seq){
        return Userdao.mod_list_dt(seq);
    }





    public List<ListDto> file_list(@Param("seq")String seq){
        return Userdao.file_list(seq);
    }


    public ListDto getFileInfo(@Param("seq")String seq){
        return Userdao.getFileInfo(seq);
    }




}
