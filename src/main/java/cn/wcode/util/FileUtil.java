package cn.wcode.util;

import cn.wcode.model.Question;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by homiss on 2017/6/30.
 */
public class FileUtil {


  /**
   * 功能：Java读取txt文件的内容
   * 步骤：1：先获得文件句柄
   * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
   * 3：读取到输入流后，需要读取生成字节流
   * 4：一行一行的输出。readline()。
   * 备注：需要考虑的是异常情况
   * @param filePath
   */
  public static String readTxtFile(String filePath){
    try {
      String txt = "", lineTxt = null;
      String encoding="UTF-8";
      File file=new File(filePath);
      if(file.isFile() && file.exists()){ //判断文件是否存在
        InputStreamReader read = new InputStreamReader(
            new FileInputStream(file),encoding);//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        while((lineTxt = bufferedReader.readLine()) != null){
          txt += lineTxt;
          // System.out.println(lineTxt);
        }
        read.close();
        return txt;
      }else{
        System.out.println("找不到指定的文件");
      }
    } catch (Exception e) {
      System.out.println("读取文件内容出错");
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) {


  }

}
