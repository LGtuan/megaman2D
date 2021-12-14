package effect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrameImage {

    private BufferedImage image;
    private String name;

    public FrameImage(){
        this.name = null;
        this.image = null;
    }

    public FrameImage(String name,BufferedImage image){
        this.name = name;
        this.image = image;
    }

    public FrameImage(FrameImage frameImage){

        this.image = new BufferedImage(frameImage.getWidth(),frameImage.getHeight(),frameImage.getType());

        Graphics2D g2 =(Graphics2D) this.image.getGraphics();
        g2.drawImage(frameImage.getImage(),0,0,null);
        this.name = frameImage.getName();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth(){
        return getImage().getWidth();
    }

    public int getHeight(){
        return getImage().getHeight();
    }

    public int getType(){
        return getImage().getType();
    }
}
