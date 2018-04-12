/**
 * 
 */

package com.xyz.mybatis.demo.service.impl;

import java.util.List;

import com.xyz.mybatis.core.annotation.Transactional;
import com.xyz.mybatis.demo.service.AddressService;
import com.xyz.mybatis.model.Address;

/**
 * @author lee
 *
 */
public class AddressServiceImpl implements AddressService {

  @Override
  @Transactional
  public List<Address> load() {
    return null;
  }

}
