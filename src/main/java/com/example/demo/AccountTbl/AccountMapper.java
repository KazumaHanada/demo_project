package com.example.demo.AccountTbl;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("select * from accounts where username = #{username} and password =#{password}")
    List<Account> findAccounts(@Param("username")String username, @Param("password")String password);
}
