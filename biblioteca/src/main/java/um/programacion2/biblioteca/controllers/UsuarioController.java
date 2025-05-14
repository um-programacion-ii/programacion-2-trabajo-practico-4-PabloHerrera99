package um.programacion2.biblioteca.controllers;

import um.programacion2.biblioteca.exceptions.UsuarioNoEncontradoException;
import um.programacion2.biblioteca.modelos.Usuario;
import um.programacion2.biblioteca.services.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Obtiene la lista completa de usuarios registrados.
     *
     * @return lista de usuarios
     */
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.findAll();
    }


    /**
     * Obtiene un usuario específico por su ID.
     *
     * @param id ID del usuario
     * @return el usuario encontrado
     * @throws UsuarioNoEncontradoException si no se encuentra el usuario
     */
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param usuario datos del usuario a registrar
     * @return el usuario creado con ID asignado
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id ID del usuario a actualizar
     * @param usuario datos nuevos del usuario
     * @return el usuario actualizado
     * @throws UsuarioNoEncontradoException si no se encuentra el usuario
     */
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.update(id, usuario);
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id ID del usuario a eliminar
     * @throws UsuarioNoEncontradoException si no se encuentra el usuario
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}
