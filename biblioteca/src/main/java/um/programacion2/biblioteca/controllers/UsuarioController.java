package um.programacion2.biblioteca.controllers;

import org.springframework.http.HttpStatus;
import um.programacion2.biblioteca.modelos.Usuario;
import um.programacion2.biblioteca.services.UsuarioService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.update(id, usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}
