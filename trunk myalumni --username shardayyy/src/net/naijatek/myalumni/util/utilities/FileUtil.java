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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.naijatek.myalumni.util.FileHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public final class FileUtil {

    private static Log logger = LogFactory.getLog(FileUtil.class);

    private FileUtil() { // prevent instantiation
    }

    public static void createDir(final String dir, final boolean ignoreIfExitst) throws IOException {
        File file = new File(dir);

        if (ignoreIfExitst && file.exists()) {
            return;
        }

        if ( file.mkdir() == false) {
            throw new IOException("Cannot create the directory = " + dir);
        }
    }

    public static void createDirs(final String dir, final boolean ignoreIfExitst) throws IOException {
        File file = new File(dir);

        if (ignoreIfExitst && file.exists()) {
            return;
        }

        if ( file.mkdirs() == false) {
            throw new IOException("Cannot create directories = " + dir);
        }
    }

    public static void deleteFile(final String filename) throws IOException {
        File file = new File(filename);
        logger.info("Delete file = " + filename);
        if (file.isDirectory()) {
            throw new IOException("IOException -> BadInputException: not a file.");
        }
        if (file.exists() == false) {
            throw new IOException("IOException -> BadInputException: file is not exist.");
        }
        if (file.delete() == false) {
            throw new IOException("Cannot delete file. filename = " + filename);
        }
    }

    public static void deleteDir(final File dir) throws IOException {
        if (dir.isFile()){
          throw new IOException(
              "IOException -> BadInputException: not a directory.");
        }
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                } else {
                    deleteDir(file);
                }
            }
        }//if
        dir.delete();
    }

    public static long getDirLength(final File dir) throws IOException {
        if (dir.isFile()){
          throw new IOException("BadInputException: not a directory.");
        }
        long size = 0;
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                long length = 0;
                if (file.isFile()) {
                    length = file.length();
                } else {
                    length = getDirLength(file);
                }
                size += length;
            }//for
        }//if
        return size;
    }
    
    public static List<FileHelper> getDirFileNameLength(final File dir, String fileExt) throws IOException {
    	List<FileHelper> content = new ArrayList<FileHelper>();

        File[] files = dir.listFiles();
        FileHelper fh = null;
        if (files != null) {
            for (File file : files) {
                fh = new FileHelper();
                if (file.isFile() && !file.getName().startsWith(".") && file.getName().indexOf(fileExt) > 1) {
                	fh.setFileName(file.getName());
                	fh.setFileSize(getHumanSize(file.length()));
                	fh.setFileDate(new Date(file.lastModified()));
                    content.add(fh);
                } 
            }//for
        }//if
        return content;
    }

    public static long getDirLength_onDisk(final File dir) throws IOException {
        if (dir.isFile()){
          throw new IOException("BadInputException: not a directory.");
        }
        long size = 0;
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                long length = 0;
                if (file.isFile()) {
                    length = file.length();
                } else {
                    length = getDirLength_onDisk(file);
                }
                double mod = Math.ceil((double)length/512);
                if (mod == 0) {
                  mod = 1;
                }
                length = (long)mod * 512;
                size += length;
            }
        }//if
        return size;
    }

    public static byte[] getBytes(final InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byte[] block = new byte[512];
        while (true) {
            int readLength = inputStream.read(block);
            if (readLength == -1) {
              break; // end of file
            }
            byteArrayOutputStream.write(block, 0, readLength);
        }
        byte[] retValue = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return retValue;
    }

    public static String getFileName(final String fullFilePath) {
        if (fullFilePath == null) {
            return "";
        }
        int index1 = fullFilePath.lastIndexOf('/');
        int index2 = fullFilePath.lastIndexOf('\\');

        //index is the maximum value of index1 and index2
        int index = index1 > index2 ? index1 : index2;
        if (index == -1) {
            // not found the path separator
            return fullFilePath;
        }
        String fileName = fullFilePath.substring(index + 1);
        return fileName;
    }

    /**
     * Fix a file directory to_email use "/" as directory seprator only.
     * 
     * @param inDir The file directory to_email be fixed.
     * @return String
     */
    public static String fixDir (final String inDir)
    {
      String path = null;

      StringBuffer sb = new StringBuffer();
      int len = inDir.length();
      for (int i =0 ; i < len; i++)
      {
        // Replace all \ with /
        if (inDir.charAt(i) == '\\'){
          sb.append('/');
        }
        else{
          sb.append(inDir.charAt(i));
        }
      }

      String newDir = sb.toString().replaceAll ("//", "/");

      len = newDir.length();
      sb = new StringBuffer();
      if (newDir.charAt(len-1) != '/') {
        sb.append(newDir);
        sb.append('/');
      } else {
        sb.append(newDir);
      }

      logger.debug ("fileTransfer fixDir() - new Dir = " + sb.toString());
      return sb.toString();
    }

  /**
   * This method create a file text/css NOTE: This method closes the
   * inputStream after it have done its work.
   *
   * @param inputStream the stream of a text/css file
   * @param textFile the output file, have the ".css" extension or orther
   *   extension
   * @throws IOException
   */
  public static void createTextFile(final InputStream inputStream, final String textFile)
        throws IOException {

        if (inputStream == null) {
            throw new IllegalArgumentException("Does not accept null input");
        }
        OutputStream outputStream = null;
        try {
            byte[] srcByte = FileUtil.getBytes(inputStream);
            outputStream = new FileOutputStream(textFile);
            outputStream.write(srcByte);
            return;
        } catch (IOException e) {
            logger.error("Error", e);
            throw e;
        } finally { // this finally is very important
            inputStream.close();
            if (outputStream != null) {
				outputStream.close();
			}
        }
    }

    /**
     * Write content to_email a fileName with the destEncoding
     * 
     * @param content String
     * @param fileName String
     * @param destEncoding String
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void writeFile(final String content, final String fileName, final String destEncoding)
        throws FileNotFoundException, IOException {

        File file = null;
        try {
            file = new File(fileName);
            if (file.isFile() == false) {
                throw new IOException("'" + fileName + "' is not a file.");
            }
            if (file.canWrite() == false) {
                throw new IOException("'" + fileName + "' is a read-only file.");
            }
        } finally {
            // we dont have to close File here
        }

        BufferedWriter out = null;
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            out = new BufferedWriter(new OutputStreamWriter(fos, destEncoding));

            out.write(content);
            out.flush();
        } catch (FileNotFoundException fe) {
            logger.error("Error", fe);
            throw fe;
        } catch (IOException e) {
            logger.error("Error", e);
            throw e;
        } finally {
            try {
                if (out != null) {
					out.close();
				}
            } catch (IOException ex) {}
        }
    }

    public static String readFile(final String fileName, final String srcEncoding)
        throws FileNotFoundException, IOException {

        File file = null;
        try {
            file = new File(fileName);
            if (file.isFile() == false) {
                throw new IOException("'" + fileName + "' is not a file.");
            }
        } finally {
            // we dont have to close File here
        }

        BufferedReader reader = null;
        try {
            StringBuffer result = new StringBuffer(1024);
            FileInputStream fis = new FileInputStream(fileName);
            reader = new BufferedReader(new InputStreamReader(fis, srcEncoding));

            char[] block = new char[512];
            while (true) {
                int readLength = reader.read(block);
                if (readLength == -1) {
					break;// end of file
				}
                result.append(block, 0, readLength);
            }
            return result.toString();
        } catch (FileNotFoundException fe) {
            logger.error("Error", fe);
            throw fe;
        } catch (IOException e) {
            logger.error("Error", e);
            throw e;
        } finally {
            try {
                if (reader != null) {
					reader.close();
				}
            } catch (IOException ex) {}
        }
    }

    /*
     *  1  ABC
     *  2  abC Gia su doc tu dong 1 lay ca thay 5 dong => 1 --> 5
     *  3  ABC
     */
    public static String[] getLastLines(final File file, final int linesToReturn, final String logType)
    throws IOException, FileNotFoundException {

    final int AVERAGE_CHARS_PER_LINE = 250;
    final int BYTES_PER_CHAR = 2;

    RandomAccessFile randomAccessFile = null;
    StringBuffer buffer = new StringBuffer(linesToReturn * AVERAGE_CHARS_PER_LINE);
    int lineTotal = 0;
    try {
        randomAccessFile = new RandomAccessFile(file, "r");
        long byteTotal = randomAccessFile.length();
        long byteEstimateToRead = linesToReturn * AVERAGE_CHARS_PER_LINE * BYTES_PER_CHAR;

        long offset = byteTotal - byteEstimateToRead;
        if (offset < 0) {
            offset = 0;
        }

        randomAccessFile.seek(offset);
        //log.debug("SKIP IS ::" + offset);

        String line = null;
        String lineUTF8 = null;
        while ((line = randomAccessFile.readLine()) != null) {
            lineUTF8 = new String(line.getBytes("ISO8859_1"), "UTF-8");
            lineTotal++;
            buffer.append(lineUTF8).append("\n");
        }
    } finally {
        if (randomAccessFile != null) {
            try {
                randomAccessFile.close();
            } catch (IOException ex) {
            }
        }
    }

    //String[] resultLines = new String[linesToReturn];
    ArrayList<String> resultLines = new ArrayList<String>();
    BufferedReader in = null;
    try {
        in = new BufferedReader(new StringReader(buffer.toString()));

        int start = lineTotal /* + 2 */ - linesToReturn; // Ex : 55 - 10 = 45 ~ offset
        if (start < 0) {
			start = 0; // not start line
		}
        for (int i = 0; i < start; i++) {
            in.readLine(); // loop until the offset. Ex: loop 0, 1 ~~ 2 lines
        }


        int i = 0;
        String line = null;
        while ((line = in.readLine()) != null) {
          if (logType.equalsIgnoreCase("ALL")){
            //resultLines[i] = line;
            resultLines.add(line);
            i++;
          }
          else if (line.indexOf(logType) > -1){
            //resultLines[i] = line;
            resultLines.add(line);
            i++;
          }
        }
    } catch (IOException ie) {
        logger.error("Error" + ie);
        throw ie;
    } finally {
        if (in != null) {
            try {
                in.close();
            } catch (IOException ex) {
            }
        }
    }

    String[] resultLines1 = new String[resultLines.size()];
    String tmp = new String();
    for (int i = 0 ; i < resultLines.size() ; i++){
      tmp = (String)resultLines.get(i);
      resultLines1[i] = tmp;
    }

    return resultLines1;
}

    public static String getHumanSize(final long size) {

        int sizeToStringLength = String.valueOf(size).length();
        String humanSize = "";
        DecimalFormat formatter = new DecimalFormat("##0.##");
        if (sizeToStringLength > 9) {
            humanSize += formatter.format((double) size / (1024 * 1024 * 1024)) + " GB";
        } else if (sizeToStringLength > 6) {
            humanSize += formatter.format((double) size / (1024 * 1024)) + " MB";
        } else if (sizeToStringLength > 3) {
            humanSize += formatter.format((double) size / 1024) + " KB";
        } else {
            humanSize += String.valueOf(size) + " Bytes";
        }
        return humanSize;
    }
}
