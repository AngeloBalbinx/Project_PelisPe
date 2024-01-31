package com.example.peliculas.controllers;

import com.example.peliculas.entities.Actor;
import com.example.peliculas.entities.Pelicula;
import com.example.peliculas.service.ActorService;
import com.example.peliculas.service.ArchivoService;
import com.example.peliculas.service.GeneroService;
import com.example.peliculas.service.PeliculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("peliculas")
@Controller
public class PeliculaController {
    @Autowired
    PeliculaService peliculaService;
    @Autowired
    GeneroService generoService;
    @Autowired
    ActorService actorService;
    @Autowired
    ArchivoService archivoService;
    @GetMapping
    public String crear(Model model){
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula",pelicula);
        model.addAttribute("generos",generoService.findAll());
        model.addAttribute("actores",actorService.findAll());
        model.addAttribute("titulo","Nueva Película");
        return "pelicula";
    }
    @GetMapping("{id}")
    public String editar(@PathVariable Long id, Model model){
        Pelicula pelicula = peliculaService.findById(id);
        String ids = "";
        for (Actor actor:pelicula.getProtagonistas()){
            if("".equals(ids)){
                ids= actor.getId().toString();
            }else{
                ids= ids + "," + actor.getId().toString();
            }
        }
        model.addAttribute("pelicula",pelicula);
        model.addAttribute("ids",ids);
        model.addAttribute("generos",generoService.findAll());
        model.addAttribute("actores",actorService.findAll());
        model.addAttribute("titulo","Editar Película");
        return "pelicula";
    }
    @PostMapping
    public String guardar(@Valid Pelicula pelicula, BindingResult br, @ModelAttribute(name="ids") String ids, Model model, @RequestParam("archivo")MultipartFile imagen) {
        if(br.hasErrors()){
            model.addAttribute("titulo","Nueva Película");
            model.addAttribute("generos",generoService.findAll());
            model.addAttribute("actores",actorService.findAll());
            return"pelicula";
        }
        if(!imagen.isEmpty()){
            String archivo = pelicula.getNombre()+ getExtension(imagen.getOriginalFilename());
            pelicula.setImagen(archivo);
            try {
                archivoService.guardar(archivo,imagen.getInputStream());
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            pelicula.setImagen("default.jpg");
        }
        if(ids !=null && !"".equals(ids)){
            List<Long> idsProtagonistas = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
            List<Actor> protagonistas = actorService.findAllById(idsProtagonistas);
            pelicula.setProtagonistas(protagonistas);
        }
        peliculaService.save(pelicula);
        return "redirect:peliculas/home";
    }
    private String getExtension(String archivo){
        return archivo.substring(archivo.lastIndexOf("."));
    }
    @GetMapping({"/","/home","/index"})
        public String home(Model model, @RequestParam(name="pagina", required = false, defaultValue = "0")Integer pagina){
        PageRequest pr = PageRequest.of(pagina, 12);
        Page<Pelicula> page = peliculaService.findAll(pr);
        model.addAttribute("peliculas",page.getContent());
    if(page.getTotalPages()>0){
        List<Integer> paginas = IntStream.rangeClosed(1,page.getTotalPages()).boxed().toList();
        model.addAttribute("paginas",paginas);
    }
        model.addAttribute("actual",pagina +1);
        model.addAttribute("titulo","Catálogo de Películas");
        return "home";
    }
    @GetMapping({"listado"})
    public String listado(Model model, @RequestParam(required = false) String msj, @RequestParam(required = false) String tipoMsj){
        model.addAttribute("peliculas",peliculaService.findAll());
        model.addAttribute("titulo","Listado de Películas");
        if(!"".equals(tipoMsj) && !"".equals(msj)){
            model.addAttribute("msj",msj);
            model.addAttribute("tipoMsj",tipoMsj);
        }
        return "listado";
    }
    @GetMapping("{id}/delete")
    public String eliminar(@PathVariable Long id, Model model, RedirectAttributes redirectAtt){
        peliculaService.delete(id);
        redirectAtt.addAttribute("msj","La película fue eliminada exitosamente");
        redirectAtt.addAttribute("tipoMsj","success");
        return "redirect:/peliculas/listado";
    }

}
