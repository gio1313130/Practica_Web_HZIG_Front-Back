package com.example.demo.features.archivo.service.impl;

import com.example.demo.core.entidades.Archivo;
import com.example.demo.features.archivo.repository.ArchivoRepository;
import com.example.demo.features.archivo.service.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
@Service
public class ArchivoServiveImpl  implements ArchivoService {
    @Autowired
    private ArchivoRepository repository;

    @Transactional
    public Archivo guardarArchivoEnBaseDeDatos(MultipartFile archivo) throws IOException {
        String nombreArchivo = StringUtils.cleanPath(
                archivo.getOriginalFilename()
        );
        Archivo archivo1 = Archivo.builder()
                .nombreArchivo(nombreArchivo)
                .tipoArchivo(archivo.getContentType())
                .datosArchivo(archivo.getBytes())
                .build();
        return  repository.save(archivo1);
    }

    @Override
    public Optional<Archivo> descargarArchivo(Long id) throws FileNotFoundException {
        Optional<Archivo> archivo = repository.findById(id);
        if (archivo.isPresent()){

            return archivo;
        }
        throw  new FileNotFoundException("El archivo no existe");
    }
}
