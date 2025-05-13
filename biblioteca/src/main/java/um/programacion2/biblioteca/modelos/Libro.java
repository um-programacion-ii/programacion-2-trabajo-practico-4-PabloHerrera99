package um.programacion2.biblioteca.modelos;

import um.programacion2.biblioteca.enums.EstadoLibro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private EstadoLibro estado;
}



