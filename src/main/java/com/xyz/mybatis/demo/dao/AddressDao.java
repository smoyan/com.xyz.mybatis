/**
 * 
 */

package com.xyz.mybatis.demo.dao;

import com.xyz.mybatis.core.annotation.Repository;
import com.xyz.mybatis.core.base.Dao;
import com.xyz.mybatis.model.Address;

import java.util.List;

/**
 * @author lee.
 */
@Repository
public interface AddressDao extends Dao<Address> {

  public List<Address> load();

  public boolean update(Address address);
}
