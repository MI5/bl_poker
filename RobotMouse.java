    import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
public class RobotMouse {


    
    public static void main(String[] args) throws InterruptedException { 
        try {
            Robot rob = new Robot();
            for (int i = 0; i < 1000; i++) {
                rob.mouseMove(0, i);
            }
            rob.mouseMove(17, 754);
            rob.mousePress(InputEvent.BUTTON1_MASK);
            rob.mouseRelease(InputEvent.BUTTON1_MASK);
            rob.mouseMove(47, 724);
            Thread.sleep(50);
            rob.mouseMove(77, 724);
            Thread.sleep(50);
            rob.mousePress(InputEvent.BUTTON1_MASK);
            rob.mouseRelease(InputEvent.BUTTON1_MASK);
            rob.mouseMove(513, 298);
            Thread.sleep(50);


            
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
