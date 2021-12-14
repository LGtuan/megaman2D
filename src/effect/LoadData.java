package effect;

import javax.imageio.ImageIO;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;

public class LoadData {
    private static LoadData instance;

    private String fileFrame = "data/frame.txt";
    private String fileAnimation = "data/animation.txt";
    private String filePhysMap = "data/phys_map1.txt";
    private String fileSound = "data/sounds.txt";

    private Hashtable<String,FrameImage> frameImages;
    private Hashtable<String,Animation> animations;
    private Hashtable<String, AudioClip> sounds;

    private int[][] phys_map;

    private LoadData(){};

    public static LoadData getInstance(){
        if(instance == null) instance = new LoadData();

        return instance;
    }

//    public void loadSound() throws IOException {
//        FileReader fr = new FileReader(fileSound);
//        BufferedReader br = new BufferedReader(fr);
//
//        if(br.readLine()==null){
//            System.out.println("File not data");
//            return;
//        }
//
//        sounds = new Hashtable<>();
//
//        fr = new FileReader(fileSound);
//        br = new BufferedReader(fr);
//
//        String line = null;
//        while((line = br.readLine()).equals(""));
//
//        int n = Integer.parseInt(line);
//
//        for (int i=0;i<n;i++){
//            AudioClip audioClip = null;
//
//            while ((line=br.readLine()).equals(""));
//
//            String[] str = line.split(" ");
//            String name = str[0];
//
//            audioClip = Applet.newAudioClip(new URL("file","",str[1]));
//
//            sounds.put(name,audioClip);
//
//        }
//        br.close();
//    }

    public AudioClip getSound(String name){
        return sounds.get(name);
    }

    public void loadData() throws IOException {

        loadFrameImage();
        loadAnimation();
        loadPhys_map();
        //loadSound();
    }

    public void loadFrameImage() throws IOException {
        FileReader fr = new FileReader(fileFrame);
        BufferedReader br = new BufferedReader(fr);

        if(br.readLine()==null) {
            System.out.println("file not date");
            return;
        }

        frameImages = new Hashtable<>();

        fr = new FileReader(fileFrame);
        br = new BufferedReader(fr);

        String line = null;

        ArrayList<String> paths = new ArrayList<>();
        Hashtable<String, BufferedImage> sourceImage = new Hashtable<>();

        int n = Integer.parseInt(br.readLine());

        for (int i=0;i<n;i++){
            System.out.println(i);
            FrameImage frame = new FrameImage();

            while((line = br.readLine()).equals(""));
            frame.setName(line);

            while((line = br.readLine()).equals(""));
            String path = line;
            if(!paths.contains(path)){
                paths.add(path);
                BufferedImage image = ImageIO.read(new File(path));
                sourceImage.put(path,image);
            }

            while((line = br.readLine()).equals(""));
            String[] str = line.split(" ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            int w = Integer.parseInt(str[2]);
            int h = Integer.parseInt(str[3]);
            BufferedImage subImage = sourceImage.get(path).getSubimage(x,y,w-x,h-y);
            frame.setImage(subImage);
            instance.frameImages.put(frame.getName(),frame);
        }
        br.close();
    }

    public FrameImage getFrameImage(String name){
        FrameImage frame = new FrameImage(instance.frameImages.get(name));
        return frame;
    }

    public void loadAnimation() throws IOException {
        FileReader fr = new FileReader(fileAnimation);
        BufferedReader br = new BufferedReader(fr);

        FileReader fr1 = new FileReader(fileFrame);
        BufferedReader br1 = new BufferedReader(fr1);
        String a = br1.readLine();
        String[] x = a.split(" ");
        System.out.println(x.length);
        System.out.println(x[0].length());
        if(br.readLine()==null){
            System.out.println("File is not data");
            return;
        }

        animations = new Hashtable<>();

        fr = new FileReader(fileAnimation);
        br = new BufferedReader(fr);

        String line = null;

        while((line = br.readLine()).equals(""));
        int n = Integer.parseInt(line);


        for (int i = 0; i < n; i++) {
            Animation animation = new Animation();

            while((line = br.readLine()).equals(""));
            animation.setName(line);

            while((line = br.readLine()).equals(""));
            String[] str = line.split(" ");

            for (int j = 0; j < str.length; j+=2) {

                animation.add(getFrameImage(str[j]),  Double.parseDouble(str[j+1]));

            }

            animations.put(animation.getName(), animation);

        }
        br.close();
    }

    public Animation getAnimation(String name){
        Animation animation = new Animation(instance.animations.get(name));
        return animation;
    }

    public void loadPhys_map() throws IOException{

        FileReader fr = new FileReader(filePhysMap);
        BufferedReader br = new BufferedReader(fr);

        if(br.readLine()==null){
            System.out.println("File is not data");
            return;
        }

        fr = new FileReader(filePhysMap);
        br = new BufferedReader(fr);

        String line = null;

        while((line = br.readLine()).equals(""));
        int y = Integer.parseInt(line);
        while((line = br.readLine()).equals(""));
        int x = Integer.parseInt(line);

        phys_map = new int[y][x];

        for (int i = 0; i < y; i++) {
            while((line = br.readLine()).equals(""));
            String[] str = line.split(" ");

            for (int j = 0; j < x; j++) {

                phys_map[i][j] = Integer.parseInt(str[j]);

            }

        }

        br.close();
    }

    public int[][] getPhysMap(){
        return phys_map;
    }


}
