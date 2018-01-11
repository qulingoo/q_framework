package com.shuyun.core.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shuyun.core.bize.IFieldReferenceBize;

@Controller
@RequestMapping("/reference")
public class ReferenceController {
	@Autowired
	public IFieldReferenceBize iFieldReferenceBize;
	
}
