package algorithmfinal;

/**
 *
 * @author Saurabh Salunkhe
 */
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class AlgorithmFinal extends JPanel {

    int epoch = 0;
    boolean flag;
    int coordinate1 = 100;
    int coordinate2 = 100;
    private static final int FINALTARGET = 50;
    private static final int MAXNUMBEROFINPUTS = 2;
    private static final int MAXNUMBEROFROBOTS = 20;
    private static final int V_MAX = 10;
    private static final int MIN = 100;
    private static final int MAX = 180;
    

    private static ArrayList<Robot> swarmRobots = new ArrayList<Robot>();

    public AlgorithmFinal() {
        this.flag = false;
    }

    public boolean PSOTransport() {
        int globalBest = 0;
        int gBest = 1000;
        Robot aRobot = null;
        boolean done = false;

        for (int i = 0; i < MAXNUMBEROFROBOTS; i++) {
            aRobot = swarmRobots.get(i);
               for (int j = 0; j < MAXNUMBEROFINPUTS; j++) {
                    if (j < MAXNUMBEROFINPUTS - 1) {
                        System.out.println(aRobot.data(j) + " + ");
                    } else {
                        System.out.println(aRobot.data(j) + " = ");
                    }
                    
                } // j
                    
            if (ProblemToBeSolved(i) == FINALTARGET) {
                done = true;
            }
        }

        gBest = target();
        aRobot = swarmRobots.get(globalBest);
        if (Math.abs(FINALTARGET - ProblemToBeSolved(gBest)) < Math.abs(FINALTARGET - ProblemToBeSolved(globalBest))) {
            globalBest = gBest;
        }

        calculateVelocity(globalBest);
        updateRobots(globalBest);
        epoch += 1;
        return done;
    }

    private void calculateVelocity(int gBestindex) {

        int testResults = 0;
        int bestResults = 0;
        double vValue = 0.0;
        Robot aRobot = null;

        bestResults = ProblemToBeSolved(gBestindex);

        for (int i = 0; i < MAXNUMBEROFROBOTS; i++) {
            testResults = ProblemToBeSolved(i);
            aRobot = swarmRobots.get(i);
            vValue = aRobot.velocity() + 2 * new Random().nextDouble() * (aRobot.pBest() - testResults) + 2 * new Random().nextDouble() * (bestResults - testResults);

            if (vValue > V_MAX) {
                aRobot.velocity(V_MAX);
            } else if (vValue < -V_MAX) {
                aRobot.velocity(-V_MAX);
            } else {
                aRobot.velocity(vValue);
            }
        }
    }
    
    public void initialize() 
    {
        for (int i = 0; i < MAXNUMBEROFROBOTS; i++) {
            Robot newParticle = new Robot();
            int total = 0;
            for (int j = 0; j < MAXNUMBEROFINPUTS; j++) {
                newParticle.data(j, getRandomNumber(MIN, MAX));
                total += newParticle.data(j);
            }
            newParticle.pBest(total);
            swarmRobots.add(newParticle);
        } 

    }

    private void updateRobots(int gBestindex) {
        Robot gBParticle = swarmRobots.get(gBestindex);

        for (int i = 0; i < MAXNUMBEROFROBOTS; i++) {
            for (int j = 0; j < MAXNUMBEROFINPUTS; j++) {
                if (swarmRobots.get(i).data(j) != gBParticle.data(j)) {
                    swarmRobots.get(i).data(j, swarmRobots.get(i).data(j) + (int) Math.round(swarmRobots.get(i).velocity()));
                }
            }

            int total = ProblemToBeSolved(i);
            if (Math.abs(FINALTARGET - total) < swarmRobots.get(i).pBest()) {
                swarmRobots.get(i).pBest(total);
            }

        }

    }

    private static int ProblemToBeSolved(int index) {
        int total = 0;
        Robot aRobot = null;

        aRobot = swarmRobots.get(index);

        for (int i = 0; i < MAXNUMBEROFINPUTS; i++) {
            total += aRobot.data(i);
        }
        return total;
    }



    private static int getRandomNumber(int low, int high) {
        return (int) ((high - low) * new Random().nextDouble() + low);
    }

    private static int target() {

        int achieve = 0;
        boolean foundNewWinner = false;
        boolean done = false;

        while (!done) {
            foundNewWinner = false;
            for (int i = 0; i < MAXNUMBEROFROBOTS; i++) {
                if (i != achieve) {

                    if (Math.abs(FINALTARGET - ProblemToBeSolved(i)) < Math.abs(FINALTARGET - ProblemToBeSolved(achieve))) {
                        achieve = i;
                        foundNewWinner = true;
                    }
                }
            }

            if (foundNewWinner == false) {
                done = true;
            }
        }

        return achieve;
    }

    private static class Robot {

        private final int[] history = new int[MAXNUMBEROFINPUTS];
        private int mpBest = 0;
        private double velocity = 0.0;

        public Robot() {
            this.mpBest = 0;
            this.velocity = 0.0;
        }

        public int data(int index) {
            return this.history[index];
        }

        public void data(int index, int value) {
            this.history[index] = value;

        }

        public int pBest() {
            return this.mpBest;
        }

        public void pBest(int value) {
            this.mpBest = value;

        }

        public double velocity() {
            return this.velocity;
        }

        public void velocity(double velocityScore) {
            this.velocity = velocityScore;

        }
    }

    @Override
    public void paint(Graphics g) {
        
        super.paint(g);
        Robot particle;
        for (int i = 0; i < MAXNUMBEROFROBOTS; i++) {
            particle = swarmRobots.get(i);
            if (i == MAXNUMBEROFROBOTS - 1) {
                g.fillOval(particle.data(0), particle.data(1), 10, 10); //This are actually the co-ordinates of the best local function
            } else {
                g.fillOval(particle.data(0), particle.data(1), 10, 10);
            }

        }
    }

    public void execute() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean val = PSOTransport();

                if (val == true) {
                    repaint();

                    timer.cancel();
                    timer.purge();
                }
                repaint();
            }
        }, 1000, 2000);
    }

}
