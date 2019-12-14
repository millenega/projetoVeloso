package br.com.lojaoficial.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojaoficial.entity.Cliente;
import br.com.lojaoficial.repository.ClienteRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ClienteController {

	@Autowired
    private ClienteRepository _clienteRepository;

    @ApiOperation(value = "Retorna uma lista de clientes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de clientes"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/cliente", method = RequestMethod.GET, produces="application/json")
    public List<Cliente> Get() {
        return _clienteRepository.findAll();
    }

    
    @ApiOperation(value = "Retorna um cliente baseada em seu id")
    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Cliente> GetById(@PathVariable(value = "id") Long id)
    {
        Optional<Cliente> cliente = _clienteRepository.findById(id);
        if(cliente.isPresent())
            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Adiciona um cliente a lista")
    @RequestMapping(value = "/cliente", method =  RequestMethod.POST, produces="application/json", consumes="application/json")
    public Cliente Post(@Valid @RequestBody Cliente cliente)
    {
        return _clienteRepository.save(cliente);
    }

    @ApiOperation(value = "Edita um cliente da lista baseada no seu id")
    @RequestMapping(value = "/cliente/{id}", method =  RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<Cliente> Put(@PathVariable(value = "id") Long id, @Valid @RequestBody Cliente newCliente)
    {
        Optional<Cliente> oldCliente = _clienteRepository.findById(id);
        if(oldCliente.isPresent()){
            Cliente cliente = oldCliente.get();
            cliente.setNome(newCliente.getNome());
            cliente.setEmail(newCliente.getEmail());
            cliente.setCpf(newCliente.getCpf());
            cliente.setEndereco(newCliente.getEndereco());
            _clienteRepository.save(cliente);
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Remove um cliente da lista baseada no seu id")
    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Long id)
    {
        Optional<Cliente> cliente = _clienteRepository.findById(id);
        if(cliente.isPresent()){
            _clienteRepository.delete(cliente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	
}