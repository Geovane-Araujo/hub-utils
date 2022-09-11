package com.hubutils.hubutils.controller;

import com.itextpdf.io.codec.Base64;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

@RestController
public class ConvertController {

    public void image(MultipartFile multi){

        try {
            BufferedImage image = ImageIO.read(multi.getInputStream());
            int height = image.getHeight();
            int width = image.getWidth();
            int x = (width - 160);
            int y = (height - 150);

            Graphics g = image.getGraphics();
            g.setFont(new Font("Arial",Font.BOLD,14));
            g.drawString("Paraná",x,y);
            g.dispose();

            ImageIO.write(image,"png",new File("./"+ UUID.randomUUID()+".png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String imageB64(String strImage, String strText){
        try {
            String b64text = strText.split(",")[1];
            byte[] imageTextByte = Base64.decode(b64text);
            BufferedImage imageText = ImageIO.read(new ByteArrayInputStream(imageTextByte));
            Image im = imageText;

            String b64 = strImage.split(",")[1];
            byte[] imageByte = Base64.decode(b64);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageByte));

            Image tempImage = image.getScaledInstance(1000,700, Image.SCALE_DEFAULT);
            BufferedImage newImage = new BufferedImage(1000,700,image.getType());

            int height = 700;
            int width = 1000;
            int x = (width - 350);
            int y = (height - 135);

            Graphics g = newImage.createGraphics();
            g.drawImage(tempImage,0,0,null);
            g.drawImage(im,x,y,null);
            g.dispose();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            ImageIO.write(newImage,"png",byteArrayOutputStream);

            String b64cv = Base64.encodeBytes(byteArrayOutputStream.toByteArray());

            return strText.split(",")[0] + "," + b64cv;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
