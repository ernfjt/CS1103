package unit7;

 /*
       A trivial applet that tests the StopWatchTimer component.
       The applet just creates and shows a StopWatchTimer.
    */
    
    import java.awt.*;
    import javax.swing.*;
    
    public class TestStopWatchRunner extends JFrame {
    
       public TestStopWatchRunner() {
    	   
          super("STOP WATCH");
          StopWatchLabel watch = new StopWatchLabel();
          watch.setFont( new Font("SansSerif", Font.BOLD, 24) );
          watch.setBackground(Color.white);
          watch.setForeground( new Color(180,0,0) );
          setSize(300,100);
          setBackground(Color.white);
          setLayout(new BorderLayout() );
          add(watch, BorderLayout.CENTER);
          setVisible(true);
       }
       
       public static void main(String[] args) {
    	   new TestStopWatchRunner();
       }
    
    }