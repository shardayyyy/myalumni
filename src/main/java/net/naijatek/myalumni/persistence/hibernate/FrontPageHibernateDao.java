/*
 * ====================================================================
 * Copyright (C) 1997-2008 by Naijatek.com
 *
 * All copyright notices regarding MyAlumni MUST remain 
 * intact in the scripts and in the outputted HTML.
 * The "powered by" text/logo with a link back to
 * http://www.naijatek.com in 
 * the footer of the pages MUST remain visible when the pages
 * are viewed on the internet or intranet.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Support can be obtained from support forums at:
 * http://www.naijatek.com/myalumni/forum
 *
 * Correspondence and Marketing Questions can be sent to:
 * info at naijatek com
 *
 * <p>Title: MyAlumni </p>
 * <p>Description: This system helps keep alive the line of communications between alumni/alumnus</p>
 * <p>Copyright: Copyright (c) 1997-2008</p>
 * <p>Company: Naijatek Solutions (http://www.naijatek.com)</p>
 * @author Folashade Adeyosoye (shardayyy@naijatek.com)
 * @version 1.0
 */
package net.naijatek.myalumni.persistence.hibernate;

import java.util.List;

import net.naijatek.myalumni.controller.exceptions.MyAlumniException;
import net.naijatek.myalumni.entity.FrontPageVO;
import net.naijatek.myalumni.persistence.hibernate.iface.FrontPageDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
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
		return getHibernateTemplate().find("from FrontPageVO r order by r.status asc");	
	}
        
	@SuppressWarnings("unchecked")
    public List<FrontPageVO> findAllByStatus(String status) {
		return getHibernateTemplate().findByNamedQueryAndNamedParam("frontpage.bystatus", "status", status);    
		//return getHibernateTemplate().find("from FrontPageVO r order by r.status asc");             
	}
        

	public FrontPageVO findById(String id) {
		return (FrontPageVO) get(FrontPageVO.class, id);
	}

	public void mergeObject(FrontPageVO o) {
	    update(o);
	}
		
}
