package scene;

import javax.swing.JFrame;

public class EndingSubScene extends SubScene{
    @Override
    public void showScene(JFrame f, SceneTracker st){
        f.setVisible(false);
        f.dispose();
        st.setState(true);
        while(st.contains("")){
            st.remove("");
        }
        for(String a: st){
            System.out.println(a);
        }
    }
}
