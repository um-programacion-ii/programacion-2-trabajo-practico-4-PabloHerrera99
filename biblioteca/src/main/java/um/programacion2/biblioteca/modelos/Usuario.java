package um.programacion2.biblioteca.modelos;

import um.programacion2.biblioteca.enums.EstadoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private EstadoUsuario estado;
}
