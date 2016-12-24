package com.xyz.mybatis.model;

import java.util.List;

/**
 * @author lee.
 */
public class User {

  private Integer id;
  private String username;
  private String sex;
  private String age;

  List<Address> addresses;

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  /**
   * @return the addresses
   */
  public List<Address> getAddresses() {
    return addresses;
  }

  /**
   * @param addresses the addresses to set
   */
  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

}
