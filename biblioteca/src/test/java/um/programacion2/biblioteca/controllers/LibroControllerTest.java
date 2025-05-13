package um.programacion2.biblioteca.controllers;

import um.programacion2.biblioteca.enums.EstadoLibro;
import um.programacion2.biblioteca.exceptions.LibroNoEncontradoException;
import um.programacion2.biblioteca.modelos.Libro;
import um.programacion2.biblioteca.services.LibroService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LibroController.class)
public class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LibroService libroService;

    @Test
    void obtenerTodos_retornaLista_retorna200() throws Exception {
        List<Libro> libros = List.of(
                new Libro(1L, "111", "Libro 1", "Autor 1", EstadoLibro.DISPONIBLE),
                new Libro(2L, "222", "Libro 2", "Autor 2", EstadoLibro.PRESTADO)
        );

        when(libroService.findAll()).thenReturn(libros);

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Libro 1"))
                .andExpect(jsonPath("$[1].titulo").value("Libro 2"));
    }

    @Test
    void obtenerPorId_existente_retornaLibro_retorna200() throws Exception {
        Long id = 1L;
        Libro libro = new Libro(id, "111", "Libro 1", "Autor 1", EstadoLibro.DISPONIBLE);
        when(libroService.findById(id)).thenReturn(libro);

        mockMvc.perform(get("/api/libros/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Libro 1"));
    }

    @Test
    void obtenerPorId_noExistente_retorna404() throws Exception {
        Long id = 99L;
        when(libroService.findById(id)).thenThrow(new LibroNoEncontradoException(id));

        mockMvc.perform(get("/api/libros/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void crearLibro_valido_retornaLibro_retorna201() throws Exception {
        Libro nuevo = new Libro(null, "333", "Nuevo Libro", "Nuevo Autor", EstadoLibro.DISPONIBLE);
        Libro guardado = new Libro(1L, "333", "Nuevo Libro", "Nuevo Autor", EstadoLibro.DISPONIBLE);
        when(libroService.save(any())).thenReturn(guardado);

        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void actualizarLibro_existente_retornaActualizado_retorna200() throws Exception {
        Long id = 1L;
        Libro actualizado = new Libro(id, "444", "Libro Modificado", "Autor Modificado", EstadoLibro.DISPONIBLE);
        when(libroService.update(eq(id), any())).thenReturn(actualizado);

        mockMvc.perform(put("/api/libros/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Libro Modificado"));
    }

    @Test
    void actualizarLibro_noExistente_retorna404() throws Exception {
        Long id = 1L;
        Libro libro = new Libro(id, "555", "Libro", "Autor", EstadoLibro.DISPONIBLE);
        when(libroService.update(eq(id), any())).thenThrow(new LibroNoEncontradoException(id));

        mockMvc.perform(put("/api/libros/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libro)))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminarLibro_existente_retorna204() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/libros/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarLibro_noExistente_retorna404() throws Exception {
        Long id = 99L;
        doThrow(new LibroNoEncontradoException(id)).when(libroService).delete(id);

        mockMvc.perform(delete("/api/libros/" + id))
                .andExpect(status().isNotFound());
    }
}