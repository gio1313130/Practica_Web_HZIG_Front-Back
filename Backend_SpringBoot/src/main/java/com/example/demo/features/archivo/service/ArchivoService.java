package com.example.demo.features.archivo.service;

import com.example.demo.core.entidades.Archivo;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public interface ArchivoService {
    Archivo guardarArchivoEnBaseDeDatos(MultipartFile archivo)
        throws IOException;
    Optional<Archivo> descargarArchivo(Long id)
        throws FileNotFoundException;

}
