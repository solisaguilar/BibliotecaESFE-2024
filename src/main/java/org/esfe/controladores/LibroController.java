package org.esfe.controladores;

import org.esfe.modelos.Editorial;
import org.esfe.modelos.Libro;
import org.esfe.servicios.interfaces.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private ILibroService libroService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; // Si no está seteado se asigna 0
        int pageSize = size.orElse(5); // Tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Libro> libros = libroService.buscarTodosPaginados(pageable);
        model.addAttribute("libros", libros);

        int totalPages = libros.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "libro/index";
    }

    @GetMapping("/create")
    public String create(Libro libro) {
        return "libro/create";
    }

    @PostMapping("/save")
    public String save(Libro libro, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(libro);
            attributes.addFlashAttribute("Error", "No se pudo guardar debido a un error.");
            return "libro/create";
        }

        libroService.createOrEditOne(libro);
        attributes.addFlashAttribute("msg", "Libro creado correctamente");
        return "redirect:/libros";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Libro libro = libroService.buscarPorId(id).get();
        model.addAttribute("libro", libro);
        return "libro/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Libro libro = libroService.buscarPorId(id).get();
        model.addAttribute("libro", libro);
        return "libro/edit";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Libro libro = libroService.buscarPorId(id).get();
        model.addAttribute("libro", libro);
        return "libro/delete";
    }

    @PostMapping("/delete")
    public String delete(Libro libro, RedirectAttributes attributes) {
        libroService.eliminarPorId(libro.getId());
        attributes.addFlashAttribute("msg", "Libros eliminada correctamente");
        return "redirect:/libros";
    }
}
