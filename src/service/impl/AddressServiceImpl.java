package service.impl;

import java.util.List;

import dao.AddressDao;
import dao.impl.AddressDaoImpl;
import entity.Address;
import service.IAddressService;

public class AddressServiceImpl extends CommonServiceImpl<Address> implements IAddressService {
	private AddressDao dao=new AddressDaoImpl();
	@Override
	public List<Address> getAddrListByUserId(int id) {
		// TODO Auto-generated method stub
		return dao.getAddrListByUserId(id);
	}

}
