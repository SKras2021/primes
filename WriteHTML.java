import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteHTML{
	private ArrayList<Integer> x;
	private ArrayList<Integer> y;
	private int h;
	private int q;

	public WriteHTML(ArrayList<Integer> x, ArrayList<Integer> y, int h, int q){
		this.x = x;
		this.y = y;
		this.h = h;
		this.q = q;
	}

	public void fillPrimes(){
		try{
			FileWriter fw = new FileWriter("/Users/Dima/Desktop/primeVisual/test.html");
			fw.write("<svg width = \""+ h + "\" height=\""+ h +"\" viewBox=\"0 0 "+ q +" "+ q +"\" version=\"1.2\" xmlns=\"http://www.w3.org/2000/svg\">\n");
			for(int i = 0; i < x.size()-1; i++){
				fw.write("<line x1=\""+ x.get(i) +"\" y1=\""+ y.get(i) +"\" x2=\""+ x.get(i+1) +"\" y2=\""+ y.get(i+1) +"\" style=\"stroke:rgb(20,20,20);stroke-width:2\"/>\n");
			}
			fw.write("</svg>\n");
			fw.close();
		} catch (IOException e){
			//Error
		}
	}
}