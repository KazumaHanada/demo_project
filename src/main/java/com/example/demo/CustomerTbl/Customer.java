package com.example.demo.CustomerTbl;

public class Customer {

    private int id;
    public void setId(int id){ this.id = id; }
    public int getId(){return id;}

    private String company;
    public void setCompany(String company){ this.company = company; }
    public String getCompany(){ return company; }

    private String dept;
    public void setDept(String dept){ this.dept = dept; }
    public String getDept(){ return dept; }

    private String post;
    public void setPost(String post){ this.post = post; }
    public String getPost(){ return post; }

    private String name;
    public void setName(String name){ this.name = name; }
    public String getName(){ return name; }


    private String c_dept;
    public void setC_dept(String c_dept){ this.c_dept = c_dept; }
    public String getC_dept(){ return c_dept; }

    private String c_post;
    public void setC_post(String c_post){ this.c_post = c_post; }
    public String getC_post(){ return c_post; }

    private String c_name;
    public void setC_name(String c_name){ this.c_name = c_name; }
    public String getC_name(){ return c_name; }
}