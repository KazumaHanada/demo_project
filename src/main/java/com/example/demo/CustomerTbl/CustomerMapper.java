package com.example.demo.CustomerTbl;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Insert("insert into customers(company, dept, post, name, c_dept, c_post, c_name) " +
            "values (#{company}, #{dept}, #{post}, #{name}, #{c_dept}, #{c_post}, #{c_name})" )
    void insert(Customer customer);

    @Delete("delete from customers where id = #{id}")
    void delete(int id);


    @Select("select id, company, dept, post, name, c_dept, c_post, c_name from customers order by id")
    List<Customer> selectAll();
}
