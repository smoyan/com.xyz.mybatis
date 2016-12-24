/**
 * 
 */

package com.xyz.mybatis.demo.dao;

import com.xyz.mybatis.core.annotation.Repository;
import com.xyz.mybatis.model.Address;

import java.util.List;

/**
 * @author lee.
 */
@Repository
public interface AddressDao {

  public List<Address> load();
  
  public int update(Address address);
}
