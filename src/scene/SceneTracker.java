package scene;

import java.util.ArrayList;

public class SceneTracker extends ArrayList<String>{
    private boolean done=false;
    
    public boolean getState(){
        return done;
    }
    
    public void setState(boolean a){
        done=a;
    }
}
