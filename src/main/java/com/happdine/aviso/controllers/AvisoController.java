package com.happdine.aviso.controllers;

import com.happdine.aviso.models.AvisoModel;
import com.happdine.aviso.repositories.AvisoRepository;
import com.happdine.aviso.services.AvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("happdine/api/avisos")
public class AvisoController {
    @Autowired
    private AvisoService avisoService;

    @PostMapping("/upload")
    public HttpStatus uploadAviso(@RequestParam("recado") String recado,
                                  @RequestParam("timestamp") String timestampp,
                                  @RequestParam("categoria") String categoria,
                                  @RequestParam("file")MultipartFile file)
    {
        try{
            avisoService.salvarAviso(recado, timestampp, categoria, file );
            return HttpStatus.OK;
        } catch(Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

    }

    @GetMapping("/{id}/details")
    public ResponseEntity<Map<String, Object>> getAvisoDetails(@PathVariable Long id) {
        Optional<AvisoModel> avisoOptional = avisoService.buscarAviso(id);
        if (avisoOptional.isPresent()) {
            AvisoModel aviso = avisoOptional.get();
            Map<String, Object> response = new HashMap<>();
            response.put("recado", aviso.getRecado());
            response.put("timestamp", aviso.getTimestampp());
            response.put("imagemUrl", "/happdine/api/avisos/" + id + "/image.jpg");

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/{id}/image.jpg")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable Long id) {
        Optional<AvisoModel> avisoOptional = avisoService.buscarAviso(id);
        if (avisoOptional.isPresent()) {
            AvisoModel aviso = avisoOptional.get();
            if (aviso.getImagem() != null) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(aviso.getImagem());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                headers.setContentDispositionFormData("attachment", "imagem.jpg");

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(new InputStreamResource(inputStream));
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    }




