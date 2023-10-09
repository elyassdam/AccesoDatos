package tema1;

	import java.io.File;
	import java.io.RandomAccessFile;
	import java.io.IOException;
	import java.util.List;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.Set;

import javax.swing.text.StyledEditorKit.ForegroundAction;
	
	public class FicheroAccesoAleatorio {
	private File f;
	private List<Pair<String, Integer>>campos;
	private long longReg;
	private long numReg =0;
	FicheroAccesoAleatorio(String nomFich, List<Pair<String, Integer>> campos) throws IOException {
	this.campos=campos;
	this.f = new File (nomFich);
	longReg = 0;
	for (Pair<String, Integer> campo: campos) (
	this.longReg+-campo.getValue();
	}
	if(f.exists()) {
		
	this.numReg-f.length()/this.longReg;
	}
	
	public long getNumReg() {
	return numReg;
	}
	public void insertar (Map<String, String> reg) throws I0Exception {
			insertar(reg, this.numReg++);
}
	public void insertar (Map<String,String>reg, long pos)throws IOException{
		try {
			RandomAccessFile faa=new RandomAccessFile(f,"rws") {
				faa.seek(pos*this.longReg);
				for(Pair<String,Integer>campo:this.campos))
			
		}
			

	}
	}
	}
	
	}
