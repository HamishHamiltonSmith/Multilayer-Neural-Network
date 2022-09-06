import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public class Graph extends JFrame{

    Integer width;
    Integer height;
    Integer mod;

    Graphics2D g2;
    JFrame frame;

    ArrayList<Double> xTrain;
    ArrayList<Double> yTrain;

    ArrayList<Double> xResult;
    ArrayList<Double> yResult;     


    Graph(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> input, ArrayList<Double> output){

        this.width = 1700;
        this.height = 900;
        this.mod = 50;

        this.xTrain = input;
        this.yTrain = output;

        this.xResult = x;
        this.yResult = y;


        setSize(this.width,this.height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Graph results");
        setVisible(true);

    }


    public void paint(Graphics g) {
        this.g2 = (Graphics2D) g;
        Ellipse2D.Double point;


        g2.setFont(new Font("Monospaced",Font.PLAIN,70));


        //Banner
        Color startColor = Color.RED;
        Color endColor = Color.GREEN;
    
        int startX = 0, startY = 0, endX = this.width, endY = this.height/6;
        
        GradientPaint gradient = new GradientPaint(startX, startY, startColor, endX, endY, endColor);
        g2.setPaint(gradient);
    
        g2.fillRect(0, 0, this.width, this.height/6);
        g2.setPaint(null);

        g2.setColor(Color.WHITE);
        g2.drawString("Results", 30, 70);

        g2.setFont(new Font("monospaced",Font.PLAIN,40));
        g2.drawString("Green = Ai fit    Red = Actaull values",30,120);
        

    

        //Graph lines

        g2.setColor(Color.BLACK);
    
        for (var i = 0; i<this.width; i+=this.mod){

            if (i == this.width/2){
                g2.setStroke(new BasicStroke(5));
            }

            g2.drawLine(i,this.height/6,i,this.height);
            g2.setStroke(new BasicStroke(1));

            

            if (i < this.height && i > this.height/6){
                if (i == this.height/2+50){
                    g2.setStroke(new BasicStroke(5));
                }

                g2.drawLine(0,i,this.width,i);
                g2.setStroke(new BasicStroke(1));
            }

        }

        //Original data
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(4));
        for (var y = 1; y<xResult.size(); y++){
            Double px2 = this.width/2+xResult.get(y)*mod.doubleValue()-5;
            Double py2 = (this.height/2+50)+yResult.get(y)*-mod-5;

            Double px1 = this.width/2+xResult.get(y-1)*mod.doubleValue()-5;
            Double py1 = (this.height/2+50)+yResult.get(y-1)*-mod-5;

            if (py2>this.height/6){
                g2.draw(new Line2D.Double(px1, py1, px2, py2));
            }
        }

        
        //Data points

        g2.setColor(Color.RED);
        for (var x = 0; x<xResult.size(); x+=1){

            Double px;
            Double py;

            px = this.width/2+xTrain.get(x)*mod.doubleValue()-5;
            py = (this.height/2+50)+yTrain.get(x)*-mod-5;

            point = new Ellipse2D.Double(px,py,10,10);

            if (py > this.height/6){
                g2.fill(point);
            }

        }
    }


}
