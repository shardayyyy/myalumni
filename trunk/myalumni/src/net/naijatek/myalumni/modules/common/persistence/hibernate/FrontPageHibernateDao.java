package net.naijatek.myalumni.modules.common.persistence.hibernate;

import java.util.List;

import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.modules.common.domain.FrontPageVO;
import net.naijatek.myalumni.modules.common.domain.ScrollVO;
import net.naijatek.myalumni.modules.common.persistence.BaseHibernateDao;
import net.naijatek.myalumni.modules.common.persistence.iface.FrontPageDao;


public class FrontPageHibernateDao extends BaseHibernateDao implements FrontPageDao {


	public void save(FrontPageVO o) {
		add(o);
	}

	public void hardDeleteObject(String id) throws MyAlumniException {
		try{
			hardDelete(load(FrontPageVO.class, id));
		} catch(Exception e){
			throw new MyAlumniException("Could not delete FrontPageVO because " + e.getMessage());
		}
	}

	public void softDeleteObject(String id, String lastModifiedBy) throws MyAlumniException {
		try{
			softDelete(load(FrontPageVO.class, id), lastModifiedBy);
		} catch(Exception e){
			throw new MyAlumniException("Could not delete front page link because " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FrontPageVO> findAll() {
   		//return getHibernateTemplate().find("from FrontPageVO");
		//FrontPageVO fp = new FrontPageVO();
		return getSession().createQuery("select from FrontPageVO r order by r.status desc")
		.list();	
	}
        
	@SuppressWarnings("unchecked")
    public List<FrontPageVO> findAllByStatus(String status) {
		return getHibernateTemplate().find("from FrontPageVO r order by r.status desc");             
	}
        

	public FrontPageVO findById(String id) {
		return (FrontPageVO) get(FrontPageVO.class, id);
	}

	public void mergeObject(FrontPageVO o) {
	    update(o);
	}
		
}
