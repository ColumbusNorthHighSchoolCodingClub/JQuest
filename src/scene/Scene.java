package scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

public class Scene {
    private SubScene first;
    private JFrame f;
    private SceneTracker a=null;
    
    private SceneTracker showScene() throws InterruptedException{
        //method is private intentionally. Only use showSceneFromFile.
        a=new SceneTracker();
        f=new JFrame();
        first.showScene(f, a);
        while(!a.getState()){
            Thread.sleep(1000);
        }
        return a;
    }
    
    public void loadFromFile(File a) throws FileNotFoundException, IOException{
        Scanner s = new Scanner(a);
        ArrayList<ArrayList<String>> info = new ArrayList();
        int ind = -1;
        while(s.hasNextLine()){
            String f = s.nextLine();
            if(f.equals("new")){
                ind++;
                info.add(new ArrayList());
            } else{
                info.get(ind).add(f);
            }
        }
        ArrayList<ArrayList<String>> firstList = new ArrayList();
        ArrayList<ArrayList<Integer>> secondList = new ArrayList();
        ArrayList<ArrayList<String>> thirdList = new ArrayList();
        for(int i=0;i<info.size();i++){
            firstList.add(new ArrayList());
            secondList.add(new ArrayList());
            thirdList.add(new ArrayList());
        }
        for(int i=0;i<info.size();i++){
            ArrayList<String> getter = info.get(i);
            for(int j=0;j<getter.size();j++){
                if(isNumber(getter.get(j))){
                    String[] parts = getter.get(j).split(",");
                    secondList.get(i).add(Integer.parseInt(parts[0]));
                    if(parts.length>1){
                        thirdList.get(i).add(parts[1]);
                    } else{
                        thirdList.get(i).add("");
                    }
                } else {
                    firstList.get(i).add(getter.get(j));
                }
            }
        }
        ArrayList<SubScene> scenes = new ArrayList();
        for(int i=0;i<firstList.size();i++){
            scenes.add(new SubScene(firstList.get(i)));
        }
        for(int i=0;i<scenes.size();i++){
            ArrayList<Integer> ints = secondList.get(i);
            for(int j=0;j<ints.size();j++){
                if(ints.get(j)==0){
                    scenes.get(i).addNext(new EndingSubScene());
                } else {
                    scenes.get(i).addNext(scenes.get(ints.get(j)-1));
                }
            }
            ArrayList<String> addGoodNameLater = thirdList.get(i);
            for(int j=0;j<addGoodNameLater.size();j++){
                scenes.get(i).addChanger(addGoodNameLater.get(j));
            }
        }
        first = scenes.get(0);
    }
    
    public static boolean isNumber(String a){
        String[] b = a.split(",");
        try{
            Integer.parseInt(b[0]);
        } catch(Exception e){
            return false;
        }
        return true;
    }
    
    public static SceneTracker showSceneFromFile(String a) throws IOException, InterruptedException{
        /*Scenes should be recreated and reloaded from a file every time they are shown to avoid issues with reusing JFrames
         *This method will handle that entire process
         *Only use this method to show scenes
         */
        Scene s = new Scene();
        s.loadFromFile(new File(a));
        return s.showScene();
    }
}
