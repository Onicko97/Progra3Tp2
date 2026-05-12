package models.domain;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ServicioJson {

	public List<Localidad> cargarLocalidad() {
		Gson gson = new Gson();
		List<Localidad> localidades = new ArrayList<>();
		
		try(FileReader file = new FileReader("src/main/localidades.json")) {
			Type listType =  new TypeToken<ArrayList<Localidad>>(){}.getType();
			localidades = gson.fromJson(file, listType);
			
		} catch (Exception e) {
			System.out.println("Archivo no existente");
		}
		return localidades;
	}
	
		public void guardarLocalidades(List<Localidad> lista) {
		    Gson gson = new GsonBuilder().setPrettyPrinting().create();
		    try (FileWriter writer = new FileWriter("src/main/localidades.json")) {
		        gson.toJson(lista, writer);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	

	
	public void generarJson (String filePath) {

	 Gson gson = new GsonBuilder().setPrettyPrinting().create();
	 String json = gson.toJson(this);
	 
	 try
	 {
	 FileWriter writer = new FileWriter(filePath);
	 writer.write(json);
	 writer.close();
	 }
	 catch(Exception e) { 
		 System.out.println(e);
	 }
	}
}
