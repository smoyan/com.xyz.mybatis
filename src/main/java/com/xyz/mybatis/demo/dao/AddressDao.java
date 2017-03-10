/**
 * 
 */

package com.xyz.mybatis.demo.dao;

import java.util.List;

import com.xyz.mybatis.core.annotation.Repository;
import com.xyz.mybatis.model.Address;

/**
 * @author lee.
 */
@Repository
public interface AddressDao {

  public List<Address> load();

  public boolean update(Address address);
}
