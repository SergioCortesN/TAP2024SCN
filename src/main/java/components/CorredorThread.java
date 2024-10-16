package components;

import javafx.scene.control.ProgressBar;

public class CorredorThread extends Thread
{
    private ProgressBar progreso;
    public CorredorThread(String name, ProgressBar progreso)
    {
        super(name);
        this.progreso = progreso;
    }

    @Override
    public void run()
    {
        super.run();
        double avance=0;
        while(avance <=1)
        {
            avance += Math.random()/10;
            try {
                Thread.sleep((long) (Math.random()*2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.progreso.setProgress(avance);
        }
        System.out.println(this.getName()+" llegó a la meta :D");
    }
}
