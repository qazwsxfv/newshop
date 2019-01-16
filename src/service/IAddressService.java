package service;

import java.util.List;

import entity.Address;

public interface IAddressService extends CommonService<Address> {

	List<Address> getAddrListByUserId(int id);

}
