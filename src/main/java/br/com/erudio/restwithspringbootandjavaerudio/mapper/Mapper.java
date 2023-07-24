package br.com.erudio.restwithspringbootandjavaerudio.mapper;

import br.com.erudio.restwithspringbootandjavaerudio.dto.PersonDto;
import br.com.erudio.restwithspringbootandjavaerudio.model.Person;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.List;

public class Mapper{

    private static ModelMapper mapper = new ModelMapper();

    public static <O,D> D parseObject(O origin, Class<D> destination ){
        return mapper.map(origin, destination);
    }

    public static <O,D> List<D> parseListObjects(List<O> origin, Class<D> destination ){
        List<D> destinationObjects = new ArrayList<D>();
        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }

}
