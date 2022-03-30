package com.womenempowerment.controller;

import com.womenempowerment.dto.IFeedBackDto;
import com.womenempowerment.entity.FeedBack;
import com.womenempowerment.exception.FeedBackAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.womenempowerment.service.IFeedBackService;
import com.womenempowerment.exception.FeedBackNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/feedBack")
public class FeedBackController {
	@Autowired
	IFeedBackService feedBackService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addFeedBack(@RequestBody IFeedBackDto feedBack){
		FeedBack feedback = feedBackService.viewFeedBack(feedBack.getFeedBackId());
		if (feedback != null)
			throw new FeedBackAlreadyExistsException();
		feedBackService.addFeedBack(feedBack);
		return new ResponseEntity<>("Feedback Added..", HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<String> viewAllFeedBack(){
		List<FeedBack> feedback = feedBackService.viewAllFeedBack();
		if (feedback.isEmpty())
			throw new FeedBackNotFoundException();
		return new ResponseEntity<>(feedback.toString(), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<String> viewFeedBack(@PathVariable int id){
		FeedBack feedback = feedBackService.viewFeedBack(id);
		if (feedback == null)
			throw new FeedBackNotFoundException();
		return new ResponseEntity<>(feedback.toString(),HttpStatus.OK);
	}
	
	@GetMapping("/sc/{schemeName}")
	public ResponseEntity<String> viewFeedBackBySchemeName(@PathVariable String schemeName){
		List<FeedBack> feedback = feedBackService.viewFeedBackBySchemeName(schemeName);
		if (feedback.isEmpty())
			throw new FeedBackNotFoundException();
		return new ResponseEntity<>(feedback.toString(),HttpStatus.OK);
	}
	
	@GetMapping("/cs/{trainingCourseName}")
	public ResponseEntity<String> viewFeedBackByTrainingCourseName(@PathVariable String trainingCourseName){
		List<FeedBack> feedback = feedBackService.viewFeedBackByTrainingCourseName(trainingCourseName);
		if (feedback.isEmpty())
			throw new FeedBackNotFoundException();
		return new ResponseEntity<>(feedback.toString(),HttpStatus.OK);
	}
}
