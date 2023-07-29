package br.com.erudio.restwithspringbootandjavaerudio.controller;

import br.com.erudio.restwithspringbootandjavaerudio.dto.PersonDto;
import br.com.erudio.restwithspringbootandjavaerudio.dto.v2.dto.PersonDtoV2;
import br.com.erudio.restwithspringbootandjavaerudio.service.PersonService;
import br.com.erudio.restwithspringbootandjavaerudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoint for managing people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Operation(summary = "Finds a person", tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = PersonDto.class))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    @GetMapping(value = "/{id}",
                produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
    public PersonDto findById(@PathVariable(value = "id") Long id){
        return personService.findById(id);
    }

    @Operation(summary = "Finds all people", tags = {"People"},
                responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PersonDto.class))
                            )
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
                })
    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
    public List<PersonDto> findAll(){
        return personService.findAll();
    }

    @Operation(
            summary = "Adds a person",
            description = "Adds a person by passing in a JSON, XML or YAML representation of the person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = PersonDto.class))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
    public PersonDto create(@RequestBody PersonDto person){
        return personService.create(person);
    }

    @PostMapping(value= "/v2",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
    public PersonDtoV2 createV2(@RequestBody PersonDtoV2 person){
        return personService.createV2(person);
    }

    @Operation(
            summary = "Updates a person",
            description = "Updates a person by passing in a JSON, XML or YAML representation of the person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = PersonDto.class))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
    public PersonDto update(@RequestBody PersonDto person){
        return personService.update(person);
    }

    @Operation(
            summary = "Deletes a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
