package com.womenempowerment.controller;

import com.womenempowerment.dto.ITraineeDto;
import com.womenempowerment.entity.Trainee;
import com.womenempowerment.exception.InvalidUserException;
import com.womenempowerment.exception.UserNotFoundException;
import com.womenempowerment.service.ITraineeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/trainee")
public class TraineeController {
    @Autowired
    ITraineeServiceImpl service;

    @PostMapping("/add")
    public ResponseEntity<String> addTrainee(@RequestBody ITraineeDto traineeDto){
        if (service.viewTrainee(traineeDto.getTraineeId()) != null)
            throw new InvalidUserException();
        Trainee trainee= service.addTrainee(traineeDto);
        if(trainee==null)
        	throw new InvalidUserException();
        return new ResponseEntity<String>("Trainee Added!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> viewAllTrainee(){
        List<Trainee> trainee = service.viewAllTrainee();
        if (trainee.isEmpty())
            throw new UserNotFoundException();
        return new ResponseEntity<>(trainee.toString(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<String> viewTrainee(@PathVariable int id){
        Trainee trainee = service.viewTrainee(id);
        if (trainee == null)
            throw new UserNotFoundException();
        return new ResponseEntity<>(trainee.toString(),HttpStatus.OK);
    }

    @GetMapping("/aadhar/{aadhar}")
    public ResponseEntity<String> viewTraineeByAadhar(@PathVariable long aadhar){
        Trainee trainee = service.viewTraineeByAadhar(aadhar);
        if (trainee==null)
            throw new UserNotFoundException();
        return new ResponseEntity<>(trainee.toString(), HttpStatus.OK);
    }
    @GetMapping("/location/{location}")
    public ResponseEntity<String> viewAllTraineeByLocation(@PathVariable String location){
        List<Trainee> trainee = service.viewAllTraineesByLocation(location);
        if (trainee==null)
            throw new UserNotFoundException();
        return new ResponseEntity<>(trainee.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTrainee(@RequestBody int traineeId){
    	Trainee trainee= service.viewTrainee(traineeId);
        if (trainee==null)
            throw new UserNotFoundException();
        service.deleteTrainee(traineeId);
        return new ResponseEntity<>("Trainee Deleted!", HttpStatus.OK);
    }
}