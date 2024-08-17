package org.esfe.controladores;

import org.esfe.modelos.Autor;
import org.esfe.servicios.interfaces.IAutorService;
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
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private IAutorService autorService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; // Si no esta seteado se asigna 0
        int pageSize = size.orElse(5); // Tamaño de la pagina, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Autor> autores = autorService.buscarTodosPaginados(pageable);
        model.addAttribute("autores", autores);

        int totalPages = autores.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "autor/index";
    }

    @GetMapping("/create")
    public String create(Autor autor) {
        return "autor/create";
    }

    @PostMapping("/save")
    public String save(Autor autor, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(autor);
            attributes.addFlashAttribute("Error", "No se pudo guardar debido a un error.");
            return "autor/create";
        }

        autorService.createOrEditOne(autor);
        attributes.addFlashAttribute("msg", "Autor creado correctamente");
        return "redirect:/autores";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Autor autor = autorService.buscarPorId(id).get();
        model.addAttribute("autor", autor);
        return "autor/details";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Autor autor = autorService.buscarPorId(id).get();
        model.addAttribute("autor", autor);
        return "autor/edit";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Autor autor = autorService.buscarPorId(id).get();
        model.addAttribute("autor", autor);
        return "autor/delete";
    }

    @PostMapping("/delete")
    public String delete(Autor autor, RedirectAttributes attributes) {
        autorService.eliminarPorId(autor.getId());
        attributes.addFlashAttribute("msg", "Autor eliminado correctamente");
        return "redirect:/autores";
    }
}

