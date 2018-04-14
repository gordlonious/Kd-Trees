import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.TreeMap;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
/**
 *
 * @author gordl
 */
public class PointST<Value> {
   private TreeMap<Point2D, Value> m;
   public PointST() { 
       m = new TreeMap<>();
   }                                // construct an empty symbol table of points 
   public boolean isEmpty() {
     return m.isEmpty();
   } // is the symbol table empty? 
   public int size() {
     return m.size();  
   } // number of points 
   public void put(Point2D p, Value val) {
     m.put(p, val);  
   } // associate the value val with point p
   public Value get(Point2D p) {
     return m.get(p);
   } // value associated with point p 
   public boolean contains(Point2D p) {
    return m.get(p) != null;   
   } // does the symbol table contain point p? 
   public Iterable<Point2D> points() {
       return m.keySet();
   } // all points in the symbol table 
   
   
   public Iterable<Point2D> range(RectHV rect) {
       ArrayList<Point2D> a = new ArrayList();
       for(Point2D p : m.keySet()) {
             if(rect.contains(p)) a.add(p);
       }
       return a;
   } // all points that are inside the rectangle
   
   // returns null if symbol table is empty
   public Point2D nearest(Point2D p) {
       if(this.isEmpty()) return null;
       Double shortestDistance = null;
       Point2D tmp = null;
       for(Point2D np : m.keySet()) {
           Double curDistance = np.distanceTo(p);
           if(shortestDistance == null || curDistance < shortestDistance) {
                shortestDistance = curDistance;
                tmp = np;
           }
       }
       return tmp;
   } // a nearest neighbor to point p; null if the symbol table is empty
   public static void main(String[] args) {
       PointST<String> st = new PointST<>();
       st.put(new Point2D(0.6, 0.2), "root");
       st.put(new Point2D(0.5, 0.4), "level 1");
       st.put(new Point2D(0.2, 0.3), "level 2");
       st.put(new Point2D(0.4, 0.7), "level 2");
       st.put(new Point2D(0.9, 0.6), "level 1");
       st.put(new Point2D(0.4, 0.1), "root");
       st.put(new Point2D(0.1, 0.2), "level 1");
       st.put(new Point2D(0.9, 0.8), "level 2");
       st.put(new Point2D(0.7, 0.1), "level 2");
       st.put(new Point2D(0.7, 0.5), "level 1");
       st.put(new Point2D(0.5, 0.5), "level 1");
       
// test points
       for(Point2D p : st.points()) {
           System.out.printf("%s ", p.toString());
       }

// test contains
       System.out.printf("%ncontains should be true, actual is %b%n", st.contains(new Point2D(0.4, 0.7)));

// test size: should be 10
       System.out.printf("tree size should be 11, actual is %d%n", st.size());
       
// test get
       System.out.println(st.get(new Point2D(0.5, 0.5)));
        
       In in1 = new In("input100K.txt");
       PointST<Integer> brute = new PointST<>();
       for (int i = 0; !in1.isEmpty(); i++) {
           double x = in1.readDouble();
           double y = in1.readDouble();
           Point2D p = new Point2D(x, y);
           brute.put(p, i);
       }
       
       System.out.println(brute.size());
      
       int count = 0;
       ArrayList<Point2D> a3 = new ArrayList<>();
       for(Point2D p3 : brute.points()) {
           a3.add(new Point2D(StdRandom.uniform() , StdRandom.uniform()));
       }
       
       Stopwatch sw = new Stopwatch();
       while (sw.elapsedTime() < 1)
       {
        brute.nearest(a3.get(count));
        count++;
       }
       StdOut.printf("We calcluated nearest %d many times for a %d sized tree.%n", count, brute.size());
       
       In in2 = new In("input1M.txt");
       PointST<Integer> brute2 = new PointST<>();
       for (int i = 0; !in2.isEmpty(); i++) {
           double x = in2.readDouble();
           double y = in2.readDouble();
           Point2D p2 = new Point2D(x, y);
           brute2.put(p2, i);
       }
      
       int c2 = 0;
       Point2D p2 = new Point2D(StdRandom.uniform() , StdRandom.uniform());
       
       ArrayList<Point2D> a4 = new ArrayList<>();
       for(Point2D p3 : brute2.points()) {
           a4.add(new Point2D(StdRandom.uniform() , StdRandom.uniform()));
       }
       
       Stopwatch sw2 = new Stopwatch();
       while (sw2.elapsedTime() < 1)
       {
        brute2.nearest(a4.get(c2));
        c2++;
       }
       StdOut.printf("We calcluated nearest %d many times for a %d sized tree.%n", c2, brute2.size());
  }
}
