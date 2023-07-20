package br.com.erudio.restwithspringbootandjavaerudio.validation;

import br.com.erudio.restwithspringbootandjavaerudio.exception.UnsupportedMathOperationException;

public class InputValidator {

    public static void validateInputs(String numberOne, String numberTwo) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Invalid input");
        }
    }

    public static Double convertToDouble(String strNumber) throws Exception{
        String formattedStrNumber = formatStrNumber(strNumber);
        return Double.parseDouble(formattedStrNumber);
    }

    public static boolean isNumeric(String strNumber) throws Exception {
        if(strNumber == null){
            return false;
        }
        String formattedStrNumber = formatStrNumber(strNumber);
        return formattedStrNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static String formatStrNumber(String strNumber){
        return strNumber.replaceAll(",", ".");
    }


}
