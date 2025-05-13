package um.programacion2.biblioteca.controllers;

import um.programacion2.biblioteca.modelos.Libro;
import um.programacion2.biblioteca.modelos.Prestamo;
import um.programacion2.biblioteca.modelos.Usuario;
import um.programacion2.biblioteca.services.PrestamoService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PrestamoController.class)
class PrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrestamoService prestamoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void obtenerTodos_retornaListaPrestamos() throws Exception {
        List<Prestamo> prestamos = List.of(new Prestamo(), new Prestamo());
        when(prestamoService.findAll()).thenReturn(prestamos);

        mockMvc.perform(get("/api/prestamos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void obtenerPorId_existente_retornaPrestamo() throws Exception {
        Prestamo prestamo = new Prestamo(1L, new Libro(), new Usuario(), LocalDate.now(), LocalDate.now().plusDays(7));
        when(prestamoService.findById(1L)).thenReturn(prestamo);

        mockMvc.perform(get("/api/prestamos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void registrar_retornaPrestamo_Retorna201() throws Exception {
        Prestamo prestamo = new Prestamo(null, new Libro(), new Usuario(), LocalDate.now(), LocalDate.now().plusDays(7));
        Prestamo guardado = new Prestamo(1L, prestamo.getLibro(), prestamo.getUsuario(), prestamo.getFechaPrestamo(), prestamo.getFechaDevolucion());

        when(prestamoService.save(any(Prestamo.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prestamo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void eliminar_invocaServicio_Retorna204() throws Exception {
        mockMvc.perform(delete("/api/prestamos/1"))
                .andExpect(status().isNoContent());

        verify(prestamoService).delete(1L);
    }

    @Test
    void obtenerPorUsuario_RetornaListaPrestamos() throws Exception {
        List<Prestamo> prestamos = List.of(new Prestamo(), new Prestamo());
        when(prestamoService.findByUsuario(1L)).thenReturn(prestamos);

        mockMvc.perform(get("/api/prestamos/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void obtenerPorLibro_retornaListaPrestamos() throws Exception {
        List<Prestamo> prestamos = List.of(new Prestamo(), new Prestamo());
        when(prestamoService.findByLibro(1L)).thenReturn(prestamos);

        mockMvc.perform(get("/api/prestamos/libro/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
