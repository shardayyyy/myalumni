package net.naijatek.myalumni.modules.common.persistence.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.modules.common.domain.ReminisceVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.persistence.BaseHibernateDao;
import net.naijatek.myalumni.modules.common.persistence.iface.ReminisceDao;

public class ReminisceHibernateDao  extends BaseHibernateDao implements ReminisceDao{


    private void init(ReminisceVO reminisceVO){
    	if(!StringUtils.isBlank(reminisceVO.getAuthorId()))
    		reminisceVO.setAuthor((MemberVO) load(MemberVO.class, reminisceVO.getAuthorId()));  // @TODO MemberVO can be null
    }
    
	public void save(ReminisceVO o) {
		init(o);
		add(o);
	}

	public void hardDeleteObject(String id) throws MyAlumniException {
		try{
			hardDelete(load(ReminisceVO.class, id));
		} catch(Exception e){
			throw new MyAlumniException("Could not delete ReminisceVO because " + e.getMessage());
		}
	}

	public void softDeleteObject(String id, String lastModifiedBy) throws MyAlumniException {
		try{
			softDelete(load(ReminisceVO.class, id), lastModifiedBy);
		} catch(Exception e){
			throw new MyAlumniException("Could not delete class news because " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ReminisceVO> findAll() {
		return getHibernateTemplate().find("from ReminisceVO r order by r.status desc");
	}
        
	@SuppressWarnings("unchecked")
    public List<ReminisceVO> findAllByStatus(String status) {
		return getHibernateTemplate().findByNamedQueryAndNamedParam("reminisce.bystatus", "status", status);                
	}
        

	public ReminisceVO findById(String id) {
		return (ReminisceVO) get(ReminisceVO.class, id);
	}

	public void mergeObject(ReminisceVO o) {
	    update(o);
	}
		

}
