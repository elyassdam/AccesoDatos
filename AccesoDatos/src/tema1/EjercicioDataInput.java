package tema1;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class EjercicioDataInput {

	public static void main(String[] args) throws IOException {
			
		
File fichero=new File("Fichdata.dat");
DataInputStream datain=new DataInputStream(new FileInputStream(fichero));
String n=null;
byte id=0;
int edad=0;
boolean mayor;
char inicial;
short numeroLetras;
long numRegistrosTotales;
double nota;
float pruebaFloat;
try {
	

for (int i = 0; i < fichero.length(); i++) {
	 n =datain.readUTF();
	 edad=datain.readInt();
	 mayor=datain.readBoolean();
	 inicial=datain.readChar();
	 numeroLetras=datain.readShort();
	 numRegistrosTotales=datain.readLong();
	 nota=datain.readDouble();
	 pruebaFloat=datain.readFloat();
	
		System.out.println("id"+id+"Inicial :"+"Nombre :"+n + ", Edad :" + edad+""+"Es mayor de edad"+mayor+"NuemroDeletras "+numeroLetras+"NumeroRegistrosTotales"+ numRegistrosTotales+"La nota es :"+nota+"PruebaFloat"+pruebaFloat);
	}
	
}catch (EOFException eo) {
	datain.close();
}
	}
}

