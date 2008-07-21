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
package net.naijatek.myalumni.modules.common.service;

import java.io.Serializable;
import java.util.List;

import net.naijatek.myalumni.framework.exceptions.MyAlumniException;
import net.naijatek.myalumni.framework.struts.MyAlumniBaseException;


/**
 * An interface shared by all business data access objects.
 * <p>
 * All CRUD (create, read, update, delete) basic data access operations are
 * isolated in this interface and shared accross all DAO implementations.
 *
 * @author Folashade Adeyosoye
 */
public interface BaseCrudService<T, ID extends Serializable> {

	/**
	 * Save an entity to the datastore.
	 * @param entity
	 */
	public void save(T entity) throws MyAlumniBaseException;
	
	/**
	 * Update an entity
	 * @param entity
	 */
	public void merge(T entity);
	
	/**
	 * Return all entities of this type belong to the organization
	 * @return
	 */
	public List<T> findAll();

	
	/**
	 * Soft Delete an entity from the datastore.
	 * @param id The id of the entity to delete
	 * @throws AdminException
	 */
	public void softDelete(ID id, String lastModifiedBy) throws MyAlumniException;
	
	
	/**
	 * Hard Delete an entity from the datastore.
	 * @param id The id of the entity to delete
	 * @throws AdminException
	 */
	public void hardDelete(ID id) throws MyAlumniException;
	
	
	/**
	 * Retrieve an entity with the specified id from the datastore.
	 * @param id
	 * @return
	 */
	public T findById(ID id);
        

	/**
	 * Return all entities of this type belong to the organization and that are active
	 * @param status The organization 
	 * @return
	 */
	public List<T> findAllByStatus(String status);       
}
