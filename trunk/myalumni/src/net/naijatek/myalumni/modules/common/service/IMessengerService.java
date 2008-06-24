package net.naijatek.myalumni.modules.common.service;


import java.util.List;

import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.modules.common.domain.MessengerVO;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;

public interface IMessengerService{
	public void saveAll(List<MessengerVO> list, String memberId) throws MyAlumniException;
	public void hardDeleteAll(String memberId) throws MyAlumniException;
	public List<XlatDetailVO> getActiveMemberMessengers(String memberId);
}
