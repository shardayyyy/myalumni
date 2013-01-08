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
package net.naijatek.myalumni.modules.common.presentation.form;

import java.util.List;

import net.naijatek.myalumni.framework.extensions.MyAlumniBaseForm;



public class SystemForm  extends MyAlumniBaseForm{
	
	private String logFileId;
    private String logFileName;
    private String logFileSize;
    private String logFileHumanSize;
    private String logType;
    private String logDir;
    private List logContent;
    private String lineCount;

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileSize(String logFileSize) {
        this.logFileSize = logFileSize;
    }

    public String getLogFileSize() {
        return logFileSize;
    }

    public void setLogFileHumanSize(String logFileHumanSize) {
        this.logFileHumanSize = logFileHumanSize;
    }

    public String getLogFileHumanSize() {
        return logFileHumanSize;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogDir(String logDir) {
        this.logDir = logDir;
    }

    public String getLogDir() {
        return logDir;
    }

    public void setLogContent(List logContent) {
        this.logContent = logContent;
    }

    public List getLogContent() {
        return logContent;
    }

    public void setLineCount(String lineCount) {
        this.lineCount = lineCount;
    }

    public String getLineCount() {
        return lineCount;
    }

	public String getLogFileId() {
		return logFileId;
	}

	public void setLogFileId(String logFileId) {
		this.logFileId = logFileId;
	}
}
