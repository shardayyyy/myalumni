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
package net.naijatek.myalumni.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.entity.ReminisceVO;
import net.naijatek.myalumni.entity.MemberVO;
import net.naijatek.myalumni.controller.helper.MyAlumniMessage;
import net.naijatek.myalumni.controller.helper.MyAlumniMessages;
import net.naijatek.myalumni.service.IReminisceService;
import net.naijatek.myalumni.util.BaseConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReminisceController extends MyAlumniBaseController {

	@Autowired
	private IReminisceService reminisceService;

	private static Log logger = LogFactory
			.getLog(ReminisceController.class);

	/*
	 * public MaintainReminisceAction(final IReminisceService reminisceService)
	 * { this.reminisceService = reminisceService; }
	 */

    @RequestMapping(value="/listReminisce", method= RequestMethod.POST)
    public ModelAndView listReminisce(@ModelAttribute("reminisce")ReminisceVO reminisceVO, BindingResult result, SessionStatus status,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response) throws
            Exception {
		logger.debug("in listReminisce");

		List<ReminisceVO> list = reminisceService
				.findAllByStatus(BaseConstants.ACTIVE);
		setRequestObject(request, BaseConstants.LIST_OF_REMINISCE, list);
		return new ModelAndView(BaseConstants.FWD_SUCCESS);
	}

    @RequestMapping(value="/prepareAddReminisce", method= RequestMethod.POST)
    public ModelAndView prepareAddReminisce(@ModelAttribute("reminisce")ReminisceVO reminisceVO, BindingResult result, SessionStatus status,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws
            Exception {
		logger.debug("in prepareAddReminisce");

		MemberVO token = getCurrentLoggedInUser(request);

		// check to see if the user logged on is a member
		if (!memberSecurityCheck(request, token)) {
			return new ModelAndView(BaseConstants.FWD_LOGIN);
		}

        reminisceVO.setFromYear(String.valueOf(token.getYearIn()));
        reminisceVO.setToYear(String.valueOf(token.getYearOut()));

		return new ModelAndView(BaseConstants.FWD_SUCCESS);
	}

    @RequestMapping(value="/addReminisce", method= RequestMethod.POST)
    public ModelAndView addReminisce(@ModelAttribute("reminisce")ReminisceVO reminisceVO, BindingResult result, SessionStatus status,
                                            HttpServletRequest request,
                                            HttpServletResponse response) throws
            Exception {

		logger.debug("in prepareAddReminisce");

//		if (isCancelled(request)) {
//			return new ModelAndView(BaseConstants.FWD_CANCEL);
//		}



        reminisceVO.setLastModifiedBy(getLastModifiedBy(request));
        reminisceVO.setAuthorId(getCurrentUserId(request));
		reminisceService.save(reminisceVO);
		MyAlumniMessages errors = new MyAlumniMessages();
		errors.add(BaseConstants.INFO_KEY, new MyAlumniMessage("message.reminiscesubmitted"));
		saveMessages(request, errors);
		return new ModelAndView(BaseConstants.FWD_SUCCESS);

	}

}
