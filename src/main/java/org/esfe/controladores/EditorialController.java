package org.esfe.controladores;

import org.esfe.modelos.Categoria;
import org.esfe.modelos.Editorial;
import org.esfe.servicios.interfaces.IEditorialService;
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
@RequestMapping("/editoriales")
public class EditorialController {
    @Autowired
    private IEditorialService editorialService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; // Si no esta seteado se asigna 0
        int pageSize = size.orElse(5); // Tamaño de la pagina, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Editorial> editoriales = editorialService.buscarTodosPaginados(pageable);
        model.addAttribute("editoriales", editoriales);

        int totalPages = editoriales.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "editorial/index";
    }

    @GetMapping("/create")
    public String create(Editorial editorial) {
        return "editorial/create";
    }

    @PostMapping("/save")
    public String save(Editorial editorial, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(editorial);
            attributes.addFlashAttribute("Error", "No se pudo guardar debido a un error.");
            return "editorial/create";
        }

        editorialService.createOrEditOne(editorial);
        attributes.addFlashAttribute("msg", "Editorial creada correctamente");
        return "redirect:/editoriales";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Editorial editorial = editorialService.buscarPorId(id).get();
        model.addAttribute("editorial", editorial);
        return "editorial/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Editorial editorial = editorialService.buscarPorId(id).get();
        model.addAttribute("editorial", editorial);
        return "editorial/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Editorial editorial = editorialService.buscarPorId(id).get();
        model.addAttribute("editorial", editorial);
        return "editorial/delete";
    }

    @PostMapping("/delete")
    public String delete(Editorial editorial, RedirectAttributes attributes) {
        editorialService.eliminarPorId(editorial.getId());
        attributes.addFlashAttribute("msg", "Editorial eliminada correctamente");
        return "redirect:/editoriales";
    }
}
