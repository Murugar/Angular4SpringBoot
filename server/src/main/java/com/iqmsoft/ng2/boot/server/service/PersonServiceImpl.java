package com.iqmsoft.ng2.boot.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.ng2.boot.server.dto.PersonDTO;
import com.iqmsoft.ng2.boot.server.mapper.PersonMapper;
import com.iqmsoft.ng2.boot.server.model.Person;
import com.iqmsoft.ng2.boot.server.repository.PersonRepository;



@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    final static Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;

    @Override
    public Page<PersonDTO> findPersons(Pageable pageable) {
        return personRepository.findAll(pageable).map(person -> personMapper.toDTO(person));
    }

    @Override
    public PersonDTO getPerson(Long id) {
        Person person = personRepository.getOne(id);
        if (person == null) {
            return null;
        } else {
            return personMapper.toDTO(person);
        }
    }

    @Override
    public void updatePerson(PersonDTO personDTO) {
        Person person = personRepository.findOne(personDTO.getId());
        personMapper.mapToEntity(personDTO, person);
    }

    @Override
    public void savePerson(PersonDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);
        personRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.delete(id);
    }
}
