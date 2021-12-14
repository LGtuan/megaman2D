package effect;

import object.ParticularObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

    private String name;

    private ArrayList<FrameImage> frameImages;
    private int currentFrame;

    private ArrayList<Double> delayFrames;

    private ArrayList<Boolean> ignoreFrame;

    private boolean isRepeat;

    private long beginTime;

    public Animation(){
        this.name = null;
        this.currentFrame = 0;
        this.isRepeat = true;
        this.beginTime = 0;

        this.frameImages = new ArrayList<>();
        this.delayFrames = new ArrayList<>();
        this.ignoreFrame = new ArrayList<>();
    }


    public Animation(Animation animation){
        this.name = animation.getName();
        this.currentFrame = animation.getCurrentFrame();
        this.isRepeat = animation.isRepeat;
        this.beginTime = animation.beginTime;

        this.frameImages = new ArrayList<>();
        this.delayFrames = new ArrayList<>();
        this.ignoreFrame = new ArrayList<>();

        for (FrameImage frameImage:
             animation.frameImages) {
            this.frameImages.add(new FrameImage(frameImage));
        }

        for (Double d :
                animation.delayFrames) {
            this.delayFrames.add(d);
        }

        for (Boolean b :
                animation.ignoreFrame) {
            this.ignoreFrame.add(b);
        }
    }

    public void update(long currentTime){

        if(beginTime == 0) beginTime = currentTime;
        else{

            if(currentTime-beginTime >delayFrames.get(currentFrame)){
                nextFrame();
                beginTime = System.nanoTime();
            }
        }
    }

    public void nextFrame(){

        if (currentFrame >= this.frameImages.size() - 1) {

            if(isRepeat) currentFrame=0;
        }
        else currentFrame ++;

        if(ignoreFrame.get(currentFrame)) nextFrame();

    }

    public void flipAllImage(){

        // vong lap de dao tung hinh anh
        for(int i = 0;i < frameImages.size(); i++){

            // get image current
            BufferedImage image = frameImages.get(i).getImage();

            // lat anh theo chieu ngang
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            // tinh tien 1 khoang da lat
            tx.translate(-image.getWidth(), 0);

            //Sau khi đã định nghĩa thao tác lật ảnh trong đối tượng AffineTransform,
            //chúng ta copy từng pixel trong ảnh vào một BufferedImage,
            //trong Java có sẵn lớp AffineTransformOp cho phép chúng ta làm điều
            //này dễ dàng với phương thức filter().
            AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);

            frameImages.get(i).setImage(image);

        }

    }

    public void add(FrameImage image,Double d){

        this.frameImages.add(image);
        this.ignoreFrame.add(false);
        this.delayFrames.add(d);

    }

    public boolean isLastFrame(){
        return frameImages.size()-currentFrame==1;
    }

    public void reset(){
        this.currentFrame = 0;
        this.beginTime = 0;
    }

    public BufferedImage getCurrentImage(){
        if(currentFrame<0 || currentFrame>frameImages.size()-1) currentFrame = 0;
        return frameImages.get(currentFrame).getImage();
    }

    public void drawImage(Graphics2D g2,int x,int y){

        g2.drawImage(getCurrentImage(),x-getCurrentImage().getWidth()/2,
                y-getCurrentImage().getHeight()/2,null);

    }

    public void draw(int x,int y,Graphics2D g2,int direction){
        BufferedImage image = getCurrentImage();
        if(direction == ParticularObject.right_Dir)
            g2.drawImage(image,x,y-image.getHeight()/2, null);
        else{
            g2.drawImage(image,x-image.getWidth(),y-image.getHeight()/2, null);
        }
    }

    public void setIgnoreFrame(int id){
        this.ignoreFrame.set(id,true);
    }

    public boolean getIgnoreFrame(int id){return ignoreFrame.get(id);}

    public void unIgnoreFrame(int id){
        this.ignoreFrame.set(id,false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentFrame() {
        if(currentFrame<0 || currentFrame>frameImages.size()-1) currentFrame = 0;
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        if(currentFrame<0 || currentFrame>frameImages.size()-1) currentFrame = 0;
        this.currentFrame = currentFrame;
    }

    public void setIsRepeat(boolean repeat) {
        isRepeat = repeat;
    }
}
