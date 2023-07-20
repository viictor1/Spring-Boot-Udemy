package br.com.erudio.restwithspringbootandjavaerudio.controller;

import br.com.erudio.restwithspringbootandjavaerudio.math.SimpleMath;
import br.com.erudio.restwithspringbootandjavaerudio.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.erudio.restwithspringbootandjavaerudio.validation.InputValidator.*;

@RestController
public class MathController {

    private SimpleMath simpleMath = new SimpleMath();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}")
    @GetMapping
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        validateInputs(numberOne, numberTwo);
        return simpleMath.sum(convertToDouble(numberOne),convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}")
    @GetMapping
    public Double sub(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        validateInputs(numberOne, numberTwo);
        return simpleMath.sub(convertToDouble(numberOne),convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/mult/{numberOne}/{numberTwo}")
    @GetMapping
    public Double mult(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        validateInputs(numberOne, numberTwo);
        return simpleMath.mult(convertToDouble(numberOne),convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}")
    @GetMapping
    public Double div(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        validateInputs(numberOne, numberTwo);
        return simpleMath.div(convertToDouble(numberOne),convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/avg/{numberOne}/{numberTwo}")
    @GetMapping
    public Double avg(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        validateInputs(numberOne, numberTwo);
        return simpleMath.avg(convertToDouble(numberOne),convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/sqrt/{number}")
    @GetMapping
    public Double sqrt(
            @PathVariable(value = "number") String number) throws Exception {

        if(!isNumeric(number)){
            throw new UnsupportedMathOperationException("Invalid input");
        }
        return simpleMath.sqrt(convertToDouble(number));
    }

}
