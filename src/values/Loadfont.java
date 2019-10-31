/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package values;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author billy
 */
public class Loadfont {

    public static class CyFont {

        private Font definedFont = null;

        public Font getDefinedFont(int ft, float fs) {
            String fontUrl = "";
            switch (ft) {
                case 1:
                    URL url = getClass().getResource("/resources/Fonts/jackeyfont.ttf");
                    fontUrl = url.getPath();//華文行楷
                    break;
//                case 2:
//                    fontUrl = "/opt/hwkt.ttf";//華文楷體
//                    break;
//                default:
//                    String fonturllocal = "/usr/share/fonts/wqy-zenhei/wqy-zenhei.ttc";
//                    if (!new File(fonturllocal).exists()) {
//                        fontUrl = "/usr/share/fonts/truetype/wqy/wqy-zenhei.ttc";
//                    } else {
//                        fontUrl = fonturllocal;
//                    }
//                    break;
            }
            if (definedFont == null) {
                InputStream is = null;
                BufferedInputStream bis = null;
                try {
                    is = new FileInputStream(new File(fontUrl));
                    bis = new BufferedInputStream(is);
                    definedFont = Font.createFont(Font.TRUETYPE_FONT, is);
                    //設定字型大小,float型
                    definedFont = definedFont.deriveFont(fs);
                } catch (FontFormatException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (null != bis) {
                            bis.close();
                        }
                        if (null != is) {
                            is.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return definedFont;
        }
    }
//    public static Font loadFont(String fontFileName, float fontSize) //第一个参数是外部字体名，第二个是字体大小
//    {
//        try {
//            File file = new File(fontFileName);
//            FileInputStream aixing = new FileInputStream(file);
//            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
//            Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
//            aixing.close();
//            return dynamicFontPt;
//        } catch (Exception e)//异常处理
//        {
//            e.printStackTrace();
//            return new java.awt.Font("jackeyfont", Font.PLAIN, 14);
//        }
//    }
//
//    public static java.awt.Font Font() {
//        String root = System.getProperty("C:");//项目根目录路径
//        Font font = Loadfont.loadFont( "\\Users\\billy\\AppData\\Local\\Microsoft\\Windows\\Fonts\\jackeyfont.ttf", 18f);//调用
//        return font;//返回字体
//    }
    }
