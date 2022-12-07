package utils;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
public class CustomFont {
   public static Font customFont; 
      public static Font load(int fontSize)
    {
        InputStream fontStream = CustomFont.class.getResourceAsStream("resource/minecraft_font.ttf");
        if (fontStream != null) 
        {
            try 
            {
                customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                customFont = customFont.deriveFont((float) fontSize);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
                return customFont;
            } catch (Exception e) 
            {
                System.out.println("error " + e);
            }

        } 
        System.out.println("im null");
        return null;
    }
}
