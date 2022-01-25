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
			fw.write("<polyline points=\"");
			for(int i = 0; i < x.size(); i++){
				fw.write(x.get(i) +","+ y.get(i) + " ");
			}
			fw.write("\" style=\"fill:none;stroke:black;stroke-width:3\" />");
			fw.write("</svg>\n");
			fw.close();
		} catch (IOException e){
			//Error
		}
	}
}