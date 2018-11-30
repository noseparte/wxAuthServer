package com.xmbl.ops.controller.statistic;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmbl.ops.controller.AbstractController;
import com.xmbl.ops.dto.ResponseResult;
import com.xmbl.ops.entity.H5BStatic;
import com.xmbl.ops.service.H5BStaticService;

/**
 * 浏览器访问统计
 * 
 */
@RestController
@RequestMapping(value = "/h5BStatic")
@CrossOrigin(origins = "*", maxAge = 3600)
public class H5BStaticController extends AbstractController {

	@Autowired
	private H5BStaticService h5BStaticService;

	@PostMapping(value = "/clickOne")
	public ResponseResult clickOne(@RequestParam(value = "type", required = false) String type) {
		if (StringUtils.isNotEmpty(type)) {
			H5BStatic h5BStatic = new H5BStatic();
			h5BStatic.setType(type);
			h5BStaticService.addH5BStatic(h5BStatic);
		}
		return successJson("success", "");
	}
}
