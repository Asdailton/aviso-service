package com.happdine.aviso.services;

import com.happdine.aviso.models.AvisoModel;
import com.happdine.aviso.models.Categoria;
import com.happdine.aviso.repositories.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository avisoRepository;

    public AvisoModel salvarAviso(String recado, String timestampp, String categoria, MultipartFile file) throws Exception {
        AvisoModel aviso = new AvisoModel();
        aviso.setRecado(recado);
        aviso.setTimestampp(timestampp);
        aviso.setCategoria(Categoria.valueOf(categoria));
        if (file != null  && !file.isEmpty()){
            aviso.setImagem(file.getBytes());
        }
        return avisoRepository.save(aviso);
    }
    public Optional<AvisoModel> buscarAviso(Long id) {
        return avisoRepository.findById(id);
    }
}
