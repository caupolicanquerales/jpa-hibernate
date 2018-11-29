package com.pruebaSpring.jpa.pruebajpa.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pruebaSpring.jpa.pruebajpa.model.dao.IntClientDao;
import com.pruebaSpring.jpa.pruebajpa.model.entities.Tabla;
import com.pruebaSpring.jpa.pruebajpa.model.service.IntService;
import com.pruebaSpring.jpa.pruebajpa.util.paginador.PageRender;

@Controller
@SessionAttributes("tabla") // esta anotacion graba los cambios efectuados en la ENTITY ejecutada la URL en el controlador, alternativa al HIDDEN en HTML
public class ClienteController {
	
	@Autowired// esto inyecta el objeto a la clase
	//@Qualifier("clienteDaoJPA")// esto asigna el nombre del BEAN o REPOSITORY a emplear
	private IntService clientService;
	
	@Value("${application.controllers.mensaje}")
	private String mensaje;
	
	/* NOTA: para implementar la PAGINACION de debe usar el objeto 
	 * 	---- Pageable
	 *  ---- Pageable pageRequest= new PageRequest(variable,size)
	 *  
	 * NOTA:  @RequestParam(name="page",defaultValue="0") forma de indicar el nombre de la variable y su valor por defecto
	 * */
	
	@RequestMapping(value="/casa",method=RequestMethod.GET)
	public String index(@RequestParam(name="page",defaultValue="0") int page, Model model) {
		
		Pageable pageRequest= new PageRequest(page,5);//Esto crea el Pageable para indicar el numero de registros por pagina a mostrar
		
		Page<Tabla> usuarios = clientService.findAll(pageRequest); //Ordena la ejecucion del metodo para la PAGINACION
		
		PageRender<Tabla> pageRender = new PageRender<>("/casa",usuarios);//Creo el objeto que establece como mostrar la paginacion
		
		model.addAttribute("titulo", mensaje);
		model.addAttribute("tabla",usuarios);//Instruccion para pasar el resultado de la PAGINACION.
		//model.addAttribute("tabla",clientService.findAll()); //Instruccion para mostrar resultado cuando se pide todo el resultado de la busqueda (Todos los Resultados)

		model.addAttribute("page",pageRender);
		
		return "index";
	}
	
	@RequestMapping(value="/form")
	public String crear(Map<String,Object> model) {
		
		Tabla tabla= new Tabla();
		model.put("tabla", tabla);
		model.put("titulo","Formulario de Nombres");
		return "form";
	}
	
	/* Para efectuar consulta pasando un ID desde el metodo GET 
	 * @PathVariable(value="id") ---- es la forma de indicar que se esta pasando una variable por la URL
	 * */
	@RequestMapping(value="/form/{id}",method=RequestMethod.GET)
	public String metodoParaUnaConsultaId(@PathVariable(value="id") int id, Map<String,Object> model,RedirectAttributes flash) {
		
		Optional<Tabla> tabla=null; //Ojo..... Esta es la NUEVA NOMENCLATURA para ESPECIFICAR las busquedas de FindById() en el CRUD
		if(id>0) {
			tabla= clientService.findOne(id); //
			if(tabla==null) {
				flash.addFlashAttribute("error", "El ID del usuario no existe");
				return "redirect:/casa";
			}
		}else {
			flash.addFlashAttribute("error", "El ID del usuario no puede ser Cero");
			return "redirect:/casa";
		}
		
		model.put("tabla", tabla); //esto actualiza el modelo de la vista con los nuevos valores de la ENTITY
		model.put("titulo", " Editar Formulario de Nombres");//actualiza los campos en HTML con la variable "titulo"
		
		return "form";
	}
	
	/* En esta estructura el metodo de llamada es POST, se pasa el OBJECT Tabla que contiene la informacion de la ENTITY 
	 * NOTA: Cuando en la ENTITY se agrega las anotaciones de VALIDACION se debe agregar @Valid y el objeto "BindingResult"
	 * NOTA: SessionStatus status ---- notifica que el cambio fue hecho y debe ser cerrado la ejecucion del STATUS
	 * 		status.setComplete();
	 * 		RedirectAttributes flash ---- este es el objeto que envia mensaje al TEMPLATE. Es un TOASTER que se genera.
	 * NOTA: La estructura para cargar un archivo al servidor
	 * 			@RequestParam("file") MultipartFile foto ---- "file" es el nombre del TYPE en el TEMPLATE
	 * 								------  MultipartFile ... nombre de la variable a utilizar 
	 * */
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Tabla tabla,BindingResult result, RedirectAttributes flash,@RequestParam("file") MultipartFile foto, SessionStatus status) {
		
		if(result.hasErrors()) {
			return "/form";
		}
		
		if(!foto.isEmpty()) {
			
			Path directorioRecurso= Paths.get("src//main//resources//static//upload"); //establece la ruta donde se almacenara el ARCHIVO
			String rootPath= directorioRecurso.toFile().getAbsolutePath();//Regresa la ruta absoluta de la carpeta en el SERVIDOR
			try {
				
				byte[] bytes= foto.getBytes(); // regresa la dimension de los bytes de la foto.
				Path rutaCompleta= Paths.get(rootPath+"//"+foto.getOriginalFilename());// formula la direccion completa con el nombre del archivo.
				Files.write(rutaCompleta,bytes);//envia el archivo(imagen) a la carpeta en el servidor.
				flash.addAttribute("info", "Has subido correctamente "+foto.getOriginalFilename()+" ");//muestra un TOASTER en el FRONT-END
				
				tabla.setFoto(foto.getOriginalFilename());//guarda el nombre de la foto en la base de datos
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		String mensajeFlash= (tabla.getId() != 0)? "Cliente Editado con Exito":"Cliente Creado con Exito";
		
		clientService.save(tabla);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash); //la variable "success" que esta declarado en el Template
		return "redirect:casa";
	}
	
	/* Metodo para mapping la ruta que mostrar el registro por la consulta*/
	
	@RequestMapping(value="/ver/{id}",method=RequestMethod.GET)
	public String ver(@PathVariable(value="id") int id, Map<String,Object> model,RedirectAttributes flash) {
		
		Optional<Tabla> tabla=null; //Ojo..... Esta es la NUEVA NOMENCLATURA para ESPECIFICAR las busquedas de FindById() en el CRUD
		if(id>0) {
			tabla= clientService.findOne(id); //
			if(tabla==null) {
				flash.addFlashAttribute("error", "El ID del usuario no existe");
				return "redirect:/casa";
			}
		}else {
			flash.addFlashAttribute("error", "El ID del usuario no puede ser Cero");
			return "redirect:/casa";
		}
		
		model.put("tabla", tabla); //esto actualiza el modelo de la vista con los nuevos valores de la ENTITY
		model.put("titulo", " Editar Formulario de Nombres de animales");//actualiza los campos en HTML con la variable "titulo"
		
		return "/ver";
	}
	
	/*Metodo para ejecutar la eliminacion del registro en la base de Datos*/
	@RequestMapping(value="/eliminar/{id}", method=RequestMethod.GET)
	public String eliminar(@PathVariable("id") int id,RedirectAttributes flash) {
		
		System.err.println("Valor de la variable id "+id);
		
		clientService.delete(id);
		flash.addFlashAttribute("success", "Cliente Eliminado con Exito");
		return "redirect:/casa";
	}
	
}
