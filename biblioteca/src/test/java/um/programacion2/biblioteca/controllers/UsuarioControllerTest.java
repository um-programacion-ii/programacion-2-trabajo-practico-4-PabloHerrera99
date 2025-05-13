package um.programacion2.biblioteca.controllers;

import um.programacion2.biblioteca.enums.EstadoUsuario;
import um.programacion2.biblioteca.exceptions.UsuarioNoEncontradoException;
import um.programacion2.biblioteca.modelos.Usuario;
import um.programacion2.biblioteca.services.UsuarioService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    void obtenerTodos_retornaLista_retorna200() throws Exception {
        List<Usuario> usuarios = List.of(
                new Usuario(1L, "Juan", "juan@mail.com", EstadoUsuario.ACTIVO),
                new Usuario(2L, "Ana", "ana@mail.com", EstadoUsuario.ACTIVO)
        );

        when(usuarioService.findAll()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[1].nombre").value("Ana"));
    }

    @Test
    void obtenerPorId_existente_retornaUsuario_retorna200() throws Exception {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "Juan", "juan@mail.com", EstadoUsuario.ACTIVO);
        when(usuarioService.findById(id)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void obtenerPorId_noExistente_retorna404() throws Exception {
        Long id = 99L;
        when(usuarioService.findById(id)).thenThrow(new UsuarioNoEncontradoException(id));

        mockMvc.perform(get("/api/usuarios/" + id))
                .andExpect(status().isNotFound());
    }


    @Test
    void crearUsuario_valido_retornaUsuario_retorna201() throws Exception {
        Usuario nuevo = new Usuario(null, "Nuevo", "nuevo@mail.com", EstadoUsuario.ACTIVO);
        Usuario guardado = new Usuario(1L, "Nuevo", "nuevo@mail.com", EstadoUsuario.ACTIVO);

        when(usuarioService.save(any())).thenReturn(guardado);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void actualizarUsuario_existente_retornaActualizado_retorna200() throws Exception {
        Long id = 1L;
        Usuario actualizado = new Usuario(id, "Modificado", "modificado@mail.com", EstadoUsuario.ACTIVO);
        when(usuarioService.update(eq(id), any())).thenReturn(actualizado);

        mockMvc.perform(put("/api/usuarios/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Modificado"));
    }

    @Test
    void actualizarUsuario_noExistente_retorna404() throws Exception {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "Nombre", "mail@mail.com", EstadoUsuario.ACTIVO);
        when(usuarioService.update(eq(id), any())).thenThrow(new UsuarioNoEncontradoException(id));

        mockMvc.perform(put("/api/usuarios/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminarUsuario_existente_retorna204() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/usuarios/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarUsuario_noExistente_retorna404() throws Exception {
        Long id = 99L;
        doThrow(new UsuarioNoEncontradoException(id)).when(usuarioService).delete(id);

        mockMvc.perform(delete("/api/usuarios/" + id))
                .andExpect(status().isNotFound());
    }
}