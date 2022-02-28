package com.carlos.os.services;

import com.carlos.os.domain.Pessoa;
import com.carlos.os.domain.Tecnico;
import com.carlos.os.dtos.TecnicoDTO;
import com.carlos.os.repositories.PessoaRepository;
import com.carlos.os.repositories.TecnicoRepository;
import com.carlos.os.services.exceptions.DataIntegratyViolationException;
import com.carlos.os.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findByid(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotfoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO){
        if (findByCPF(objDTO) != null){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }

    public Tecnico update(Integer id,@Valid TecnicoDTO objDTO) {
        Tecnico oldObj = findByid(id);

        if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());

        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findByid(id);
        if (obj.getList().size() > 0){
            throw new DataIntegratyViolationException("Técnico possui Ordens de serviço, não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private Pessoa findByCPF(TecnicoDTO objDTO){
        Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
        if (obj != null){
            return obj;
        }
        return null;
    }

}
