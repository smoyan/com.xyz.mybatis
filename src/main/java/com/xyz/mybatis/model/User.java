package com.xyz.mybatis.model;

import java.util.List;

/**
 * @author lee.
 */
public class User {

  private Integer id;
  private String username;
  private String sex;
  private int age;

  List<Address> addresses;

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
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
   * @param addresses
   *          the addresses to set
   */
  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", sex=" + sex + ", age=" + age + ", addresses=" + addresses
        + "]";
  }

}
