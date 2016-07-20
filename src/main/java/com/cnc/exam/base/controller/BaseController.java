package com.cnc.exam.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.cnc.exam.auth.constant.Constants;

public abstract class BaseController {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	public Pageable buildPageRequest(int pageNumber) {
		
		return new PageRequest(pageNumber-1, Constants.PAGE_SIZE, new Sort(Direction.DESC,
				"id"));
	}
}
