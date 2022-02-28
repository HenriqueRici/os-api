package com.carlos.os.services;

import com.carlos.os.domain.Cliente;
import com.carlos.os.domain.OS;
import com.carlos.os.domain.Tecnico;
import com.carlos.os.domain.enuns.Prioridade;
import com.carlos.os.domain.enuns.Status;
import com.carlos.os.repositories.ClienteRepository;
import com.carlos.os.repositories.OSRepository;
import com.carlos.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OSRepository osRepository;

    public void instanciaDB(){
        Tecnico t1 = new Tecnico(null, "Carlos Henrique", "064.655.331-38","(65) 99647-3732");
        Cliente c1 = new Cliente(null, "Carlos Eduardo", "774.659.461-15","(65) 99984-9640");
        OS os1 = new OS(null, Prioridade.ALTA,"Teste create OS", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
