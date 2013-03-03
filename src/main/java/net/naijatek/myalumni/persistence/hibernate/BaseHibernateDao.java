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



import java.io.Serializable;

import java.util.Date;
import java.util.List;

import net.naijatek.myalumni.entity.MyAlumniBaseVO;
import net.naijatek.myalumni.util.BaseConstants;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Used for basic CRUD operations accross the entire application
 *
 */
@Repository
@Transactional
public class BaseHibernateDao extends HibernateDaoSupport {

	public static int BATCH_SIZE = 20;
	
	public void add(Object o){
		
		if(o instanceof MyAlumniBaseVO){
			MyAlumniBaseVO obj = (MyAlumniBaseVO) o;
			obj.setLastModification(BaseConstants.ADDED);
			obj.setLastModifiedDate(new Date());					
		}
		
		getHibernateTemplate().persist(o);
		getHibernateTemplate().flush();
		getHibernateTemplate().evict(o);
		
	}
	
	/**
	 * Use this to add objects in batches
	 * @param list
	 */
	public void batchAdd(List list){
		Object o;
		for(int i=0; i<list.size(); i++){
			o = list.get(i);

			
			if(o instanceof MyAlumniBaseVO){
				MyAlumniBaseVO obj = (MyAlumniBaseVO) o;
				obj.setLastModification(BaseConstants.ADDED);
				obj.setLastModifiedDate(new Date());					
			}
			
			getHibernateTemplate().persist(o); //save!!!
			
			if(i%BATCH_SIZE == 0){
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
		
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
		
		for(int i=0; i<list.size(); i++){
			getHibernateTemplate().evict(list.get(i));
		}
	}
	
	
	/**
	 * Use this to update objects in batches
	 * @param list
	 */
	public void batchUpdate(List list){
		Object o;
		for(int i=0; i<list.size(); i++){
			o = list.get(i);

			
			if(o instanceof MyAlumniBaseVO){
				MyAlumniBaseVO obj = (MyAlumniBaseVO) o;
				obj.setLastModification(BaseConstants.UPDATED);
				obj.setLastModifiedDate(new Date());					
			}
			
			getHibernateTemplate().update(o); //update!!!
			
			if(i%BATCH_SIZE == 0){
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
		
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
		
		for(int i=0; i<list.size(); i++){
			getHibernateTemplate().evict(list.get(i));
		}
	}

	
	public void update(Object o){
		           
		if(o instanceof MyAlumniBaseVO){
			MyAlumniBaseVO obj = (MyAlumniBaseVO) o;
			obj.setLastModification(BaseConstants.UPDATED);
			obj.setLastModifiedDate(new Date());					
		}
		
		getHibernateTemplate().update(o);
		getHibernateTemplate().flush();
		getHibernateTemplate().evict(o);
	}
	
	public void softDelete(Object o, String lastModifiedBy){
		if(o instanceof MyAlumniBaseVO){
			MyAlumniBaseVO obj = (MyAlumniBaseVO) o;
			obj.setLastModification(BaseConstants.SOFT_DELETED);
			obj.setLastModifiedBy(lastModifiedBy);
			obj.setLastModifiedDate(new Date());					
		}
		
		getHibernateTemplate().update(o);
		getHibernateTemplate().flush();
		getHibernateTemplate().evict(o);
	}
	
	public Object get(Class clazz, Serializable id){
		return getHibernateTemplate().get(clazz, id);
	}
	
	public Object load(Class clazz, Serializable id){
		return getHibernateTemplate().load(clazz, id);
	}
	
	public void hardDelete(Object o){
		getHibernateTemplate().delete(o);
	}
	
}
