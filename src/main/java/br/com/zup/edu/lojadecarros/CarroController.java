package br.com.zup.edu.lojadecarros;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @PostMapping("/carro")
    public ResponseEntity<?> cadastra(@RequestBody @Valid CarroRequest carroRequest,
                                      UriComponentsBuilder uriComponentsBuilder){
        if(carroRepository.existsByChassi(carroRequest.getChassi())){
            throw  new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Carro já existente!!!");

        }

        Carro carro = carroRequest.toModel();
        carroRepository.save(carro);

        URI location = uriComponentsBuilder.path("/carro/{id}")
                .buildAndExpand(carro.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> errorHandleException(ConstraintViolationException e, WebRequest request){
        Map<String, Object> body = Map.of(
                "status", "422",
                "timestamp", LocalDateTime.now(),
                "path", request.getDescription(false).replace("uri=",""),
                "message", "Carro já existente!!!"
        );
        return ResponseEntity.unprocessableEntity().body(body);
    }
}
