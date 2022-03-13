package com.interviewproject.userApi._basicClass;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Getter
@Setter
public abstract class BasicRequest implements Serializable {

	private int pageNo = 0;
	private int pageSize = 50;
	private Sort sort = Sort.unsorted();
}
