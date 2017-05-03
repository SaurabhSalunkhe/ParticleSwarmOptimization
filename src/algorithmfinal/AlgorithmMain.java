
package algorithmfinal;

import javax.swing.JFrame;

/**
 *
 * @author Saurabh Salunkhe
 */
public class AlgorithmMain {
    
     public static void main(String[] args) {
        
        
        JFrame frame = new JFrame();
        AlgorithmFinal p = new AlgorithmFinal();
        p.initialize();
        frame.add(p);
        frame.setSize(400, 400);
        frame.show();
        p.execute();
               
    }
    
}


//
//
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package algorithmfinal;
//
///**
// *
// * @author Saurabh Salunkhe
// */
//import java.awt.Graphics;
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.Timer;
//import java.util.TimerTask;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//public class AlgorithmFinal extends JPanel {
//
//    private static final int TARGET = 50; 
//    private static final int MAX_INPUTS = 2; 
//    private static final int MAX_PARTICLES = 20; 
//    private static final int V_MAX = 10;             
//
//    private static final int MAX_EPOCHS = 200;
//    private static final int START_RANGE_MIN = 140; 
//    private static final int START_RANGE_MAX = 190; 
//    int epoch = 0;
//    boolean done;
//    int o1 = 100;
//          int o2 = 100;
//
//    private static ArrayList<Particle> particles = new ArrayList<Particle>();
//
//    public AlgorithmFinal() {
//        this.done = false;
//    }
//
//    private void initialize() //set al the factors of each particle
//    {
//        for (int i = 0; i < MAX_PARTICLES; i++) {
//            Particle newParticle = new Particle();
//            int total = 0;
//            for (int j = 0; j < MAX_INPUTS; j++) {
//                newParticle.data(j, getRandomNumber(START_RANGE_MIN, START_RANGE_MAX));
//                total += newParticle.data(j);
//            } // j
//            newParticle.pBest(total); //set personal best as the total
//            particles.add(newParticle);
//        } // 
//        
//    }
//
//    boolean PSOAlgorithm() {
//        int gBest = 0;
//        int gBestTest = 0;
//        Particle aParticle = null;
//        
//        boolean done = false;
//
//       // initialize(); //set the factors of each particle
//
//        // while (!done) {
//        // Two conditions can end this loop:
//        //    if the maximum number of epochs allowed has been reached, or,
//        //    if the Target value has been found.
////        if (epoch < MAX_EPOCHS) {
//
//            for (int i = 0; i < MAX_PARTICLES; i++) {
//                aParticle = particles.get(i);
//                for (int j = 0; j < MAX_INPUTS; j++) {
//                    if (j < MAX_INPUTS - 1) {
//                        System.out.print(aParticle.data(j) + " + ");
//                    } else {
//                        System.out.print(aParticle.data(j) + " = ");
//                    }
//                } // j
//
//                System.out.print(testProblem(i) + "\n");
//                if (testProblem(i) == TARGET) {
//                    done = true;
//                }
//            } // i
//
//            gBestTest = minimum();
//            aParticle = particles.get(gBest);
//            // if(any particle's pBest value is better than the gBest value, make it the new gBest value.
//            if (Math.abs(TARGET - testProblem(gBestTest)) < Math.abs(TARGET - testProblem(gBest))) {
//                gBest = gBestTest;
//            }
//
//            getVelocity(gBest);
//
//            updateparticles(gBest);
//
//            System.out.println("epoch number: " + epoch);
//
//            epoch += 1;
//
////        } else {
////            done = true;
////        }
//
//        return done;
//    }
//
//    private void getVelocity(int gBestindex) {
//        //  from Kennedy & Eberhart(1995).
//        //    vx[][] = vx[][] + 2 * rand() * (pbestx[][] - presentx[][]) + 
//        //                      2 * rand() * (pbestx[][gbest] - presentx[][])
//
//        int testResults = 0;
//        int bestResults = 0;
//        double vValue = 0.0;
//        Particle aParticle = null;
//
//        bestResults = testProblem(gBestindex);
//
//        for (int i = 0; i < MAX_PARTICLES; i++) {
//            testResults = testProblem(i);
//            aParticle = particles.get(i);
//            vValue = aParticle.velocity() + 2 * new Random().nextDouble() * (aParticle.pBest() - testResults) + 2 * new Random().nextDouble() * (bestResults - testResults);
//
//            if (vValue > V_MAX) {
//                aParticle.velocity(V_MAX);
//            } else if (vValue < -V_MAX) {
//                aParticle.velocity(-V_MAX);
//            } else {
//                aParticle.velocity(vValue);
//            }
//        }
//        return;
//    }
//
//    private void updateparticles(int gBestindex) {
//        Particle gBParticle = particles.get(gBestindex);
//
//        for (int i = 0; i < MAX_PARTICLES; i++) {
//            for (int j = 0; j < MAX_INPUTS; j++) {
//                if (particles.get(i).data(j) != gBParticle.data(j)) {
//                    particles.get(i).data(j, particles.get(i).data(j) + (int) Math.round(particles.get(i).velocity()));
//                }
//            } // j
//
//            // Check pBest value.
//            int total = testProblem(i);
//            if (Math.abs(TARGET - total) < particles.get(i).pBest()) {
//                particles.get(i).pBest(total);
//            }
//
//        } // i
//        return;
//    }
//
//    private static int testProblem(int index) {
//        int total = 0;
//        Particle aParticle = null;
//
//        aParticle = particles.get(index);
//
//        for (int i = 0; i < MAX_INPUTS; i++) {
//            total += aParticle.data(i);
//        }
//        return total;
//    }
//
//    private static void printSolution() {
//        // Find solution particle.
//        int i = 0;
//        for (; i < particles.size(); i++) {
//            if (testProblem(i) == TARGET) {
//                break;
//            }
//        }
//        // Print it.
//        System.out.println("Particle " + i + " has achieved target.");
//        for (int j = 0; j < MAX_INPUTS; j++) {
//            if (j < MAX_INPUTS - 1) {
//                System.out.print(particles.get(i).data(j) + " + ");
//            } else {
//                System.out.print(particles.get(i).data(j) + " = " + TARGET);
//            }
//        } // j
//        System.out.print("\n");
//        return;
//    }
//
//    private static int getRandomNumber(int low, int high) {
//        return (int) ((high - low) * new Random().nextDouble() + low);
//    }
//
//    private static int minimum() {
//        // Returns an array index.
//        int winner = 0;
//        boolean foundNewWinner = false;
//        boolean done = false;
//
//        while (!done) {
//            foundNewWinner = false;
//            for (int i = 0; i < MAX_PARTICLES; i++) {
//                if (i != winner) {             // Avoid self-comparison.
//                    // The minimum has to be in relation to the Target.
//                    if (Math.abs(TARGET - testProblem(i)) < Math.abs(TARGET - testProblem(winner))) {
//                        winner = i;
//                        foundNewWinner = true;
//                    }
//                }
//            }
//
//            if (foundNewWinner == false) {
//                done = true;
//            }
//        }
//
//        return winner;
//    }
//
//    private static class Particle {
//
//        private int mData[] = new int[MAX_INPUTS];
//        private int mpBest = 0;
//        private double mVelocity = 0.0;
//
//        public Particle() {
//            this.mpBest = 0;
//            this.mVelocity = 0.0;
//        }
//
//        public int data(int index) {
//            return this.mData[index];
//        }
//
//        public void data(int index, int value) {
//            this.mData[index] = value;
//            return;
//        }
//
//        public int pBest() {
//            return this.mpBest;
//        }
//
//        public void pBest(int value) {
//            this.mpBest = value;
//            return;
//        }
//
//        public double velocity() {
//            return this.mVelocity;
//        }
//
//        public void velocity(double velocityScore) {
//            this.mVelocity = velocityScore;
//            return;
//        }
//    }
//// Particle
//
//    public static void main(String[] args) {
//        
//        // Initialization.listOfBuildings();
//        //System.out.println("*TARGET DISTANCE THAT THE DRONE CAN COVER IS** " + TARGET);
//        JFrame f = new JFrame();
//        AlgorithmFinal p = new AlgorithmFinal();
//        p.initialize();
//        f.add(p);
//        f.setSize(300, 300);
//        f.show();
//        p.execute();
//               
//    }
//
//    @Override
//    public void paint(Graphics g){
//        
//        super.paint(g);
//        //Graphics2D g2 = (Graphics2D) g;
//        //g.fillOval(200, 200, 20, 20);
//        Particle p;
//         
////        for(int i = 0;i<10;i++){
////          //p = particles.get(i);
////         
////          g.fillOval(o1  , o2 , 20, 20);
////          o1 = o1 + 15;
////          o2 = o2 + 15 ;
////        }
//for(int i = 0 ; i< MAX_PARTICLES;i++){
//    p = particles.get(i);
//    if(i==MAX_PARTICLES-1){
//        g.fillOval(p.data(0),p.data(1) ,10,10);
//    }else{
//        g.fillOval(p.data(0),p.data(1) ,10,10);
//    }
//    
//}
//    }
//    
//    public void execute() {
//        Timer t = new Timer();
//        t.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                boolean val = PSOAlgorithm();
//
//                if (val == true) {
//                    repaint();
//                   printSolution();
//
//                    t.cancel();
//                    t.purge();
//                }
//                repaint();
//            }
//        }, 1000, 2000);
//    }
//
//
//}