package net.naijatek.myalumni.modules.common.service.impl;

import java.util.List;

import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.modules.common.domain.FrontPageVO;
import net.naijatek.myalumni.modules.common.persistence.iface.FrontPageDao;
import net.naijatek.myalumni.modules.common.service.IFrontPageService;

public class FrontPageServiceImpl implements IFrontPageService {

    private FrontPageDao FrontPageDao;

    public FrontPageServiceImpl(FrontPageDao FrontPageDao) {
        this.FrontPageDao = FrontPageDao;
    }
    
	public List<FrontPageVO> findAll() {
		return FrontPageDao.findAll();
	}

	public List<FrontPageVO> findAllByStatus(String status) {
		return FrontPageDao.findAllByStatus(status);
	}

	public FrontPageVO findById(String id) {
		return FrontPageDao.findById(id);
	}

	public void hardDelete(String id) throws MyAlumniException {
		FrontPageDao.hardDeleteObject(id);
	}

	public void merge(FrontPageVO entity) {
		FrontPageDao.mergeObject(entity);
	}

	public void save(FrontPageVO entity) {
		FrontPageDao.save(entity);
	}

	public void softDelete(String id, String lastModifiedBy) throws MyAlumniException {
		FrontPageDao.softDeleteObject(id, lastModifiedBy);
	}
}
