package com.example.demo.features.archivo.controller;

import com.example.demo.core.entidades.Archivo;
import com.example.demo.features.archivo.dto.RespuestaDTO;
import com.example.demo.features.archivo.service.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/apiArchivos/archivo")

public class ArchivoController {
    @Autowired
    private ArchivoService service;

    @PostMapping("/subirArchivo")
    public ResponseEntity<RespuestaDTO> subirArchivo(@RequestParam MultipartFile archivo)
            throws IOException{
        service.guardarArchivoEnBaseDeDatos(archivo);
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setMensaje("Archivo guardado stisfactoriamente");
        return ResponseEntity.ok().body(respuesta);
    }
    @GetMapping("/descargarArchivo/{id}")
    public ResponseEntity<byte[]> descargarArchivo(@PathVariable Long id)
        throws FileNotFoundException{
        Archivo file = service.descargarArchivo(id).get();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE,
                        file.getTipoArchivo())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getNombreArchivo() + "\"")
                .body(file.getDatosArchivo());


    }


}
