package com.co1248.monitoringsb.controlloer;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co1248.monitoringsb.dto.HostVO;
import com.co1248.monitoringsb.host.HostService;

@RestController
public class HostController {
	private final HostService hostService = null;
	@GetMapping("/test")
	public String test () {
        return "����";
    }

	//��ȸ
	
	//����Ʈ��ȸ
	@GetMapping("/host/all")
	public List<HostVO> getHostList () {
    	List<HostVO> host = hostService.getHostList();
        return host;
    }

	//���

	//����
	
	//����


}
