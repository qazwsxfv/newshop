package dao;

import java.util.List;

import entity.Address;

public interface AddressDao  extends CommonDao<Address>{

	List<Address> getAddrListByUserId(int id);

}
