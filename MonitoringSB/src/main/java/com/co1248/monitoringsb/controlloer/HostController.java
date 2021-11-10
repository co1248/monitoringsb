package com.co1248.monitoringsb.controlloer;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co1248.monitoringsb.dto.Host;
import com.co1248.monitoringsb.host.HostService;

@RestController
public class HostController {
	private final HostService hostService = null;
	@GetMapping("/")
	public String test () {
        return "������ ���� �۵� �˴ϴ�.";
    }

	//��ȸ
	@GetMapping("/host/{id}")
	public Host getHost (@PathVariable(value = "id") String id) {
		System.out.println("=====>getHost ����");
    	Host host = hostService.getHost(id);
        return host;
    }
	
	//����Ʈ��ȸ
	@GetMapping("/host/all")
	public List<Host> getHostList () {
		System.out.println("=====>getHostList ����");
    	List<Host> hostlist = hostService.getHostList();
        return hostlist;
    }

	//���
	@PostMapping("/add")
	public void insertHost (@RequestBody Host vo) {
		System.out.println("=====>insertHost ����");
    	hostService.insertHost(vo);
    }

	//����
	@PutMapping("/host/{id}")
	public void updateHost (@RequestBody Host vo) {
		System.out.println("=====>updateHost ����");
    	hostService.updateHost(vo);
    }
	
	//����
	@DeleteMapping("/host/{id}")
	public void deleteHost (@PathVariable(value = "id") String id) {
		System.out.println("=====>deleteHost ����");
    	hostService.deleteHost(id);
    }
}
