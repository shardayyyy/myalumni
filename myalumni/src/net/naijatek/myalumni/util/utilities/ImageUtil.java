/*
 * ====================================================================
 * Copyright (C) 1997-2008 by Naijatek.com
 *
 * All copyright notices regarding MyAlumni Board MUST remain 
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
 * <p>Title: MyAlumni Board </p>
 * <p>Description: This system helps keep alive the line of communications between alumni/alumnus</p>
 * <p>Copyright: Copyright (c) 1997-2008</p>
 * <p>Company: Naijatek Solutions (http://www.naijatek.com)</p>
 * @author Folashade Adeyosoye (shardayyy@naijatek.com)
 * @version 1.0
 */
package net.naijatek.myalumni.util.utilities;

import java.util.Iterator;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * boolean b;
    
    // Check availability using a format name
    b = canReadFormat("foo");          // false
    b = canReadFormat("gif");          // true
    b = canReadFormat("giF");          // true
    b = canWriteFormat("foo");         // false
    b = canWriteFormat("gif");         // false
    b = canWriteFormat("PNG");         // true
    b = canWriteFormat("jPeG");        // true
    
    // Get extension from a File object
    File f = new File("image.jpg");
    String s = f.getName().substring(f.getName().lastIndexOf('.')+1);
    
    // Check availability using a filename extension
    b = canReadExtension(s);
    b = canWriteExtension(s);
    
    // Check availability using a MIME type
    b = canReadMimeType("image/jpg");     // false
    b = canReadMimeType("image/jpeg");    // true
    b = canWriteMimeType("image/gif");    // false
    b = canWriteMimeType("image/jPeg");   // true
 *
 */
public class ImageUtil {

    private static Log logger = LogFactory.getLog(FileUtil.class);

    private ImageUtil() { // prevent instantiation
    }
    
    // Returns true if the specified format name can be read
    public static boolean canReadFormat(String formatName) {
        Iterator iter = ImageIO.getImageReadersByFormatName(formatName);
        return iter.hasNext();
    }
    
    // Returns true if the specified format name can be written
    public static boolean canWriteFormat(String formatName) {
        Iterator iter = ImageIO.getImageWritersByFormatName(formatName);
        return iter.hasNext();
    }
    
    // Returns true if the specified file extension can be read
    public static boolean canReadExtension(String fileExt) {
        Iterator iter = ImageIO.getImageReadersBySuffix(fileExt);
        return iter.hasNext();
    }
    
    // Returns true if the specified file extension can be written
    public static boolean canWriteExtension(String fileExt) {
        Iterator iter = ImageIO.getImageWritersBySuffix(fileExt);
        return iter.hasNext();
    }
    
    // Returns true if the specified mime type can be read
    public static boolean canReadMimeType(String mimeType) {
        Iterator iter = ImageIO.getImageReadersByMIMEType(mimeType);
        return iter.hasNext();
    }
    
    // Returns true if the specified mime type can be written
    public static boolean canWriteMimeType(String mimeType) {
        Iterator iter = ImageIO.getImageWritersByMIMEType(mimeType);
        return iter.hasNext();
    }
}
